package es.situm.plugin;

import static es.situm.plugin.JsonUtils.toMap;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Base64;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Arrays;

import es.situm.sdk.directions.DirectionsRequest;
import es.situm.sdk.location.ForegroundServiceNotificationOptions;
import es.situm.sdk.location.LocationRequest;
import es.situm.sdk.location.LocationStatus;
import es.situm.sdk.location.OutdoorLocationOptions;
import es.situm.sdk.location.util.CoordinateConverter;
import es.situm.sdk.model.I18nString;
import es.situm.sdk.model.URL;
import es.situm.sdk.model.cartography.Building;
import es.situm.sdk.model.cartography.Circle;
import es.situm.sdk.model.cartography.Floor;
import es.situm.sdk.model.cartography.Poi;
import es.situm.sdk.model.cartography.PoiCategory;
import es.situm.sdk.model.cartography.Point;
import es.situm.sdk.model.cartography.Geofence;
import es.situm.sdk.model.cartography.BuildingInfo;
import es.situm.sdk.model.directions.Indication;
import es.situm.sdk.model.directions.Route;
import es.situm.sdk.model.directions.RouteSegment;
import es.situm.sdk.model.directions.RouteStep;
import es.situm.sdk.model.location.Angle;
import es.situm.sdk.model.location.BeaconFilter;
import es.situm.sdk.model.location.Bounds;
import es.situm.sdk.model.location.CartesianCoordinate;
import es.situm.sdk.model.location.Coordinate;
import es.situm.sdk.model.location.Dimensions;
import es.situm.sdk.model.location.Location;
import es.situm.sdk.model.location.Location.Quality;
import es.situm.sdk.model.navigation.NavigationProgress;
import es.situm.sdk.v1.Point2f;
import es.situm.sdk.v1.SitumConversionArea;
import es.situm.sdk.v1.SitumEvent;
import es.situm.sdk.realtime.RealTimeRequest;
import es.situm.sdk.model.realtime.RealTimeData;
import es.situm.sdk.userhelper.UserHelperColorScheme;


class SitumMapper {

  public static final float MIN_SNR = 10;
  public static final float MAX_SNR = 40;

  public static final String ADDRESS = "address";
  public static final String BOUNDS = "bounds";
  public static final String BOUNDS_ROTATED = "boundsRotated";
  public static final String CENTER = "center";
  public static final String DIMENSIONS = "dimensions";
  public static final String INFO_HTML = "infoHtml";
  public static final String BUILDING_NAME = "name";
  public static final String PICTURE_THUMB_URL = "pictureThumbUrl";
  public static final String POI_NAME = "poiName";
  public static final String POI_CATEGORY_NAME = "poiCategoryName";
  public static final String POI_CATEGORY_CODE = "poiCategoryCode";
  public static final String POI_CATEGORY = "category";
  public static final String IS_PUBLIC = "public";
  public static final String PICTURE_URL = "pictureUrl";
  public static final String ROTATION = "rotation";
  public static final String USER_IDENTIFIER = "userIdentifier";

  public static final String ALTITUDE = "altitude";
  public static final String BUILDING_IDENTIFIER = "buildingIdentifier";
  public static final String FLOOR_IDENTIFIER = "floorIdentifier";

  public static final String LEVEL = "level";
  public static final String FLOOR = "floor";
  public static final String MAP_URL = "mapUrl";
  public static final String SCALE = "scale";

  public static final String COORDINATE = "coordinate";
  public static final String CARTESIAN_COORDINATE = "cartesianCoordinate";
  public static final String CARTESIAN_BEARING = "cartesianBearing";
  public static final String POSITION = "position";
  public static final String IS_INDOOR = "isIndoor";
  public static final String PROVIDER = "provider";
  public static final String QUALITY = "quality";
  public static final String IS_OUTDOOR = "isOutdoor";
  public static final String DEVICE_ID = "deviceId";

  public static final String RADIUS = "radius";
  public static final String ACCURACY = "accuracy";
  public static final String BEARING = "bearing";
  public static final String TIMESTAMP = "timestamp";
  public static final String LATITUDE = "latitude";
  public static final String LONGITUDE = "longitude";
  public static final String STATUS_NAME = "statusName";
  public static final String STATUS_ORDINAL = "statusOrdinal";

  public static final String HAS_BEARING = "hasBearing";
  public static final String HAS_CARTESIAN_BEARING = "hasCartesianBearing";
  public static final String BEARING_QUALITY = "bearingQuality";

  public static final String NORTH_EAST = "northEast";
  public static final String NORTH_WEST = "northWest";
  public static final String SOUTH_EAST = "southEast";
  public static final String SOUTH_WEST = "southWest";

  public static final String DEGREES = "degrees";
  public static final String DEGREES_CLOCKWISE = "degreesClockwise";
  public static final String RADIANS_MINUS_PI_PI = "radiansMinusPiPi";
  public static final String RADIANS = "radians";

  public static final String WIDTH = "width";
  public static final String HEIGHT = "height";
  public static final String X = "x";
  public static final String Y = "y";
  public static final String ID = "id";

  public static final String EDGES = "edges";
  public static final String FIRST_STEP = "firstStep";
  public static final String LAST_STEP = "lastStep";
  public static final String INDICATIONS = "indications";
  public static final String NODES = "nodes";
  public static final String POINTS = "points";
  public static final String FROM = "from";
  public static final String TO = "TO";
  public static final String STEPS = "steps";
  public static final String SEGMENTS = "segments";

  public static final String DISTANCE_TO_GOAL = "distanceToGoal";
  public static final String DISTANCE = "distance";
  public static final String DISTANCE_TO_NEXT_LEVEL = "distanceToNextLevel";
  public static final String DISTANCE_TO_CLOSEST_POINT_IN_ROUTE = "distanceToClosestPointInRoute";
  public static final String DISTANCE_TO_END_STEP = "distanceToEndStep";
  public static final String INDICATION_TYPE = "indicationType";
  public static final String CURRENT_INDICATION = "currentIndication";
  public static final String NEXT_INDICATION = "nextIndication";
  public static final String IS_FIRST = "isFirst";
  public static final String IS_LAST = "isLast";
  public static final String CLOSEST_POINT_IN_ROUTE = "closestPointInRoute";
  public static final String STEP_IDX_DESTINATION = "stepIdxDestination";
  public static final String STEP_IDX_ORIGIN = "stepIdxOrigin";
  public static final String NEEDED_LEVEL_CHANGE = "neededLevelChange";
  public static final String HUMAN_READABLE_MESSAGE = "humanReadableMessage";
  public static final String ORIENTATION_TYPE = "orientationType";
  public static final String ORIENTATION = "orientation";
  public static final String ROUTE_STEP = "routeStep";
  public static final String TIME_TO_END_STEP = "timeToEndStep";
  public static final String TIME_TO_GOAL = "timeToGoal";
  public static final String NEXT_LEVEL = "nextLevel";

