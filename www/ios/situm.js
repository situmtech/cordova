var exec = require('cordova/exec');

var PLUGIN_NAME = 'SitumPlugin';

var Situm = {
  setApiKey: function (email, apiKey, cb, error) {
    exec(cb, error, PLUGIN_NAME, 'setApiKey', [email, apiKey]);
  },
  setUserPass: function (email, apiKey, cb, error) {
    exec(cb, error, PLUGIN_NAME, 'setUserPass', [email, password]);
  },
  setCacheMaxAge: function (cacheAge, cb, error) {
    exec(cb, error, PLUGIN_NAME, 'setCacheMaxAge', [cacheAge]);
  },
  startPositioning: function (arrBuilding, cb, error) {
    exec(cb, error, PLUGIN_NAME, 'startPositioning', arrBuilding);
  },
  stopPositioning: function (cb, error) {
    exec(cb, error, PLUGIN_NAME, 'stopPositioning', []);
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
    exec(cb, error, PLUGIN_NAME, 'updateNavigationWithLocation', args);
  },
  removeNavigationUpdates: function (args, cb, error) {
    exec(cb, error, PLUGIN_NAME, 'removeNavigationUpdates', args);
  }
};
module.exports = Situm;
