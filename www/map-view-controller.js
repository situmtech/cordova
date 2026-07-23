const Situm = require('@situm/cordova.situm');

/**
 * Here's an example on how to use this controller class:
 * <div id="codeSnippet">
 * <pre id="textToCopy">
 * <p style="font-weight: bold;position: absolute;top: 10px;left: 10px;text-decoration: underline">JAVASCRIPT</p>
 * cordova.plugins.MapView.onLoad((controller: any) => {
 *  // Once the MapView was loaded you can start managing our map by:
 *
 *  // 1. Sending actions like selecting or navigating to a POI in a building:
 *   // controller.selectPoi('YOUR_POI_IDENTIFIER');
 *   // controller.navigateToPoi('YOUR_POI_IDENTIFIER');
 *
 *   // 2. Listen to events that take place inside our map like a POI being selected or deselected:
 *   controller.onPoiSelected((poiSelectedResult: any) => {
 *     console.log("EXAMPLE> onPoiSelected -> ", poiSelectedResult);
 *   });
 *
 *   controller.onPoiDeselected((poiDeselectedResult: any) => {
 *     console.log("EXAMPLE> onPoiDeselected -> ", poiDeselectedResult);
 *   });
 * });
 * </pre>
 * 
 * <button id="copySnippetButton">Copy</button>
 * </div>
 * 
 * <script>
    document.getElementById("copySnippetButton").addEventListener("click", function() {
      var textToCopy = document.getElementById("textToCopy");

      var range = document.createRange();
      range.selectNode(textToCopy);
      window.getSelection().removeAllRanges();
      window.getSelection().addRange(range);

      document.execCommand("copy");
      window.getSelection().removeAllRanges();
      this.textContent = "Copied!";
    });
    </script>
 *
 *
 * @namespace MapViewControllerImpl
 * @name MapViewController
 */
class MapViewControllerImpl {
  _onLoadCallback = undefined;
  _onPoiSelectedCallback = undefined;
  _onPoiDeselectedCallback = undefined;
  _mapView = undefined;
  _isWaitingForAuth = false;

  constructor() {
    Situm.internalSetEventDelegate(this._handleSdkNativeEvents.bind(this));
    Situm.internalSetTokenCallback(this._handleTokenSet.bind(this));
  }

  _prepare(mapView) {
    this._mapView = mapView;

    if (mapView.hasAttribute('use-viewer-navigation')) {
      console.warn(
        'Situm> MapView> [!] The "use-viewer-navigation" attribute is deprecated and ignored. ' +
        'The MapView always uses its own routing and navigation library.'
      );
    }
  }

  _setOnLoadCallback(callback) {
    this._onLoadCallback = callback;
  }

  _sendMessageToViewer(type, payload) {
    let message = {
      type: type,
      payload: payload
    };
    if (this._mapView && this._mapView.firstElementChild) {
      this._mapView.firstElementChild.contentWindow.postMessage(
        message,
        this._mapView._getViewerDomain()
      );
    }
  }

  _getDeviceId(callback) {
    Situm.getDeviceId(
      (deviceId) => callback(deviceId),
      () => {
        console.error('Error retrieving the device id.');
        callback(null);
      }
    );
  }

  // ==================================================
  // SDK MESSAGES:
  // ==================================================

