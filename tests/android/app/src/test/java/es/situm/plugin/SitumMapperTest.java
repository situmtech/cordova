package es.situm.plugin;


import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.util.Date;

import es.situm.plugin.angle.AngleCreator;
import es.situm.plugin.bounds.BoundsCreator;
import es.situm.plugin.cartesianCoordinate.CartesianCoordinateCreator;
import es.situm.plugin.coordinate.CoordinateCreator;
import es.situm.plugin.locationStatus.LocationStatusCreator;
import es.situm.plugin.dimensions.DimensionsCreator;
import es.situm.plugin.floor.FloorCreator;
import es.situm.plugin.indication.IndicationCreator;
import es.situm.plugin.location.LocationCreator;
import es.situm.sdk.location.LocationStatus;
import es.situm.sdk.model.cartography.Floor;
import es.situm.sdk.model.directions.Indication;
import es.situm.sdk.model.location.Angle;
import es.situm.sdk.model.location.Bounds;
import es.situm.sdk.model.location.CartesianCoordinate;
import es.situm.sdk.model.location.Coordinate;
import es.situm.sdk.model.location.Dimensions;
import es.situm.sdk.model.location.Location;

@RunWith(JUnit4.class)
public class SitumMapperTest {

    private DateFormat dateFormat = DateFormat.getInstance();

    // Properties names
    private static final String RADIANS_MINUS_PI_PI = "radiansMinusPiPi";
    private static final String RADIANS = "radians";
    private static final String DEGREES_CLOCKWISE = "degreesClockwise";
    private static final String DEGREES = "degrees";
    private static final String NORTH_WEST = "northWest";
    private static final String SOUTH_WEST = "northWest";
    private static final String NORTH_EAST = "northWest";
    private static final String SOUTH_EAST = "northWest";
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
            System.out.println(e.getMessage());
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
            System.out.println(e.getMessage());
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
            System.out.println(e.getMessage());
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
            System.out.println(e.getMessage());
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
            System.out.println(e.getMessage());
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
            System.out.println(e.getMessage());
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
            System.out.println(e.getMessage());
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
        } catch (JSONException e) {
            System.out.println(e.getMessage());
        }
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

    private void testBounds(JSONObject bounds, JSONObject defaultBounds) throws JSONException {
        Assert.assertEquals(JSONObject.class, bounds.get(NORTH_WEST).getClass());
        testCoordinate(defaultBounds.getJSONObject(NORTH_WEST), bounds.getJSONObject(NORTH_WEST));
        Assert.assertEquals(JSONObject.class, bounds.get(SOUTH_WEST).getClass());
        testCoordinate(defaultBounds.getJSONObject(SOUTH_WEST), bounds.getJSONObject(SOUTH_WEST));
        Assert.assertEquals(JSONObject.class, bounds.get(NORTH_EAST).getClass());
        testCoordinate(defaultBounds.getJSONObject(NORTH_EAST), bounds.getJSONObject(NORTH_EAST));
        Assert.assertEquals(JSONObject.class, bounds.get(SOUTH_EAST).getClass());
        testCoordinate(defaultBounds.getJSONObject(SOUTH_EAST), bounds.getJSONObject(SOUTH_EAST));
    }

    private void testCartesianCoordinate(JSONObject cartesianCoordinate, JSONObject defaultCartesianCoordinate) throws JSONException {
        Assert.assertEquals(Double.class, cartesianCoordinate.get(X).getClass());
        Assert.assertEquals(defaultCartesianCoordinate.getDouble(X), cartesianCoordinate.getDouble(X), 0);
        Assert.assertEquals(Double.class, cartesianCoordinate.get(Y).getClass());
        Assert.assertEquals(defaultCartesianCoordinate.getDouble(Y), cartesianCoordinate.getDouble(Y), 0);
    }

    private void testCoordinate(JSONObject coordinate, JSONObject defaultCoordinate) throws JSONException {
        Assert.assertEquals(Double.class, coordinate.get(LATITUDE).getClass());
        Assert.assertEquals(defaultCoordinate.getDouble(LATITUDE), coordinate.getDouble(LATITUDE), 0);
        Assert.assertEquals(Double.class, coordinate.get(LONGITUDE).getClass());
        Assert.assertEquals(defaultCoordinate.getDouble(LONGITUDE), coordinate.getDouble(LONGITUDE), 0);
    }

    private void testDimensions(JSONObject dimensions, JSONObject defaultDimensions) throws JSONException {
        Assert.assertEquals(Double.class, dimensions.get(WIDTH).getClass());
        Assert.assertEquals(defaultDimensions.getDouble(WIDTH), dimensions.getDouble(WIDTH), 0);
        Assert.assertEquals(Double.class, dimensions.get(HEIGHT).getClass());
        Assert.assertEquals(defaultDimensions.getDouble(HEIGHT), dimensions.getDouble(HEIGHT), 0);
    }

    private void testFloor(JSONObject floor, JSONObject defaultFloor) throws JSONException {
        Assert.assertEquals(Double.class, floor.get(ALTITUDE).getClass());
        Assert.assertEquals(defaultFloor.getDouble(ALTITUDE), floor.getDouble(ALTITUDE), 0);
        Assert.assertEquals(Date.class, floor.get(CREATED_AT).getClass());
        Assert.assertEquals(dateFormat.parseObject(defaultFloor.get(CREATED_AT).toString(), new ParsePosition(0)), dateFormat.parseObject(floor.get(CREATED_AT).toString(), new ParsePosition(0)));
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
        Assert.assertEquals(Date.class, floor.get(UPDATED_AT).getClass());
        Assert.assertEquals(dateFormat.parseObject(defaultFloor.get(UPDATED_AT).toString(), new ParsePosition(0)), dateFormat.parseObject(floor.get(UPDATED_AT).toString(), new ParsePosition(0)));
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

    private void testLocationStatus(JSONObject locationStatus, JSONObject defaultLocationStatus) throws JSONException{
        Assert.assertEquals(Integer.class, locationStatus.get(STATUS_ORDINAL).getClass());
        Assert.assertEquals(defaultLocationStatus.getInt(STATUS_ORDINAL), locationStatus.getInt(STATUS_ORDINAL));
        Assert.assertEquals(String.class, locationStatus.get(STATUS_NAME).getClass());
        Assert.assertEquals(defaultLocationStatus.getString(STATUS_NAME), locationStatus.getString(STATUS_NAME));
    }
}