  public static final String CONVERSION_AREA = "conversionArea";
  public static final String CONVERSION = "conversion";
  public static final String TRIGGER = "trigger";
  public static final String IDENTIFIER = "identifier";
  public static final String CUSTOM_FIELDS = "customFields";
  public static final String TOP_LEFT = "topLeft";
  public static final String BOTTOM_LEFT = "bottomLeft";
  public static final String TOP_RIGHT = "topRight";
  public static final String BOTTOM_RIGHT = "bottomRight";
  public static final String POI_CATEGORY_ICON_SELECTED = "icon_selected";
  public static final String POI_CATEGORY_ICON_UNSELECTED = "icon_unselected";

  public static final String INTERVAL = "interval";
  public static final String INDOOR_PROVIDER = "indoorProvider";
  public static final String USE_BLE = "useBle";
  public static final String USE_WIFI = "useWifi";
  public static final String MOTION_MODE = "motionMode";
  public static final String USE_FOREGROUND_SERVICE = "useForegroundService";
  public static final String USE_DEAD_RECKONING = "useDeadReckoning";
  public static final String USE_GPS = "useGps";
  public static final String USE_BAROMETER = "useBarometer";
  public static final String AUTO_ENABLE_BLE = "autoEnableBleDuringPositioning";

  public static final String OUTDOOR_LOCATION_OPTIONS = "outdoorLocationOptions";
  public static final String USER_DEFINED_THRESHOLD = "userDefinedThreshold";
  public static final String COMPUTE_INTERVAL = "computeInterval";
  public static final String AVERAGE_SNR_THRESHOLD = "averageSnrThreshold";

  public static final String BEACON_FILTERS = "beaconFilters";
  public static final String UUID = "uuid";

  public static final String SMALLEST_DISPLACEMENT = "smallestDisplacement";
  public static final String REALTIME_UPDATE_INTERVAL = "realtimeUpdateInterval";
  public static final String ACCESSIBLE = "accessible";
  public static final String ACCESSIBLE_ROUTE = "accessibleRoute";
  public static final String CACHE_AGE = "cacheAge";

  public static final String DISTANCE_TO_IGNORE_FIRST_INDICATION = "distanceToIgnoreFirstIndication";
  public static final String OUTSIDE_ROUTE_THRESHOLD = "outsideRouteThreshold";
  public static final String DISTANCE_TO_GOAL_THRESHOLD = "distanceToGoalThreshold";
  public static final String DISTANCE_TO_CHANGE_INDICATION_THRESHOLD = "distanceToChangeIndicationThreshold";
  public static final String DISTANCE_TO_CHANGE_FLOOR_THRESHOLD = "distanceToChangeFloorThreshold";
  public static final String INDICATIONS_INTERVAL = "indicationsInterval";
  public static final String TIME_TO_FIRST_INDICATION = "timeToFirstIndication";
  public static final String ROUND_INDICATION_STEP = "roundIndicationsStep";
  public static final String TIME_TO_IGNORE_UNEXPECTED_FLOOR_CHANGES = "timeToIgnoreUnexpectedFloorChanges";
  public static final String IGNORE_LOW_QUALITY_LOCATIONS = "ignoreLowQualityLocations";

  public static final String CURRENT_STEP_INDEX = "currentStepIndex";
  public static final String CLOSEST_LOCATION_IN_ROUTE = "closestLocationInRoute";

  public static final String STARTING_ANGLE = "startingAngle";
  public static final String MINIMIZE_FLOOR_CHANGES = "minimizeFloorChanges";
  public static final String CREATED_AT = "createdAt";
  public static final String UPDATED_AT = "updatedAt";
  public static final String NAME = "name";
  public static final String ACCESSIBILITY_MODE = "accessibilityMode";
  public static final String INCLUDED_TAGS = "includedTags";
  public static final String EXCLUDED_TAGS = "excludedTags";
  public static final String POLYGON_POINTS = "polygonPoints";
  public static final String CODE = "code";
  public static final String BUILDING = "building";
  public static final String FLOORS = "floors";
  public static final String EVENTS = "events";
  public static final String INDOOR_POIS = "indoorPOIs";
  public static final String OUTDOOR_POIS = "outdoorPOIs";
  public static final String LOCATIONS = "locations";
  public static final String POLL_TIME = "pollTime";

  public static final String FOREGROUND_SERVICE_NOTIFICATION_OPTIONS = "foregroundServiceNotificationOptions";

  public static final DateFormat dateFormat = DateUtils.dateFormat;

  private static final String TAG = "PluginHelper";

  static JSONObject buildingToJsonObject(Building building) throws JSONException {
    JSONObject jo = new JSONObject();
    jo.put(ADDRESS, building.getAddress());
    jo.put(BOUNDS, boundsToJsonObject(building.getBounds()));
    jo.put(BOUNDS_ROTATED, boundsToJsonObject(building.getBoundsRotated()));
    jo.put(CENTER, coordinateToJsonObject(building.getCenter()));
    jo.put(DIMENSIONS, dimensionsToJsonObject(building.getDimensions()));
    jo.put(INFO_HTML, building.getInfoHtml());
    jo.put(BUILDING_NAME, building.getName());
    jo.put(PICTURE_THUMB_URL, building.getPictureThumbUrl().getValue());
    jo.put(PICTURE_URL, building.getPictureUrl().getValue());
    jo.put(ROTATION, building.getRotation().radians());
    jo.put(USER_IDENTIFIER, building.getUserIdentifier());
    jo.put(BUILDING_IDENTIFIER, building.getIdentifier());
    jo.put(CUSTOM_FIELDS, mapStringToJsonObject(building.getCustomFields()));
    jo.put(CREATED_AT, dateFormat.format(building.getCreatedAt()));
    jo.put(UPDATED_AT, dateFormat.format(building.getUpdatedAt()));
    return jo;
  }

