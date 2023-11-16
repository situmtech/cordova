const MapViewController = require('./map-view-controller');

class MapView extends HTMLElement {

  constructor() {
    super();
  }

  connectedCallback() {
    MapViewController._prepare(this);
    this.innerHTML = `
      <iframe
        id="map-view-iframe"
        src="${this._getViewerURL()}"
        width="100%"
        height="100%"
        style="border: none; box-shadow: none;"
      />
    `;
    window.addEventListener("message", this._messageReceivedCallback);
  }

  _getViewerURL() {
    let base = this.getAttribute("viewer-domain");
    let query = `apikey=${this.getAttribute("situm-api-key")}&buildingid=${this.getAttribute("building-identifier")}&mode=embed`;
    let remoteIdentifier = this.getAttribute("remote-identifier");
    if (remoteIdentifier) {
      return `${base}/id/${remoteIdentifier}?${query}`;
    }
    return `${base}?${query}`;
  }

  _messageReceivedCallback(m) {
    const msg = JSON.parse(m.data);
    if (msg && msg.type) {
      MapViewController._handleMapViewMessages(msg);
    }
  }

  static onLoad(callback) {
    // Por ahora o seteo da callback de on-load dende o integrador faise usando
    // este puente (estático). Quizais sexa millorable.
    MapViewController._setOnLoadCallback(callback);
  }
  
}

customElements.define("map-view", MapView);

module.exports = MapView
