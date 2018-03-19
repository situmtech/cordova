var exec = require('cordova/exec');

var PLUGIN_NAME = 'Situm';

/** 
 * @namespace Situm
 */

var Situm = {

  /**
   * Provides your API key to the Situm SDK for Android.
   * @description Provides your API key to the Situm SDK for Android. This key is generated for your application in the Dashboard. Old credentials will be removed.
   * @param {string} email - email that identifies the account. Can't be empty.
   * @param {string} apiKey - key obtained at situm.es. Can't be empty.
   * @param {callback} cb - Cordova native callback to recive data. The plugin returns synchronously.
   * @param {callback} error - Cordova native callback to recive errors. The plugin returns synchronously.
   * @returns {boolean} true if operation finished successfully, otherwise false
   */
  setApiKey: function (email, apiKey, cb, error) {
    exec(cb, error, PLUGIN_NAME, 'setApiKey', [email, apiKey]);
  },
  /**
   * Provides user's email and password.
   * @description Provides user's email and password. This credentials will be used to obtain a valid user token to authenticate the server request, when necessary. Token obtaining is not necessary done when this method is executed. Old credentials will be removed.
   * @param {string} email - user's email. Can't be empty.
   * @param {string} password - user's password. Can't be empty.
   * @param {callback} cb - Cordova native callback to recive data. The plugin returns synchronously.
   * @param {callback} error - Cordova native callback to recive errors. The plugin returns synchronously.
   * @returns {boolean} true if operation finished successfully, otherwise false
   */
  setUserPass: function (email, apiKey, cb, error) {
    exec(cb, error, PLUGIN_NAME, 'setUserPass', [email, password]);
  },
  /**
   * Sets the maximum age of a cached response.
   * @description Sets the maximum age of a cached response. If the cache response's age exceeds maxAge, it will not be used and a network request will be made.
   * @param {number} maxAge - a non-negative integer
   * @param {callback} cb - Cordova native callback to recive data. The plugin returns synchronously.
   * @param {callback} error - Cordova native callback to recive errors. The plugin returns synchronously.
   * @returns {void}
   */
  setCacheMaxAge: function (cacheAge, cb, error) {
    exec(cb, error, PLUGIN_NAME, 'setCacheMaxAge', [cacheAge]);
  },
  /**
   * Set callback and starts listen onLocationChanged event.
   * @param {building[]} arrBuilding - Array of buildings to activate locationListener.
   * @param {callback} cb - Cordova native callback to recive data. The plugin returns synchronously.
   * @param {callback} error - Cordova native callback to recive errors. The plugin returns synchronously.
   * @return {position} position - Current position of device.
   */
  startPositioning: function (arrBuilding, cb, error) {
    exec(cb, error, PLUGIN_NAME, 'startPositioning', arrBuilding);
  },
  /**
   * Stop locationListener on current active listener.
   * @param {callback} cb - Cordova native callback to recive data. The plugin returns synchronously.
   * @param {callback} error - Cordova native callback to recive errors. The plugin returns synchronously.
   * @returns {void}
   */
  stopPositioning: function (cb, error) {
    exec(cb, error, PLUGIN_NAME, 'stopPositioning', []);
  },
  /**
   * @function fetchBuildings
   * Download all the buildings for the current user.
   * @description Download all the buildings for the current user
   * @param {callback} cb - Cordova native callback to recive data. The plugin returns synchronously.
   * @param {callback} error - Cordova native callback to recive errors. The plugin returns synchronously.
   * @returns {building[]} {@link Building} - buildings array.
   */
  fetchBuildings: function (cb, error) {
    exec(cb, error, PLUGIN_NAME, 'fetchBuildings', []);
  },
  /**
   * Download all the floors of a building.
   * @description Download all the floors of a building
   * @param {building} {@link Building building} - the building. Not null.
   * @param {callback} cb - Cordova native callback to recive data. The plugin returns synchronously.
   * @param {callback} error - Cordova native callback to recive errors. The plugin returns synchronously.
   * @returns {floor[]} {@link Floor} - floors array 
   */
  fetchFloorsFromBuilding: function (building, cb, error) {
    exec(cb, error, PLUGIN_NAME, 'fetchFloorsFromBuilding', [building]);
  },
  /**
   * Download the indoor POIs of a building.
   * @description Download the indoor POIs of a building
   * @param {building} {@link Building building} - the building. Not null.
   * @param {callback} cb - Cordova native callback to recive data. The plugin returns synchronously.
   * @param {callback} error - Cordova native callback to recive errors. The plugin returns synchronously.
   * @returns {poi[]} {@link Poi} - Array of POIs
   */
  fetchIndoorPOIsFromBuilding: function (building, cb, error) {
    exec(cb, error, PLUGIN_NAME, 'fetchIndoorPOIsFromBuilding', [building]);
  },
  /**
   * Download the outdoor POIs of a building.
   * @description Download the outdoor POIs of a building
   * @param {building} {@link Building building} - the building. Not null.
   * @param {callback} cb - Cordova native callback to recive data. The plugin returns synchronously.
   * @param {callback} error - Cordova native callback to recive errors. The plugin returns synchronously.
   * @returns {poi[]} {@link Poi} - Array of POIs
   */
  fetchOutdoorPOIsFromBuilding: function (building, cb, error) {
    exec(cb, error, PLUGIN_NAME, 'fetchOutdoorPOIsFromBuilding', [building]);
  },
  /**
   * Download the events of a building.
   * @description Download the events of a building
   * @param {building} {@link Building building} - the building. Not null.
   * @param {callback} cb - Cordova native callback to recive data. The plugin returns synchronously.
   * @param {callback} error - Cordova native callback to recive errors. The plugin returns synchronously.
   * @returns {event[]} The evetns of a building
   */
  fetchEventsFromBuilding: function (building, cb, error) {
    exec(cb, error, PLUGIN_NAME, 'fetchEventsFromBuilding', [building]);
  },
  /**
   * Get all POI categories, download and cache their icons asynchronously.
   * @description Get all POI categories, download and cache their icons asynchronously. To get some of those icons from local storage @see {@link fetchPoiCategoryIconNormal(PoiCategory)} or {@link fetchPoiCategoryIconSelected(PoiCategory)}
   * @param {callback} cb - Cordova native callback to recive data. The plugin returns synchronously.
   * @param {callback} error - Cordova native callback to recive errors. The plugin returns synchronously.
   * @returns {poiCategory[]} {@link PoiCategory} - poiCategories
   */
  fetchPoiCategories: function (cb, error) {
    exec(cb, error, PLUGIN_NAME, 'fetchPoiCategories');
  },
  /**
   * Download the map image of a floor
   * @description Download the map image of a floor
   * @param {floor} {@link Floor floor} - the floor. Not null.
   * @param {callback} cb - Cordova native callback to recive data. The plugin returns synchronously.
   * @param {callback} error - Cordova native callback to recive errors. The plugin returns synchronously.
   * @returns {map} - The map image
   */
  fetchMapFromFloor: function (floor, cb, error) {
    exec(cb, error, PLUGIN_NAME, 'fetchMapFromFloor', [floor]);
  },
  /**
   * Get the normal category icon for a category.
   * @description Get the normal category icon for a category
   * @param {category} {@link PoiCategory category} - the category. Not null.
   * @param {callback} cb - Cordova native callback to recive data. The plugin returns synchronously.
   * @param {callback} error - Cordova native callback to recive errors. The plugin returns synchronously.
   * @returns {icon} - The category icon
   */
  fetchPoiCategoryIconNormal: function (category, cb, error) {
    exec(cb, error, PLUGIN_NAME, 'fetchPoiCategoryIconNormal', [category]);
  },
  /**
   * Get the selected category icon for a category.
   * @description Get the selected category icon for a category
   * @param {category} {@link PoiCategory category} - the category. Not null.
   * @param {callback} cb - Cordova native callback to recive data. The plugin returns synchronously.
   * @param {callback} error - Cordova native callback to recive errors. The plugin returns synchronously.
   * @returns {icon} - The category icon
   */
  fetchPoiCategoryIconSelected: function (category, cb, error) {
    exec(cb, error, PLUGIN_NAME, 'fetchPoiCategoryIconSelected', [category]);
  },
  /**
   * Invalidate all the resources in the cache.
   * @description Invalidate all the resources in the cache
   * @param {callback} cb - Cordova native callback to recive data. The plugin returns synchronously.
   * @param {callback} error - Cordova native callback to recive errors. The plugin returns synchronously.
   * @returns {void}
   */
  invalidateCache: function (cb, error) {
    exec(cb, error, PLUGIN_NAME, 'invalidateCache');
  },
  /**
   * Calculates a route between two points.
   * @description Calculates a route between two points.
   * @param {args} request - request - non-null search parameters. {@link Building building}, {@link Point from} and {@link Point to}. Points have to be inside the same building.
   * @param {callback} cb - Cordova native callback to recive data. The plugin returns synchronously.
   * @param {callback} error - Cordova native callback to recive errors. The plugin returns synchronously.
   * @return {route} {@link Route} - The route between provided points
   */
  requestDirections: function (args, cb, error) {
    exec(cb, error, PLUGIN_NAME, 'requestDirections', args);
  }
};
module.exports = Situm;