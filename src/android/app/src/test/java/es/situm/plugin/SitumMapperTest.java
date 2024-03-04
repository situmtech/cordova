package es.situm.plugin;


import com.google.common.truth.Truth;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.text.DateFormat;
import java.text.ParsePosition;

import es.situm.plugin.angle.AngleCreator;
import es.situm.plugin.bounds.BoundsCreator;
import es.situm.plugin.building.BuildingCreator;
import es.situm.plugin.cartesianCoordinate.CartesianCoordinateCreator;
import es.situm.plugin.circle.CircleCreator;
import es.situm.plugin.coordinate.CoordinateCreator;
import es.situm.plugin.dimensions.DimensionsCreator;
import es.situm.plugin.directionsRequest.DirectionsRequestCreator;
import es.situm.plugin.event.EventCreator;
import es.situm.plugin.floor.FloorCreator;
import es.situm.plugin.indication.IndicationCreator;
import es.situm.plugin.location.LocationCreator;
import es.situm.plugin.locationRequest.LocationRequestCreator;
import es.situm.plugin.locationStatus.LocationStatusCreator;
import es.situm.plugin.navigationProgress.NavigationProgressCreator;
import es.situm.plugin.poi.PoiCreator;
import es.situm.plugin.poiCategory.PoiCategoryCreator;
import es.situm.plugin.point.PointCreator;
import es.situm.plugin.route.RouteCreator;
import es.situm.plugin.routeStep.RouteStepCreator;
import es.situm.plugin.situmConversionArea.SitumConversionAreaCreator;
import es.situm.sdk.directions.DirectionsRequest;
import es.situm.sdk.location.LocationRequest;
import es.situm.sdk.location.LocationStatus;
import es.situm.sdk.model.cartography.Building;
import es.situm.sdk.model.cartography.Circle;
import es.situm.sdk.model.cartography.Floor;
import es.situm.sdk.model.cartography.Poi;
import es.situm.sdk.model.cartography.PoiCategory;
import es.situm.sdk.model.cartography.Point;
import es.situm.sdk.model.directions.Indication;
import es.situm.sdk.model.directions.Route;
import es.situm.sdk.model.directions.RouteStep;
import es.situm.sdk.model.location.Angle;
import es.situm.sdk.model.location.Bounds;
import es.situm.sdk.model.location.CartesianCoordinate;
import es.situm.sdk.model.location.Coordinate;
import es.situm.sdk.model.location.Dimensions;
import es.situm.sdk.model.location.Location;
import es.situm.sdk.model.navigation.NavigationProgress;
import es.situm.sdk.v1.SitumConversionArea;
import es.situm.sdk.v1.SitumEvent;

import static com.google.common.truth.Truth.assertThat;
import static es.situm.plugin.SitumMapper.ADDRESS;
import static es.situm.plugin.SitumMapper.BOUNDS;
import static es.situm.plugin.SitumMapper.BOUNDS_ROTATED;
import static es.situm.plugin.SitumMapper.CENTER;
import static es.situm.plugin.SitumMapper.DIMENSIONS;
import static es.situm.plugin.SitumMapper.PICTURE_THUMB_URL;
import static es.situm.plugin.SitumMapper.PICTURE_URL;
import static es.situm.plugin.SitumMapper.POI_NAME;
import static es.situm.plugin.SitumMapper.ROTATION;
import static es.situm.plugin.SitumMapper.SEGMENTS;
import static es.situm.plugin.SitumMapper.USER_IDENTIFIER;
import static es.situm.plugin.SitumMapper.circleToJsonObject;
import static es.situm.plugin.SitumMapper.conversionAreaToJsonObject;

import android.util.Log;

@RunWith(JUnit4.class)
public class SitumMapperTest {

    private DateFormat dateFormat = DateUtils.dateFormat;

    // Properties names
    private static final String RADIANS_MINUS_PI_PI = "radiansMinusPiPi";
    private static final String RADIANS = "radians";
    private static final String DEGREES_CLOCKWISE = "degreesClockwise";
    private static final String DEGREES = "degrees";
    private static final String NORTH_WEST = "northWest";
    private static final String SOUTH_WEST = "southWest";
    private static final String NORTH_EAST = "northEast";
    private static final String SOUTH_EAST = "southEast";
    private static final String LATITUDE = "latitude";
    private static final String LONGITUDE = "longitude";
    private static final String X = "x";
    private static final String Y = "y";
    private static final String WIDTH = "width";
    private static final String HEIGHT = "height";
    private static final String ALTITUDE = "altitude";
    private static final String CREATED_AT = "createdAt";
    private static final String FLOOR_IDENTIFIER = "floorIdentifier";
    private static final String LEVEL = "level";
    private static final String CUSTOM_FIELDS = "customFields";
    private static final String MAP_URL = "mapUrl";
    private static final String SCALE = "scale";
    private static final String BUILDING_IDENTIFIER = "buildingIdentifier";
    private static final String UPDATED_AT = "updatedAt";
    private static final String ORIENTATION_TYPE = "orientationType";
    private static final String ORIENTATION = "orientation";
    private static final String STEP_IDX_DESTINTATION = "stepIdxDestination";
    private static final String DISTANCE = "distance";
    private static final String STEP_IDX_ORIGIN = "stepIdxOrigin";
    private static final String INDICATION_TYPE = "indicationType";
    private static final String DISTANCE_TO_NEXT_LEVEL = "distanceToNextLevel";
    private static final String NEEDED_LEVEL_CHANGE = "neededLevelChange";
    private static final String HAS_CARTESIAN_BEARING = "hasCartesianBearing";
    private static final String COORDINATE = "coordinate";
    private static final String BEARING = "bearing";
    private static final String ACCURACY = "accuracy";
    private static final String IS_INDOOR = "isIndoor";
    private static final String DEVICE_ID = "deviceId";
    private static final String QUALITY = "quality";
    private static final String IS_OUTDOOR = "isOutdoor";
    private static final String PROVIDER = "provider";
    private static final String CARTESIAN_BEARING = "cartesianBearing";
    private static final String BEARING_QUALITY = "bearingQuality";
    private static final String HAS_BEARING = "hasBearing";
    private static final String CARTESIAN_COORDINATE = "cartesianCoordinate";
    private static final String POSITION = "position";
    private static final String TIMESTAMP = "timestamp";
    private static final String STATUS_ORDINAL = "statusOrdinal";
    private static final String STATUS_NAME = "statusName";
    private static final String ICON_SELECTED = "icon_selected";
    private static final String ICON_UNSELECTED = "icon_unselected";
    private static final String PUBLIC = "public";
    private static final String POI_CATEGORY_NAME = "poiCategoryName";
    private static final String POI_CATEGORY_CODE = "poiCategoryCode";
    private static final String BOTTOM_LEFT = "bottomLeft";
    private static final String BOTTOM_RIGHT = "bottomRight";
    private static final String TOP_LEFT = "topLeft";
    private static final String TOP_RIGHT = "topRight";
    private static final String CURRENT_INDICATION = "currentIndication";
    private static final String NEXT_INDICATION = "nextIndication";
    private static final String DISTANCE_TO_END_STEP = "distanceToEndStep";
    private static final String CLOSEST_POINT_IN_ROUTE = "closestPointInRoute";
    private static final String DISTANCE_TO_CLOSEST_POINT_IN_ROUTE = "distanceToClosestPointInRoute";
    private static final String TIME_TO_END_STEP = "timeToEndStep";
    private static final String ROUTE_STEP = "routeStep";
    private static final String TIME_TO_GOAL = "timeToGoal";
    private static final String CURRENT_STEP_INDEX = "currentStepIndex";
    private static final String DISTANCE_TO_GOAL = "distanceToGoal";
    private static final String IS_FIRST = "isFirst";
    private static final String IS_LAST = "isLast";
    private static final String FROM = "from";
    private static final String TO = "TO";
    private static final String ID = "id";
    private static final String LAST_STEP = "lastStep";
    private static final String FIRST_STEP = "firstStep";
    private static final String INDICATIONS = "indications";
    private static final String NODES = "nodes";
    private static final String EDGES = "edges";
    private static final String STEPS = "steps";
    private static final String POINTS = "points";
    public static final String IDENTIFIER = "identifier";
    public static final String INFO_HTML = "infoHtml";
    public static final String CONVERSION_AREA = "conversionArea";
    public static final String RADIUS = "radius";
    public static final String NAME = "name";
    public static final String CATEGORY = "category";

    // Creators
    private AngleCreator angleCreator = new AngleCreator();
    private BoundsCreator boundsCreator = new BoundsCreator();
    private CartesianCoordinateCreator cartesianCoordinateCreator = new CartesianCoordinateCreator();
    private CoordinateCreator coordinateCreator = new CoordinateCreator();
    private DimensionsCreator dimensionsCreator = new DimensionsCreator();
    private FloorCreator floorCreator = new FloorCreator();
    private IndicationCreator indicationCreator = new IndicationCreator();
    private LocationCreator locationCreator = new LocationCreator();
    private LocationStatusCreator locationStatusCreator = new LocationStatusCreator();
    private PoiCategoryCreator poiCategoryCreator = new PoiCategoryCreator();
    private SitumConversionAreaCreator situmConversionAreaCreator = new SitumConversionAreaCreator();
    private NavigationProgressCreator navigationProgressCreator = new NavigationProgressCreator();
    private RouteStepCreator routeStepCreator = new RouteStepCreator();
    private PointCreator pointCreator = new PointCreator();
    private RouteCreator routeCreator = new RouteCreator();
    private EventCreator eventCreator = new EventCreator();
    private CircleCreator circleCreator = new CircleCreator();
    private PoiCreator poiCreator = new PoiCreator();
    private BuildingCreator buildingCreator = new BuildingCreator();
    private DirectionsRequestCreator directionsRequestCreator = new DirectionsRequestCreator();
    private LocationRequestCreator locationRequestCreator = new LocationRequestCreator();

