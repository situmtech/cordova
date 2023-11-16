const Situm = require("situm-cordova-plugin-official.situm");

class MapViewControllerImpl {
  _onLoadCallback = undefined;
  _buildings = undefined;
  _mapView = undefined;

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
    // TODO: hardcoded URL!!!
    document
      .getElementById("map-view-iframe")
      .contentWindow.postMessage(message, "https://map-viewer.situm.com");
  }

  /**
   * Listen to native SDK events and notify them to map-viewer.
   * @param {string} eventName 
   * @param {any} payload 
   */
  _handleSdkNativeEvents(eventName, payload) {
    switch (eventName) {
      case "onLocationUpdate":
        if (!payload.statusName) {
          console.log("location.update", payload);
          this._sendMessageToViewer("location.update", payload);
        } else {
          console.log("location.update_status", { status: payload.statusName });
          this._sendMessageToViewer(
            "location.update_status",
            {status: payload.statusName}
          );
        }
        break;
    }
  }

  /**
   * Listen to the events that map-viewer externalizes
   * @param {any} m
   */
  _handleMapViewMessages(m) {
    switch (m.type) {
      case "app.map_is_ready":
        console.log("Map is ready!")
        if (this._onLoadCallback && typeof this._onLoadCallback === 'function') {
          this._onLoadCallback(this);
          console.log(`MapView is: ${this._mapView._uuid}`);
        }
        break;
      case "cartography.poi_selected":
        console.log(`poi (${m.payload.identifier}) was selected`);
        break;
      case "directions.requested":
        this._onDirectionsRequested(m.payload);
        break;
      default:
        console.log("Got unmanaged message: ", m);
        break;
    }
  }

  // MAP-VIEWER MESSAGES:

  _onDirectionsRequested(payload) {
    let directionsRequest = payload.directionsRequest;
    let mapViewerData = {
      identifier: payload.identifier,
      originIdentifier: payload.originIdentifier,
      destinationIdentifier: payload.destinationIdentifier,
      type: directionsRequest.accessibilityMode,
    }
    if (this._buildings) {
      // Calculate route immediately.
      this._doCalculateRoute(payload.buildingIdentifier, directionsRequest, mapViewerData);
    } else {
      // Fetch buildings and calculate route.
      cordova.plugins.Situm.fetchBuildings(
        (res) => {
          this._buildings = res;
          this._doCalculateRoute(payload.buildingIdentifier, directionsRequest, mapViewerData);
        },
        (err) => {
          this._sendMessageToViewer("directions.update", { error: -1, identifier: payload.identifier });
        }
      );
    }
  }

  _doCalculateRoute(buildingId, directionsRequest, mapViewerData) {
    let building = this._buildings.find(b => b.buildingIdentifier == buildingId);
    Situm.requestDirections(
      [building, directionsRequest.from, directionsRequest.to, directionsRequest],
      (route) => {
        this._sendMessageToViewer("directions.update", { ...route, ...mapViewerData });
      },
      (error) => {
        this._sendMessageToViewer("directions.update", { error: -1, identifier: mapViewerData.identifier });
      }
    );
  }

  // ACTIONS

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
