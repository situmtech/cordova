/** @name
 * Building
 * @description
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
 * @property {number} rotation - Rotation angle of the building's base, relative to the west-east axis, increasing in counter-clockwise, being 0 the west-east axis.
 * @property {string} userIdentifier - Unique identifier of the owner user of the building
 * @property {object} customFields - Map of custom fields, indexed by their name.
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
 * @name
 * Floor
 * @description
 * Floor of a building.
 * @property {number} altitude - Altitude of the floor above ground level, in meters.
 * @property {string} buildingIdentifier - The identifier of building which this floor belongs.
 * @property {number} level - The number of the floor.
 * @property {string} name - The name of the floor
 * @property {string} mapUrl - The floor map image url
 * @property {number} scale - The scale of the floor image, in px/meters
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

/** @name
 * Dimensions
 * @description
 * Define 2D dimensions of a rectangular area.
 * @property {number} width - Width of rectangle in meters
 * @property {number} height - Height of rectangle in meters.
 */

var Dimensions = {
  width,
  height
}

module.exports = Dimensions

/** @name
 * Bounds
 * @description
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
 * @name
 * Coordinate
 * @description
 * A structure that contains geographical coordinate.
 * @property {number} latitude - Latitude in degrees
 * @property {number} longitude - Longitude in degrees
 */

var Coordinate = {
  latitude,
  longitude
}

module.exports = Coordinate

/**
 * @name
 * CartesianCoordinate
 * @description
 * A structure that contains cartesian coordinate.
 * @property {number} x - Value of coordinate at x-axis
 * @property {number} y - Value of coordinate at y-axis
 */

var CartesianCoordinate = {
  x,
  y
}

module.exports = CartesianCoordinate

/**
 * @name
 * POI
 * @description
 * Point of Interest, associated to a building, regardless of whether it's place inside or outside the building.
 * @property {string} identifier - The unique identifier of the resource
 * @property {string} buildingIdentifier - Identifier of building to which the POI belongs.
 * @property {CartesianCoordinate} cartesianCoordinate - Cartesian coordinate of this position, relative to building {@link Bounds}.
 * @property {Coordinate} coordinate - Geographical coordinate of this position
 * @property {string} floorIdentifier - If this POI is outside the building (isOutdoor == true), this field has no meaning.
 * @property {string} poiName - A name for the POI, appropriate for display to the user.
 * @property {Point} position - {@link Point} where the point is located.
 * @property {boolean} isIndoor - Whether the POI is placed outside the building or not.
 * @property {boolean} isOutdoor - Whether the POI is placed outside the building or not.
 * @property {PoiCategory} category - Category of the POI
 * @property {string} infoHtml - Additional information about POI, in HTML
 * @property {object} customFields - Map of custom fields, indexed by their name.
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
 * @name
 * PoiCategory
 * @description
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
 * @name
 * Point
 * @description
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
 * @name
 * Location
 * @description
 * A location. It can be indoor or outdoor, check isIndoor and isOutdoor. A valid indoor location has floorIdentifier and cartesianCoordinate.
 * @property {number} accuracy - The accuracy radius (in meters)
 * @property {Angle} bearing -  The bearing (in degrees) with respect to the Earth North.
 * @property {string} bearingQuality - The quality of the cartesian bearing.
 * @property {string} buildingIdentifier - Only used in indoor.
 * @property {Angle} cartesianBearing - Only used in indoor. In {@link Angle} object.
 * @property {CartesianCoordinate} cartesianCoordinate - Only used in indoor. In {@link CartesianCoordinate} object.
 * @property {Coordinate} coordinate - The {@link Coordinate} of the location
 * @property {string} floorIdentifier - Only used in indoor.
 * @property {Point} position - The position of the location as {@link Point}.
 * @property {string} provider - The device identifier that has generated the location
 * @property {string} quality - Only used in indoor. Possible values are HIGH and LOW.
 * @property {boolean} hasBearing - True if the location has bearing and the bearing quality is Location.Quality.HIGH, false otherwise.
 * @property {number} timestamp - The timestamp of the location.
 * @property {boolean} hasCartesianBearing - Only used in indoor.
 * @property {boolean} isIndoor - True if the location is indoor
 * @property {boolean} isOutdoor - True if the location is outdoor
 * @property {string} deviceId - Identifier of the device that has generated the location
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
 * @name
 * Angle
 * @description
 * Representation of an angle.
 * @property {number} degrees - Angle in degrees, increasing in counter-clockwise
 * @property {number} degreesClockwise - Angle in radians, increasing in clockwise
 * @property {number} radians - Angle in radians, increasing in counter-clockwise
 * @property {number} radiansMinusPiPi - Angle in radians in range (-pi,pi)
 */