  static JSONObject mapStringToJsonObject(Map<String, String> mp) throws JSONException {
    JSONObject jo = new JSONObject();
    Iterator it = mp.entrySet().iterator();
    while (it.hasNext()) {
      Map.Entry<String, String> pairs = (Map.Entry<String, String>) it.next();
      jo.put(pairs.getKey(), pairs.getValue());
    }
    return jo;
  }

  static Map<String,String> jsonObjectToMapString(JSONObject jo) throws JSONException {
    Map<String,String> map = new HashMap<String, String>();
    int length = jo.length();
    for(int i = 0; i<length; i++){
      map.put(jo.names().get(i).toString(),jo.getString(jo.names().get(i).toString()));
    }
    return map;
  }

  static Building buildingJsonObjectToBuilding(JSONObject jo) throws JSONException, ParseException {
    Building building = null;
    Coordinate center = new Coordinate(jo.getJSONObject(CENTER).getDouble(LATITUDE),
        jo.getJSONObject(CENTER).getDouble(LONGITUDE));
    Dimensions dimesnsions = new Dimensions(jo.getJSONObject(DIMENSIONS).getDouble(WIDTH),
        jo.getJSONObject(DIMENSIONS).getDouble(HEIGHT));
    building = new Building.Builder().identifier(jo.getString(BUILDING_IDENTIFIER)).address(jo.getString(ADDRESS))
            .rotation(Angle.fromRadians(jo.getDouble(ROTATION)))
            .updatedAt(dateFormat.parse(jo.getString(UPDATED_AT)))
            .createdAt(dateFormat.parse(jo.getString(CREATED_AT)))
            .customFields(jsonObjectToMapString(jo.getJSONObject(CUSTOM_FIELDS)))
        .name(jo.getString(BUILDING_NAME)).userIdentifier(jo.getString(USER_IDENTIFIER)).center(center)
        .dimensions(dimesnsions).infoHtml(jo.getString(INFO_HTML)).build();
    return building;
  }

static JSONArray arrayFromFloors(Collection<Floor> floors) throws JSONException {
	JSONArray jsonaFloors = new JSONArray();

    for (Floor floor : floors) {
        JSONObject jsonoFloor = SitumMapper.floorToJsonObject(floor);
        jsonaFloors.put(jsonoFloor);
    }
	return jsonaFloors;
  }

static JSONArray arrayFromPois(Collection<Poi> pois) throws JSONException {
	JSONArray jsonaPois = new JSONArray();

    for (Poi poi : pois) {
		JSONObject jsonoPoi = SitumMapper.poiToJsonObject(poi);
        jsonaPois.put(jsonoPoi);
    }
	return jsonaPois;
}

static JSONObject realtimeDataToJson(RealTimeData realtimeData) throws JSONException {
	JSONObject jsonObject = new JSONObject();

	JSONArray jarrayLocations = new JSONArray();

	for (Location location : realtimeData.getLocations()) {
		JSONObject jsonLocation = SitumMapper.locationToJsonObject(location);
		jarrayLocations.put(jsonLocation);
	}

	jsonObject.put(LOCATIONS, jarrayLocations);

	return jsonObject;
}

static JSONArray arrayFromEvents(Collection<SitumEvent> situmEvents) throws JSONException {
	JSONArray array = new JSONArray();
    for (SitumEvent situmEvent : situmEvents) {
    	JSONObject jsonoSitumEvent = situmEventToJsonObject(situmEvent);
        array.put(jsonoSitumEvent);
	}
	return array;
}

  // Building Info
static JSONObject buildingInfoToJsonObject(BuildingInfo buildingInfo) throws JSONException {
    JSONObject jo = new JSONObject();

	// Parse Building
  jo.put(BUILDING, buildingToJsonObject(buildingInfo.getBuilding()));
	// Parse Floors
  jo.put(FLOORS, arrayFromFloors(buildingInfo.getFloors()));
	// Parse Indoor Pois
	jo.put(INDOOR_POIS, arrayFromPois(buildingInfo.getIndoorPOIs()));
	// Parse Outdoor Pois
	jo.put(OUTDOOR_POIS, arrayFromPois(buildingInfo.getOutdoorPOIs()));
	// Events
	jo.put(EVENTS, arrayFromEvents(buildingInfo.getEvents()));
	// fetch geofences

    return jo;
  }

  // Geofence
  static JSONObject geofenceToJsonObject(Geofence geofence) throws JSONException {
    JSONObject jo = new JSONObject();
    jo.put(NAME, geofence.getName());
    jo.put(CODE, geofence.getCode());
    jo.put(INFO_HTML, geofence.getInfoHtml());
    jo.put(BUILDING_IDENTIFIER, geofence.getBuildingIdentifier());
    jo.put(FLOOR_IDENTIFIER, geofence.getFloorIdentifier());

    // polygonPoints
    jo.put(POLYGON_POINTS, jsonPointsFromPoints(geofence.getPolygonPoints()));

    jo.put(IDENTIFIER, geofence.getIdentifier());
    jo.put(CUSTOM_FIELDS, mapStringToJsonObject(geofence.getCustomFields()));
    jo.put(CREATED_AT, dateFormat.format(geofence.getCreatedAt()));
    jo.put(UPDATED_AT, dateFormat.format(geofence.getUpdatedAt()));

    return jo;
  }

   static JSONArray parseGeofencesToJsonArray(List<Geofence> geofences) throws JSONException {
    JSONArray jsonaGeofences = new JSONArray();
    for (Geofence geofence : geofences) {
      JSONObject jsonoGeofence = geofenceToJsonObject(geofence);
      jsonaGeofences.put(jsonoGeofence);
    }
    return jsonaGeofences;
  }

  static JSONArray jsonPointsFromPoints(List<Point> points) throws JSONException {
    JSONArray pointsJsonArray = new JSONArray();
     for (Point point : points) {
       pointsJsonArray.put(pointToJsonObject(point));
     }
     return pointsJsonArray;
  }

  // Floor

