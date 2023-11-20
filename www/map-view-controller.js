const Situm = require("situm-cordova-plugin-official.situm");

class MapViewControllerImpl {
  _onLoadCallback = undefined;
  _buildings = undefined;
  _mapView = undefined;
  _isNavigating = false;

  constructor() {
    Situm.internalSetEventDelegate(this._handleSdkNativeEvents.bind(this));
  }

  _prepare(mapView) {
    this._mapView = mapView;
  }

  _setOnLoadCallback(callback) {
    this._onLoadCallback = callback;
  }

  _sendMessageToViewer(type, payload) {
    let message = {
      type: type,
      payload: payload,
    };
    if (this._mapView.firstElementChild){
      this._mapView.firstElementChild.contentWindow.postMessage(
        message,
        this._mapView._getViewerDomain()
      )
    };
  }

  // ==================================================
  // SDK MESSAGES: 
  // ==================================================

  _handleSdkNativeEvents(eventName, payload) {
    switch (eventName) {
      case "onLocationUpdate":
        // iOS is sending String messages here, check some fields 
        // to avoid assuming that we receive an object of type Location.
        if (payload.buildingIdentifier && payload.position) {
          this._handleOnLocationUpdate(payload);
        } else if (payload.statusName) {
          this._handleOnLocationStatus(payload);
        }
        break;
    }
  }

  _handleOnLocationUpdate(payload) {
    this._sendMessageToViewer("location.update", payload);
    if (this._isNavigating) {
      Situm.updateNavigationWithLocation(
        [payload],
        () => {
          // Do nothing.
        },
        () => {
          console.error("Error ata updateNavigationWithLocation")
        }
      );
    }
  }

  _handleOnLocationStatus(payload) {
    this._sendMessageToViewer("location.update_status", {status: payload.statusName});
  }

  // ==================================================
  // MAP-VIEWER MESSAGES: 
  // ==================================================

  _handleMapViewMessages(m) {
    switch (m.type) {
      case "app.map_is_ready":
        if (this._onLoadCallback && typeof this._onLoadCallback === 'function') {
          this._onLoadCallback(this);
          console.debug('Map is ready!');
        }
        break;
      case "cartography.poi_selected":
        console.debug(`poi (${m.payload.identifier}) was selected`);
        break;
      case "directions.requested":
        this._onDirectionsRequested(m.payload);
        break;
      case "navigation.requested":
        this._onNavigationRequested(m.payload);
        break;
      case "navigation.stopped":
        this._onNavigationCancel();
        break;
      default:
        console.debug("Got unmanaged message: ", m);
        break;
    }
  }

  /**
   * Fetch the given building and return it or undefined if not found.
   * @param {any} buildingId 
   * @param {function} callback 
   */
  _ensureBuilding(buildingId, callback) {
    if (this._buildings) {
      let building = this._buildings.find(b => b.buildingIdentifier == buildingId);
      callback(building);
    } else {
      // Fetch buildings and calculate route.
      cordova.plugins.Situm.fetchBuildings(
        (res) => {
          this._buildings = res;
          let building = this._buildings.find(b => b.buildingIdentifier == buildingId);
          callback(building);
        },
        (err) => {
          callback(undefined);
        }
      );
    }
  }

  // DIRECTIONS:

  _onDirectionsRequested(payload) {
    let directionsRequest = payload.directionsRequest;
    let mapViewerData = {
      identifier: payload.identifier,
      originIdentifier: payload.originIdentifier,
      destinationIdentifier: payload.destinationIdentifier,
      type: directionsRequest.accessibilityMode,
    }
    this._ensureBuilding(payload.buildingIdentifier, (building) => {
      if (building){
        Situm.requestDirections(
          [building, directionsRequest.from, directionsRequest.to, directionsRequest],
          (route) => {
            this._sendMessageToViewer("directions.update", { ...route, ...mapViewerData });
          },
          (error) => {
            this._sendMessageToViewer("directions.update", { error: -1, identifier: mapViewerData.identifier });
          }
        );
      } else {
        this._sendMessageToViewer("directions.update", { error: -1, identifier: payload.identifier });
      }
    });
  }

  // NAVIGATION

  _onNavigationRequested(payload) {
    let directionsRequest = payload.directionsRequest;
    let mapViewerData = {
      identifier: payload.identifier,
      originIdentifier: payload.originIdentifier,
      destinationIdentifier: payload.destinationIdentifier,
      type: directionsRequest.accessibilityMode,
    }
    let navigationRequest = payload.navigationRequest;
    this._ensureBuilding(payload.buildingIdentifier, (building) => {
      // Request directions again to update the calculated route on the native side.
      Situm.requestDirections(
        [building, directionsRequest.from, directionsRequest.to, directionsRequest],
        (route) => {
          this._isNavigating = true;
          this._sendMessageToViewer("navigation.start", { ...route, ...mapViewerData });
          Situm.requestNavigationUpdates(
            [navigationRequest],
            (progress) => {
              // Navigation is working, handle different progress types:
              if (progress.type == "progress") {
                this._sendMessageToViewer("navigation.update", progress);
              } else if (progress.type == "destinationReached") {
                this._sendMessageToViewer("navigation.update", {type: "DESTINATION_REACHED"});
                this._isNavigating = false;
              } else if (progress.type == "userOutsideRoute") {
                this._sendMessageToViewer("navigation.update", {type: "OUT_OF_ROUTE"});
              }
            },
            (error) => {
              this._sendMessageToViewer("directions.update", { error: -1, identifier: mapViewerData.identifier });
              this._isNavigating = false;
            });
        },
        (error) => {
          this._sendMessageToViewer("directions.update", { error: -1, identifier: mapViewerData.identifier });
          this._isNavigating = false;
        }
      );
    });
  }

  _onNavigationCancel() {
    this._isNavigating = false;
    Situm.removeNavigationUpdates(
      () => {
        // Do nothing.
      },
      () => {
        console.error("Error removing navigation updates.");
      }
    );
  }

  // ==================================================
  // ACTIONS
  // ==================================================

  /**
   * Select a poi of a building.
   * @param {number} identifier
   * */
  selectPoi(identifier) {
    this._sendMessageToViewer("cartography.select_poi", { identifier: identifier });
  }

}

let MapViewController = new MapViewControllerImpl();
module.exports = MapViewController;
