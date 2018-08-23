var exec = require('cordova/exec');

var PLUGIN_NAME = 'Situm';

/** 
 * @namespace Situm
 */

var Situm = {

  /**
   * Provides your API key to the Situm SDK.
   * @description Provides your API key to the Situm SDK. This key is generated for your application in the Dashboard. Old credentials will be removed.
   * @param {string} email Email that identifies the account. Can't be empty.
   * @param {string} apiKey Key obtained at situm.es. Can't be empty.
   * @param {function} cb Cordova native callback to recive data.
   * @param {function} error Cordova native callback to recive errors.
   * @return {boolean} success True if operation finished successfully, otherwise false
   */
  setApiKey: function (email, apiKey, cb, error) {
    exec(cb, error, PLUGIN_NAME, 'setApiKey', [email, apiKey]);
  },
  /**
   * Provides user's email and password.
   * @description Provides user's email and password. This credentials will be used to obtain a valid user token to authenticate the server request, when necessary. Token obtaining is not necessary done when this method is executed. Old credentials will be removed.
   * @param {string} email User's email. Can't be empty.
   * @param {string} password User's password. Can't be empty.
   * @param {function} cb Cordova native callback to recive data.
   * @param {function} error Cordova native callback to recive errors.
   * @return {boolean} success True if operation finished successfully, otherwise false
   */
  setUserPass: function (email, password, cb, error) {
    exec(cb, error, PLUGIN_NAME, 'setUserPass', [email, password]);
  },
  /**
   * Sets the maximum age of a cached response.
   * @description Sets the maximum age of a cached response. If the cache response's age exceeds maxAge, it will not be used and a network request will be made.
   * @param {number} cacheAge A non-negative integer
   * @param {function} cb Cordova native callback to recive data.
   * @param {function} error Cordova native callback to recive errors.
   * @return {void}
   */
  setCacheMaxAge: function (cacheAge, cb, error) {
    exec(cb, error, PLUGIN_NAME, 'setCacheMaxAge', [cacheAge]);
  },
  /**
   * Set callback and starts listen onLocationChanged event.
   * @param {LocationRequest} request Location Request.
   * @param {function} cb Cordova native callback to recive data.
   * @param {function} error Cordova native callback to recive errors.
   * @return {Location} position Current position of device.
   */
  startPositioning: function (request, cb, error) {
    exec(cb, error, PLUGIN_NAME, 'startPositioning', request);
  },
  /**
   * Stop locationListener on current active listener.
   * @description Stop locationListener on current active listener.
   * @param {function} cb Cordova native callback to recive data.
   * @param {function} error Cordova native callback to recive errors.
   * @return {void}
   */
  stopPositioning: function (cb, error) {
    exec(cb, error, PLUGIN_NAME, 'stopPositioning', []);
  },
  /**
   * Download all the buildings for the current user.
   * @description Download all the buildings for the current user
   * @param {function} cb Cordova native callback to recive data.
   * @param {function} error Cordova native callback to recive errors.
   * @return {Building[]} buildings Array of buildings.
   */
  fetchBuildings: function (cb, error) {
    exec(cb, error, PLUGIN_NAME, 'fetchBuildings', []);
  },
  /**
   * Download all the floors of a building.
   * @description Download all the floors of a building
   * @param {Building} building The building. Not null.
   * @param {function} cb Cordova native callback to recive data.
   * @param {function} error Cordova native callback to recive errors.
   * @return {Floor[]} floors Array of floors. 
   */
  fetchFloorsFromBuilding: function (building, cb, error) {
    exec(cb, error, PLUGIN_NAME, 'fetchFloorsFromBuilding', [building]);
  },
  /**
   * Download the indoor POIs of a building.
   * @description Download the indoor POIs of a building
   * @param {Building} building The building. Not null.
   * @param {function} cb Cordova native callback to recive data.
   * @param {function} error Cordova native callback to recive errors.
   * @return {Poi[]} pois Array of POIs
   */
  fetchIndoorPOIsFromBuilding: function (building, cb, error) {
    exec(cb, error, PLUGIN_NAME, 'fetchIndoorPOIsFromBuilding', [building]);
  },
  /**
   * Download the outdoor POIs of a building.
   * @description Download the outdoor POIs of a building
   * @param {Building} building The building. Not null.
   * @param {function} cb Cordova native callback to recive data.
   * @param {function} error Cordova native callback to recive errors.
   * @return {Poi[]} pois Array of POIs
   */
  fetchOutdoorPOIsFromBuilding: function (building, cb, error) {
    exec(cb, error, PLUGIN_NAME, 'fetchOutdoorPOIsFromBuilding', [building]);
  },
  /**
   * Download the events of a building.
   * @description Download the events of a building
   * @param {Building} building The building. Not null.
   * @param {function} cb Cordova native callback to recive data.
   * @param {function} error Cordova native callback to recive errors.
   * @return {event[]} events The events of a building
   */
  fetchEventsFromBuilding: function (building, cb, error) {
    exec(cb, error, PLUGIN_NAME, 'fetchEventsFromBuilding', [building]);
  },
  /**
   * Get all POI categories, download and cache their icons asynchronously.
   * @description Get all POI categories, download and cache their icons asynchronously. To get some of those icons from local storage @see {@link fetchPoiCategoryIconNormal(PoiCategory)} or {@link fetchPoiCategoryIconSelected(PoiCategory)}
   * @param {function} cb Cordova native callback to recive data.
   * @param {function} error Cordova native callback to recive errors.
   * @return {PoiCategory[]} poiCategories The poiCategories array
   */
  fetchPoiCategories: function (cb, error) {
    exec(cb, error, PLUGIN_NAME, 'fetchPoiCategories');
  },
  /**
   * Download the map image of a floor
   * @description Download the map image of a floor
   * @param {Floor} floor The floor. Not null.
   * @param {function} cb Cordova native callback to recive data.
   * @param {function} error Cordova native callback to recive errors.
   * @return {bitmap} map The map image
   */
  fetchMapFromFloor: function (floor, cb, error) {
    exec(cb, error, PLUGIN_NAME, 'fetchMapFromFloor', [floor]);
  },
  /**
   * Get the normal category icon for a category.
   * @description Get the normal category icon for a category
   * @param {PoiCategory} category The category. Not null.
   * @param {function} cb Cordova native callback to recive data.
   * @param {function} error Cordova native callback to recive errors.
   * @return {bitmap} icon The category icon
   */
  fetchPoiCategoryIconNormal: function (category, cb, error) {
    exec(cb, error, PLUGIN_NAME, 'fetchPoiCategoryIconNormal', [category]);
  },
  /**
   * Get the selected category icon for a category.
   * @description Get the selected category icon for a category
   * @param {PoiCategory} category The category. Not null.
   * @param {function} cb Cordova native callback to recive data.
   * @param {function} error Cordova native callback to recive errors.
   * @return {bitmap} icon The category icon
   */
  fetchPoiCategoryIconSelected: function (category, cb, error) {
    exec(cb, error, PLUGIN_NAME, 'fetchPoiCategoryIconSelected', [category]);
  },
  /**
   * Invalidate all the resources in the cache.
   * @description Invalidate all the resources in the cache
   * @param {function} cb Cordova native callback to recive data.
   * @param {function} error Cordova native callback to recive errors.
   * @return {void}
   */
  invalidateCache: function (cb, error) {
    exec(cb, error, PLUGIN_NAME, 'invalidateCache');
  },
  /**
   * Calculates a route between two points.
   * @description Calculates a route between two points.
   * @param {DirectionsRequest} directionsRequest Request - non-null search parameters.
   * @param {function} cb Cordova native callback to recive data.
   * @param {function} error Cordova native callback to recive errors.
   * @return {Route} route The route between provided points
   */
  requestDirections: function (directionsRequest, cb, error) {
    exec(cb, error, PLUGIN_NAME, 'requestDirections', directionsRequest);
  },
  /**
   * Set the navigation params, and the listener that receives the updated navigation progress.
   * @description Set the navigation params, and the listener that receives the updated navigation progress. Can only exist one navigation with one listener at a time. If this method was previously invoked, but removeUpdates() wasn't, removeUpdates() is called internally.
   * @param {NavigationRequest} navigationRequest Request non-null search parameters.
   * @param {function} cb Cordova native callback to recive data.
   * @param {function} error Cordova native callback to recive errors.
   * @return {void}
   */
  requestNavigationUpdates: function (navigationRequest, cb, error) {
    exec(cb, error, PLUGIN_NAME, 'requestNavigationUpdates', navigationRequest);
  },
  /**
   * Informs NavigationManager object the change of the user's location.
   * @description Informs NavigationManager object the change of the user's location.
   * @param {Location} location New Location of the user. If null, nothing is done.
   * @param {function} cb Cordova native callback to recive data.
   * @param {function} error Cordova native callback to recive errors.
   * @return {boolean} success True if there is a listener to which notify progress update. False if there isn't, so this method do nothing.
   */
  updateNavigationWithLocation: function (location, cb, error) {
    exec(cb, error, PLUGIN_NAME, 'updateNavigationWithLocation', [location]);
  },
  /**
   * Removes all location updates.
   * @description Removes all location updates. This removes the internal state of the manager, including the listener provided in requestNavigationUpdates(NavigationRequest, NavigationListener), so it won't receive more progress updates.
   * @param {function} cb Cordova native callback to recive data.
   * @param {function} error Cordova native callback to recive errors.
   * @return {boolean} success True if a listener was removed. False if there was no listener.
   */
  removeNavigationUpdates: function (cb, error) {
    exec(cb, error, PLUGIN_NAME, 'removeNavigationUpdates', []);
  }
};
module.exports = Situm;
