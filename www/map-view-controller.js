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

  handleMapViewMessages(m) {
    switch (m.type) {
      case "app.map_is_ready":
        this.selectPoi(122321);
        break;
      case "cartography.poi_selected":
        console.log(`poi (${m.payload.identifier}) was selected`);
        break;
      default:
        console.log("Unknown message: ", m);
        break;
    }
  }

  selectPoi(identifier) {
    this._sendMessageToViewer({
      type: "cartography.select_poi",
      payload: { identifier: identifier },
    });
  }
}

module.exports = MapViewController;