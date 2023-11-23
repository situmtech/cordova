const MapViewController = require('./map-view-controller');
/**
 * @namespace MapView
 */
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
    try {
      const msg = JSON.parse(m.data);
      if (msg && msg.type) {
        MapViewController._handleMapViewMessages(msg);
      }
    } catch (error) {
      console.warn(`Got unparseable message: ${m}`);
    }
  }

  /**
   * MapView was loaded and you can now start using the MapViewController methods.
   * @param {Function} callback A callback that gives back a MapViewController.
   */
  static onLoad(callback) {
    // For now, setting the on-load callback from the integrator is done using this (static) bridge.
    // Probably this could be improved.
    MapViewController._setOnLoadCallback(callback);
  }
}

customElements.define("map-view", MapView);

module.exports = MapView
