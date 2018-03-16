/** @namespace
 * <b>Building</b><br />
 * Building object definition
 * @property {string} buildingIdentifier - Identifier of building
 * @property {string} name - Name of building
 * @property {string} address - Address
 * @property {Bounds} bounds - {@link Bounds} of building without rotation, in earth coordinates
 * @property {Bounds} boundsRotated - {@link Bounds} of building with rotation, in earth coordinates
 * @property {Coordinate} center - Center of building, as {@link Coordinate}
 * @property {Dimensions} Dimensions - {@link Dimensions} of building in metets(width and height)
 * @property {string} infoHtml - Additionalinformation about building, formatted with HTML
 * @property {string} pictureThumbUrl - URL of building thumbnail image
 * @property {string} pictureUrl - URL of building image
 * @property {float} rotation - Angle of building rotation in radians
 * @property {string} userIdentifier - The user identifier
 * @property {Object} customFields - Custom fields of building in an Object with {key: value,...}
 */

var Building = {
  buildingIdentifier,
  name,
  address,
  bounds,
  boundsRotated,
  center,
  dimensions,
  infoHtml,
  pictureThumbUrl,
  pictureUrl,
  rotation,
  userIdentifier,
  customFields
}

module.exports = Building

/**
 * @namespace
 * <b>Floor</b>
 * Floor object definition
 * @property {float} altitude - The altitude of floor, in metres
 * @property {string} buildingIdentifier - The building identifier
 * @property {number} level - The floor level
 * @property {string} mapUrl - The url of floor map image
 * @property {float} scale - The scale of floor map in px/meters
 * @property {string} floorIdentifier - The floor identifier
 */

var Floor = {
  altitude,
  buildingIdentifier,
  level,
  mapUrl,
  scale,
  floorIdentifier
}

module.exports = Floor

/** @namespace
 * <b>Dimensions</b><br />
 * Dimensions object definition
 * @property {float} width - Width
 * @property {float} height - Height
 */

var Dimensions = {
  width,
  height
}

module.exports = Dimensions

/** @namespace
 * <b>Bounds</b><br />
 * Bounds of map, floor or building
 * @property {Coordinate} northEast - {@link Coordinate} object
 * @property {Coordinate} northWest - {@link Coordinate} object
 * @property {Coordinate} southEast - {@link Coordinate} object
 * @property {Coordinate} southWest - {@link Coordinate} object
 */

var Bounds = {
  northEast,
  northWest,
  southEast,
  southWest
}

module.exports = Bounds

/**
 * @namespace
 * <b>Coordinate</b><br />
 * Coordinate
 * @property {float} latitude - Latitude
 * @property {float} longitude - Longitude
 */

var Coordinate = {
  latitude,
  longitude
}

module.exports = Coordinate