var Angle = {
  degrees,
  degreesClockwise,
  radians,
  radiansMinusPiPi
}

module.exports = Angle

/**
 * @name
 * Route
 * @description
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
 * @property {RouteSegment[]} segments - List of segments formed by consecutive points and a floor identifier
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
  steps,
  segments
}

module.exports = Route

/**
 * @name
 * RouteStep
 * @description
 * A fragment of a route, described by the initial point from and the last point to of the fragment, and some information about the step within the route.
 * @property {number} distance - Distance between from and to in meters.
 * @property {number} distanceToGoal - Distance in meters between the start point of this step (from) and the last point in the route ('to' of the last step).
 * @property {Point} from - Start point of this step.
 * @property {number} id - Position of this RouteStep in the list of steps (Route.steps) of the route to which it belongs.
 * @property {Point} to - End point of this step.
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
 * @name
 * RouteSegment
 * @description
 * A fragment of a route, described by a floor identifier and a list of consecutive points from the same floor
 * @property {string} floorIdentifier - Identifier of the floor containing the points in this segment
 * @property {Point[]} points - Consecutive points in the same floor forming a path
 */

var RouteSegment = {
  floorIdentifier,
  points
}

module.exports = RouteSegment

/**
 * @name
 * Indication
 * @description
 * Represents the instruction that a user should follow when on a RouteStep to continue the route.
 * @property {number} distance - The distance between the origin and destination
 * @property {number} distanceToNextLevel - The number of levels between the origin and destination
 * @property {string} indicationType - The Indication.Action of the instruction as String
 * @property {number} orientation - The angle a user should change his direction in order to go from the origin to the destination.
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


/**
 * @name
 * NavigationProgress
 * @description
 * Provides information of the progress of a user while following a route.
 * @property {Point} closestPointInRoute - Closest point in the route from the user location provided . @deprecated Use closestLocationInRoute instead.
 * @property {Location} closestLocationInRoute - Closest location in the route from the user location provided .
 * @property {number} distanceToClosestPointInRoute - Distance between the real user location (provided to updateWithLocation(Location)) and the closest route location.
 * @property {Indication} currentIndication - The current indication.
 * @property {Indication} nextIndication - The next indication.
 * @property {number} currentStepIndex - The index of the closest route step to the user, where closestLocationInRoute is.
 * @property {number} distanceToGoal -  The distance in meters from closestLocationInRoute to route's goal point.
 * @property {number} distanceToEndStep - The distance in meters to go from closestLocationInRoute to the end of the current step.
 * @property {number} timeToEndStep - The estimated time to go from closestLocationInRoute to the end of the current route step,  considering a speed of 1 meter/second.
 * @property {number} timeToGoal - The estimated time to go from closestLocationInRoute to the goal/end of route, considering a speed of 1 meter/second.
 * @property {RouteStep} routeStep - The closest route step to the user, where closestLocationInRoute is.
 * @property {Point[]} points - List of ordered points of the remaining route
 * @property {RouteSegment[]} segments - List of segments formed by consecutive points and a floor identifier
 */

var NavigationProgress = {
  closestPointInRoute,
  closestLocationInRoute,
  distanceToClosestPointInRoute,
  currentIndication,
  nextIndication,
  currentStepIndex,
  distanceToGoal,
  distanceToEndStep,
  timeToEndStep,
  timeToGoal,
  routeStep,
  points,
  segments
}

module.exports = NavigationProgress


