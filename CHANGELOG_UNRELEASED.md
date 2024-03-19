## Unreleased

### Fixed

- Fixed some bug that prevents our plugin from sending actions to <map-view> in case [viewer-domain](https://developers.situm.com/sdk_documentation/cordova/jsdoc/latest/mapview) parameter wasn't specified.

### Deprecated

- Situm.[startPositioning()](https://developers.situm.com/sdk_documentation/cordova/jsdoc/latest/situm#.startPositioning) is now deprecated, instead use Situm.[requestLocationUpdate()](https://developers.situm.com/sdk_documentation/cordova/jsdoc/latest/situm#.requestLocationUpdates).
