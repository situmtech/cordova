const MapViewController = require('./map-view-controller')
/**
 * <h4>CODE SNIPPET</h4>
 *
 * Here's an example on how to implement this in your html view:
 * <div id="codeSnippet">
 * <pre id="textToCopy">
 * <p style="font-weight: bold;position: absolute;top: 10px;left: 10px;text-decoration: underline">HTML</p>
 * &lt;map-view
 *   situm-api-key="YOUR_SITUM_API_KEY"
 *   building-identifier="YOUR_BUILDING_IDENTIFIER"
 *   remote-identifier="YOUR_REMOTE_IDENTIFIER"
 * /&gt;
 * </pre>
 * 
 * <button id="copySnippetButton">Copy</button>
 * </div>
 * 
 * <script>
    document.getElementById("copyButton").addEventListener("click", function() {
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
 * <h4>ATTRIBUTES</h4>
 * <dl>
 *   <dt>situm-api-key</dt>
 *   <dd>Your API Key to authenticate yourself in our plugin.
 *      You can manage out your Situm API Key in <a href="https://dashboard.situm.com/accounts/profile"> https://dashboard.situm.com/accounts/profile</a>.</dd>
 *
 *   <dt>building-identifier</dt>
 *   <dd>The identifier of the building you want to display on the map.
 *      You can manage this identifier with this guide <a href="https://situm.com/docs/sdk-cartography/#building-details"> https://situm.com/docs/sdk-cartography/#building-details</a>.</dd>
 *
 *   <dt>remote-identifier</dt>
 *   <dd>An identifier that allows you to remotely configure all map settings.</dd>
 *
 *   <dt>language</dt>
 *   <dd>Choose the language that map-view will use to display its UI by specifying an ISO 639-1 code.
 *      You can also change the language by calling {@link MapViewController.setLanguage()}.
 *      Checkout the <a href="https://situm.com/docs/13-internationalization/">Situm docs</a> to see the list of supported languages.</dd>
 *
 *   <dt>viewer-domain</dt>
 *   <dd>A parameter that allows you to specify which domain will be displayed inside our webview.
 *       Default is <a href="https://map-viewer.situm.com">https://map-viewer.situm.com</a></dd>
 * </dl>
 *
 * @namespace MapView
 */
class MapView extends HTMLElement {
  _viewerDomain

  constructor() {
    super()
  }

  connectedCallback() {
    MapViewController._prepare(this)
    this.innerHTML = `
      <iframe
        id="map-view-iframe"
        src="${this._getViewerURL()}"
        width="100%"
        height="100%"
        style="border: none; box-shadow: none;"
      />
    `
    window.addEventListener('message', this._messageReceivedCallback)
  }

  _getViewerDomain() {
    return this._viewerDomain
  }

  _getViewerURL() {
    let viewerDomain = this._formatValidDomain(
      this.getAttribute('viewer-domain')
    )
    let situmApiKey = this.getAttribute('situm-api-key') ?? ''
    let buildingIdentifier = this.getAttribute('building-identifier') ?? ''
    let language = this.getAttribute('language') ?? ''

    let situmApiKeyQP = situmApiKey.length > 0 ? `apikey=${situmApiKey}` : ''
    let buildingIdentifierQP =
      buildingIdentifier.length > 0 ? `&buildingid=${buildingIdentifier}` : ''
    let languageQP = language.length > 0 ? `&lng=${language}` : ''

    let query = `${situmApiKeyQP}${buildingIdentifierQP}${languageQP}&mode=embed`

    let remoteIdentifier = this.getAttribute('remote-identifier')
    if (remoteIdentifier) {
      return `${viewerDomain}/id/${remoteIdentifier}?${query}`
    }
    return `${viewerDomain}/?${query}`
  }

  _formatValidDomain(domain) {
    let result = domain
    if (result == null) {
      return 'https://map-viewer.situm.com'
    }
    if (!result.startsWith('https://') && !result.startsWith('http://')) {
      result = `https://${result}`
    }
    if (result.endsWith('/')) {
      result = result.substring(0, result.length - 1)
    }
    this._viewerDomain = result
    return result
  }

  _messageReceivedCallback(m) {
    try {
      const msg = JSON.parse(m.data)
      if (msg && msg.type) {
        MapViewController._handleMapViewMessages(msg)
      }
    } catch (error) {
      console.warn('Got unparseable message:', m)
    }
  }

  /**
   * MapView was loaded.
   * The {@link MapViewController} will be sent by the callback.
   * @param {Function} cb The callback that gives back the controller of this visual component.
   */
  static onLoad(cb) {
    // For now, setting the on-load callback from the integrator is done using this (static) bridge.
    // Probably this could be improved.
    MapViewController._setOnLoadCallback(cb)
  }
}

customElements.define('map-view', MapView)

module.exports = MapView
