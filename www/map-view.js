const MapViewController = require("situm-cordova-plugin-official.map-view-controller");

class MapView extends HTMLElement {
  constructor() {
    super();
  }

  connectedCallback() {
    let viewerDomain = this.getAttribute("viewer-domain");
    let situmApiKey = this.getAttribute("situm-api-key");
    let buildingIdentifier = this.getAttribute("building-identifier");

    this.innerHTML = `\
        <iframe\
            id="map-view-iframe"\
            src="${viewerDomain}/?apikey=${situmApiKey}&buildingid=${buildingIdentifier}&mode=embed"\
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
