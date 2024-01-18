const Situm = require("@situm/cordova.situm");

/**
 * @namespace MapViewControllerImpl
 */
class MapViewControllerImpl {
  _onLoadCallback = undefined;
  _onPoiSelectedCallback = undefined;
  _onPoiDeselectedCallback = undefined;
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
    if (this._mapView && this._mapView.firstElementChild) {
      this._mapView.firstElementChild.contentWindow.postMessage(
        message,
        this._mapView._getViewerDomain()
      );
    }
  }

  // ==================================================
  // SDK MESSAGES:
  // ==================================================

  _handleSdkNativeEvents(eventName, payload) {
    switch (eventName) {
      case "onLocationUpdate":
        // TODO: iOS is sending messages here not related to Location. Check
        // some fields to avoid assuming that we receive an object of type Location.
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
          console.error("Error at updateNavigationWithLocation");
        }
      );
    }
  }

  _handleOnLocationStatus(payload) {
    this._sendMessageToViewer("location.update_status", {
      status: payload.statusName,
    });
  }

  // ==================================================
  // MAP-VIEWER MESSAGES:
  // ==================================================

  _handleMapViewMessages(m) {
    switch (m.type) {
      case "app.map_is_ready":
        if (
          this._onLoadCallback &&
          typeof this._onLoadCallback === "function"
        ) {
          this._onLoadCallback(this);
          console.debug("Map is ready!");
        }
        break;
      case "cartography.poi_selected":
        console.debug(`poi (${m.payload.identifier}) was selected`);
        const poiSelectedResult = {
          poi: {
            identifier: m.payload.identifier,
            buildingIdentifier: m.payload.buildingIdentifier,
          },
        };
        this._onPoiSelectedCallback(poiSelectedResult);
        break;
      case "cartography.poi_deselected":
        const poiDeselectedResult = {
          poi: {
            identifier: m.payload.identifier,
            buildingIdentifier: m.payload.buildingIdentifier,
          },
        };
        this._onPoiDeselectedCallback(poiDeselectedResult);
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

  // Fetch the given building and return it or undefined if not found.
  _ensureBuilding(buildingId, callback) {
    if (this._buildings) {
      let building = this._buildings.find(
        (b) => b.buildingIdentifier == buildingId
      );
      callback(building);
    } else {
      // Fetch buildings and calculate route.
      cordova.plugins.Situm.fetchBuildings(
        (res) => {
          this._buildings = res;
          let building = this._buildings.find(
            (b) => b.buildingIdentifier == buildingId
          );
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
    };
    this._ensureBuilding(payload.buildingIdentifier, (building) => {
      if (building) {
        Situm.requestDirections(
          [
            building,
            directionsRequest.from,
            directionsRequest.to,
            directionsRequest,
          ],
          (route) => {
            this._sendMessageToViewer("directions.update", {
              ...route,
              ...mapViewerData,
            });
          },
          (error) => {
            this._sendMessageToViewer("directions.update", {
              error: -1,
              identifier: mapViewerData.identifier,
            });
          }
        );
      } else {
        this._sendMessageToViewer("directions.update", {
          error: -1,
          identifier: payload.identifier,
        });
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
    };
    let navigationRequest = payload.navigationRequest;
    this._ensureBuilding(payload.buildingIdentifier, (building) => {
      // Request directions again to update the calculated route on the native side.
      Situm.requestDirections(
        [
          building,
          directionsRequest.from,
          directionsRequest.to,
          directionsRequest,
        ],
        (route) => {
          this._isNavigating = true;
          this._sendMessageToViewer("navigation.start", {
            ...route,
            ...mapViewerData,
          });
          Situm.requestNavigationUpdates(
            [navigationRequest],
            (progress) => {
              // Navigation is working, handle different progress types:
              if (progress.type == "progress") {
                progress.type = "PROGRESS"; // The map-viewer waits for an upper case "type".
                this._sendMessageToViewer("navigation.update", progress);
              } else if (progress.type == "destinationReached") {
                this._sendMessageToViewer("navigation.update", {
                  type: "DESTINATION_REACHED",
                });
                this._isNavigating = false;
              } else if (progress.type == "userOutsideRoute") {
                this._sendMessageToViewer("navigation.update", {
                  type: "OUT_OF_ROUTE",
                });
              }
            },
            (error) => {
              this._sendMessageToViewer("directions.update", {
                error: -1,
                identifier: mapViewerData.identifier,
              });
              this._isNavigating = false;
            }
          );
        },
        (error) => {
          this._sendMessageToViewer("directions.update", {
            error: -1,
            identifier: mapViewerData.identifier,
          });
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
   * Allows you to select a {@link POI} programmatically in your venue. This will cause the {@link MapView} to center the view on that POI and show its information.
   * 
   * @param {number} identifier The unique identifier of the resource.
   * */
  selectPoi(identifier) {
    this._sendMessageToViewer("cartography.select_poi", {
      identifier: identifier,
    });
  }

  /**
   * Allows you to navigate to a {@link POI} programmatically in your venue. This will cause the {@link MapView} to start navigation mode displaying the route between the user's location and the POI specified by parameters.
   *
   * The types of {@link accessibilityMode} you can use are:
   * - 'CHOOSE_SHORTEST' : Calculates the shortest route to the destination {@link POI}.
   * - 'ONLY_ACCESSIBLE' : Calculates the shortest route to the destination {@link POI} but avoiding stairs and prioritizing accessible floor changes such as lifts.
   * - 'ONLY_NOT_ACCESSIBLE_FLOOR_CHANGES' : Calculates the shortest route to the destination {@link POI} but avoiding lifts and prioritizing non-accessible floor changes such as stairs.
   *
   * accessibilityMode defaults to CHOOSE_SHORTEST.
   *
   * @param {number} identifier The identifier of the poi.
   * @param {'CHOOSE_SHORTEST' | 'ONLY_ACCESSIBLE' | 'ONLY_NOT_ACCESSIBLE_FLOOR_CHANGES' | undefined} accessibilityMode Choose the route type to calculate.
   * */
  navigateToPoi(identifier, accessibilityMode) {
    this._sendMessageToViewer("navigation.start", {
      navigationTo: Number.parseInt(identifier),
      type: accessibilityMode,
    });
  }

  /**
   * Allows you to change the initial language that <map-view> uses to display its UI. 
   * Checkout the [Situm docs](https://situm.com/docs/query-params/) to see the list of
   * supported languages.
   * 
   * @param {string} language an ISO 639-1 code.
   */
  setLanguage(language){
    this._sendMessageToViewer("ui.set_language", language);
  }

  // ==================================================
  // EVENTS
  // ==================================================

  /**
   * Informs you, through the function you pass as callback ({@link cb}), that a {@link POI} was selected in your building.
   * @param {Function} cb A callback that returns a {@link PoiSelectedResult} by its parameters.
   * */
  onPoiSelected(cb) {
    this._onPoiSelectedCallback = cb;
  }

  /**
   * Informs you, through the function you pass as callback ({@link cb}), that a {@link POI} was deselected in your building.
   * @param {Function} cb A callback that returns a {@link PoiDeselectedResult} by its parameters.
   * */
  onPoiDeselected(cb) {
    this._onPoiDeselectedCallback = cb;
  }
}

let MapViewController = new MapViewControllerImpl();
module.exports = MapViewController;
