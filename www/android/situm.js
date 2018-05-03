var exec = require('cordova/exec');

var PLUGIN_NAME = 'Situm';

/** 
 * @namespace Situm
 */

var Situm = {

  /**
   * Provides your API key to the Situm SDK.
   * @description Provides your API key to the Situm SDK. This key is generated for your application in the Dashboard. Old credentials will be removed.
   * @param {string} email - email that identifies the account. Can't be empty.
   * @param {string} apiKey - key obtained at situm.es. Can't be empty.
   * @param {callback} cb - Cordova native callback to recive data.
   * @param {callback} error - Cordova native callback to recive errors.
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
   * @param {callback} cb - Cordova native callback to recive data.
   * @param {callback} error - Cordova native callback to recive errors.
   * @returns {boolean} true if operation finished successfully, otherwise false
   */
  setUserPass: function (email, password, cb, error) {
    exec(cb, error, PLUGIN_NAME, 'setUserPass', [email, password]);
  },
  /**
   * Sets the maximum age of a cached response.
   * @description Sets the maximum age of a cached response. If the cache response's age exceeds maxAge, it will not be used and a network request will be made.
   * @param {number} maxAge - a non-negative integer
   * @param {callback} cb - Cordova native callback to recive data.
   * @param {callback} error - Cordova native callback to recive errors.
   * @returns {void}
   */
  setCacheMaxAge: function (cacheAge, cb, error) {
    exec(cb, error, PLUGIN_NAME, 'setCacheMaxAge', [cacheAge]);
  },
  /**
   * Set callback and starts listen onLocationChanged event.
   * @param {Building} arrBuilding - Array of buildings to activate locationListener, the plugin only uses the first building.
   * @param {callback} cb - Cordova native callback to recive data.
   * @param {callback} error - Cordova native callback to recive errors.
   * @return {position} position - Current position of device.
   */
  startPositioning: function (arrBuilding, cb, error) {
    exec(cb, error, PLUGIN_NAME, 'startPositioning', arrBuilding);
  },
  /**
   * Stop locationListener on current active listener.
   * @param {callback} cb - Cordova native callback to recive data.
   * @param {callback} error - Cordova native callback to recive errors.
   * @returns {void}
   */
  stopPositioning: function (cb, error) {
    exec(cb, error, PLUGIN_NAME, 'stopPositioning', []);
  },
  /**
   * @function fetchBuildings
   * Download all the buildings for the current user.
   * @description Download all the buildings for the current user
   * @param {callback} cb - Cordova native callback to recive data.
   * @param {callback} error - Cordova native callback to recive errors.
   * @returns {Building[]} - buildings array.
   */
  fetchBuildings: function (cb, error) {
    exec(cb, error, PLUGIN_NAME, 'fetchBuildings', []);
  },
  /**
   * Download all the floors of a building.
   * @description Download all the floors of a building
   * @param {Building} building - the building. Not null.
   * @param {callback} cb - Cordova native callback to recive data.
   * @param {callback} error - Cordova native callback to recive errors.
   * @returns {Floor[]} - floors array 
   */
  fetchFloorsFromBuilding: function (building, cb, error) {
    exec(cb, error, PLUGIN_NAME, 'fetchFloorsFromBuilding', [building]);
  },
  /**
   * Download the indoor POIs of a building.
   * @description Download the indoor POIs of a building
   * @param {Building} building - the building. Not null.
   * @param {callback} cb - Cordova native callback to recive data.
   * @param {callback} error - Cordova native callback to recive errors.
   * @returns {Poi[]} - Array of POIs
   */
  fetchIndoorPOIsFromBuilding: function (building, cb, error) {
    exec(cb, error, PLUGIN_NAME, 'fetchIndoorPOIsFromBuilding', [building]);
  },
  /**
   * Download the outdoor POIs of a building.
   * @description Download the outdoor POIs of a building
   * @param {Building} building - the building. Not null.
   * @param {callback} cb - Cordova native callback to recive data.
   * @param {callback} error - Cordova native callback to recive errors.
   * @returns {Poi[]} - Array of POIs
   */
  fetchOutdoorPOIsFromBuilding: function (building, cb, error) {
    exec(cb, error, PLUGIN_NAME, 'fetchOutdoorPOIsFromBuilding', [building]);
  },
  /**
   * Download the events of a building.
   * @description Download the events of a building
   * @param {Building} building - the building. Not null.
   * @param {callback} cb - Cordova native callback to recive data.
   * @param {callback} error - Cordova native callback to recive errors.
   * @returns {event[]} The evetns of a building
   */
  fetchEventsFromBuilding: function (building, cb, error) {
    exec(cb, error, PLUGIN_NAME, 'fetchEventsFromBuilding', [building]);
  },
  /**
   * Get all POI categories, download and cache their icons asynchronously.
   * @description Get all POI categories, download and cache their icons asynchronously. To get some of those icons from local storage @see {@link fetchPoiCategoryIconNormal(PoiCategory)} or {@link fetchPoiCategoryIconSelected(PoiCategory)}
   * @param {callback} cb - Cordova native callback to recive data.
   * @param {callback} error - Cordova native callback to recive errors.
   * @returns {PoiCategory[]} - poiCategories
   */
  fetchPoiCategories: function (cb, error) {
    exec(cb, error, PLUGIN_NAME, 'fetchPoiCategories');
  },
  /**
   * Download the map image of a floor
   * @description Download the map image of a floor
   * @param {Floor} floor - the floor. Not null.
   * @param {callback} cb - Cordova native callback to recive data.
   * @param {callback} error - Cordova native callback to recive errors.
   * @returns {map} - The map image
   */
  fetchMapFromFloor: function (floor, cb, error) {
    exec(cb, error, PLUGIN_NAME, 'fetchMapFromFloor', [floor]);
  },
  /**
   * Get the normal category icon for a category.
   * @description Get the normal category icon for a category
   * @param {PoiCategory} category - the category. Not null.
   * @param {callback} cb - Cordova native callback to recive data.
   * @param {callback} error - Cordova native callback to recive errors.
   * @returns {icon} - The category icon
   */
  fetchPoiCategoryIconNormal: function (category, cb, error) {
    exec(cb, error, PLUGIN_NAME, 'fetchPoiCategoryIconNormal', [category]);
  },
  /**
   * Get the selected category icon for a category.
   * @description Get the selected category icon for a category
   * @param {PoiCategory} - the category. Not null.
   * @param {callback} cb - Cordova native callback to recive data.
   * @param {callback} error - Cordova native callback to recive errors.
   * @returns {icon} - The category icon
   */
  fetchPoiCategoryIconSelected: function (category, cb, error) {
    exec(cb, error, PLUGIN_NAME, 'fetchPoiCategoryIconSelected', [category]);
  },
  /**
   * Invalidate all the resources in the cache.
   * @description Invalidate all the resources in the cache
   * @param {callback} cb - Cordova native callback to recive data.
   * @param {callback} error - Cordova native callback to recive errors.
   * @returns {void}
   */
  invalidateCache: function (cb, error) {
    exec(cb, error, PLUGIN_NAME, 'invalidateCache');
  },
  /**
   * Calculates a route between two points.
   * @description Calculates a route between two points.
   * @param {args} request - request - non-null search parameters. {@link Building building}, {@link Point from} and {@link Point to}. Points have to be inside the same building.
   * @param {callback} cb - Cordova native callback to recive data.
   * @param {callback} error - Cordova native callback to recive errors.
   * @return {Route} - The route between provided points
   */
  requestDirections: function (args, cb, error) {
    exec(cb, error, PLUGIN_NAME, 'requestDirections', args);
  },
  /**
   * Set the navigation params, and the listener that receives the updated navigation progress.
   * @description Set the navigation params, and the listener that receives the updated navigation progress. Can only exist one navigation with one listener at a time. If this method was previously invoked, but removeUpdates() wasn't, removeUpdates() is called internally.
   * @param {args} request - request - non-null search parameters. Must be an object with next properties:
   * - distanceToFloorChangeThreshold, integer in meters
   * - distanceToChangeIndicationThreshold, integer in meters
   * - distanceToGoalThreshold, integer in meters
   * - outsideRouteThreshold, integer in meters
   * - indicationsInterval, long in miliseconds
   * - timeToFirstIndication, long in miliseconds
   * - roundIndicationsStep, integer in meters
   */
  requestNavigationUpdates: function (args, cb, error) {
    exec(cb, error, PLUGIN_NAME, 'requestNavigationUpdates', args);
  },
  /**
   * Informs NavigationManager object the change of the user's location.
   * @description Informs NavigationManager object the change of the user's location.
   * @param {location} location - new Location of the user. If null, nothing is done.
   * @return {boolean} - true if there is a listener to which notify progress update. False if there isn't, so this method do nothing.
   */
  updateNavigationWithLocation: function (location, cb, error) {
    exec(cb, error, PLUGIN_NAME, 'updateNavigationWithLocation', [location]);
  },
  /**
   * Removes all location updates.
   * @description Removes all location updates. This removes the internal state of the manager, including the listener provided in requestNavigationUpdates(NavigationRequest, NavigationListener), so it won't receive more progress updates.
   * @return {boolean} - true if a listener was removed. False if there was no listener.
   */
  removeNavigationUpdates: function (cb, error) {
    exec(cb, error, PLUGIN_NAME, 'removeNavigationUpdates', []);
  }
};
module.exports = Situm;