  static JSONObject floorToJsonObject(Floor floor) throws JSONException {
    JSONObject jo = new JSONObject();
    jo.put(ALTITUDE, floor.getAltitude());
    jo.put(BUILDING_IDENTIFIER, floor.getBuildingIdentifier());
    jo.put(LEVEL, floor.getLevel());
    // Include floor here
    jo.put(FLOOR, floor.getFloor());
    jo.put(NAME, floor.getName());
    jo.put(MAP_URL, floor.getMapUrl().getValue());
    jo.put(SCALE, floor.getScale());
    jo.put(FLOOR_IDENTIFIER, floor.getIdentifier());
    jo.put(IDENTIFIER, floor.getIdentifier());
    jo.put(CUSTOM_FIELDS, mapStringToJsonObject(floor.getCustomFields()));
    jo.put(CREATED_AT, dateFormat.format(floor.getCreatedAt()));
    jo.put(UPDATED_AT, dateFormat.format(floor.getUpdatedAt()));
    return jo;
  }

  static Floor floorJsonObjectToFloor(JSONObject jo) throws JSONException {
    Floor floor = null;
    floor = new Floor.Builder()
            .buildingIdentifier(jo.getString(BUILDING_IDENTIFIER))
            .altitude(jo.getDouble(ALTITUDE))
            .customFields(jsonObjectToMapString(jo.getJSONObject(CUSTOM_FIELDS)))
            .name(jo.getString(NAME))
            .level(jo.getInt(LEVEL))
            .floor(jo.getInt(FLOOR))
            .name(jo.getString(NAME))
            .mapUrl(new URL(jo.getString(MAP_URL)))
            .scale(jo.getDouble(SCALE)).build();

    return floor;
  }

  // Situm Events

  static JSONObject situmEventToJsonObject(SitumEvent situmEvent) throws JSONException {
    JSONObject jo = new JSONObject();
    jo.put(BUILDING_IDENTIFIER, situmEvent.getBuildingId());
    jo.put(IDENTIFIER, situmEvent.getId());
    jo.put(FLOOR_IDENTIFIER, situmEvent.getFloor_id());
    jo.put(INFO_HTML, situmEvent.getHtml());
    jo.put(CONVERSION_AREA, conversionAreaToJsonObject(situmEvent.getConversionArea()));
    jo.put(CUSTOM_FIELDS, mapStringToJsonObject(situmEvent.getCustomFields()));
    jo.put(RADIUS, situmEvent.getRadius());
    jo.put(NAME, situmEvent.getName());
    jo.put(X, situmEvent.getX());
    jo.put(Y, situmEvent.getY());
    if (situmEvent.getConversion() != null) {
      jo.put(CONVERSION, circleToJsonObject(situmEvent.getConversion()));
    }
    jo.put(TRIGGER, circleToJsonObject(situmEvent.getTrigger()));

    return jo;
  }

  static  JSONObject circleToJsonObject(Circle circle) throws JSONException {
    JSONObject jo = new JSONObject();
    jo.put(CENTER, circle != null ? pointToJsonObject(circle.getCenter()) : null);
    jo.put(RADIUS, circle != null ? circle.getRadius(): null);
    return jo;
  }

  static JSONObject conversionAreaToJsonObject(SitumConversionArea situmCA) throws JSONException {
    JSONObject jo = new JSONObject();
    jo.put(FLOOR_IDENTIFIER, situmCA.getFloor_id());
    jo.put(TOP_LEFT, convertPoint2fToJsonObject(situmCA.getTopLeft()));
    jo.put(TOP_RIGHT, convertPoint2fToJsonObject(situmCA.getTopRight()));
    jo.put(BOTTOM_LEFT, convertPoint2fToJsonObject(situmCA.getBottomLeft()));
    jo.put(BOTTOM_RIGHT, convertPoint2fToJsonObject(situmCA.getBottomRight()));
    return jo;
  }

  static JSONObject convertPoint2fToJsonObject(Point2f point) throws JSONException{
    JSONObject jo = new JSONObject();
    jo.put(X, point.getX());
    jo.put(Y, point.getY());
    return jo;
  }

  // POI

  static JSONObject poiToJsonObject(Poi poi) throws JSONException {
    JSONObject jo = new JSONObject();
    jo.put(IDENTIFIER, poi.getIdentifier());
    jo.put(BUILDING_IDENTIFIER, poi.getBuildingIdentifier());
    jo.put(CARTESIAN_COORDINATE, cartesianCoordinateToJsonObject(poi.getCartesianCoordinate()));
    jo.put(COORDINATE, coordinateToJsonObject(poi.getCoordinate()));
    jo.put(FLOOR_IDENTIFIER, poi.getFloorIdentifier());
    jo.put(POI_NAME, poi.getName());
    jo.put(POSITION, pointToJsonObject(poi.getPosition()));
    jo.put(IS_INDOOR, poi.isIndoor());
    jo.put(IS_OUTDOOR, poi.isOutdoor());
    jo.put(POI_CATEGORY, poi.getCategory().getCode());
    jo.put(INFO_HTML, poi.getInfoHtml());
    jo.put(CUSTOM_FIELDS, mapStringToJsonObject(poi.getCustomFields()));
    jo.put(CREATED_AT, dateFormat.format(poi.getCreatedAt()));
    jo.put(UPDATED_AT, dateFormat.format(poi.getUpdatedAt()));
    return jo;
  }

  static JSONObject poiCategoryToJsonObject(PoiCategory poiCategory) throws JSONException {
    JSONObject jo = new JSONObject();
    jo.put(POI_CATEGORY_CODE, poiCategory.getCode());
    jo.put(POI_CATEGORY_NAME, poiCategory.getName());
    jo.put(POI_CATEGORY_ICON_SELECTED, poiCategory.getSelectedIconUrl().getValue());
    jo.put(POI_CATEGORY_ICON_UNSELECTED, poiCategory.getUnselectedIconUrl().getValue());
    jo.put(IS_PUBLIC, poiCategory.isPublic());
    return jo;
  }

  static PoiCategory poiCategoryFromJsonObject(JSONObject jo) throws JSONException {
    PoiCategory category = null;
    category = new PoiCategory.Builder()
        .code(jo.getString(POI_CATEGORY_CODE))
        .name(new I18nString.Builder(jo.getString(POI_CATEGORY_NAME)).build())
        .isPublic(jo.getBoolean(IS_PUBLIC))
        .selectedIcon(new URL(jo.getString(POI_CATEGORY_ICON_SELECTED)))
        .unselectedIcon(new URL(jo.getString(POI_CATEGORY_ICON_UNSELECTED)))
        .build();
    return category;
  }