/**
 * @name
 * SitumEvent
 * @description
 * An event: POI with radius, conversion area and asociated statistics. It is intended for usage in marketing apps.
 * @property {number} buildingIdentifier - The identifier of the building this floor belongs to. Deprecated, use trigger.center.buildingIdentifier instead
 * @property {number} identifier - Unique identifier of the SitumEvent.
 * @property {number} floorIdentifier - The identifier of the floor this event is located at. @deprecated, use trigger.center.floorIdentifier instead
 * @property {string} infoHtml - Information contained into the event, in HTML format.
 * @property {SitumConversionArea} conversionArea - Location where the event is located. @deprecated, use conversion instead
 * @property {Circle} conversion - Location where the event is located.
 * @property {Circle} trigger - Location where the event should be fired
 * @property {object} customFields - Key-value pairs that allow to extend and fully customize the information associated with the event.
 * @property {number} radius - Radius of the event associated area. @deprecated, use trigger.radius instead
 * @property {string} name - Name of the event
 * @property {number} x - Center of the event in the x-axis. @deprecated, use trigger.center.cartesianCoordinate.x instead
 * @property {number} y - Center of the event in the y-axis. @deprecated, use trigger.center.cartesianCoordinate.y instead
 */

var SitumEvent = {
  buildingIdentifier,
  identifier,
  floorIdentifier,
  infoHtml,
  conversionArea,
  customFields,
  radius,
  name,
  x,
  y
}

module.exports = SitumEvent

/**
 * @name
 * SitumConversionArea
 * @description
 * A rectangular area of a floor defining the conversion area of an event
 * @property {number} floorIdentifier - The identifier of the floor the SitumConversionArea is located at.
 * @property {object} topLeft - Top-left corner
 * @property {object} topRight - Top-right corner
 * @property {object} bottomLeft - Bottom-left corner
 * @property {object} bottomRight - Bottom-right corner
 */

var SitumConversionArea = {
  floorIdentifier,
  topLeft,
  topRight,
  bottomLeft,
  bottomRight,
}

module.exports = Circle

/**
 * @name
 * Circle
 * @description
 * A circular area
 * @property {Point} center - The center of the circle
 * @property {number} radius - The radius of the circle
 */

var Circle = {
  center,
  radius,
}

module.exports = SitumConversionArea

/**
 * @name
 * LocationOptions
 * @description
 * A data object that contains parameters for the location service, LocationManager.
 * @property {number} buildingIdentifier - Identifier of the building on which the positioning will be started
 * @property {number} interval - Default interval (in milliseconds) to notify location updates
 * @property {string} indoorProvider - Default indoor provider. Possible values are INPHONE and SUPPORT
 * @property {boolean} useBle - Defines whether or not to use BLE for positioning
 * @property {boolean} useWifi - Defines whether or not to use Wi-Fi for positioning
 * @property {boolean} useGps - Defines whether or not to use the GPS for indoor positioning
 * @property {boolean} useBarometer - Defines whether or not to use barometer for indoor positioning
 * @property {string} motionMode - Default motion mode. Possible values are BY_CAR, BY_FOOT and RADIOMAX
 * @property {boolean} useForegroundService - Defines whether or not to activate the {@link http://developers.situm.es/pages/android/using_situm_sdk_background.html foreground service}
 * @property {boolean} useDeadReckoning - Defines whether ot not to use dead reckoning to get fast position updates using only the inertial sensors, between the server position updates.
 * @property {OutdoorLocationOptions} outdoorLocationOptions - Outdoor location options. Only used in an indoor/outdoor request
 * @property {BeaconFilter[]} beaconFilters - Beacon filters to be handled during scan time, otherwise only Situm beacons will be scanned. Can be invoked multiple times to add as much beacon filters as you want @deprecated The SitumSDK now does it automatically
 * @property {number} smallestDisplacement - Default smallest displacement to nofiy location updates
 * @property {string} realtimeUpdateInterval - Default interval to send locations to the Realtime. Possible values are REALTIME, FAST, NORMAL, SLOW and BATTERY_SAVER
 * @property {boolean} autoEnableBleDuringPositioning - Set if the BLE should be re-enabled during positioning if the ble is used. Android only
 */

var LocationOptions = {
  buildingIdentifier,
  interval,
  indoorProvider,
  useBle,
  useWifi,
  useGps,
  useBarometer,
  motionMode,
  useForegroundService,
  useDeadReckoning,
  outdoorLocationOptions,
  beaconFilters,
  smallestDisplacement,
  realtimeUpdateInterval
}

module.exports = LocationOptions

