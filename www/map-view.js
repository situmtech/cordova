const MapViewController = require('./map-view-controller');

class MapView extends HTMLElement {
  _viewerDomain;

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

  _getViewerDomain() {
    return this._viewerDomain;
  }

  _getViewerURL() {
    let viewerDomain = this._formatValidDomain(this.getAttribute("viewer-domain"));
    let situmApiKey = this.getAttribute("situm-api-key") ?? "";
    let buildingIdentifier = this.getAttribute("building-identifier") ?? "";
    let situmApiKeyQP = situmApiKey.length > 0 ? `apikey=${situmApiKey}` : "";
    let buildingIdentifierQP = buildingIdentifier.length > 0 ? `&buildingid=${buildingIdentifier}` : "";
    let query = `${situmApiKeyQP}${buildingIdentifierQP}&mode=embed`;
    let remoteIdentifier = this.getAttribute("remote-identifier");
    if (remoteIdentifier) {
      return `${viewerDomain}/id/${remoteIdentifier}?${query}`;
    }
    return `${viewerDomain}/?${query}`;
  }

  _formatValidDomain(domain) {
    let result = domain;
    if (result == null) {
      return 'https://map-viewer.situm.com';
    }
    if (!result.startsWith('https://') && !result.startsWith('http://')) {
      result = `https://${result}`;
    }
    if (result.endsWith('/')) {
      result = result.substring(0, result.length - 1);
    }
    this._viewerDomain = result;
    return result;
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
