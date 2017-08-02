var exec = require('cordova/exec');

var PLUGIN_NAME = 'Situm';

var Situm = {
  setApiKey: function (email, apiKey, cb) {
    exec(cb, null, PLUGIN_NAME, 'setApiKey', [email, apiKey]);
  },
  setUserPass: function (email, apiKey, cb) {
    exec(cb, null, PLUGIN_NAME, 'setUserPass', [email, password]);
  },
  fetchBuildings: function (cb) {
    exec(cb, null, PLUGIN_NAME, 'fetchBuildings', []);
  },
  startPositioning: function (arrBuilding, cb) {
    exec(cb, null, PLUGIN_NAME, 'startPositioning', arrBuilding);
  },
  stopPositioning: function (cb) {
    exec(cb, null, PLUGIN_NAME, 'stopPositioning', []);
  },
  fetchFloorsFromBuilding: function (building, cb) {
    exec(cb, null, PLUGIN_NAME, 'fetchFloorsFromBuildings', [building]);
  },
  fetchPoiCategories: function (cb) {
    exec(cb, null, PLUGIN_NAME, 'fetchPoiCategories');
  }
};
module.exports = Situm;