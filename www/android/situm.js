var exec = require('cordova/exec');

var PLUGIN_NAME = 'Situm';

/** @namespace
 * <b>Situm Plugin</b><br />
 * Plugin para implementar las funciones de Situm en Ionic<br />
 * <br />
 * Dependencias:<br />
 *  - cordova-plugin-googlemaps<br />
 * <br />
 * Como utilizarlo:<br />
 *  - Para poder usarlo solo tienes que crear una aplicación de Ionic, instalarlo junto a sus dependencias 
 *    y configurar una api-key. Para obtener una ap-key debes estar registrado en la web de Situma<br />
 * <br />
 * Uso básico:
 */

var Situm = {
  /**
 * Configura la api-key para utilizar en las peticiones.
 * @param {string} email
 * @param {string} api_key
 * @returns {void}
 */
  setApiKey: function (email, apiKey, cb, error) {
    exec(cb, error, PLUGIN_NAME, 'setApiKey', [email, apiKey]);
  },
  /**
  * Obtener un token de usuario con una email y una contraseña.
  * @param {string} email
  * @param {string} password
  * @returns {void}
  */
  setUserPass: function (email, apiKey, cb, error) {
    exec(cb, error, PLUGIN_NAME, 'setUserPass', [email, password]);
  },
  /**
 * Configurar tiempo máximo para mantener los datos en caché
 * @param {number} cacheAge
 */
  setCacheMaxAge: function (cacheAge, cb, error) {
    exec(cb, error, PLUGIN_NAME, 'setCacheMaxAge', [cacheAge]);
  },
  /**
  * Iniciar el evento que devuelve la ubicación, esta función devolverá la posición actual del dispositivo hasta que se llame a la función <tt>sopPositioning</tt>
  * @param {building[]} arrBuilding - El array de edificios que tengamos para nuestra cuenta
  * @return {position} position - Posición actual devuelta en un objeto <tt>position</tt>
  */
  startPositioning: function (arrBuilding, cb, error) {
    exec(cb, error, PLUGIN_NAME, 'startPositioning', arrBuilding);
  },
  /**
 * Detiene el evento que devuelve la ubicación del dispositivo, si no se ha llamado a la función <tt>startPositioning</tt> no tendrá efecto alguno.
 * @returns {void}
 */
  stopPositioning: function (cb, error) {
    exec(cb, error, PLUGIN_NAME, 'stopPositioning', []);
  },
  /**
 * Obtener los edificios asociados a una cuenta.
 * @returns {building[]} Devuelve un array con los edificios asociados a la cuenta que tenemos configurada
 */
  fetchBuildings: function (cb, error) {
    exec(cb, error, PLUGIN_NAME, 'fetchBuildings', []);
  },
  /**
 * Obtener los pisos de un edificio
 * @param {building} building - Un edificio de los obtenidos por la función <tt>fetchBuildings</tt>
 * @returns {floor[]} Devuelve un array con las plantas de un edificio
 */
  fetchFloorsFromBuilding: function (building, cb, error) {
    exec(cb, error, PLUGIN_NAME, 'fetchFloorsFromBuilding', [building]);
  },
  /**
 * Obtener los puntos de interés dentro del edificio
 * @param {building} building - Uno de los edificios disponibles
 * @returns {poi[]} Devuelve un array con los puntos de interés
 */
  fetchIndoorPOIsFromBuilding: function (building, cb, error) {
    exec(cb, error, PLUGIN_NAME, 'fetchIndoorPOIsFromBuilding', [building]);
  },
  /**
 * Obtener los puntos de interés del exterior de un edificio
 * @param {building} building - Uno de los edificios disponibles
 * @returns {poi[]} Devuelve un array con los puntos de interés
 */
  fetchOutdoorPOIsFromBuilding: function (building, cb, error) {
    exec(cb, error, PLUGIN_NAME, 'fetchOutdoorPOIsFromBuilding', [building]);
  },
  /**
 * Obtener los eventos asociados a un edificio
 * @param {building} building - Uno de los edificios disponibled
 * @returns {event[]} Devuelve un array con los eventos asociados
 */
  fetchEventsFromBuilding: function (building, cb, error) {
    exec(cb, error, PLUGIN_NAME, 'fetchEventsFromBuilding', [building]);
  },
  /**
 * Obtener los tipos de puntos de interés que tenemos disponibles
 * @returns {poiCategory[]} Devuelve un array con los tipos de puntos de interés
 */
  fetchPoiCategories: function (cb, error) {
    exec(cb, error, PLUGIN_NAME, 'fetchPoiCategories');
  },
  /**
 * Obtener el mapa de una de las plantas de un edificio
 * @param {floor} floor - Una planta de alguno de los edificios
 * @returns {map} - Una imagen en base64
 */
  fetchMapFromFloor: function (floor, cb, error) {
    exec(cb, error, PLUGIN_NAME, 'fetchMapFromFloor', [floor]);
  },
  /**
 * Devuelve el icono para una categoría de puntos de interés
 * @param {category} category - La categoría de la que queremos obtener el icono
 * @returns {icon} - El icono en base 64
 */
  fetchPoiCategoryIconNormal: function (category, cb, error) {
    exec(cb, error, PLUGIN_NAME, 'fetchPoiCategoryIconNormal', [category]);
  },
  /**
  * Devuelve el icono de selección para una categoría de puntos de interés
  * @param {category} category - la cateogría de la que queremos obtener el icono
  * @returns {icon} - El icono en base 64
  */
  fetchPoiCategoryIconSelected: function (category, cb, error) {
    exec(cb, error, PLUGIN_NAME, 'fetchPoiCategoryIconSelected', [category]);
  },
  /**
 * In validar la caché para poder refrescar los datos
 * @returns {void}
 */
  invalidateCache: function (cb, error) {
    exec(cb, error, PLUGIN_NAME, 'invalidateCache');
  },
  /**
  * Intenta convertir un punto en una dirección
  * @param {point} - Un punto del mapa
  * @return {direction} - La dirección correspondiente al punto que se envía
  */
  requestDirections: function (args, cb, error) {
    exec(cb, error, PLUGIN_NAME, 'requestDirections', args);
  }
};
module.exports = Situm;
