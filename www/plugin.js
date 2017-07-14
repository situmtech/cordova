
var exec = require('cordova/exec');

var PLUGIN_NAME = 'Situm';

var Situm = {
  echo: function(phrase, cb) {
    exec(cb, null, PLUGIN_NAME, 'echo', [phrase]);
  },
  fetchBuildings: function(cb) {
    exec(cb, null, PLUGIN_NAME, 'fetchBuildings', []);
  },
  startPositioning: function(arrBuilding,cb) {
    exec(cb, null, PLUGIN_NAME, 'startPositioning', arrBuilding);
  },
  stopPositioning: function(cb) {
    exec(cb, null, PLUGIN_NAME, 'stopPositioning', []);
  }
};

module.exports = Situm;
