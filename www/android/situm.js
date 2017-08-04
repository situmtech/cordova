var exec = require('cordova/exec');

var PLUGIN_NAME = 'Situm';

var Situm = {
  setApiKey: function (email, apiKey, cb) {
    exec(cb, null, PLUGIN_NAME, 'setApiKey', [email, apiKey]);
  },
  setUserPass: function (email, apiKey, cb) {
    exec(cb, null, PLUGIN_NAME, 'setUserPass', [email, password]);
  },
  startPositioning: function (arrBuilding, cb) {
    exec(cb, null, PLUGIN_NAME, 'startPositioning', arrBuilding);
  },
  stopPositioning: function (cb) {
    exec(cb, null, PLUGIN_NAME, 'stopPositioning', []);
  },
  fetchBuildings: function (cb) {
    exec(cb, null, PLUGIN_NAME, 'fetchBuildings', []);
  },
  fetchFloorsFromBuilding: function (building, cb) {
    exec(cb, null, PLUGIN_NAME, 'fetchFloorsFromBuilding', [building]);
  },
  fetchIndoorPOIsFromBuilding: function (building, cb) {
    exec(cb, null, PLUGIN_NAME, 'fetchIndoorPOIsFromBuilding', [building]);
  },
  fetchOutdoorPOIsFromBuilding: function (building, cb) {
    exec(cb, null, PLUGIN_NAME, 'fetchOutdoorPOIsFromBuilding', [building]);
  },
  fetchEventsFromBuilding: function (building, cb) {
    exec(cb, null, PLUGIN_NAME, 'fetchEventsFromBuilding', [building]);
  },
  fetchPoiCategories: function (cb) {
    exec(cb, null, PLUGIN_NAME, 'fetchPoiCategories');
  },
  fetchMapFromFloor: function (floor, cb) {
    exec(cb, null, PLUGIN_NAME, 'fetchMapFromFloor', [floor]);
  },
  fetchPoiCategoryIconNormal: function (category, cb) {
    exec(cb, null, PLUGIN_NAME, 'fetchPoiCategoryIconNormal', [category]);
  },
  fetchPoiCategoryIconSelected: function (category, cb) {
    exec(cb, null, PLUGIN_NAME, 'fetchPoiCategoryIconSelected', [category]);
  }
};
module.exports = Situm;