/**
 * @name
 * LocationRequest
 * @description
 * A data object that contains parameters for the location service, LocationManager.
 * @type {array}
 * @property {Building} building 0 - Building on which the positioning will be started
 * @property {LocationOptions} locationOptions 1 - Location options.
 */

var LocationRequest = {
    building,
    locationOptions
}

module.exports = LocationRequest

/**
 * @name
 * NavigationRequest
 * @description
 * A data object that contains parameters for the navigation service, NavigationManager.
 * @property {number} distanceToChangeIndicationThreshold - Distance threshold from when the next indication is considered reached.
 * @property {number} distanceToFloorChangeThreshold - Distance threshold from when a floor change is considered reached.
 * @property {number} distanceToGoalThreshold - Distance threshold from when the goal is considered reached.
 * @property {number} distanceToIgnoreFirstIndication - Maximum distance to ignore the first indication when navigating a route (Only available for Android).
 * @property {number} ignoreLowQualityLocations - Set if low quality locations should be ignored. (Only available for Android).
 * @property {number} indicationsInterval - Time to wait between indications.
 * @property {number} outsideRouteThreshold - Set the distance to consider the use is outside of the route. A type=userOutsideRoute will be sent trough the NavigationListener .
 * @property {number} roundIndicationsStep - Step that will be used to round indications distance.
 * @property {number} timeToFirstIndication - Time to wait until the first indication is returned.
 * @property {number} timeToIgnoreUnexpectedFloorChanges - Time (in millis) to ignore the locations received during navigation, when the next indication is a floor change, if the locations are in a wrong floor (not in origin or destination floors).
 */

var NavigationRequest = {
  distanceToGoalThreshold,
  outsideRouteThreshold,
  distanceToFloorChangeThreshold,
  distanceToChangeIndicationThreshold,
  indicationsInterval,
  timeToFirstIndication,
  roundIndicationsStep,
  timeToIgnoreUnexpectedFloorChanges
}

module.exports = NavigationRequest

/**
 * @name
 * DirectionsRequest
 * @description
 * A data object that contains the request for directions.
 * @property {Building} positioningBuilding
 * @property {Point|Location} from - Current user's position as the starting point of the route.
 * @property {Point|POI} to - Point to, where the route should end.
 * @property {DirectionsOptions} options - Options that can be added to the request.
 */

var DirectionsRequest = {
  positioningBuilding,
  from,
  to,
  options
}

module.exports = DirectionsRequest

/**
 * @name
 * DirectionsOptions
 * @description
 * A data object that contains the directions options.
 * @property {boolean} minimizeFloorChanges - Defines wheter or not the route should be calculated minimizing the floor changes even if the result is longer.
 * @property {boolean} accessibleRoute - Defines wheter or not the route has to be suitable for wheel chairs (true) or not (false). @deprecated, use accessibilityMode
 * @property {boolean} accessible - Defines wheter or not the route has to be suitable for wheel chairs (true) or not (false). @deprecated, use accessibilityMode
 * @property {string} accessibilityMode - Defines the accessibility mode of the route. Possible values are: CHOOSE_SHORTEST, ONLY_NOT_ACCESSIBLE_FLOOR_CHANGES, ONLY_ACCESSIBLE
 * @property {number} startingAngle - Current user's orientation in degrees.
 */

var DirectionsOptions = {
  minimizeFloorChanges,
  accessibleRoute,
  startingAngle
}

module.exports = DirectionsOptions

/**
 * @name
 * OutdoorLocationOptions
 * @description
 * Outdoor location options are only used in indoor-outdoor mode (Only available for Android)
 * @property {boolean} continuousMode - Environment detection continuous mode (true) or burst mode (false).
 * @property {boolean} userDefinedThreshold
 * @property {number} burstInterval - Interval to scan for GPS and detect the environment (in seconds).
 * @property {number} averageSnrThreshold
 */

var OutdoorLocationOptions = {
  continuousMode,
  userDefinedThreshold,
  burstInterval,
  averageSnrThreshold
}

module.exports = OutdoorLocationOptions

/**
 * @name
 * BeaconFilter
 * @description
 * Represents a BLE filter. Now the only field is the BLE proximity UUID
 * @property {string} uuid - Assigns the proximity UUID
 */

var BeaconFilter = {
  uuid
}

module.exports = BeaconFilter
