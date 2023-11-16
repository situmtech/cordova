var exec = require('cordova/exec');

var PLUGIN_NAME = 'SitumPlugin';
let _internalEventDelegate = undefined;

var Situm = {
  internalSetEventDelegate: function (callback) {
    _internalEventDelegate = callback;
  },
  setApiKey: function (email, apiKey, cb, error) {
    exec(cb, error, PLUGIN_NAME, 'setApiKey', [email, apiKey]);
  },
  setUseRemoteConfig: function (useRemoteConfig, cb, error) {
    exec(cb, error, PLUGIN_NAME, 'setUseRemoteConfig', [useRemoteConfig]);
  },
  setUserPass: function (email, password, cb, error) {
    exec(cb, error, PLUGIN_NAME, 'setUserPass', [email, password]);
  },
  setCacheMaxAge: function (cacheAge, cb, error) {
    exec(cb, error, PLUGIN_NAME, 'setCacheMaxAge', [cacheAge]);
  },
  startPositioning: function (request, cb, error) {
    let internalCallback = (res) => {
      _internalEventDelegate('onLocationUpdate', res);
      cb(res);
    };
    exec(internalCallback, error, PLUGIN_NAME, 'startPositioning', request);
  },
  stopPositioning: function (cb, error) {
    exec(cb, error, PLUGIN_NAME, 'stopPositioning', []);
  },
  onEnterGeofences: function (cb, error) {
    exec(cb, error, PLUGIN_NAME, 'onEnterGeofences', []);
  },
  onExitGeofences: function (cb, error) {
    exec(cb, error, PLUGIN_NAME, 'onExitGeofences', []);
  },
  fetchBuildings: function (cb, error) {
    exec(cb, error, PLUGIN_NAME, 'fetchBuildings', []);
  },
  fetchFloorsFromBuilding: function (building, cb, error) {
    exec(cb, error, PLUGIN_NAME, 'fetchFloorsFromBuilding', [building]);
  },
  fetchIndoorPOIsFromBuilding: function (building, cb, error) {
    exec(cb, error, PLUGIN_NAME, 'fetchIndoorPOIsFromBuilding', [building]);
  },
  fetchOutdoorPOIsFromBuilding: function (building, cb, error) {
    exec(cb, error, PLUGIN_NAME, 'fetchOutdoorPOIsFromBuilding', [building]);
  },
  fetchEventsFromBuilding: function (building, cb, error) {
    exec(cb, error, PLUGIN_NAME, 'fetchEventsFromBuilding', [building]);
  },
  fetchPoiCategories: function (cb, error) {
    exec(cb, error, PLUGIN_NAME, 'fetchPoiCategories');
  },
  fetchMapFromFloor: function (floor, cb, error) {
    exec(cb, error, PLUGIN_NAME, 'fetchMapFromFloor', [floor]);
  },
  fetchPoiCategoryIconNormal: function (category, cb, error) {
    exec(cb, error, PLUGIN_NAME, 'fetchPoiCategoryIconNormal', [category]);
  },
  fetchPoiCategoryIconSelected: function (category, cb, error) {
    exec(cb, error, PLUGIN_NAME, 'fetchPoiCategoryIconSelected', [category]);
  },
  fetchBuildingInfo: function(building, cb, error) {
    exec(cb, error, PLUGIN_NAME, 'fetchBuildingInfo', [building]);
  },
  fetchGeofencesFromBuilding: function (building, cb, error) {
    exec(cb, error, PLUGIN_NAME, 'fetchGeofencesFromBuilding', [building]);
  },
  invalidateCache: function (cb, error) {
    exec(cb, error, PLUGIN_NAME, 'invalidateCache');
  },
  requestDirections: function (args, cb, error) {
    exec(cb, error, PLUGIN_NAME, 'requestDirections', args);
  },
  requestNavigationUpdates: function (args, cb, error) {
    exec(cb, error, PLUGIN_NAME, 'requestNavigationUpdates', args);
  },
  updateNavigationWithLocation: function (args, cb, error) {
    if (!args) {
      args = []
    } else if (!Array.isArray(args)) {
      args = [args]
    }

    exec(cb, error, PLUGIN_NAME, 'updateNavigationWithLocation', args);
  },
  removeNavigationUpdates: function (args, cb, error) {
    exec(cb, error, PLUGIN_NAME, 'removeNavigationUpdates', args);
  },
  requestRealTimeUpdates: function (request, cb, error) {
    exec(cb, error, PLUGIN_NAME, 'requestRealTimeUpdates', [request]);
  },
  removeRealTimeUpdates: function(cb, error) {
    exec(cb, error, PLUGIN_NAME, 'removeRealTimeUpdates', []);
  }
};
module.exports = Situm;
