/** @namespace <b>Building</b><br />
 * A Building object definition
 * @property {string} buildingIdentifier - The unique identifier of the resource
 * @property {string} name - The building name that is appropriate for display to the user.
 * @property {string} address - Te building address.
 * @property {Bounds} bounds - Compute corners of this building, without rotation, in earth coordinates.
 * @property {Bounds} boundsRotated - Compute corners of this building, with rotation, in earth coordinates.
 * @property {Coordinate} center - Center of the building's base, as geographical coordinate.
 * @property {Dimensions} Dimensions - Dimensions of building's base (height and width) in meters.
 * @property {string} infoHtml - Additional information about building, formatted with HTML
 * @property {string} pictureThumbUrl - The URL of building thumbnail image
 * @property {string} pictureUrl - The URL of building image
 * @property {float} rotation - Rotation angle of the building's base, relative to the west-east axis, increasing in counter-clockwise, being 0 the west-east axis.
 * @property {string} userIdentifier - Unique identifier of the owner user of the building
 * @property {Object} customFields - Map of custom fields, indexed by their name.
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
 * Floor of a building.
 * @property {float} altitude - Altitude of the floor above ground level, in meters.
 * @property {string} buildingIdentifier - The identifier of building which this floor belongs.
 * @property {number} level - The number of the floor.
 * @property {string} mapUrl - The floor map image url
 * @property {float} scale - The scale of the floor image, in px/meters
 * @property {string} floorIdentifier - The unique identifier of the resource
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
 * Define 2D dimensions of a rectangular area.
 * @property {float} width - Width of rectangle in meters
 * @property {float} height - Height of rectangle in meters.
 */

var Dimensions = {
  width,
  height
}

module.exports = Dimensions

/** @namespace
 * <b>Bounds</b><br />
 * Represents a rectangle bounds in a greographic 2D space.
 * @property {Coordinate} northEast - The coordinate of the north-east corner of the bound.
 * @property {Coordinate} northWest - The coordinate of the north-west corner of the bound.
 * @property {Coordinate} southEast - The coordinate of the south-east corner of the bound.
 * @property {Coordinate} southWest - The coordinate of the south-east corner of the bound.
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
 * A structure that contains geographical coordinate.
 * @property {float} latitude - Latitude in degrees
 * @property {float} longitude - Longitude in degrees
 */

var Coordinate = {
  latitude,
  longitude
}

module.exports = Coordinate

/**
 * @namespace
 * <b>Cartesian Coordinate</b><br />
 * A structure that contains cartesian coordinate.
 * @property {float} x - Value of coordinate at x-axis
 * @property {float} y - Value of coordinate at y-axis
 */

var CartesianCoordinate = {
  x,
  y
}

module.exports = CartesianCoordinate

/**
 * @namespace
 * <b>POI (Point of Interest)</b>
 * Point of Interest, associated to a building, regardless of whether it's place inside or outside the building.
 * @property {string} identifier - The unique identifier of the resource
 * @property {string} buildingIdentifier - Identifier of building to which the POI belongs.
 * @property {CartesianCoordinate} cartesianCoordinate - Cartesian coordinate of this position, relative to building Bounds.
 * @property {Coordinate} coordinate - Geographical coordinate of this position
 * @property {string} floorIdentifier - If this POI is outside the building (isOutdoor == true), this field has no meaning.
 * @property {string} poiName - A name for the POI, appropriate for display to the user.
 * @property {Point} position
 * @property {boolean} isIndoor - Whether the POI is placed outside the building or not.
 * @property {boolean} isOutdoor - Whether the POI is placed outside the building or not.
 * @property {PoiCategory} category - Category of the POI
 * @property {string} infoHtml - Additional information about POI, in HTML
 * @property {Object} customFields - Map of custom fields, indexed by their name.
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
 * Category of Point of Interest.
 * @property {string} poiCategoryCode - Unique code of the category
 * @property {string} poiCategoryName - The category name appropriate for display to the user
 * @property {string} icon_selected - The selected icon url
 * @property {string} icon_unselected - The unselected icon url
 * @property {boolean} public - Whether the category is public or not
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
 * Associate geographical coordinate (Location) with Building and Floor (Cartography) and cartesian coordinate relative to that building.
 * @property {string} buildingIdentifier - Unique identifier for the building to which this point belongs
 * @property {CartesianCoordinate} cartesianCoordinate - Cartesian coordinate (in meters) relative to the Bounds of building's base.
 * @property {Coordinate} coordinate - Geographic coordinate (latitude, longitude) of the point, regardless of whether it's placed inside or outside the building.
 * @property {string} floorIdentifier - Floor identifier (inside the building) where this point is placed.
 * @property {boolean} isIndoor - If the POI is inside the building.
 * @property {boolean} idOutdoor - If the POI is outside the building.
 */

var Point = {
  buildingIdentifier,
  cartesianCoordinate,
  coordinate,
  floorIdentifier,
  isIndoor,
  isOutdoor
}

