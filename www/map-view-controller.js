const Situm = require("situm-cordova-plugin-official.situm");

class MapViewController {
  constructor() {
    Situm.internalSetEventDelegate(this._handleSdkNativeEvents.bind(this));
  }

  _sendMessageToViewer(message) {
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
      case "startPositioning":
        this._sendMessageToViewer({
          type: "location.update",
          payload,
        });
        break;
    }
  }

  /**
   * Listen to the events that map-viewer externalizes
   * @param {any} m
   */
  handleMapViewMessages(m) {
    switch (m.type) {
      case "app.map_is_ready":
        this._onLoadCallback(this);
        break;
      case "cartography.poi_selected":
        console.log(`poi (${m.payload.identifier}) was selected`);
        break;
      default:
        console.log("Unknown message: ", m);
        break;
    }
  }

  // ACTIONS

  /**
   * Select a poi of a building.
   * @param {number} identifier
   * */
  selectPoi(identifier) {
    this._sendMessageToViewer({
      type: "cartography.select_poi",
      payload: { identifier: identifier },
    });
  }

  onLoad(cb) {
    this._onLoadCallback = cb;
  }
}

module.exports = MapViewController;