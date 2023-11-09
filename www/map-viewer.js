// Inside your Cordova plugin code
var InAppBrowser = cordova.InAppBrowser;

var MapView = {
  openMap: function() {
    var url = 'https://map-viewer.situm.com/';
    var options = 'location=no,toolbar=no';

    var webViewRef = InAppBrowser.open(url, '_blank', options);

    webViewRef.addEventListener('loadstop', function () {
      console.log('loadstop');
    });
  }
};

module.exports = MapView;
  