module.exports = Point

/**
 * @namespace
 * <b>Location</b>
 * A location. It can be indoor or outdoor, check isIndoor and isOutdoor. A valid indoor location has floorIdentifier and cartesianCoordinate.
 * @property {float} accuracy - The accuracy radius (in meters)
 * @property {Angle} bearing -  The bearing (in degrees) with respect to the Earth North.
 * @property {string} bearingQuality - The quality of the cartesian bearing.
 * @property {string} buildingIdentifier - Only used in indoor.
 * @property {Angle} cartesianBearing - Only used in indoor. In {@link Angle} object.
 * @property {CartesianCoordinate} cartesianCoordinate - Only used in indoor. In {@link CartesianCoordinate} object.
 * @property {Coordinate} coordinate - The {@link Coordinate} of the location
 * @property {string} floorIdentifier - Only used in indoor.
 * @property {Point} position - The position of the location as {@link Point}.
 * @property {string} provider - The device identifier that has generated the location
 * @property {string} quality - Only used in indoor.
 * @property {boolean} hasBearing - True if the location has bearing and the bearing quality is Location.Quality.HIGH, false otherwise.
 * @property {number} timestamp - The timestamp of the location.
 * @property {boolean} hasCartesianBearing - Only used in indoor.
 * @property {boolean} isIndoor - True if the location is indoor
 * @property {boolean} isOutdoor - True if the location is outdoor
 * @property {string} deviceId
 */

var Location = {
  accuracy,
  bearing,
  bearingQuality,
  buildingIdentifier,
  cartesianBearing,
  cartesianCoordinate,
  coordinate,
  floorIdentifier,
  position,
  provider,
  quality,
  hasBearing,
  timestamp,
  hasCartesianBearing,
  isIndoor,
  isOutdoor,
  deviceId
}

module.exports = Location

/**
 * @namespace
 * <b>Angle</b>
 * Representation of an angle.
 * @property {float} degrees - Angle in degrees, increasing in counter-clockwise 
 * @property {float} degreesClockwise - Angle in radians, increasing in clockwise
 * @property {float} radians - Angle in radians, increasing in counter-clockwise
 * @property {float} radiansMinusPiPi - Angle in radians in range (-pi,pi)
 */

var Angle = {
  degrees,
  degreesClockwise,
  radians,
  radiansMinusPiPi
}

module.exports = Angle

/**
 * @namespace
 * <b>Route</b>
 * Route between two points.
 * @property {RouteStep[]} edges - Ordered list of steps to go to the goal point
 * @property {RouteStep} firstStep - First step
 * @property {Point} from - Point where the route starts.
 * @property {Indication} indications - Ordered list of instructions to go to the destination
 * @property {RouteStep} lastStep - Last step
 * @property {Point[]} nodes - A collection of points of the route (not ordered)
 * @property {Point[]} points - List of ordered points of the route
 * @property {Point} to - Last point and goal of the route.
 * @property {RouteStep[]} steps - Ordered list of steps to go to the goal point
 */

var Route = {
  edges,
  firstStep,
  from,
  indications,
  lastStep,
  nodes,
  points,
  to,
  steps
}

module.exports = Route

/**
 * @namespace
 * <b>RouteStep</b>
 * A fragment of a route, described by the initial point from and the last point to of the fragment, and some information about the step within the route.
 * @property {float} distance - Distance between from and to in meters.
 * @property {float} distanceToGoal - Distance in meters between the start point of this step (from) and the last point in the route ('to' of the last step).
 * @property {Point} from - Start point of this step.
 * @property {number} id - Position of this RouteStep in the list of steps (Route.steps) of the route to which it belongs.
 * @property {Point} to: End point of this step.
 * @property {boolean} isFirst - Returns true if this is the first step in the route.
 * @property {boolean} isLast - Returns true if this is the last step in the route.
 */

var RouteStep = {
  distance,
  distanceToGoal,
  from,
  id,
  to,
  isFirst,
  isLast
}

module.exports = RouteStep

/**
 * @namespace
 * <b>Indication</b>
 * Represents the instruction that a user should follow when on a RouteStep to continue the route.
 * @property {float} distance - The distance between the origin and destination
 * @property {number} distanceToNextLevel - The number of levels between the origin and destination
 * @property {string} indicationType - The Indication.Action of the instruction as String
 * @property {float} orientation - The angle a user should change his direction in order to go from the origin to the destination.
 * @property {string} orientationType - The Indication.Orientation of the instruction as String
 * @property {number} stepIdxDestination - The index of the indication's step of destination. 
 * @property {number} stepIdxOrigin - The index of the indication's step of origin
 * @property {boolean} neededLevelChange - If the user should change the level in order to arrive to destination
 */

var Indication = {
  distance,
  distanceToNextLevel,
  indicationType,
  orientation,
  orientationType,
  stepIdxDestination,
  stepIdxOrigin,
  neededLevelChange
}

module.exports = Indication