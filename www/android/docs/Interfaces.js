/** @namespace <b>Building</b><br />
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

/**
 * @namespace
 * <b>Cartesian Coordinate</b><br />
 * Cartesisan Coordinate
 * @property {float} x - x
 * @property {float} y - y
 */

var CartesianCoordinate = {
  x,
  y
}

module.exports = CartesianCoordinate

/**
 * @namespace
 * <b>POI (Point of Interest)</b>
 * Poi
 * @property {string} identifier - POI identifier
 * @property {string} buildingIdentifier - The building identifier
 * @property {CartesianCoordinate} cartesianCoordinate - The cartesians coordinates of POI center relative to building {@link Bounds} @see {@link CartesianCoordinate}
 * @property {Coordinate} coordinate - The geographical coordinates of this position @see {@link Coordinate}
 * @property {string} floorIdentifier - The identifier of floor
 * @property {string} poiName - The POI name
 * @property {Point} position - The position POI
 * @property {boolean} isIndoor
 * @property {boolean} isOutdoor
 * @property {PoiCategory} category - The {@link PoiCategory}
 * @property {string} infoHtml - Additional information about POI, in HTML
 * @property {Object} customFields - Additional information about POI, in format {key: value,...}
 */

var Poi = {
  identifier,
  buildingIdentifier,
  cartesianCoordinate,
  coordinate,
  floorIdentifier,
  poiName,
  position,
  isIndoor,
  isOutdoor,
  category,
  infoHtml,
  customFields
}

module.exports = Poi

/**
 * @namespace
 * <b>PoiCategory</b>
 * PoiCategory
 * @property {string} poiCategoryCode - The code that identify PoiCategory
 * @property {string} poiCategoryName - The name of PoiCategory
 * @property {string} icon_selected - The url of selected icon
 * @property {string} icon_unselected - The unselected icon url
 * @property {boolean} public - Flag to indicate if PoiCategory is public
 */

var PoiCategory = {
  poiCategoryCode,
  poiCategoryName,
  icon_selected,
  icon_unselected,
  public
}

module.exports = PoiCategory

/**
 * @namespace
 * <b>Point</b>
 * Point
 * @property {string} buildingIdentifier - The building identifier
 * @property {CartesiansCoordinate} cartesianCoordinate - The coordinates of point in {@link CartesianCoordinate} relative to map bounds.
 * @property {Coordinate} coordinate - The coordinate of point in {@link Coordinate}
 * @property {string} floorIdentifier - The floor identifier
 * @property {boolean} isIndoor - Flag that indicates if point is indoor
 * @property {boolean} idOutdoor - Flag that indicates if point is outdoor
 */

var Point = {
  buildingIdentifier,
  cartesianCoordinate,
  coordinate,
  floorIdentifier,
  isIndoor,
  isOutdoor
}