package es.situm.plugin;


import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Date;

import es.situm.plugin.angle.AngleCreator;
import es.situm.plugin.creators.BoundsCreator;
import es.situm.plugin.creators.CartesianCoordinateCreator;
import es.situm.plugin.creators.CoordinateCreator;
import es.situm.plugin.creators.DimensionsCreator;
import es.situm.plugin.creators.FloorCreator;
import es.situm.plugin.creators.IndicationCreator;
import es.situm.plugin.creators.LocationCreator;
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

    // Creators
    private AngleCreator angleCreator = new AngleCreator();
    private BoundsCreator boundsCreator = new BoundsCreator();
    private CartesianCoordinateCreator cartesianCoordinateCreator = new CartesianCoordinateCreator();
    private CoordinateCreator coordinateCreator = new CoordinateCreator();
    private DimensionsCreator dimensionsCreator = new DimensionsCreator();
    private FloorCreator floorCreator = new FloorCreator();
    private IndicationCreator indicationCreator = new IndicationCreator();
    private LocationCreator locationCreator = new LocationCreator();

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
    private static final String MAPURL = "mapUrl";
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

    @Test
    public void angleJSONObjectTest(){
        try {
            Angle angle = angleCreator.createAngleFromDegrees();
            JSONObject angleJSONObject =  SitumMapper.angleToJsonObject(angle);
            JSONObject angle_1 = angleCreator.getAngle1();
            testAngle(angleJSONObject, angle_1);
            angle = angleCreator.createAngleFromRadians();
            System.out.println(angle);
        }catch(JSONException e){
            System.out.println(e.getMessage());
        }
    }

  @Test
  public void boundsJSONObjectTest() {
      try{
          Bounds bounds = boundsCreator.createBounds();
          JSONObject boundsJSONObject = SitumMapper.boundsToJsonObject(bounds);
          testBounds(boundsJSONObject);
          testCoordinate((JSONObject) boundsJSONObject.get(NORTH_WEST));
          testCoordinate((JSONObject) boundsJSONObject.get(SOUTH_WEST));
          testCoordinate((JSONObject) boundsJSONObject.get(NORTH_EAST));
          testCoordinate((JSONObject) boundsJSONObject.get(SOUTH_EAST));
          bounds = boundsCreator.createBoundsWithArray();
          boundsJSONObject = SitumMapper.boundsToJsonObject(bounds);
          testBounds(boundsJSONObject);
          testCoordinate((JSONObject) boundsJSONObject.get(NORTH_WEST));
          testCoordinate((JSONObject) boundsJSONObject.get(SOUTH_WEST));
          testCoordinate((JSONObject) boundsJSONObject.get(NORTH_EAST));
          testCoordinate((JSONObject) boundsJSONObject.get(SOUTH_EAST));
      }catch(JSONException e){
          System.out.println(e.getMessage());
      }
  }

  @Test
  public void cartesianCoordinateJSONObjectTest() {
      try{
          CartesianCoordinate cartesianCoordinate = cartesianCoordinateCreator.createCartesianCoordinate();
          JSONObject cartesianCoordinateJSONObject = SitumMapper.cartesianCoordinateToJsonObject(cartesianCoordinate);
          testCartesianCoordinate(cartesianCoordinateJSONObject);
      }catch(JSONException e){
          System.out.println(e.getMessage());
      }
  }

  @Test
  public void coordinateJSONObjectTest() {
      try{
          Coordinate coordinate = coordinateCreator.createCoordinate();
          JSONObject coordinateJSONObject = SitumMapper.coordinateToJsonObject(coordinate);
          testCoordinate(coordinateJSONObject);
      }catch(JSONException e){
          System.out.println(e.getMessage());
      }
  }

  @Test
  public void dimensionJSONObjectTest() {
      try{
          Dimensions dimensions = dimensionsCreator.createDimensions();
          JSONObject dimessionsJSONObject = SitumMapper.dimensionsToJsonObject(dimensions);
          testDimensions(dimessionsJSONObject);
      }catch(JSONException e){
          System.out.println(e.getMessage());
      }
  }

  @Test
  public void floorJSONObjectTest() {
      try{
          Floor floor = floorCreator.createFloorWithAltitude();
          JSONObject floorJSONObject = SitumMapper.floorToJsonObject(floor);
          testFloor(floorJSONObject);
          floor = floorCreator.createFloorWithoutAltitude();
          floorJSONObject = SitumMapper.floorToJsonObject(floor);
          testFloor(floorJSONObject);
      }catch(JSONException e){
          System.out.println(e.getMessage());
      }
  }

  @Test
  public void indicationJSONObjectTest() {
      try{
          Indication indication = indicationCreator.createIndication();
          JSONObject indicationJSONObject = SitumMapper.indicationToJsonObject(indication);
          testIndication(indicationJSONObject);
      }catch (JSONException e){
          System.out.println(e.getMessage());
      }
  }

  @Test
  public void locationJSONObjectTest() {
      try{
          Location location = locationCreator.createLocationWithBuildingFloorAndCartesianCoordinates();
          JSONObject locationJSONObject = SitumMapper.locationToJsonObject(location);
          testLocation(locationJSONObject);
          testCoordinate((JSONObject) locationJSONObject.get(COORDINATE));
          testCartesianCoordinate((JSONObject) locationJSONObject.get(CARTESIAN_COORDINATE));
          testPoint((JSONObject) locationJSONObject.get(POSITION));
          testCoordinate((JSONObject) ((JSONObject) locationJSONObject.get(POSITION)).get(COORDINATE));
          testCartesianCoordinate((JSONObject) ((JSONObject) locationJSONObject.get(POSITION)).get(CARTESIAN_COORDINATE));
          location = locationCreator.createLocationWithCoordinate();
          locationJSONObject = SitumMapper.locationToJsonObject(location);
          System.out.println(locationJSONObject);
      }catch(JSONException e){
          System.out.println(e.getMessage());
      }
  }

  private void testAngle(JSONObject angle, JSONObject defaultAngle) throws JSONException{
      Assert.assertEquals(Double.class, angle.get(RADIANS_MINUS_PI_PI).getClass());
      Assert.assertEquals(angle.get(RADIANS_MINUS_PI_PI), defaultAngle.get(RADIANS_MINUS_PI_PI));
      Assert.assertEquals(Double.class, angle.get(RADIANS).getClass());
      Assert.assertEquals(angle.get(RADIANS), defaultAngle.get(RADIANS));
      Assert.assertEquals(Double.class, angle.get(DEGREES_CLOCKWISE).getClass());
      Assert.assertEquals(angle.get(DEGREES_CLOCKWISE), defaultAngle.get(DEGREES_CLOCKWISE));
      Assert.assertEquals(Double.class, angle.get(DEGREES).getClass());
      Assert.assertEquals(angle.get(DEGREES), defaultAngle.get(DEGREES));
  }

  private void testBounds(JSONObject bounds) throws JSONException {
      Assert.assertEquals(JSONObject.class, bounds.get(NORTH_WEST).getClass());
      Assert.assertEquals(JSONObject.class, bounds.get(SOUTH_WEST).getClass());
      Assert.assertEquals(JSONObject.class, bounds.get(NORTH_EAST).getClass());
      Assert.assertEquals(JSONObject.class, bounds.get(SOUTH_EAST).getClass());
  }

  private void testCartesianCoordinate(JSONObject cartesianCoordinate) throws JSONException {
    Assert.assertEquals(Double.class, cartesianCoordinate.get(X).getClass());
    Assert.assertEquals(Double.class, cartesianCoordinate.get(Y).getClass());
  }

  private void testCoordinate(JSONObject coordinate) throws JSONException{
      Assert.assertEquals(Double.class, coordinate.get(LATITUDE).getClass());
      Assert.assertEquals(Double.class, coordinate.get(LONGITUDE).getClass());
  }

  private void testDimensions(JSONObject dimensions) throws JSONException {
      Assert.assertEquals(Double.class, dimensions.get(WIDTH).getClass());
      Assert.assertEquals(Double.class, dimensions.get(HEIGHT).getClass());
  }

  private void testFloor(JSONObject floor) throws JSONException {
      Assert.assertEquals(Double.class, floor.get(ALTITUDE).getClass());
      Assert.assertEquals(Date.class, floor.get(CREATED_AT).getClass());
      Assert.assertEquals(String.class, floor.get(FLOOR_IDENTIFIER).getClass());
      Assert.assertEquals(Integer.class, floor.get(LEVEL).getClass());
      Assert.assertEquals(JSONObject.class, floor.get(CUSTOM_FIELDS).getClass());
      Assert.assertEquals(String.class, floor.get(MAPURL).getClass());
      Assert.assertEquals(Double.class, floor.get(SCALE).getClass());
      Assert.assertEquals(String.class, floor.get(BUILDING_IDENTIFIER).getClass());
      Assert.assertEquals(Date.class, floor.get(UPDATED_AT).getClass());
  }

  private void testIndication(JSONObject indication) throws JSONException {
      Assert.assertEquals(Indication.Orientation.class, indication.get(ORIENTATION_TYPE).getClass());
      Assert.assertEquals(Double.class, indication.get(ORIENTATION).getClass());
      Assert.assertEquals(Integer.class, indication.get(STEP_IDX_DESTINTATION).getClass());
      Assert.assertEquals(Double.class, indication.get(DISTANCE).getClass());
      Assert.assertEquals(Integer.class, indication.get(STEP_IDX_ORIGIN).getClass());
      Assert.assertEquals(String.class, indication.get(INDICATION_TYPE).getClass());
      Assert.assertEquals(Integer.class, indication.get(DISTANCE_TO_NEXT_LEVEL).getClass());
      Assert.assertEquals(Boolean.class, indication.get(NEEDED_LEVEL_CHANGE).getClass());
  }

  private void testLocation(JSONObject location) throws JSONException {
      Assert.assertEquals(Boolean.class, location.get(HAS_CARTESIAN_BEARING).getClass());
      Assert.assertEquals(JSONObject.class, location.get(COORDINATE).getClass());
      Assert.assertEquals(JSONObject.class, location.get(BEARING).getClass());
      Assert.assertEquals(Double.class, location.get(ACCURACY).getClass());
      Assert.assertEquals(Boolean.class, location.get(IS_INDOOR).getClass());
      Assert.assertEquals(String.class, location.get(DEVICE_ID).getClass());
      Assert.assertEquals(String.class, location.get(BUILDING_IDENTIFIER).getClass());
      Assert.assertEquals(String.class, location.get(QUALITY).getClass());
      Assert.assertEquals(Boolean.class, location.get(IS_OUTDOOR).getClass());
      Assert.assertEquals(String.class, location.get(FLOOR_IDENTIFIER).getClass());
      Assert.assertEquals(String.class, location.get(PROVIDER).getClass());
      Assert.assertEquals(JSONObject.class, location.get(CARTESIAN_BEARING).getClass());
      Assert.assertEquals(String.class, location.get(BEARING_QUALITY).getClass());
      Assert.assertEquals(Boolean.class, location.get(HAS_BEARING).getClass());
      Assert.assertEquals(JSONObject.class, location.get(CARTESIAN_COORDINATE).getClass());
      Assert.assertEquals(JSONObject.class, location.get(POSITION).getClass());
      Assert.assertEquals(Long.class, location.get(TIMESTAMP).getClass());
  }

  private void testPoint(JSONObject point) throws JSONException {
      Assert.assertEquals(JSONObject.class, point.get(COORDINATE).getClass());
      Assert.assertEquals(String.class, point.get(FLOOR_IDENTIFIER).getClass());
      Assert.assertEquals(JSONObject.class, point.get(CARTESIAN_COORDINATE).getClass());
      Assert.assertEquals(Boolean.class, point.get(IS_INDOOR).getClass());
      Assert.assertEquals(String.class, point.get(BUILDING_IDENTIFIER).getClass());
      Assert.assertEquals(Boolean.class, point.get(IS_OUTDOOR).getClass());
  }
}