  // Location

  static JSONObject locationToJsonObject(Location location) throws JSONException {
    JSONObject jo = new JSONObject();
    jo.put(ACCURACY, location.getAccuracy());
    jo.put(BEARING, angleToJsonObject(location.getBearing()));
    jo.put(BEARING_QUALITY, location.getBearingQuality().toString());
    jo.put(BUILDING_IDENTIFIER, location.getBuildingIdentifier());
    jo.put(CARTESIAN_BEARING, angleToJsonObject(location.getCartesianBearing()));
    jo.put(CARTESIAN_COORDINATE, cartesianCoordinateToJsonObject(location.getCartesianCoordinate()));
    jo.put(COORDINATE, coordinateToJsonObject(location.getCoordinate()));
    jo.put(FLOOR_IDENTIFIER, location.getFloorIdentifier());
    jo.put(POSITION, pointToJsonObject(location.getPosition()));
    jo.put(PROVIDER, location.getProvider());
    jo.put(QUALITY, location.getQuality().toString());
    jo.put(HAS_BEARING, location.hasBearing());
    jo.put(TIMESTAMP, location.getTime());
    jo.put(HAS_CARTESIAN_BEARING, location.hasCartesianBearing());
    jo.put(IS_INDOOR, location.isIndoor());
    jo.put(IS_OUTDOOR, location.isOutdoor());
    jo.put(DEVICE_ID, location.getDeviceId());
    return jo;
  }

  static JSONObject locationStatusToJsonObject(LocationStatus locationStatus) throws JSONException {
    JSONObject jo = new JSONObject();
    jo.put(STATUS_NAME, locationStatus.name());
    jo.put(STATUS_ORDINAL, locationStatus.ordinal());
    return jo;
  }

  static Point jsonPointToPoint(JSONObject jo) throws JSONException {
    Point point = null;

    Coordinate coordinate = coordinateJsonObjectToCoordinate(jo.getJSONObject(COORDINATE));
    String buildingIdentifier = jo.getString(BUILDING_IDENTIFIER);

    if (jo.getBoolean(IS_INDOOR) == true) {
      point = new Point(buildingIdentifier, jo.getString(FLOOR_IDENTIFIER), coordinate,
          cartesianCoordinateJsonObjectToCartesianCoordinate(jo.getJSONObject(CARTESIAN_COORDINATE)));
    } else {
      point = new Point(buildingIdentifier, coordinate);
    }

    return point;
  }

  static Location jsonLocationObjectToLocation(JSONObject jo) throws JSONException {
    Location.Builder builder = new Location.Builder(jo.getLong(TIMESTAMP), jo.getString(PROVIDER),
        jsonPointToPoint(jo.getJSONObject(POSITION)), (float) jo.getDouble(ACCURACY));
    builder.deviceId(jo.getString(DEVICE_ID));

    // Check if is indoor (insert cartesian bearing and other properties)
    Angle bearingAngle = angleJSONObjectToAngle(jo.getJSONObject(BEARING));
    Quality bearingQuality = qualityJSONObjectToQuality(jo.getString(BEARING_QUALITY));

    if (jo.getBoolean(IS_INDOOR) == true) {
      // Insert cartesian properties
      builder.cartesianBearing(angleJSONObjectToAngle(jo.getJSONObject(CARTESIAN_BEARING)), bearingAngle,
          bearingQuality);
    } else {
      builder.bearing(bearingAngle);
    }

    builder.quality(qualityJSONObjectToQuality(jo.getString(QUALITY)));

    return builder.build(); // Complete this
  }

  static Quality qualityJSONObjectToQuality(String quality) throws JSONException {
    return (quality.equals("HIGH")) ? Quality.HIGH : Quality.LOW;
  }

  static Angle angleJSONObjectToAngle(JSONObject jo) throws JSONException {
    return Angle.fromDegrees(jo.getDouble(DEGREES));
  }

  // Coordinate

  static JSONObject coordinateToJsonObject(Coordinate coordinate) throws JSONException {
    JSONObject jo = new JSONObject();
    jo.put(LATITUDE, coordinate.getLatitude());
    jo.put(LONGITUDE, coordinate.getLongitude());
    return jo;
  }

  static Coordinate coordinateJsonObjectToCoordinate(JSONObject jo) throws JSONException {
    Coordinate coordinate = null;
    coordinate = new Coordinate(jo.getDouble(LATITUDE), jo.getDouble(LONGITUDE));
    return coordinate;
  }

  // Point

  static JSONObject pointToJsonObject(Point point) throws JSONException {
    JSONObject jo = new JSONObject();
    jo.put(BUILDING_IDENTIFIER, point.getBuildingIdentifier());
    jo.put(CARTESIAN_COORDINATE, cartesianCoordinateToJsonObject(point.getCartesianCoordinate()));
    jo.put(COORDINATE, coordinateToJsonObject(point.getCoordinate()));
    jo.put(FLOOR_IDENTIFIER, point.getFloorIdentifier());
    jo.put(IS_INDOOR, point.isIndoor());
    jo.put(IS_OUTDOOR, point.isOutdoor());
    return jo;
  }

  static Point pointJsonObjectToPoint(JSONObject jo, JSONObject joBuilding) throws JSONException, ParseException {
    Building building = null;
    Point point = null;

    if (!jo.has(COORDINATE)) {
      building = buildingJsonObjectToBuilding(joBuilding);
      JSONObject joCartesianCoordinate = jo.getJSONObject(CARTESIAN_COORDINATE);
      CartesianCoordinate cartesianCoordinate = cartesianCoordinateJsonObjectToCartesianCoordinate(
          joCartesianCoordinate);
      CoordinateConverter coordinateConverter = new CoordinateConverter(building.getDimensions(), building.getCenter(),
          building.getRotation());
      Coordinate coordinate = coordinateConverter.toCoordinate(cartesianCoordinate);
      JSONObject joCoordinate = coordinateToJsonObject(coordinate);
      jo.put(COORDINATE, joCoordinate);
    }

    point = new Point(jo.getString(BUILDING_IDENTIFIER), jo.getString(FLOOR_IDENTIFIER),
        coordinateJsonObjectToCoordinate(jo.getJSONObject(COORDINATE)),
        cartesianCoordinateJsonObjectToCartesianCoordinate(jo.getJSONObject(CARTESIAN_COORDINATE)));
    return point;
  }

