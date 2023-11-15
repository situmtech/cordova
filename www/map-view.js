const MapViewController = require("situm-cordova-plugin-official.map-view-controller");

class MapView extends HTMLElement {
  constructor() {
    super();
  }

  connectedCallback() {
    let baseDomain = this.getAttribute("base-domain");
    let situmApiKey = this.getAttribute("situm-api-key");
    let buildingId = this.getAttribute("building-id");

    this.innerHTML = `\
        <iframe\
            id="map-view-iframe"\
            src="${baseDomain}/?apikey=${situmApiKey}&buildingid=${buildingId}&mode=embed"\
            width="100%"\
            height="600px"\
        />\
        `;
    this.controller = new MapViewController();
    this._messageReceivedCallback = this._messageReceivedCallback.bind(this);

    window.addEventListener("message", this._messageReceivedCallback);
  }

  _messageReceivedCallback(m) {
    const msg = JSON.parse(m.data);
    if (msg && msg.type) {
      this.controller.handleMapViewMessages(msg);
    }
  }
}

customElements.define("map-view", MapView);
