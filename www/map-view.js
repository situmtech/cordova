const MapViewController = require('./map-view-controller');

class MapView extends HTMLElement {
  _uuid = undefined

  constructor() {
    super();
    this._uuid = this._generateUUID();
    console.log("MapView#constructor");
  }

  connectedCallback() {
    console.log("MapView#connectedCallback");
    MapViewController._prepare(this);
    this.innerHTML = `
      <iframe
        id="map-view-iframe"
        src="${this._getViewerURL()}"
        width="100%"
        height="100%"
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

  _generateUUID() {
    return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
      const r = Math.random() * 16 | 0;
      const v = c === 'x' ? r : (r & 0x3 | 0x8);
      return v.toString(16);
    });
  }
  
}

customElements.define("map-view", MapView);

module.exports = MapView