  // CartesianCoordinate

  static JSONObject cartesianCoordinateToJsonObject(CartesianCoordinate cartesianCoordinate) throws JSONException {
    JSONObject jo = new JSONObject();
    jo.put(X, cartesianCoordinate.getX());
    jo.put(Y, cartesianCoordinate.getY());
    return jo;
  }

  static CartesianCoordinate cartesianCoordinateJsonObjectToCartesianCoordinate(JSONObject jo) throws JSONException {
    CartesianCoordinate cartesianCoordinate = null;
    cartesianCoordinate = new CartesianCoordinate(jo.getDouble(X), jo.getDouble(Y));
    return cartesianCoordinate;
  }

  // Dimensions

  static JSONObject dimensionsToJsonObject(Dimensions dimensions) throws JSONException {
    JSONObject jo = new JSONObject();
    jo.put(WIDTH, dimensions.getWidth());
    jo.put(HEIGHT, dimensions.getHeight());
    return jo;
  }

  // Bounds

  static JSONObject boundsToJsonObject(Bounds bounds) throws JSONException {
    JSONObject jo = new JSONObject();
    jo.put(NORTH_EAST, coordinateToJsonObject(bounds.getNorthEast()));
    jo.put(NORTH_WEST, coordinateToJsonObject(bounds.getNorthWest()));
    jo.put(SOUTH_EAST, coordinateToJsonObject(bounds.getSouthEast()));
    jo.put(SOUTH_WEST, coordinateToJsonObject(bounds.getSouthWest()));
    return jo;
  }

  // Angle

  static JSONObject angleToJsonObject(Angle angle) throws JSONException {
    JSONObject jo = new JSONObject();
    jo.put(DEGREES, angle.degrees());
    jo.put(DEGREES_CLOCKWISE, angle.degreesClockwise());
    jo.put(RADIANS, angle.radians());
    jo.put(RADIANS_MINUS_PI_PI, angle.radiansMinusPiPi());
    return jo;
  }

  // Route

  static JSONObject routeToJsonObject(Route route) throws JSONException {
    return routeToJsonObject(route, null);
  }

  static JSONObject routeToJsonObject(Route route, Context context) throws JSONException {
    return new JSONObject(route.toMap());
  }

  // Indication

  static Indication indicationJsonObjectToIndication(JSONObject jo) throws JSONException {
    Indication indication = null;
    indication = new Indication.Builder().setDistance(jo.getDouble(DISTANCE))
        .setDistanceToNextLevel(jo.getInt(DISTANCE_TO_NEXT_LEVEL))
        .setInstructionType(Indication.Action.valueOf(jo.getString(INDICATION_TYPE)))
        .setOrientation(jo.getDouble(ORIENTATION))
        .setOrientationType(Indication.Orientation.valueOf(jo.getString(ORIENTATION_TYPE)))
        .setStepIdxDestination(jo.getInt(STEP_IDX_DESTINATION)).setStepIdxOrigin(jo.getInt(STEP_IDX_ORIGIN))
        .setNextLevel(jo.getInt(NEXT_LEVEL)).build();
    return indication;
  }

  // NavigationProgress

  static JSONObject navigationProgressToJsonObject(NavigationProgress navigationProgress) throws JSONException {
    return navigationProgressToJsonObject(navigationProgress, null);
  }

  static JSONObject navigationProgressToJsonObject(NavigationProgress navigationProgress, Context context)
      throws JSONException {
    return new JSONObject(navigationProgress.toMap());
  }

  // Utils