    @Test
    public void angleTest1() {
        try {
            JSONObject jo = angleCreator.getAngle1();
            Angle angle = SitumMapper.angleJSONObjectToAngle(jo);
            Angle angle1 = angleCreator.createAngleFromDegrees();
            testAngle(angle, angle1);
        } catch (JSONException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void angleTest2() {
        try {
            JSONObject jo = angleCreator.getAngle2();
            Angle angle = SitumMapper.angleJSONObjectToAngle(jo);
            Angle angle1 = angleCreator.createAngleFromRadians();
            testAngle(angle, angle1);
        } catch (JSONException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void angleJSONObjectTest() {
        try {
            Angle angleFromDegrees = angleCreator.createAngleFromDegrees();
            Angle angleFromRadians = angleCreator.createAngleFromRadians();
            JSONObject angleFromDegreesJSONObject = SitumMapper.angleToJsonObject(angleFromDegrees);
            JSONObject angleFromRadiansJSONObject = SitumMapper.angleToJsonObject(angleFromRadians);
            JSONObject angle1 = angleCreator.getAngle1();
            JSONObject angle2 = angleCreator.getAngle2();
            testAngle(angleFromDegreesJSONObject, angle1);
            testAngle(angleFromRadiansJSONObject, angle2);
        } catch (JSONException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void boundsJSONObjectTest() {
        try {
            Bounds bounds = boundsCreator.createBounds();
            Bounds boundsWithArray = boundsCreator.createBoundsWithArray();
            JSONObject boundsJSONObject = SitumMapper.boundsToJsonObject(bounds);
            JSONObject boundsWithArrayJSONObject = SitumMapper.boundsToJsonObject(boundsWithArray);
            JSONObject bounds1 = boundsCreator.getBounds1();
            JSONObject bounds2 = boundsCreator.getBounds2();
            testBounds(boundsJSONObject, bounds1);
            testBounds(boundsWithArrayJSONObject, bounds2);
        } catch (JSONException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void buildingTest() {
        try{
            JSONObject jo = buildingCreator.getBuilding1();
            Building building = SitumMapper.buildingJsonObjectToBuilding(jo);
            Building building1 = buildingCreator.createBuilding1();
            testBuilding(building, building1);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void buildingJSONObjectTest() {
        try {
            Building building = buildingCreator.createBuilding1();
            JSONObject buildingJSONObject = SitumMapper.buildingToJsonObject(building);
            JSONObject building1 = buildingCreator.getBuilding1();
            testBuilding(buildingJSONObject,building1);
        } catch (JSONException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void cartesianCoordinateTest() {
        try {
            JSONObject jo = cartesianCoordinateCreator.getCartesianCoordinate1();
            CartesianCoordinate cartesianCoordinate = SitumMapper.cartesianCoordinateJsonObjectToCartesianCoordinate(jo);
            CartesianCoordinate cartesianCoordinate1 = cartesianCoordinateCreator.createCartesianCoordinate();
            testCartesianCoordinate(cartesianCoordinate,cartesianCoordinate1);
        } catch (JSONException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void cartesianCoordinateJSONObjectTest() {
        try {
            CartesianCoordinate cartesianCoordinate = cartesianCoordinateCreator.createCartesianCoordinate();
            JSONObject cartesianCoordinateJSONObject = SitumMapper.cartesianCoordinateToJsonObject(cartesianCoordinate);
            JSONObject cartesianCoordinate1 = cartesianCoordinateCreator.getCartesianCoordinate1();
            testCartesianCoordinate(cartesianCoordinateJSONObject, cartesianCoordinate1);
        } catch (JSONException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void coordinateTest() {
        try {
            JSONObject jo = coordinateCreator.getCoordinate1();
            Coordinate coordinate = SitumMapper.coordinateJsonObjectToCoordinate(jo);
            Coordinate coordinate1 = coordinateCreator.createCoordinate();
            testCoordinate(coordinate, coordinate1);
        } catch (JSONException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void coordinateJSONObjectTest() {
        try {
            Coordinate coordinate = coordinateCreator.createCoordinate();
            JSONObject coordinateJSONObject = SitumMapper.coordinateToJsonObject(coordinate);
            JSONObject coordinate1 = coordinateCreator.getCoordinate1();
            testCoordinate(coordinateJSONObject, coordinate1);
        } catch (JSONException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void dimensionJSONObjectTest() {
        try {
            Dimensions dimensions = dimensionsCreator.createDimensions();
            JSONObject dimessionsJSONObject = SitumMapper.dimensionsToJsonObject(dimensions);
            JSONObject dimensions1 = dimensionsCreator.getDimensions1();
            testDimensions(dimessionsJSONObject, dimensions1);
        } catch (JSONException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void directionsRequestTest1() {
        try {
            JSONArray jo = directionsRequestCreator.getDirectionsRequest1();
            DirectionsRequest directionsRequest = SitumMapper.jsonObjectToDirectionsRequest(
                    buildingCreator.getBuilding1(), pointCreator.getPoint1(), pointCreator.getPoint2(), jo.getJSONObject(3));
            DirectionsRequest directionsRequest1 = directionsRequestCreator.createDirectionsRequest1();
            testDirectionsRequest(directionsRequest, directionsRequest1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void directionsRequestTest2() {
        try {
            JSONArray jo = directionsRequestCreator.getDirectionsRequest2();
            DirectionsRequest directionsRequest = SitumMapper.jsonObjectToDirectionsRequest(
                    buildingCreator.getBuilding1(), pointCreator.getPoint1(), pointCreator.getPoint2(), jo.getJSONObject(3));
            DirectionsRequest directionsRequest1 = directionsRequestCreator.createDirectionsRequest2();
            testDirectionsRequest(directionsRequest, directionsRequest1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void directionsRequestTest3() {
        try {
            JSONArray jo = directionsRequestCreator.getDirectionsRequest3();
            DirectionsRequest directionsRequest = SitumMapper.jsonObjectToDirectionsRequest(
                    buildingCreator.getBuilding1(), pointCreator.getPoint1(), pointCreator.getPoint2(), jo.getJSONObject(3));
            DirectionsRequest directionsRequest1 = directionsRequestCreator.createDirectionsRequest3();
            testDirectionsRequest(directionsRequest, directionsRequest1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void directionsRequestTest4() {
        try {
            JSONArray jo = directionsRequestCreator.getDirectionsRequest4();
            DirectionsRequest directionsRequest = SitumMapper.jsonObjectToDirectionsRequest(
                    buildingCreator.getBuilding1(), pointCreator.getPoint1(), pointCreator.getPoint2(), jo.getJSONObject(3));
            DirectionsRequest directionsRequest1 = directionsRequestCreator.createDirectionsRequest4();
            testDirectionsRequest(directionsRequest, directionsRequest1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void directionsRequestTest5() {
        try {
            JSONArray jo = directionsRequestCreator.getDirectionsRequest5();
            DirectionsRequest directionsRequest = SitumMapper.jsonObjectToDirectionsRequest(
                    buildingCreator.getBuilding1(), pointCreator.getPoint1(), pointCreator.getPoint2(), jo.getJSONObject(3));
            DirectionsRequest directionsRequest1 = directionsRequestCreator.createDirectionsRequest5();
            testDirectionsRequest(directionsRequest, directionsRequest1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void directionsRequestTest6() {
        try {
            JSONArray jo = directionsRequestCreator.getDirectionsRequest6();
            DirectionsRequest directionsRequest = SitumMapper.jsonObjectToDirectionsRequest(
                    buildingCreator.getBuilding1(), pointCreator.getPoint1(), pointCreator.getPoint2(), jo.getJSONObject(3));
            DirectionsRequest directionsRequest1 = directionsRequestCreator.createDirectionsRequest6();
            testDirectionsRequest(directionsRequest, directionsRequest1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void locationRequestTest1() {
        try {
            JSONArray jo = locationRequestCreator.getLocationRequest1();
            LocationRequest locationRequest = SitumMapper.locationRequestJSONObjectToLocationRequest(jo);
            LocationRequest locationRequest1 = locationRequestCreator.createLocationRequest1();
            testLocationRequest(locationRequest, locationRequest1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void locationRequestTest2() {
        try {
            JSONArray jo = locationRequestCreator.getLocationRequest2();
            LocationRequest locationRequest = SitumMapper.locationRequestJSONObjectToLocationRequest(jo);
            LocationRequest locationRequest2 = locationRequestCreator.createLocationRequest2();
            testLocationRequest(locationRequest, locationRequest2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void floorTest1() {
        try {
            JSONObject jo = floorCreator.getFloor1();
            Floor floor = SitumMapper.floorJsonObjectToFloor(jo);
            Floor floor1 = floorCreator.createFloorWithAltitude();
            testFloor(floor, floor1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void floorTest2() {
        try {
            JSONObject jo = floorCreator.getFloor2();
            Floor floor = SitumMapper.floorJsonObjectToFloor(jo);
            Floor floor1 = floorCreator.createFloorWithoutAltitude();
            testFloor(floor, floor1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void floorJSONObjectTest() {
        try {
            Floor floor = floorCreator.createFloorWithAltitude();
            JSONObject floorJSONObject = SitumMapper.floorToJsonObject(floor);
            JSONObject floor1 = floorCreator.getFloor1();
            testFloor(floorJSONObject, floor1);
            Floor floorWithoutAltitude = floorCreator.createFloorWithoutAltitude();
            JSONObject floorWithoutAltitudeJSONObject = SitumMapper.floorToJsonObject(floorWithoutAltitude);
            JSONObject floor2 = floorCreator.getFloor2();
            testFloor(floorWithoutAltitudeJSONObject, floor2);
        } catch (JSONException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void indicationJSONObjectTest() {
        try {
            Indication indication = indicationCreator.createIndication();
            JSONObject indicationJSONObject = SitumMapper.indicationToJsonObject(indication);
            JSONObject indication1 = indicationCreator.getIndication1();
            testIndication(indicationJSONObject, indication1);
        } catch (JSONException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void locationTest1() {
        try {
            JSONObject jo = locationCreator.getLocation1();
            Location location = SitumMapper.jsonLocationObjectToLocation(jo);
            Location location1 = locationCreator.createLocationWithBuildingFloorAndCartesianCoordinates();
            testLocation(location, location1);
        } catch (JSONException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void locationTest2() {
        try {
            JSONObject jo = locationCreator.getLocation2();
            Location location = SitumMapper.jsonLocationObjectToLocation(jo);
            Location location1 = locationCreator.createLocationWithCoordinate();
            testLocation(location, location1);
        } catch (JSONException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void locationTest3() {
        try {
            JSONObject jo = locationCreator.getLocation3();
            Location location = SitumMapper.jsonLocationObjectToLocation(jo);
            Location location1 = locationCreator.createLocationWithBuildingAndCoordinate();
            testLocation(location, location1);
        } catch (JSONException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void locationTest4() {
        try {
            JSONObject jo = locationCreator.getLocation4();
            Location location = SitumMapper.jsonLocationObjectToLocation(jo);
            Location location1 = locationCreator.locationWithCartesianBearing();
            testLocation(location, location1);
        } catch (JSONException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void locationTest5() {
        try {
            JSONObject jo = locationCreator.getLocation5();
            Location location = SitumMapper.jsonLocationObjectToLocation(jo);
            Location location1 = locationCreator.locationWithoutCartesianBearing();
            testLocation(location, location1);
        } catch (JSONException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void locationTest6() {
        try {
            JSONObject jo = locationCreator.getLocation6();
            Location location = SitumMapper.jsonLocationObjectToLocation(jo);
            Location location1 = locationCreator.locationWithBearing();
            testLocation(location, location1);
        } catch (JSONException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void locationTest7() {
        try {
            JSONObject jo = locationCreator.getLocation7();
            Location location = SitumMapper.jsonLocationObjectToLocation(jo);
            Location location1 = locationCreator.locationWithoutBearing();
            testLocation(location, location1);
        } catch (JSONException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void locationTest8() {
        try {
            JSONObject jo = locationCreator.getLocation8();
            Location location = SitumMapper.jsonLocationObjectToLocation(jo);
            Location location1 = locationCreator.indoorLocationWithIndoorBearingQualityLow();
            testLocation(location, location1);
        } catch (JSONException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void locationTest9() {
        try {
            JSONObject jo = locationCreator.getLocation9();
            Location location = SitumMapper.jsonLocationObjectToLocation(jo);
            Location location1 = locationCreator.indoorLocationWithIndoorBearingQualityHigh();
            testLocation(location, location1);
        } catch (JSONException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void locationTest10() {
        try {
            JSONObject jo = locationCreator.getLocation10();
            Location location = SitumMapper.jsonLocationObjectToLocation(jo);
            Location location1 = locationCreator.outdoorLocation();
            testLocation(location, location1);
        } catch (JSONException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void locationJSONObjectTest() {
        try {
            Location locationWithBuildingFloorAndCartesianCoordinates = locationCreator.createLocationWithBuildingFloorAndCartesianCoordinates();
            JSONObject locationWithBuildingFloorAndCartesianCoordinatesJSONObject = SitumMapper.locationToJsonObject(locationWithBuildingFloorAndCartesianCoordinates);
            JSONObject location1 = locationCreator.getLocation1();
            testLocation(locationWithBuildingFloorAndCartesianCoordinatesJSONObject, location1);
            Location locationWithCoordinate = locationCreator.createLocationWithCoordinate();
            JSONObject locationWithCoordinateJSONObject = SitumMapper.locationToJsonObject(locationWithCoordinate);
            JSONObject location2 = locationCreator.getLocation2();
            testLocation(locationWithCoordinateJSONObject, location2);
            Location locationWithBuildingAndCoordinate = locationCreator.createLocationWithBuildingAndCoordinate();
            JSONObject locationWithBuildingAndCoordinateJSONObject = SitumMapper.locationToJsonObject(locationWithBuildingAndCoordinate);
            JSONObject location3 = locationCreator.getLocation3();
            testLocation(locationWithBuildingAndCoordinateJSONObject, location3);
            Location locationWithCartesianBearing = locationCreator.locationWithCartesianBearing();
            JSONObject locationWithCartesianBearingJSONObject = SitumMapper.locationToJsonObject(locationWithCartesianBearing);
            JSONObject location4 = locationCreator.getLocation4();
            testLocation(locationWithCartesianBearingJSONObject, location4);
            Location locationWithoutCartesianBearing = locationCreator.locationWithoutCartesianBearing();
            JSONObject locationWithoutCartesianBearingJSONObject = SitumMapper.locationToJsonObject(locationWithoutCartesianBearing);
            JSONObject location5 = locationCreator.getLocation5();
            testLocation(locationWithoutCartesianBearingJSONObject, location5);
            Location locationWithBearing = locationCreator.locationWithBearing();
            JSONObject locationWithBearingJSONObject = SitumMapper.locationToJsonObject(locationWithBearing);
            JSONObject location6 = locationCreator.getLocation6();
            testLocation(locationWithBearingJSONObject, location6);
            Location locationWithoutBearing = locationCreator.locationWithoutBearing();
            JSONObject locationWithoutBearingJSONObject = SitumMapper.locationToJsonObject(locationWithoutBearing);
            JSONObject location7 = locationCreator.getLocation7();
            testLocation(locationWithoutBearingJSONObject, location7);
            Location indoorLocationWithIndoorBearingQualityLow = locationCreator.indoorLocationWithIndoorBearingQualityLow();
            JSONObject indoorLocationWithIndoorBearingQualityLowJSONObject = SitumMapper.locationToJsonObject(indoorLocationWithIndoorBearingQualityLow);
            JSONObject location8 = locationCreator.getLocation8();
            testLocation(indoorLocationWithIndoorBearingQualityLowJSONObject, location8);
            Location indoorLocationWithIndoorBearingQualityHigh = locationCreator.indoorLocationWithIndoorBearingQualityHigh();
            JSONObject indoorLocationWithIndoorBearingQualityHighJSONObject = SitumMapper.locationToJsonObject(indoorLocationWithIndoorBearingQualityHigh);
            JSONObject location9 = locationCreator.getLocation9();
            testLocation(indoorLocationWithIndoorBearingQualityHighJSONObject, location9);
            Location outdoorLocation = locationCreator.outdoorLocation();
            JSONObject outdoorLocationJSONObject = SitumMapper.locationToJsonObject(outdoorLocation);
            JSONObject location10 = locationCreator.getLocation10();
            testLocation(outdoorLocationJSONObject, location10);
        } catch (JSONException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void locationStatusJSONObjectTest() {
        try {
            LocationStatus locationStatusStarting = locationStatusCreator.createLocationStatusStarting();
            JSONObject locationStatusStartingJSONObject = SitumMapper.locationStatusToJsonObject(locationStatusStarting);
            JSONObject locationStatus1 = locationStatusCreator.getLocationStatus1();
            testLocationStatus(locationStatusStartingJSONObject, locationStatus1);
            LocationStatus locationStatusBLENotAvailable = locationStatusCreator.createLocationStatusBLENotAvailable();
            JSONObject locationStatusBLENotAvailableJSONObject = SitumMapper.locationStatusToJsonObject(locationStatusBLENotAvailable);
            JSONObject locationStatus2 = locationStatusCreator.getLocationStatus2();
            testLocationStatus(locationStatusBLENotAvailableJSONObject, locationStatus2);
            LocationStatus locationStatusCalculating = locationStatusCreator.createLocationStatusCalculating();
            JSONObject locationStatusCalculatingJSONObject = SitumMapper.locationStatusToJsonObject(locationStatusCalculating);
            JSONObject locationStatus3 = locationStatusCreator.getLocationStatus3();
            testLocationStatus(locationStatusCalculatingJSONObject, locationStatus3);
            LocationStatus locationStatusCompassCalibrationNeeded = locationStatusCreator.createLocationStatusCompassCalibrationNeeded();
            JSONObject locationStatusCompassCalibrationNeededJSONObject = SitumMapper.locationStatusToJsonObject(locationStatusCompassCalibrationNeeded);
            JSONObject locationStatus4 = locationStatusCreator.getLocationStatus4();
            testLocationStatus(locationStatusCompassCalibrationNeededJSONObject, locationStatus4);
            LocationStatus locationStatusCompassCalibrationNotNeeded = locationStatusCreator.createLocationStatusCompassCalibrationNotNeeded();
            JSONObject locationStatusCompassCalibrationNotNeededJSONObject = SitumMapper.locationStatusToJsonObject(locationStatusCompassCalibrationNotNeeded);
            JSONObject locationStatus5 = locationStatusCreator.getLocationStatus5();
            testLocationStatus(locationStatusCompassCalibrationNotNeededJSONObject, locationStatus5);
            LocationStatus locationStatusNoConnection = locationStatusCreator.createLocationStatusNoConnection();
            JSONObject locationStatusNoConnectionJSONObject = SitumMapper.locationStatusToJsonObject(locationStatusNoConnection);
            JSONObject locationStatus6 = locationStatusCreator.getLocationStatus6();
            testLocationStatus(locationStatusNoConnectionJSONObject, locationStatus6);
            LocationStatus locationStatusPreparingPositioningModel = locationStatusCreator.createLocationStatusPreparingPositioningModel();
            JSONObject locationStatusPreparingPositioningModelJSONObject = SitumMapper.locationStatusToJsonObject(locationStatusPreparingPositioningModel);
            JSONObject locationStatus7 = locationStatusCreator.getLocationStatus7();
            testLocationStatus(locationStatusPreparingPositioningModelJSONObject, locationStatus7);
            LocationStatus locationStatusProcessingPositioningModel = locationStatusCreator.createLocationStatusProcessingPositioningModel();
            JSONObject locationStatusProcessingPositioningModelJSONObject = SitumMapper.locationStatusToJsonObject(locationStatusProcessingPositioningModel);
            JSONObject locationStatus8 = locationStatusCreator.getLocationStatus8();
            testLocationStatus(locationStatusProcessingPositioningModelJSONObject, locationStatus8);
            LocationStatus locationStatusRetryDownloadPositioningModel = locationStatusCreator.createLocationStatusRetryDownloadPositioningModel();
            JSONObject locationStatusRetryDownloadPositioningModelJSONObject = SitumMapper.locationStatusToJsonObject(locationStatusRetryDownloadPositioningModel);
            JSONObject locationStatus9 = locationStatusCreator.getLocationStatus9();
            testLocationStatus(locationStatusRetryDownloadPositioningModelJSONObject, locationStatus9);
            LocationStatus locationStatusStartDownloadPositioningModel = locationStatusCreator.createLocationStatusStartDownloadPositioningModel();
            JSONObject locationStatusStartDownloadPositioningModelJSONObject = SitumMapper.locationStatusToJsonObject(locationStatusStartDownloadPositioningModel);
            JSONObject locationStatus10 = locationStatusCreator.getLocationStatus10();
            testLocationStatus(locationStatusStartDownloadPositioningModelJSONObject, locationStatus10);
            LocationStatus locationStatusStartingPositioning = locationStatusCreator.createLocationStatusStartingPositioning();
            JSONObject locationStatusStartingPositioningJSONObject = SitumMapper.locationStatusToJsonObject(locationStatusStartingPositioning);
            JSONObject locationStatus11 = locationStatusCreator.getLocationStatus11();
            testLocationStatus(locationStatusStartingPositioningJSONObject, locationStatus11);
            LocationStatus locationStatusTimeSettingsManual = locationStatusCreator.createLocationStatusTimeSettingsManual();
            JSONObject locationStatusTimeSettingsManualJSONObject = SitumMapper.locationStatusToJsonObject(locationStatusTimeSettingsManual);
            JSONObject locationStatus12 = locationStatusCreator.getLocationStatus12();
            testLocationStatus(locationStatusTimeSettingsManualJSONObject, locationStatus12);
            LocationStatus locationStatusUserNotInBuilding = locationStatusCreator.createLocationStatusUserNotInBuilding();
            JSONObject locationStatusUserNotInBuildingJSONObject = SitumMapper.locationStatusToJsonObject(locationStatusUserNotInBuilding);
            JSONObject locationStatus13 = locationStatusCreator.getLocationStatus13();
            testLocationStatus(locationStatusUserNotInBuildingJSONObject, locationStatus13);
        } catch (JSONException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void poiJSONObjectTest1() {
        try{
            Poi poiOutdoorWithCategory = poiCreator.createOutdoorPoiWithCategory();
            JSONObject poiOutdoorWithCategoryJSONObject = SitumMapper.poiToJsonObject(poiOutdoorWithCategory);
            testPoi(poiOutdoorWithCategoryJSONObject, poiCreator.getPoi1());
            System.out.println(poiOutdoorWithCategoryJSONObject.toString());
        }catch (JSONException e){
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void poiJSONObjectTest2() {
        try{
            Poi poi = poiCreator.createPoiWithBuildingFloorAndCoordinateWithCategory();
            JSONObject poiJSONObject = SitumMapper.poiToJsonObject(poi);
            testPoi(poiJSONObject,poiCreator.getPoi2());
            System.out.println(poiJSONObject.toString());
        }catch (JSONException e){
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void poiJSONObjectTest3() {
        try{
            Poi poi = poiCreator.createPoiWithCoordinateAndBuildingId();
            JSONObject poiJSONObject = SitumMapper.poiToJsonObject(poi);
            testPoi(poiJSONObject,poiCreator.getPoi3());
            System.out.println(poiJSONObject.toString());
        }catch (JSONException e){
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void poiJSONObjectTest4() {
        try{
            Poi poi = poiCreator.createPoiWithBuildingFloorCoordinateAndCartesian();
            JSONObject poiJSONObject = SitumMapper.poiToJsonObject(poi);
            testPoi(poiJSONObject,poiCreator.getPoi4());
            System.out.println(poiJSONObject.toString());
        }catch (JSONException e){
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void poiJSONObjectTest5() {
        try{
            Poi poi = poiCreator.createPoiWithBuildingFloorAndCoordinates();
            JSONObject poiJSONObject = SitumMapper.poiToJsonObject(poi);
            testPoi(poiJSONObject,poiCreator.getPoi5());
            System.out.println(poiJSONObject.toString());
        }catch (JSONException e){
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void poiCategoryTest() {
        try{
            JSONObject jo = poiCategoryCreator.getPoiCategory1();
            PoiCategory poiCategory = SitumMapper.poiCategoryFromJsonObject(jo);
            PoiCategory poiCategory1 = poiCategoryCreator.createPoiCategory();
            testPoiCategory(poiCategory, poiCategory1);
        }catch (JSONException e){
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void poiCategoryJSONObjectTest() {
        try{
            PoiCategory poiCategory = poiCategoryCreator.createPoiCategory();
            JSONObject poiCategoryJSONObject = SitumMapper.poiCategoryToJsonObject(poiCategory);
            JSONObject poiCategory1 = poiCategoryCreator.getPoiCategory1();
            System.out.println(poiCategoryJSONObject.toString());
            System.out.println(poiCategory1.toString());
            testPoiCategory(poiCategoryJSONObject, poiCategory1);
        }catch(JSONException e){
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void situmConversionAreaJSONObjectTest() {
        try{
            SitumConversionArea situmConversionArea = situmConversionAreaCreator.createSitumConversionArea();
            JSONObject situmConversionAreaJSONObject = conversionAreaToJsonObject(situmConversionArea);
            JSONObject situmConversionArea1 = situmConversionAreaCreator.getSitumConversionArea1();
            testSitumConversionArea(situmConversionAreaJSONObject, situmConversionArea1);
        }catch(JSONException e){
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void situmCircleJSONObjectTest() {
        try{
            Circle circle = circleCreator.createCircle();
            JSONObject circleJSONObject = circleToJsonObject(circle);
            JSONObject circle1 = circleCreator.getCircle1();
            testCircle(circleJSONObject, circle1);
        }catch(JSONException e){
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void navigationProgressJSONObjectTest() {
        try{
            NavigationProgress navigationProgressOutdoor = navigationProgressCreator.createNavigationProgressOutdoor();
            JSONObject navigationProgressOutdoorJSONObject = SitumMapper.navigationProgressToJsonObject(navigationProgressOutdoor);
            JSONObject navigationProgress1 = navigationProgressCreator.getNavigationProgress1();
            testNavigationProgress(navigationProgressOutdoorJSONObject, navigationProgress1);
            NavigationProgress navigationProgressIndoor = navigationProgressCreator.createNavigationProgressIndoor();
            JSONObject navigationProgressIndoorJSONObject = SitumMapper.navigationProgressToJsonObject(navigationProgressIndoor);
            JSONObject navigationProgress2 = navigationProgressCreator.getNavigationProgress2();
            testNavigationProgress(navigationProgressIndoorJSONObject, navigationProgress2);
        }catch(JSONException e){
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void pointTest1() {
        try{
            JSONObject jo = pointCreator.getPoint1();
            Point point = SitumMapper.jsonPointToPoint(jo);
            Point point1 = pointCreator.createPointWithCoordinate();
            testPoint(point, point1);
        } catch (JSONException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void pointTest2() {
        try{
            JSONObject jo = pointCreator.getPoint2();
            Point point = SitumMapper.jsonPointToPoint(jo);
            Point point1 = pointCreator.createPointWithCoordinateAndBuildingId();
            testPoint(point, point1);
        } catch (JSONException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void pointTest3() {
        try{
            JSONObject jo = pointCreator.getPoint3();
            Point point = SitumMapper.jsonPointToPoint(jo);
            Point point1 = pointCreator.createPointWithBuildingIdAndFloor();
            testPoint(point, point1);
        } catch (JSONException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void pointTest4() {
        try{
            JSONObject jo = pointCreator.getPoint4();
            Point point = SitumMapper.pointJsonObjectToPoint(jo, buildingCreator.getBuilding1());
            Point point1 = pointCreator.createPointWithBuildingWithAngleFromDegrees();
            testPoint(point, point1);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void pointTest5() {
        try{
            JSONObject jo = pointCreator.getPoint5();
            Point point = SitumMapper.pointJsonObjectToPoint(jo, buildingCreator.getBuilding1());
            Point point1 = pointCreator.createPointWithBuildingWithAngleFromRadians();
            testPoint(point, point1);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void pointTest6() {
        try{
            JSONObject jo = pointCreator.getPoint6();
            Point point = SitumMapper.jsonPointToPoint(jo);
            Point point1 = pointCreator.createPointWithBuildingWithAddress();
            testPoint(point, point1);
        } catch (JSONException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void pointTest7() {
        try{
            JSONObject jo = pointCreator.getPoint7();
            Point point = SitumMapper.jsonPointToPoint(jo);
            Point point1 = pointCreator.createPointWithBuildingWithInfo();
            testPoint(point, point1);
        } catch (JSONException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void pointTest8() {
        try{
            JSONObject jo = pointCreator.getPoint8();
            Point point = SitumMapper.jsonPointToPoint(jo);
            Point point1 = pointCreator.createPointWithBuildingWithPicture();
            testPoint(point, point1);
        } catch (JSONException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void pointJSONObjectTest() {
        try{
            Point pointWithCoordinate = pointCreator.createPointWithCoordinate();
            JSONObject pointWithCoordinateJSONObject = SitumMapper.pointToJsonObject(pointWithCoordinate);
            JSONObject point1 = pointCreator.getPoint1();
            testPoint(pointWithCoordinateJSONObject, point1);
            Point pointWithCoordinateAndBuildingId = pointCreator.createPointWithCoordinateAndBuildingId();
            JSONObject pointWithCoordinateAndBuildingIdJSONObject = SitumMapper.pointToJsonObject(pointWithCoordinateAndBuildingId);
            JSONObject point2 = pointCreator.getPoint2();
            testPoint(pointWithCoordinateAndBuildingIdJSONObject, point2);
            Point pointWithBuildingIdAndFloor = pointCreator.createPointWithBuildingIdAndFloor();
            JSONObject pointWithBuildingIdAndFloorJSONObject = SitumMapper.pointToJsonObject(pointWithBuildingIdAndFloor);
            JSONObject point3 = pointCreator.getPoint3();
            testPoint(pointWithBuildingIdAndFloorJSONObject, point3);
            Point pointWithBuildingWithAngleFromDegrees = pointCreator.createPointWithBuildingWithAngleFromDegrees();
            JSONObject pointWithBuildingWithAngleFromDegreesJSONObject = SitumMapper.pointToJsonObject(pointWithBuildingWithAngleFromDegrees);
            JSONObject point4 = pointCreator.getPoint4();
            testPoint(pointWithBuildingWithAngleFromDegreesJSONObject, point4);
            Point pointWithBuildingWithAngleFromRadians = pointCreator.createPointWithBuildingWithAngleFromRadians();
            JSONObject pointWithBuildingWithAngleFromRadiansJSONObject = SitumMapper.pointToJsonObject(pointWithBuildingWithAngleFromRadians);
            JSONObject point5 = pointCreator.getPoint5();
            testPoint(pointWithBuildingWithAngleFromRadiansJSONObject, point5);
            Point pointWithBuildingWithAddress = pointCreator.createPointWithBuildingWithAddress();
            JSONObject pointWithBuildingWithAddressJSONObject = SitumMapper.pointToJsonObject(pointWithBuildingWithAddress);
            JSONObject point6 = pointCreator.getPoint6();
            testPoint(pointWithBuildingWithAddressJSONObject, point6);
            Point pointWithBuildingWithInfo = pointCreator.createPointWithBuildingWithInfo();
            JSONObject pointWithBuildingWithInfoJSONObject = SitumMapper.pointToJsonObject(pointWithBuildingWithInfo);
            JSONObject point7 = pointCreator.getPoint7();
            testPoint(pointWithBuildingWithInfoJSONObject, point7);
            Point pointWithBuildingWithPicture = pointCreator.createPointWithBuildingWithPicture();
            JSONObject pointWithBuildingWithPictureJSONObject = SitumMapper.pointToJsonObject(pointWithBuildingWithPicture);
            JSONObject point8 = pointCreator.getPoint8();
            testPoint(pointWithBuildingWithPictureJSONObject, point8);
        }catch(JSONException e){
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void routeJSONObjectTest(){
        try{
            Route route = routeCreator.createRouteBuildingWithDegreesPointWithCoordinates();
            JSONObject routeJSONObject = SitumMapper.routeToJsonObject(route);
            JSONObject route1 = routeCreator.getRoute1();
            testRoute(routeJSONObject, route1);
        }catch(JSONException e){
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void eventJSONObjectTest() throws JSONException {
        SitumEvent event = eventCreator.createEvent1();
        JSONObject jsonObject = SitumMapper.situmEventToJsonObject(event);
        JSONObject event1 = eventCreator.getEvent1();
        assertThat(jsonObject.toString()).isEqualTo(event1.toString());
    }

    private void testRoute(JSONObject route, JSONObject defaultRoute) throws JSONException {
        Assert.assertEquals(JSONObject.class, route.get(LAST_STEP).getClass());
        testRouteStep(route.getJSONObject(LAST_STEP), defaultRoute.getJSONObject(LAST_STEP));
        Assert.assertEquals(JSONObject.class, route.get(FIRST_STEP).getClass());
        testRouteStep(route.getJSONObject(FIRST_STEP), defaultRoute.getJSONObject(FIRST_STEP));
        Assert.assertEquals(JSONArray.class, route.get(INDICATIONS).getClass());
        JSONArray indications = route.getJSONArray(INDICATIONS);
        JSONArray defaultIndications = defaultRoute.getJSONArray(INDICATIONS);
        Assert.assertEquals(defaultIndications.length(), indications.length());
        for(int i = 0; i < indications.length(); i++) {
            testIndication(indications.getJSONObject(i), defaultIndications.getJSONObject(i));
        }
        Assert.assertEquals(JSONArray.class, route.get(NODES).getClass());
        JSONArray nodes = route.getJSONArray(NODES);
        JSONArray defaultNodes = defaultRoute.getJSONArray(NODES);
        Assert.assertEquals(defaultNodes.length(), nodes.length());
        for(int i = 0; i < nodes.length(); i++){
            testPoint(nodes.getJSONObject(i), defaultNodes.getJSONObject(i));
        }
        Assert.assertEquals(JSONArray.class, route.get(EDGES).getClass());
        JSONArray edges = route.getJSONArray(EDGES);
        JSONArray defaultEdges = defaultRoute.getJSONArray(EDGES);
        Assert.assertEquals(defaultEdges.length(), edges.length());
        for(int i = 0; i < edges.length(); i++) {
            testRouteStep(edges.getJSONObject(i), defaultEdges.getJSONObject(i));
        }
        Assert.assertEquals(JSONObject.class, route.get(FROM).getClass());
        testPoint(route.getJSONObject(FROM), defaultRoute.getJSONObject(FROM));
        Assert.assertEquals(JSONObject.class, route.get(TO).getClass());
        testPoint(route.getJSONObject(TO),defaultRoute.getJSONObject(TO));
        Assert.assertEquals(JSONArray.class, route.get(STEPS).getClass());
        JSONArray steps = route.getJSONArray(STEPS);
        JSONArray defaultSteps = defaultRoute.getJSONArray(STEPS);
        Assert.assertEquals(defaultSteps.length(), steps.length());
        for(int i = 0; i < steps.length(); i++) {
            testRouteStep(steps.getJSONObject(i), defaultSteps.getJSONObject(i));
        }
        Assert.assertEquals(JSONArray.class, route.get(POINTS).getClass());
        JSONArray points = route.getJSONArray(POINTS);
        JSONArray defaultPoints = defaultRoute.getJSONArray(POINTS);
        Assert.assertEquals(defaultPoints.length(), points.length());
        for(int i = 0; i < points.length(); i++) {
            testPoint(points.getJSONObject(i), defaultPoints.getJSONObject(i));
        }
        Assert.assertEquals(JSONArray.class, route.get(SEGMENTS).getClass());
        JSONArray segments = route.getJSONArray(SEGMENTS);
        JSONArray defaultSegments = defaultRoute.getJSONArray(SEGMENTS);
        Assert.assertEquals(segments.length(), defaultSegments.length());
        for(int i = 0; i < segments.length(); i++) {
            testSegment(segments.getJSONObject(i), defaultSegments.getJSONObject(i));
        }
    }

    private void testAngle(Angle angle, Angle defaultAngle) {
        Assert.assertEquals(defaultAngle.degrees(), angle.degrees(),0.0000001);
        Assert.assertEquals(defaultAngle.degreesClockwise(), angle.degreesClockwise(),0.0000001);
        Assert.assertEquals(defaultAngle.radians(), angle.radians(),0.0000001);
        Assert.assertEquals(defaultAngle.radiansMinusPiPi(), angle.radiansMinusPiPi(),0.0000001);
    }

    private void testAngle(JSONObject angle, JSONObject defaultAngle) throws JSONException {
        Assert.assertEquals(Double.class, angle.get(RADIANS_MINUS_PI_PI).getClass());
        Assert.assertEquals(defaultAngle.getDouble(RADIANS_MINUS_PI_PI), angle.getDouble(RADIANS_MINUS_PI_PI), 0);
        Assert.assertEquals(Double.class, angle.get(RADIANS).getClass());
        Assert.assertEquals(defaultAngle.getDouble(RADIANS), angle.getDouble(RADIANS),0);
        Assert.assertEquals(Double.class, angle.get(DEGREES_CLOCKWISE).getClass());
        Assert.assertEquals(defaultAngle.getDouble(DEGREES_CLOCKWISE), angle.getDouble(DEGREES_CLOCKWISE), 0);
        Assert.assertEquals(Double.class, angle.get(DEGREES).getClass());
        Assert.assertEquals(defaultAngle.getDouble(DEGREES), angle.getDouble(DEGREES),0);
    }

    private void testBounds(Bounds bounds, Bounds defaultBounds) {
        testCoordinate(defaultBounds.getNorthEast(), bounds.getNorthEast());
        testCoordinate(defaultBounds.getNorthWest(), bounds.getNorthWest());
        testCoordinate(defaultBounds.getSouthEast(), bounds.getSouthEast());
        testCoordinate(defaultBounds.getSouthWest(), bounds.getSouthWest());
    }

    private void testBounds(JSONObject bounds, JSONObject defaultBounds) throws JSONException {
        Assert.assertEquals(JSONObject.class, bounds.get(NORTH_WEST).getClass());
        testCoordinate(bounds.getJSONObject(NORTH_WEST), defaultBounds.getJSONObject(NORTH_WEST));
        Assert.assertEquals(JSONObject.class, bounds.get(SOUTH_WEST).getClass());
        testCoordinate(bounds.getJSONObject(SOUTH_WEST), defaultBounds.getJSONObject(SOUTH_WEST));
        Assert.assertEquals(JSONObject.class, bounds.get(NORTH_EAST).getClass());
        testCoordinate(bounds.getJSONObject(NORTH_EAST), defaultBounds.getJSONObject(NORTH_EAST));
        Assert.assertEquals(JSONObject.class, bounds.get(SOUTH_EAST).getClass());
        testCoordinate(bounds.getJSONObject(SOUTH_EAST), defaultBounds.getJSONObject(SOUTH_EAST));
    }

    private void testBuilding(Building building, Building defaultBuilding) {
        Assert.assertEquals(defaultBuilding.getAddress(), building.getAddress());
        testBounds(defaultBuilding.getBounds(), building.getBounds());
        testBounds(defaultBuilding.getBoundsRotated(), building.getBoundsRotated());
        testCoordinate(defaultBuilding.getCenter(), building.getCenter());
        testDimensions(defaultBuilding.getDimensions(), building.getDimensions());
        Assert.assertEquals(defaultBuilding.getInfoHtml(), building.getInfoHtml());
        Assert.assertEquals(defaultBuilding.getName(), building.getName());
        Assert.assertEquals(defaultBuilding.getPictureThumbUrl(), building.getPictureThumbUrl());
        Assert.assertEquals(defaultBuilding.getPictureUrl(), building.getPictureUrl());
        testAngle(defaultBuilding.getRotation(), building.getRotation());
        Assert.assertEquals(defaultBuilding.getUserIdentifier(), building.getUserIdentifier());
        Assert.assertEquals(defaultBuilding.getIdentifier(), building.getIdentifier());
        Assert.assertEquals(defaultBuilding.getCustomFields(), building.getCustomFields());
        Assert.assertEquals(defaultBuilding.getUpdatedAt(), building.getUpdatedAt());
        Assert.assertEquals(defaultBuilding.getCreatedAt(), building.getCreatedAt());

    }

    private void testBuilding(JSONObject building, JSONObject defaultBuilding) throws JSONException {
        Assert.assertEquals(String.class, building.get(ADDRESS).getClass());
        Assert.assertEquals(defaultBuilding.getString(ADDRESS),building.getString(ADDRESS));
        Assert.assertEquals(JSONObject.class, building.get(BOUNDS).getClass());
        testBounds(building.getJSONObject(BOUNDS), defaultBuilding.getJSONObject(BOUNDS));
        Assert.assertEquals(JSONObject.class, building.get(BOUNDS_ROTATED).getClass());
//        testBounds(building.getJSONObject(BOUNDS_ROTATED), defaultBuilding.getJSONObject(BOUNDS_ROTATED));
        Assert.assertEquals(JSONObject.class, building.get(CENTER).getClass());
        testCoordinate(building.getJSONObject(CENTER),defaultBuilding.getJSONObject(CENTER));
        Assert.assertEquals(JSONObject.class, building.get(DIMENSIONS).getClass());
        testDimensions(building.getJSONObject(DIMENSIONS), defaultBuilding.getJSONObject(DIMENSIONS));
        Assert.assertEquals(String.class, building.get(INFO_HTML).getClass());
        Assert.assertEquals(defaultBuilding.getString(INFO_HTML),building.getString(INFO_HTML));
        Assert.assertEquals(String.class, building.get(NAME).getClass());
        Assert.assertEquals(defaultBuilding.getString(NAME),building.getString(NAME));
        Assert.assertEquals(String.class, building.get(PICTURE_THUMB_URL).getClass());
        Assert.assertEquals(defaultBuilding.getString(PICTURE_THUMB_URL),building.getString(PICTURE_THUMB_URL));
        Assert.assertEquals(String.class, building.get(PICTURE_URL).getClass());
        Assert.assertEquals(defaultBuilding.getString(PICTURE_URL),building.getString(PICTURE_URL));
        Assert.assertEquals(Double.class, building.get(ROTATION).getClass());
        Assert.assertEquals(defaultBuilding.getLong(ROTATION),building.getLong(ROTATION));
        Assert.assertEquals(String.class, building.get(USER_IDENTIFIER).getClass());
        Assert.assertEquals(defaultBuilding.getString(USER_IDENTIFIER),building.getString(USER_IDENTIFIER));
        Assert.assertEquals(String.class, building.get(BUILDING_IDENTIFIER).getClass());
        Assert.assertEquals(defaultBuilding.getString(BUILDING_IDENTIFIER),building.getString(BUILDING_IDENTIFIER));
        testCustomFields(defaultBuilding.getJSONObject(CUSTOM_FIELDS).toString(),building.getJSONObject(CUSTOM_FIELDS).toString());
        Assert.assertEquals(String.class, building.get(UPDATED_AT).getClass());
        Assert.assertEquals(dateFormat.parseObject(defaultBuilding.get(UPDATED_AT).toString(),new ParsePosition(0)),dateFormat.parseObject(building.get(UPDATED_AT).toString(),new ParsePosition(0)));
        Assert.assertEquals(String.class, building.get(CREATED_AT).getClass());
        Assert.assertEquals(dateFormat.parseObject(defaultBuilding.get(CREATED_AT).toString(),new ParsePosition(0)),dateFormat.parseObject(building.get(CREATED_AT).toString(),new ParsePosition(0)));

    }

    private void testCartesianCoordinate(CartesianCoordinate cartesianCoordinate, CartesianCoordinate defaultCartesianCoordinate) {
        Assert.assertEquals(defaultCartesianCoordinate.getX(), cartesianCoordinate.getX(), 0);
        Assert.assertEquals(defaultCartesianCoordinate.getY(), cartesianCoordinate.getY(), 0);
    }

    private void testCartesianCoordinate(JSONObject cartesianCoordinate, JSONObject defaultCartesianCoordinate) throws JSONException {
        Assert.assertEquals(Double.class, cartesianCoordinate.get(X).getClass());
        Assert.assertEquals(defaultCartesianCoordinate.getDouble(X), cartesianCoordinate.getDouble(X), 0);
        Assert.assertEquals(Double.class, cartesianCoordinate.get(Y).getClass());
        Assert.assertEquals(defaultCartesianCoordinate.getDouble(Y), cartesianCoordinate.getDouble(Y), 0);
    }

    private void testCoordinate(Coordinate coordinate, Coordinate defaultCoordinate) {
        Assert.assertEquals(defaultCoordinate.getLongitude(), coordinate.getLongitude(), 0);
        Assert.assertEquals(defaultCoordinate.getLatitude(), coordinate.getLatitude(), 0);
    }

    private void testCoordinate(JSONObject coordinate, JSONObject defaultCoordinate) throws JSONException {
        Assert.assertEquals(Double.class, coordinate.get(LATITUDE).getClass());
        Assert.assertEquals(defaultCoordinate.getDouble(LATITUDE), coordinate.getDouble(LATITUDE), 0);
        Assert.assertEquals(Double.class, coordinate.get(LONGITUDE).getClass());
        Assert.assertEquals(defaultCoordinate.getDouble(LONGITUDE), coordinate.getDouble(LONGITUDE), 0);
    }

    private void testCustomFields(String  customFields,String defaultCustomFields) {
        Assert.assertEquals(customFields,defaultCustomFields);
    }

    private void testDimensions(Dimensions dimensions, Dimensions defaultDimensions) {
        Assert.assertEquals(defaultDimensions.getHeight(), dimensions.getHeight(), 0);
        Assert.assertEquals(defaultDimensions.getWidth(), dimensions.getWidth(),0);
    }

    private void testDimensions(JSONObject dimensions, JSONObject defaultDimensions) throws JSONException {
        Assert.assertEquals(Double.class, dimensions.get(WIDTH).getClass());
        Assert.assertEquals(defaultDimensions.getDouble(WIDTH), dimensions.getDouble(WIDTH), 0);
        Assert.assertEquals(Double.class, dimensions.get(HEIGHT).getClass());
        Assert.assertEquals(defaultDimensions.getDouble(HEIGHT), dimensions.getDouble(HEIGHT), 0);
    }

    private void testDirectionsRequest(DirectionsRequest directionsRequest, DirectionsRequest defaultDirectionsRequest) {
        Assert.assertEquals(defaultDirectionsRequest.getAccessibilityMode(), directionsRequest.getAccessibilityMode());
        Assert.assertEquals(defaultDirectionsRequest.minimizeFloorChanges(), directionsRequest.minimizeFloorChanges());
    }

    private void testLocationRequest(LocationRequest locationRequest, LocationRequest defaultLocationRequest) {
        Assert.assertEquals(locationRequest.getBuildingIdentifier(), defaultLocationRequest.getBuildingIdentifier());
        Assert.assertEquals(locationRequest.getSmallestDisplacement(), defaultLocationRequest.getSmallestDisplacement());
        Assert.assertEquals(locationRequest.getInterval(), defaultLocationRequest.getInterval());
        Assert.assertEquals(locationRequest.autoEnableBleDuringPositioning(), defaultLocationRequest.autoEnableBleDuringPositioning());

    }

    private void testFloor(Floor floor, Floor defaultFloor) {
        Assert.assertEquals(defaultFloor.getAltitude(), floor.getAltitude(), 0);
        Assert.assertEquals(defaultFloor.getCreatedAt(), floor.getCreatedAt());
        Assert.assertEquals(defaultFloor.getIdentifier(), floor.getIdentifier());
        Assert.assertEquals(defaultFloor.getLevel(), floor.getLevel());
        Assert.assertEquals(defaultFloor.getCustomFields(), floor.getCustomFields());
        Assert.assertEquals(defaultFloor.getMapUrl(), floor.getMapUrl());
        Assert.assertEquals(defaultFloor.getScale(), floor.getScale(), 0);
        Assert.assertEquals(defaultFloor.getBuildingIdentifier(), floor.getBuildingIdentifier());
        Assert.assertEquals(defaultFloor.getUpdatedAt(), floor.getUpdatedAt());
    }

    private void testFloor(JSONObject floor, JSONObject defaultFloor) throws JSONException {
        Assert.assertEquals(Double.class, floor.get(ALTITUDE).getClass());
        Assert.assertEquals(defaultFloor.getDouble(ALTITUDE), floor.getDouble(ALTITUDE), 0);
        Assert.assertEquals(String.class, floor.get(CREATED_AT).getClass());
        Assert.assertEquals(defaultFloor.getString(CREATED_AT), floor.getString(CREATED_AT));
        Assert.assertEquals(String.class, floor.get(FLOOR_IDENTIFIER).getClass());
        Assert.assertEquals(defaultFloor.getString(FLOOR_IDENTIFIER), floor.getString(FLOOR_IDENTIFIER));
        Assert.assertEquals(Integer.class, floor.get(LEVEL).getClass());
        Assert.assertEquals(defaultFloor.getInt(LEVEL), floor.getInt(LEVEL));
        Assert.assertEquals(JSONObject.class, floor.get(CUSTOM_FIELDS).getClass());
        Assert.assertEquals(defaultFloor.getJSONObject(CUSTOM_FIELDS).toString(), floor.getJSONObject(CUSTOM_FIELDS).toString());
        Assert.assertEquals(String.class, floor.get(MAP_URL).getClass());
        Assert.assertEquals(defaultFloor.getString(MAP_URL), floor.getString(MAP_URL));
        Assert.assertEquals(Double.class, floor.get(SCALE).getClass());
        Assert.assertEquals(defaultFloor.getDouble(SCALE), floor.getDouble(SCALE), 0);
        Assert.assertEquals(String.class, floor.get(BUILDING_IDENTIFIER).getClass());
        Assert.assertEquals(defaultFloor.getString(BUILDING_IDENTIFIER), floor.getString(BUILDING_IDENTIFIER));
        Assert.assertEquals(String.class, floor.get(UPDATED_AT).getClass());
        Assert.assertEquals(defaultFloor.getString(UPDATED_AT), floor.getString(UPDATED_AT));
    }

    private void testIndication(JSONObject indication, JSONObject defaultIndication) throws JSONException {
        Assert.assertEquals(Indication.Orientation.class, indication.get(ORIENTATION_TYPE).getClass());
        Assert.assertEquals(Indication.Orientation.valueOf(defaultIndication.get(ORIENTATION_TYPE).toString()), indication.get(ORIENTATION_TYPE));
        Assert.assertEquals(Double.class, indication.get(ORIENTATION).getClass());
        Assert.assertEquals(defaultIndication.getDouble(ORIENTATION), indication.getDouble(ORIENTATION), 0);
        Assert.assertEquals(Integer.class, indication.get(STEP_IDX_DESTINTATION).getClass());
        Assert.assertEquals(defaultIndication.getInt(STEP_IDX_DESTINTATION), indication.getInt(STEP_IDX_DESTINTATION));
        Assert.assertEquals(Double.class, indication.get(DISTANCE).getClass());
        Assert.assertEquals(defaultIndication.getDouble(DISTANCE), indication.getDouble(DISTANCE), 0);
        Assert.assertEquals(Integer.class, indication.get(STEP_IDX_ORIGIN).getClass());
        Assert.assertEquals(defaultIndication.getInt(STEP_IDX_ORIGIN), indication.getInt(STEP_IDX_ORIGIN));
        Assert.assertEquals(String.class, indication.get(INDICATION_TYPE).getClass());
        Assert.assertEquals(defaultIndication.getString(INDICATION_TYPE), indication.getString(INDICATION_TYPE));
        Assert.assertEquals(Integer.class, indication.get(DISTANCE_TO_NEXT_LEVEL).getClass());
        Assert.assertEquals(defaultIndication.getInt(DISTANCE_TO_NEXT_LEVEL), indication.getInt(DISTANCE_TO_NEXT_LEVEL));
        Assert.assertEquals(Boolean.class, indication.get(NEEDED_LEVEL_CHANGE).getClass());
        Assert.assertEquals(defaultIndication.getBoolean(NEEDED_LEVEL_CHANGE), indication.getBoolean(NEEDED_LEVEL_CHANGE));
    }

    private void testLocation(Location location,Location defaultLocation) {
        Assert.assertEquals(defaultLocation.hasCartesianBearing(), location.hasCartesianBearing());
        testCoordinate(defaultLocation.getCoordinate(), location.getCoordinate());
        testAngle(defaultLocation.getBearing(), location.getBearing());
        Assert.assertEquals(defaultLocation.getAccuracy(), location.getAccuracy(), 0);
        Assert.assertEquals(defaultLocation.isIndoor(), location.isIndoor());
        Assert.assertEquals(defaultLocation.getDeviceId(), location.getDeviceId());
        Assert.assertEquals(defaultLocation.getBuildingIdentifier(), location.getBuildingIdentifier());
        Assert.assertEquals(defaultLocation.getQuality(), location.getQuality());
        Assert.assertEquals(defaultLocation.isOutdoor(), location.isOutdoor());
        Assert.assertEquals(defaultLocation.getFloorIdentifier(), location.getFloorIdentifier());
        Assert.assertEquals(defaultLocation.getProvider(), location.getProvider());
        testAngle(defaultLocation.getCartesianBearing(), location.getCartesianBearing());
        Assert.assertEquals(defaultLocation.getBearingQuality(), location.getBearingQuality());
        Assert.assertEquals(defaultLocation.hasBearing(), location.hasBearing());
        testCartesianCoordinate(defaultLocation.getCartesianCoordinate(), location.getCartesianCoordinate());
        testPoint(defaultLocation.getPosition(), location.getPosition());
        Assert.assertEquals(defaultLocation.getTime(), location.getTime());
    }

    private void testLocation(JSONObject location, JSONObject defaultLocation) throws JSONException {
        Assert.assertEquals(Boolean.class, location.get(HAS_CARTESIAN_BEARING).getClass());
        Assert.assertEquals(defaultLocation.getBoolean(HAS_CARTESIAN_BEARING), location.getBoolean(HAS_CARTESIAN_BEARING));
        Assert.assertEquals(JSONObject.class, location.get(COORDINATE).getClass());
        testCoordinate(location.getJSONObject(COORDINATE), defaultLocation.getJSONObject(COORDINATE));
        Assert.assertEquals(JSONObject.class, location.get(BEARING).getClass());
        testAngle(location.getJSONObject(BEARING), defaultLocation.getJSONObject(BEARING));
        Assert.assertEquals(Double.class, location.get(ACCURACY).getClass());
        Assert.assertEquals(defaultLocation.getDouble(ACCURACY), location.getDouble(ACCURACY), 0);
        Assert.assertEquals(Boolean.class, location.get(IS_INDOOR).getClass());
        Assert.assertEquals(defaultLocation.getBoolean(IS_INDOOR), location.getBoolean(IS_INDOOR));
        Assert.assertEquals(String.class, location.get(DEVICE_ID).getClass());
        Assert.assertEquals(defaultLocation.getString(DEVICE_ID), location.getString(DEVICE_ID));
        Assert.assertEquals(String.class, location.get(BUILDING_IDENTIFIER).getClass());
        Assert.assertEquals(defaultLocation.getString(BUILDING_IDENTIFIER), location.getString(BUILDING_IDENTIFIER));
        Assert.assertEquals(String.class, location.get(QUALITY).getClass());
        Assert.assertEquals(defaultLocation.getString(QUALITY), location.getString(QUALITY));
        Assert.assertEquals(Boolean.class, location.get(IS_OUTDOOR).getClass());
        Assert.assertEquals(defaultLocation.getBoolean(IS_OUTDOOR), location.getBoolean(IS_OUTDOOR));
        Assert.assertEquals(String.class, location.get(FLOOR_IDENTIFIER).getClass());
        Assert.assertEquals(defaultLocation.getString(FLOOR_IDENTIFIER), location.getString(FLOOR_IDENTIFIER));
        Assert.assertEquals(String.class, location.get(PROVIDER).getClass());
        Assert.assertEquals(defaultLocation.getString(PROVIDER), location.getString(PROVIDER));
        Assert.assertEquals(JSONObject.class, location.get(CARTESIAN_BEARING).getClass());
        testAngle(location.getJSONObject(CARTESIAN_BEARING), defaultLocation.getJSONObject(CARTESIAN_BEARING));
        Assert.assertEquals(String.class, location.get(BEARING_QUALITY).getClass());
        Assert.assertEquals(defaultLocation.getString(BEARING_QUALITY), location.getString(BEARING_QUALITY));
        Assert.assertEquals(Boolean.class, location.get(HAS_BEARING).getClass());
        Assert.assertEquals(defaultLocation.getBoolean(HAS_BEARING), location.getBoolean(HAS_BEARING));
        Assert.assertEquals(JSONObject.class, location.get(CARTESIAN_COORDINATE).getClass());
        testCartesianCoordinate(location.getJSONObject(CARTESIAN_COORDINATE), location.getJSONObject(CARTESIAN_COORDINATE));
        Assert.assertEquals(JSONObject.class, location.get(POSITION).getClass());
        testPoint(location.getJSONObject(POSITION), defaultLocation.getJSONObject(POSITION));
        Assert.assertEquals(Long.class, location.get(TIMESTAMP).getClass());
        Assert.assertEquals(defaultLocation.getLong(TIMESTAMP), location.getLong(TIMESTAMP));
    }

    private void testPoint(Point point, Point defaultPoint) {
        testCoordinate(defaultPoint.getCoordinate(), point.getCoordinate());
        testCartesianCoordinate(defaultPoint.getCartesianCoordinate(), point.getCartesianCoordinate());
        Assert.assertEquals(defaultPoint.getFloorIdentifier(), point.getFloorIdentifier());
        Assert.assertEquals(defaultPoint.getBuildingIdentifier(), point.getBuildingIdentifier());
        Assert.assertEquals(defaultPoint.isIndoor(), point.isIndoor());
        Assert.assertEquals(defaultPoint.isOutdoor(), point.isOutdoor());
    }

    private void testQuality(Location.Quality quality, Location.Quality defaultQuality) {
        Assert.assertEquals(defaultQuality, quality);
    }

    private void testPoint(JSONObject point, JSONObject defaultPoint) throws JSONException {
        Assert.assertEquals(JSONObject.class, point.get(COORDINATE).getClass());
        testCoordinate(point.getJSONObject(COORDINATE), defaultPoint.getJSONObject(COORDINATE));
        Assert.assertEquals(String.class, point.get(FLOOR_IDENTIFIER).getClass());
        Assert.assertEquals(defaultPoint.getString(FLOOR_IDENTIFIER), point.getString(FLOOR_IDENTIFIER));
        Assert.assertEquals(JSONObject.class, point.get(CARTESIAN_COORDINATE).getClass());
        testCartesianCoordinate(point.getJSONObject(CARTESIAN_COORDINATE), defaultPoint.getJSONObject(CARTESIAN_COORDINATE));
        Assert.assertEquals(Boolean.class, point.get(IS_INDOOR).getClass());
        Assert.assertEquals(defaultPoint.getBoolean(IS_INDOOR), point.getBoolean(IS_INDOOR));
        Assert.assertEquals(String.class, point.get(BUILDING_IDENTIFIER).getClass());
        Assert.assertEquals(defaultPoint.getString(BUILDING_IDENTIFIER), point.getString(BUILDING_IDENTIFIER));
        Assert.assertEquals(Boolean.class, point.get(IS_OUTDOOR).getClass());
        Assert.assertEquals(defaultPoint.getBoolean(IS_OUTDOOR), point.getBoolean(IS_OUTDOOR));
    }

    private void testSegment(JSONObject segment, JSONObject defaultSegment) throws JSONException {
        Assert.assertEquals(String.class, segment.get(FLOOR_IDENTIFIER).getClass());
        Assert.assertEquals(segment.getString(FLOOR_IDENTIFIER), defaultSegment.getString(FLOOR_IDENTIFIER));
        Assert.assertEquals(JSONArray.class, segment.get(POINTS).getClass());
        JSONArray points = segment.getJSONArray(POINTS);
        JSONArray defaultPoints = defaultSegment.getJSONArray(POINTS);
        Assert.assertEquals(points.length(), defaultPoints.length());
        for(int i = 0; i < points.length(); i++) {
            testPoint(points.getJSONObject(i), defaultPoints.getJSONObject(i));
        }
    }

    private void testLocationStatus(JSONObject locationStatus, JSONObject defaultLocationStatus) throws JSONException{
        Assert.assertEquals(Integer.class, locationStatus.get(STATUS_ORDINAL).getClass());
        Assert.assertEquals(defaultLocationStatus.getInt(STATUS_ORDINAL), locationStatus.getInt(STATUS_ORDINAL));
        Assert.assertEquals(String.class, locationStatus.get(STATUS_NAME).getClass());
        Assert.assertEquals(defaultLocationStatus.getString(STATUS_NAME), locationStatus.getString(STATUS_NAME));
    }

    private void testPoi(JSONObject poi, JSONObject defaultPoi) throws JSONException {
        Assert.assertEquals(String.class, poi.get(IDENTIFIER).getClass());
        Assert.assertEquals(defaultPoi.getString(IDENTIFIER),poi.getString(IDENTIFIER));
        testCoordinate(poi.getJSONObject(COORDINATE),defaultPoi.getJSONObject(COORDINATE));
        Assert.assertEquals(String.class, poi.get(POI_NAME).getClass());
        Assert.assertEquals(defaultPoi.getString(POI_NAME),poi.getString(POI_NAME));
        testCustomFields(defaultPoi.getJSONObject(CUSTOM_FIELDS).toString(),poi.getJSONObject(CUSTOM_FIELDS).toString());
        Assert.assertEquals(Boolean.class, poi.get(IS_INDOOR).getClass());
        Assert.assertEquals(defaultPoi.getBoolean(IS_INDOOR),poi.getBoolean(IS_INDOOR));
        Assert.assertEquals(String.class, poi.get(INFO_HTML).getClass());
        Assert.assertEquals(defaultPoi.getString(INFO_HTML),poi.getString(INFO_HTML));
        Assert.assertEquals(String.class, poi.get(BUILDING_IDENTIFIER).getClass());
        Assert.assertEquals(defaultPoi.getString(BUILDING_IDENTIFIER),poi.getString(BUILDING_IDENTIFIER));
        Assert.assertEquals(Boolean.class, poi.get(IS_OUTDOOR).getClass());
        Assert.assertEquals(defaultPoi.getBoolean(IS_OUTDOOR),poi.getBoolean(IS_OUTDOOR));
        Assert.assertEquals(String.class, poi.get(CREATED_AT).getClass());
        Assert.assertEquals(defaultPoi.getString(CREATED_AT), poi.getString(CREATED_AT));
        Assert.assertEquals(String.class, poi.get(FLOOR_IDENTIFIER).getClass());
        Assert.assertEquals(defaultPoi.getString(FLOOR_IDENTIFIER),poi.getString(FLOOR_IDENTIFIER));
        testCartesianCoordinate(poi.getJSONObject(CARTESIAN_COORDINATE),defaultPoi.getJSONObject(CARTESIAN_COORDINATE));
        testPoint(poi.getJSONObject(POSITION), poi.getJSONObject(POSITION));
        Assert.assertEquals(String.class, poi.get(CATEGORY).getClass());
        Assert.assertEquals(defaultPoi.getString(CATEGORY),poi.getString(CATEGORY));
        Assert.assertEquals(String.class, poi.get(UPDATED_AT).getClass());
        Assert.assertEquals(defaultPoi.getString(UPDATED_AT), poi.getString(UPDATED_AT));
    }

    private void testPoiCategory(PoiCategory poiCategory, PoiCategory defaultPoiCategory) {
        Assert.assertEquals(defaultPoiCategory.getSelectedIconUrl(), poiCategory.getSelectedIconUrl());
        Assert.assertEquals(defaultPoiCategory.isPublic(), poiCategory.isPublic());
        Assert.assertEquals(defaultPoiCategory.getName(), poiCategory.getName());
        Assert.assertEquals(defaultPoiCategory.getCode(), poiCategory.getCode());
        Assert.assertEquals(defaultPoiCategory.getUnselectedIconUrl(), poiCategory.getUnselectedIconUrl());
    }

    private void testPoiCategory(JSONObject poiCategory, JSONObject defaultPoiCategory) throws JSONException {
        Assert.assertEquals(String.class, poiCategory.get(ICON_SELECTED).getClass());
        Assert.assertEquals(defaultPoiCategory.getString(ICON_SELECTED), poiCategory.getString(ICON_SELECTED));
        Assert.assertEquals(Boolean.class, poiCategory.get(PUBLIC).getClass());
        Assert.assertEquals(defaultPoiCategory.getBoolean(PUBLIC), poiCategory.getBoolean(PUBLIC));
        Assert.assertEquals(String.class, poiCategory.get(POI_CATEGORY_NAME).getClass());
        Assert.assertEquals(defaultPoiCategory.getString(POI_CATEGORY_NAME), poiCategory.getString(POI_CATEGORY_NAME));
        Assert.assertEquals(String.class, poiCategory.get(POI_CATEGORY_CODE).getClass());
        Assert.assertEquals(defaultPoiCategory.getString(POI_CATEGORY_CODE), poiCategory.getString(POI_CATEGORY_CODE));
        Assert.assertEquals(String.class, poiCategory.get(ICON_UNSELECTED).getClass());
        Assert.assertEquals(defaultPoiCategory.getString(ICON_UNSELECTED), poiCategory.getString(ICON_UNSELECTED));
    }

    private void testPoiCategoryIcon(JSONObject poiCategoryIcon, JSONObject defaultPoiCategoryIcon) throws JSONException {
        Assert.assertEquals(String.class, poiCategoryIcon.get("data").getClass());
        Assert.assertEquals(defaultPoiCategoryIcon.getString("data"), poiCategoryIcon.getString("data"));
    }

    private void testSitumConversionArea(JSONObject situmConversionArea, JSONObject defaultSitumConversionArea) throws JSONException {
        Assert.assertEquals(JSONObject.class, situmConversionArea.get(BOTTOM_LEFT).getClass());
        Assert.assertEquals(defaultSitumConversionArea.get(BOTTOM_LEFT).toString(), situmConversionArea.get(BOTTOM_LEFT).toString());
        Assert.assertEquals(Integer.class, situmConversionArea.get(FLOOR_IDENTIFIER).getClass());
        Assert.assertEquals(defaultSitumConversionArea.getInt(FLOOR_IDENTIFIER), situmConversionArea.getInt(FLOOR_IDENTIFIER));
        Assert.assertEquals(JSONObject.class, situmConversionArea.get(BOTTOM_RIGHT).getClass());
        Assert.assertEquals(defaultSitumConversionArea.get(BOTTOM_RIGHT).toString(), situmConversionArea.get(BOTTOM_RIGHT).toString());
        Assert.assertEquals(JSONObject.class, situmConversionArea.get(TOP_LEFT).getClass());
        Assert.assertEquals(defaultSitumConversionArea.get(TOP_LEFT).toString(), situmConversionArea.get(TOP_LEFT).toString());
        Assert.assertEquals(JSONObject.class, situmConversionArea.get(TOP_RIGHT).getClass());
        Assert.assertEquals(defaultSitumConversionArea.get(TOP_RIGHT).toString(), situmConversionArea.get(TOP_RIGHT).toString());
    }

    private void testCircle(JSONObject circle, JSONObject defaultCircle) throws JSONException {
        testPoint(circle.getJSONObject(CENTER), defaultCircle.getJSONObject(CENTER));
        Assert.assertEquals(Float.class, circle.get(RADIUS).getClass());
        Truth.assertThat(defaultCircle.getDouble(RADIUS)).isWithin(0.00001).of(circle.getDouble(RADIUS));
    }

    private void testNavigationProgress(JSONObject navigationProgress, JSONObject defaultNavigationProgress) throws JSONException {
        Assert.assertEquals(JSONObject.class, navigationProgress.get(CURRENT_INDICATION).getClass());
        testIndication(navigationProgress.getJSONObject(CURRENT_INDICATION), defaultNavigationProgress.getJSONObject(CURRENT_INDICATION));
        Assert.assertEquals(JSONObject.class, navigationProgress.get(NEXT_INDICATION).getClass());
        testIndication(navigationProgress.getJSONObject(NEXT_INDICATION), defaultNavigationProgress.getJSONObject(NEXT_INDICATION));
        Assert.assertEquals(Double.class, navigationProgress.get(DISTANCE_TO_END_STEP).getClass());
        Assert.assertEquals(defaultNavigationProgress.getDouble(DISTANCE_TO_END_STEP), navigationProgress.getDouble(DISTANCE_TO_END_STEP), 0);
        Assert.assertEquals(JSONObject.class, navigationProgress.get(CLOSEST_POINT_IN_ROUTE).getClass());
        testPoint(navigationProgress.getJSONObject(CLOSEST_POINT_IN_ROUTE), defaultNavigationProgress.getJSONObject(CLOSEST_POINT_IN_ROUTE));
        Assert.assertEquals(Double.class, navigationProgress.get(DISTANCE_TO_CLOSEST_POINT_IN_ROUTE).getClass());
        Assert.assertEquals(defaultNavigationProgress.getDouble(DISTANCE_TO_CLOSEST_POINT_IN_ROUTE), navigationProgress.getDouble(DISTANCE_TO_CLOSEST_POINT_IN_ROUTE), 0);
        Assert.assertEquals(Double.class, navigationProgress.get(TIME_TO_END_STEP).getClass());
        Assert.assertEquals(defaultNavigationProgress.getDouble(TIME_TO_END_STEP), navigationProgress.getDouble(TIME_TO_END_STEP), 0);
        Assert.assertEquals(JSONObject.class, navigationProgress.get(ROUTE_STEP).getClass());
        testRouteStep(navigationProgress.getJSONObject(ROUTE_STEP), defaultNavigationProgress.getJSONObject(ROUTE_STEP));
        Assert.assertEquals(Double.class, navigationProgress.get(TIME_TO_GOAL).getClass());
        Assert.assertEquals(defaultNavigationProgress.getDouble(TIME_TO_GOAL), navigationProgress.getDouble(TIME_TO_GOAL), 0);
        Assert.assertEquals(Integer.class, navigationProgress.get(CURRENT_STEP_INDEX).getClass());
        Assert.assertEquals(defaultNavigationProgress.getInt(CURRENT_STEP_INDEX), navigationProgress.getInt(CURRENT_STEP_INDEX));
        Assert.assertEquals(Double.class, navigationProgress.get(DISTANCE_TO_GOAL).getClass());
        Assert.assertEquals(defaultNavigationProgress.getDouble(DISTANCE_TO_GOAL), navigationProgress.getDouble(DISTANCE_TO_GOAL), 0);
    }

    private void testRouteStep(JSONObject routeStep, JSONObject defaultRouteStep) throws JSONException {
        Assert.assertEquals(Boolean.class, routeStep.get(IS_FIRST).getClass());
        Assert.assertEquals(defaultRouteStep.getBoolean(IS_FIRST), routeStep.getBoolean(IS_FIRST));
        Assert.assertEquals(Double.class, routeStep.get(DISTANCE).getClass());
        Assert.assertEquals(defaultRouteStep.getDouble(DISTANCE), routeStep.getDouble(DISTANCE), 0);
        Assert.assertEquals(Boolean.class, routeStep.get(IS_LAST).getClass());
        Assert.assertEquals(defaultRouteStep.getBoolean(IS_LAST), routeStep.getBoolean(IS_LAST));
        Assert.assertEquals(Double.class, routeStep.get(DISTANCE_TO_GOAL).getClass());
        Assert.assertEquals(defaultRouteStep.getDouble(DISTANCE_TO_GOAL), routeStep.getDouble(DISTANCE_TO_GOAL), 0);
        Assert.assertEquals(JSONObject.class, routeStep.get(FROM).getClass());
        testPoint(routeStep.getJSONObject(FROM), defaultRouteStep.getJSONObject(FROM));
        Assert.assertEquals(Integer.class, routeStep.get(ID).getClass());
        Assert.assertEquals(defaultRouteStep.getInt(ID), routeStep.getInt(ID));
        Assert.assertEquals(JSONObject.class, routeStep.get(TO).getClass());
        testPoint(routeStep.getJSONObject(TO), defaultRouteStep.getJSONObject(TO));
    }
}