  _handleSdkNativeEvents(eventName, payload) {
    switch (eventName) {
      case 'onLocationUpdate':
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
    this._sendMessageToViewer('location.update', payload);
  }

  _handleOnLocationStatus(payload) {
    this._sendMessageToViewer('location.update_status', {
      status: payload.statusName
    });
  }

  // ==================================================
  // MAP-VIEWER MESSAGES:
  // ==================================================

  _handleMapViewMessages(m) {
    switch (m.type) {
      case 'app.map_is_ready':
        this._handleMapIsReady();
        break;
      case 'app.ready_for_auth':
        this._handleReadyForAuth();
        break;
      case 'cartography.poi_selected':
        console.debug(`poi (${m.payload.identifier}) was selected`);
        const poiSelectedResult = {
          poi: {
            identifier: m.payload.identifier,
            buildingIdentifier: m.payload.buildingIdentifier
          }
        };
        this._onPoiSelectedCallback(poiSelectedResult);
        break;
      case 'cartography.poi_deselected':
        const poiDeselectedResult = {
          poi: {
            identifier: m.payload.identifier,
            buildingIdentifier: m.payload.buildingIdentifier
          }
        };
        this._onPoiDeselectedCallback(poiDeselectedResult);
        break;
      case 'viewer.navigation.started':
        this._onViewerNavigationStarted(m.payload);
        break;
      case 'viewer.navigation.updated':
        this._onViewerNavigationUpdated(m.payload);
        break;
      case 'viewer.navigation.stopped':
        this._onViewerNavigationStopped(m.payload);
        break;
      case 'ui.speak_aloud_text': 
      // case ...: Add here any other message that must be delegated to the native side.
        this._internalHandleMapViewMessage(m.type, m.payload);
        break;
      default:
        console.debug('Got unmanaged message: ', m);
        break;
    }
  }

  _handleMapIsReady() {
    if (this._onLoadCallback &&
        typeof this._onLoadCallback === 'function') {
      this._onLoadCallback(this);
      console.debug('Map is ready!');
    }
    this._sendMessageToViewer('app.set_config_item', [
      {key: "internal.tts.engine", value: "mobile"},
      // Any other config item here...
    ]);
  }

  _handleReadyForAuth() {
    this._isWaitingForAuth = true;
    this._sendTokenToViewer(Situm.internalGetToken());
  }

  _handleTokenSet(token) {
    if (this._isWaitingForAuth) {
      this._sendTokenToViewer(token);
    }
  }

  _sendTokenToViewer(token) {
    if (!token) {
      return;
    }
    this._sendMessageToViewer('app.set_auth', 
      {jwt: token}
    );
  }

  // NAVIGATION

  _onViewerNavigationStarted(webPayload) {
    let externalNavigation = { messageType: "NavigationStarted", payload: webPayload};
    Situm.updateNavigationState(externalNavigation,  () => {}, () => {});
  }

  _onViewerNavigationUpdated(webPayload) {
    let messageType;
    switch (webPayload.type) {
      case 'PROGRESS':
        messageType = 'NavigationUpdated';
        break;
      case 'DESTINATION_REACHED':
        messageType = 'DestinationReached';
        break;
      case 'OUT_OF_ROUTE':
        messageType = 'OutsideRoute';
        break;
      default:
        return;
    }

    const externalNavigation = { messageType, payload: webPayload };
    Situm.updateNavigationState(externalNavigation, () => { }, () => { });
  }

  _onViewerNavigationStopped(webPayload) {
    let externalNavigation = { messageType: "NavigationCancelled", payload: webPayload};
    Situm.updateNavigationState(externalNavigation,  () => {}, () => {});
  }

  _internalHandleMapViewMessage(message, webPayload) {
    Situm.internalHandleMapViewMessage(message, webPayload);
  }

  // ==================================================
  // ACTIONS
  // ==================================================

  /**
   * Allows you to select a {@link POI} programmatically in your venue. This will cause the {@link MapView} to center the view on that POI and show its information.
   *
   * @param {number} identifier The unique identifier of the resource.
   * @memberof MapViewController
   * @name selectPoi
   * @method
   * */
  selectPoi(identifier) {
    this._sendMessageToViewer('cartography.select_poi', {
      identifier: identifier
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
   * @param {number} identifier The identifier of the POI.
   * @param {'CHOOSE_SHORTEST' | 'ONLY_ACCESSIBLE' | 'ONLY_NOT_ACCESSIBLE_FLOOR_CHANGES' | undefined} accessibilityMode Choose the route type to calculate.
   * @memberof MapViewController
   * @name navigateToPoi
   * @method
   * */
  navigateToPoi(identifier, accessibilityMode) {
    this._sendMessageToViewer('navigation.start', {
      navigationTo: Number.parseInt(identifier),
      type: accessibilityMode
    });
  }

  /**
   * Allows you to change the initial language that &lt;map-view> uses to display its UI.
   * Checkout the <a href="https://situm.com/docs/13-internationalization/">Situm docs</a> to see the list of supported languages.
   *
   * @param {string} language an ISO 639-1 code.
   * @memberof MapViewController
   * @name setLanguage
   * @method
   */
  setLanguage(language) {
    this._sendMessageToViewer('ui.set_language', language);
  }

  /**
   * @typedef {Object} MapViewDirectionsOptions - Options to apply to directions requests
   * @property {string[]} includedTags - List of tags that you want to use when calculating a route. Only the tags added here will be used. If there are other tags in the graph they won't be used. The edges without a tag will be used. If you don't set any tag, all the graph will be used to calculate the route. You can learn more about this topic on https://situm.com/docs/cartography-management/#tags
   * @property {string[]} excludedTags - List of tags that you want your route to avoid. If you exclude a tag the route will never pass through an edge that have this tag. If the route can only be generated passing through an edge with this tag the route calculation will fail. You can learn more about this topic on https://situm.com/docs/cartography-management/#tags.
   */

  /**
   * If you want to change the route calculated based on tags you can use this interface.
   * Using this interface you can change all the routes that will be calculated including or excluding tags.
   * Use the method this method after the MapView ends loading
   * You can call this as many times you want and the mapviewer will use the last options that you set.
   *
   * @param {MapViewDirectionsOptions} directionsOptions the desired MapViewDirectionsOptions
   * @memberof MapViewController
   * @name setDirectionsOptions
   * @method
   */
  setDirectionsOptions(directionsOptions) {
    this._sendMessageToViewer('directions.set_options', {
      includedTags: directionsOptions.includedTags,
      excludedTags: directionsOptions.excludedTags
    });
  }

  // ==================================================
  // EVENTS
  // ==================================================

  /**
   * Informs you, through the function you pass as callback ({@link cb}), that a {@link POI} was selected in your building.
   * @param {Function} cb A callback that returns a {@link PoiSelectedResult} by its parameters.
   * @memberof MapViewController
   * @name onPoiSelected
   * @method
   * */
  onPoiSelected(cb) {
    this._onPoiSelectedCallback = cb;
  }

  /**
   * Informs you, through the function you pass as callback ({@link cb}), that a {@link POI} was deselected in your building.
   * @param {Function} cb A callback that returns a {@link PoiDeselectedResult} by its parameters.
   * @memberof MapViewController
   * @name onPoiDeselected
   * @method
   * */
  onPoiDeselected(cb) {
    this._onPoiDeselectedCallback = cb;
  }
}

let MapViewController = new MapViewControllerImpl();

module.exports = MapViewController;