  static JSONObject bitmapToString(Bitmap bitmap) throws JSONException {
    JSONObject jo = new JSONObject();
    String encodedImage;
    ByteArrayOutputStream byteArrayOS = new ByteArrayOutputStream();
    bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOS);
    encodedImage = Base64.encodeToString(byteArrayOS.toByteArray(), Base64.DEFAULT);
    jo.put("data", encodedImage);
    return jo;
  }

  static RealTimeRequest jsonObjectRealtimeRequest(JSONObject object) throws JSONException, ParseException {
	RealTimeRequest.Builder builder = new RealTimeRequest.Builder();

	if (object.has(BUILDING)) {
		builder.building(buildingJsonObjectToBuilding(object.getJSONObject(BUILDING)));
	}

	if (object.has(POLL_TIME)) {
		Integer poll_interval = object.getInt(SitumMapper.POLL_TIME);

		if (poll_interval != null) {
			builder.pollTimeMs(poll_interval);
		}
	}
	return builder.build();
  }

  static LocationRequest locationRequestJSONObjectToLocationRequest(JSONArray args) throws JSONException {
    LocationRequest.Builder locationBuilder = new LocationRequest.Builder();

    // Remote configuration, no params
    if (args.length() == 0) {
      return locationBuilder.build();
    }

    JSONObject request = args.getJSONObject(0);
    if (request.has(SitumMapper.FOREGROUND_SERVICE_NOTIFICATION_OPTIONS)) {
      JSONObject notificationOptions = request.getJSONObject(SitumMapper.FOREGROUND_SERVICE_NOTIFICATION_OPTIONS);
      ForegroundServiceNotificationOptions notificationConfig = ForegroundServiceNotificationOptions.fromMap(toMap(notificationOptions));
      locationBuilder.foregroundServiceNotificationOptions(notificationConfig);
    }

    if (request.has(SitumMapper.BUILDING_IDENTIFIER)) {
      String buildingIdentifier = String.valueOf(request.get(SitumMapper.BUILDING_IDENTIFIER));
      if (buildingIdentifier.isEmpty()) {
        buildingIdentifier = "-1";
      }
      locationBuilder.buildingIdentifier(buildingIdentifier);
      Log.i(TAG, "buildingIdentifier: " + buildingIdentifier);
    }

    if (request.has(SitumMapper.INTERVAL)) {
      Integer interval = request.getInt(SitumMapper.INTERVAL);
      if (interval != null) {
        locationBuilder.interval(interval);
        Log.i(TAG, "interval: " + interval);
      }
    }

    if (request.has(SitumMapper.INDOOR_PROVIDER)) {
      String indoorProvider = request.getString(SitumMapper.INDOOR_PROVIDER);
      if (indoorProvider != null && !indoorProvider.isEmpty()) {
        if (indoorProvider.equals(LocationRequest.IndoorProvider.SUPPORT.name())) {
          locationBuilder.indoorProvider(LocationRequest.IndoorProvider.SUPPORT);
        } else {
          locationBuilder.indoorProvider(LocationRequest.IndoorProvider.INPHONE);
        }
        Log.i(TAG, "indoorProvider: " + indoorProvider);
      }
    }

    if (request.has(SitumMapper.USE_BLE)) {
      Boolean useBle = request.getBoolean(SitumMapper.USE_BLE);
      locationBuilder.useBle(useBle);
      Log.i(TAG, "useBle: " + useBle);
    }

    if (request.has(SitumMapper.USE_WIFI)) {
      Boolean useWifi = request.getBoolean(SitumMapper.USE_WIFI);
      locationBuilder.useWifi(useWifi);
      Log.i(TAG, "useWifi: " + useWifi);
    }

    if (request.has(SitumMapper.USE_GPS)) {
      Boolean useGps = request.getBoolean(SitumMapper.USE_GPS);
      locationBuilder.useGps(useGps);
      Log.i(TAG, "useGps: " + useGps);
    }

    if (request.has(SitumMapper.USE_BAROMETER)) {
      Boolean useBarometer = request.getBoolean(SitumMapper.USE_BAROMETER);
      locationBuilder.useBarometer(useBarometer);
      Log.i(TAG, "useBarometer: " + useBarometer);
    }

    if (request.has(SitumMapper.AUTO_ENABLE_BLE)) {
      Boolean autoEnableBleDuringPositioning = request.getBoolean(SitumMapper.AUTO_ENABLE_BLE);
      locationBuilder.autoEnableBleDuringPositioning(autoEnableBleDuringPositioning);
      Log.i(TAG, "autoEnableBleDuringPositioning: " + autoEnableBleDuringPositioning);
    }

    if (request.has(SitumMapper.MOTION_MODE)) {
      String motionMode = request.getString(SitumMapper.MOTION_MODE);
      if (motionMode != null) {
        if (motionMode.equals(LocationRequest.MotionMode.BY_FOOT.name())) {
          locationBuilder.motionMode(LocationRequest.MotionMode.BY_FOOT);
        } else if (motionMode.equals(LocationRequest.MotionMode.BY_CAR.name())) {
          locationBuilder.motionMode(LocationRequest.MotionMode.BY_CAR);
        }
        Log.i(TAG, "motionMode: " + motionMode);
      }
    }

    if (request.has(SitumMapper.USE_FOREGROUND_SERVICE)) {
      Boolean useForegroundService = request.getBoolean(SitumMapper.USE_FOREGROUND_SERVICE);
      locationBuilder.useForegroundService(useForegroundService);
      Log.i(TAG, "useForegroundService: " + useForegroundService);
    }

    if (request.has(SitumMapper.USE_DEAD_RECKONING)) {
      Boolean useDeadReckoning = request.getBoolean(SitumMapper.USE_DEAD_RECKONING);
      locationBuilder.useDeadReckoning(useDeadReckoning);
      Log.i(TAG, "useDeadReckoning: " + useDeadReckoning);
    }

    if (request.has(SitumMapper.OUTDOOR_LOCATION_OPTIONS)) {
      JSONObject outdoorLocationOptions = request.getJSONObject(SitumMapper.OUTDOOR_LOCATION_OPTIONS);
      if (outdoorLocationOptions != null) {
        locationBuilder.outdoorLocationOptions(buildOutdoorLocationOptions(outdoorLocationOptions));
      }
    }

    if (request.has(SitumMapper.BEACON_FILTERS)) {
      JSONArray beaconFilters = request.getJSONArray(SitumMapper.BEACON_FILTERS);
      List<BeaconFilter> filtersList = new ArrayList<BeaconFilter>();
      for (int i = 0; i < beaconFilters.length(); i++) {
        JSONObject beaconFilter = beaconFilters.getJSONObject(i);
        if (beaconFilter.has(SitumMapper.UUID)) {
          String uuid = beaconFilter.getString(SitumMapper.UUID);
          if (uuid != null && !uuid.isEmpty()) {
            BeaconFilter.Builder builder = new BeaconFilter.Builder().uuid(uuid);
            filtersList.add(builder.build());
            Log.i(TAG, "beaconFilter: " + uuid);
          }
        }
      }
      locationBuilder.addBeaconFilters(filtersList);
    }

    if (request.has(SitumMapper.SMALLEST_DISPLACEMENT)) {
      Float smallestDisplacement = new Float(request.getDouble(SitumMapper.SMALLEST_DISPLACEMENT));
      if (smallestDisplacement != null && smallestDisplacement > 0) {
        locationBuilder.smallestDisplacement(smallestDisplacement);
        Log.i(TAG, "smallestDisplacement: " + smallestDisplacement);
      }
    }

    if (request.has(SitumMapper.REALTIME_UPDATE_INTERVAL) &&
      request.get(SitumMapper.REALTIME_UPDATE_INTERVAL) instanceof String) {
      String realtimeUpdateInterval = request.getString(SitumMapper.REALTIME_UPDATE_INTERVAL);
      if (realtimeUpdateInterval != null) {
        if (realtimeUpdateInterval.equals(LocationRequest.RealtimeUpdateInterval.REALTIME.name())) {
          locationBuilder.realtimeUpdateInterval(LocationRequest.RealtimeUpdateInterval.REALTIME);
        } else if (realtimeUpdateInterval.equals(LocationRequest.RealtimeUpdateInterval.FAST.name())) {
          locationBuilder.realtimeUpdateInterval(LocationRequest.RealtimeUpdateInterval.FAST);
        } else if (realtimeUpdateInterval.equals(LocationRequest.RealtimeUpdateInterval.NORMAL.name())) {
          locationBuilder.realtimeUpdateInterval(LocationRequest.RealtimeUpdateInterval.NORMAL);
        } else if (realtimeUpdateInterval.equals(LocationRequest.RealtimeUpdateInterval.SLOW.name())) {
          locationBuilder.realtimeUpdateInterval(LocationRequest.RealtimeUpdateInterval.SLOW);
        } else if (realtimeUpdateInterval.equals(LocationRequest.RealtimeUpdateInterval.BATTERY_SAVER.name())) {
          locationBuilder.realtimeUpdateInterval(LocationRequest.RealtimeUpdateInterval.BATTERY_SAVER);
        }
        Log.i(TAG, "realtimeUpdateInterval: " + realtimeUpdateInterval);
      }
    }

    return locationBuilder.build();
  }

  static OutdoorLocationOptions buildOutdoorLocationOptions(JSONObject outdoorLocationOptions) throws JSONException{
    OutdoorLocationOptions.Builder optionsBuilder = new OutdoorLocationOptions.Builder();

    if (outdoorLocationOptions.has(SitumMapper.USER_DEFINED_THRESHOLD)) {
      Boolean userDefinedThreshold = outdoorLocationOptions.getBoolean(SitumMapper.USER_DEFINED_THRESHOLD);
      optionsBuilder.userDefinedThreshold(userDefinedThreshold);
      Log.i(TAG, "userDefinedThreshold: " + userDefinedThreshold);
    }

    if (outdoorLocationOptions.has(SitumMapper.COMPUTE_INTERVAL)) {
      Integer computeInterval = outdoorLocationOptions.getInt(SitumMapper.COMPUTE_INTERVAL);
      if (computeInterval != null && computeInterval >= 1) {
        optionsBuilder.computeInterval(computeInterval);
        Log.i(TAG, "computeInterval: " + computeInterval);
      }
    }

    if (outdoorLocationOptions.has(SitumMapper.AVERAGE_SNR_THRESHOLD));
    Float averageSnrThreshold = new Float(outdoorLocationOptions.getDouble(SitumMapper.AVERAGE_SNR_THRESHOLD));
    if (averageSnrThreshold != null && averageSnrThreshold >= MIN_SNR && averageSnrThreshold <= MAX_SNR) {
      optionsBuilder.averageSnrThreshold(averageSnrThreshold);
      Log.i(TAG, "averageSnrThreshold: " + averageSnrThreshold);
    }
    return optionsBuilder.build();
  }

  static DirectionsRequest jsonObjectToDirectionsRequest(JSONObject joBuilding, JSONObject joFrom,
                                                         JSONObject joTo, JSONObject joOptions) throws JSONException, ParseException {
    Point from = SitumMapper.pointJsonObjectToPoint(joFrom, joBuilding);
    Point to = SitumMapper.pointJsonObjectToPoint(joTo, joBuilding);
    DirectionsRequest.AccessibilityMode accessibilityMode = DirectionsRequest.AccessibilityMode.CHOOSE_SHORTEST;
    Boolean minimizeFloorChanges = false;
    double startingAngle = 0.0;
    List<String> includedTags = null;
    List<String> excludedTags = null;

    if ( joOptions != null) {
      if (joOptions.has(SitumMapper.INCLUDED_TAGS) && joOptions.get(SitumMapper.INCLUDED_TAGS) != null) {
        includedTags = new ArrayList<String>();
        JSONArray jsonArray = joOptions.getJSONArray(SitumMapper.INCLUDED_TAGS);
        for (int i = 0; i < jsonArray.length(); i++) {
          includedTags.add(jsonArray.get(i).toString());
        }
      }

      if (joOptions.has(SitumMapper.EXCLUDED_TAGS) && joOptions.get(SitumMapper.EXCLUDED_TAGS) != null) {
        excludedTags = new ArrayList<String>();
        JSONArray jsonArray = joOptions.getJSONArray(SitumMapper.EXCLUDED_TAGS);
        for (int i = 0; i < jsonArray.length(); i++) {
          excludedTags.add(jsonArray.get(i).toString());
        }
      }
      if (joOptions.has(SitumMapper.ACCESSIBILITY_MODE)) {
        String mode = joOptions.getString(SitumMapper.ACCESSIBILITY_MODE);
        if (mode.equals(DirectionsRequest.AccessibilityMode.ONLY_ACCESSIBLE.name())) {
          accessibilityMode = DirectionsRequest.AccessibilityMode.ONLY_ACCESSIBLE;
        } else if (mode.equals(DirectionsRequest.AccessibilityMode.ONLY_NOT_ACCESSIBLE_FLOOR_CHANGES.name())) {
          accessibilityMode = DirectionsRequest.AccessibilityMode.ONLY_NOT_ACCESSIBLE_FLOOR_CHANGES;
        }
      } else if (joOptions.has(SitumMapper.ACCESSIBLE) && joOptions.getBoolean(SitumMapper.ACCESSIBLE)) {
        accessibilityMode = DirectionsRequest.AccessibilityMode.ONLY_ACCESSIBLE;
      } else if (joOptions.has(SitumMapper.ACCESSIBLE_ROUTE) && joOptions.getBoolean(SitumMapper.ACCESSIBLE_ROUTE)) {
        accessibilityMode = DirectionsRequest.AccessibilityMode.ONLY_ACCESSIBLE;
      }
      if (joOptions.has(SitumMapper.STARTING_ANGLE)) {
        startingAngle = joOptions.getDouble(SitumMapper.STARTING_ANGLE);
      }
      if (joOptions.has(SitumMapper.MINIMIZE_FLOOR_CHANGES)) {
        minimizeFloorChanges = joOptions.getBoolean(SitumMapper.MINIMIZE_FLOOR_CHANGES);
      }
    }
    return new DirectionsRequest.Builder().from(from, Angle.fromDegrees(startingAngle)).to(to).accessibilityMode(accessibilityMode).minimizeFloorChanges(minimizeFloorChanges).includedTags(includedTags).excludedTags(excludedTags).build();
  }

  static UserHelperColorScheme jsonObjectToUserHelperColorScheme(JSONObject args) throws JSONException {
    UserHelperColorScheme.Builder builder = new UserHelperColorScheme.Builder();

    if (args.has("primaryColor")) {
      String primaryColor = args.getString("primaryColor");
      if (primaryColor != null) {
        builder.setPrimaryColor(primaryColor);
      }
    }

    if (args.has("secondaryColor")) {
      String secondaryColor = args.getString("secondaryColor");
      if (secondaryColor != null) {
        builder.setSecondaryColor(secondaryColor);
      }
    }

    return builder.build();
  }
}
