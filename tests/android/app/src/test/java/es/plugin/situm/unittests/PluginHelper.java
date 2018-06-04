package es.plugin.situm.unittests;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.net.URL;

public class PluginHelper{

  JSONParser parser;
  ClassLoader classLoader;

  @Before
  public void prepareTests() {
    parser = new JSONParser();
    classLoader = getClass().getClassLoader();
  }

  @Test
  public void fetchBuildings() throws Exception {
      URL resource = classLoader.getResource("fetch_buildings.json");
      File fetchBuildings = new File(resource.getFile());
      Object fileContent = parser.parse(new FileReader(fetchBuildings));
      JSONArray buildings = (JSONArray) fileContent;
      for(int i = 0; i < buildings.size(); i++) {
          Assert.assertEquals(buildings.get(i).getClass(), JSONObject.class);
          JSONObject building = (JSONObject) buildings.get(i);
          Assert.assertEquals(building.get("address").getClass(), String.class);
          Assert.assertEquals(building.get("bounds").getClass(), JSONObject.class);
          Assert.assertEquals(building.get("boundsRotated").getClass(), JSONObject.class);
          Assert.assertEquals(building.get("center").getClass(), JSONObject.class);
          Assert.assertEquals(building.get("dimensions").getClass(), JSONObject.class);
          Assert.assertEquals(building.get("infoHtml").getClass(), String.class);
          Assert.assertEquals(building.get("name").getClass(), String.class);
          Assert.assertEquals(building.get("pictureThumbUrl").getClass(), String.class);
          Assert.assertEquals(building.get("pictureUrl").getClass(), String.class);
          Assert.assertEquals(building.get("rotation").getClass(), Double.class);
          Assert.assertEquals(building.get("userIdentifier").getClass(), String.class);
          Assert.assertEquals(building.get("buildingIdentifier").getClass(), String.class);
          Assert.assertEquals(building.get("customFields").getClass(), JSONObject.class);
          testBoundsJsonObject((JSONObject) building.get("bounds"));
          testBoundsJsonObject((JSONObject) building.get("boundsRotated"));
          testCoordinateJsonObject((JSONObject) building.get("center"));
          testDimensionJsonObject((JSONObject) building.get("dimensions"));
      }
  }

  @Test
  public void fetchFloorsFromBuilding() throws Exception {
      URL resource = classLoader.getResource("fetch_floors_from_building.json");
      File fetchFloors = new File(resource.getFile());
      Object fileContent = parser.parse(new FileReader(fetchFloors));
      JSONArray floors = (JSONArray) fileContent;
      for(int i = 0; i < floors.size(); i++) {
          Assert.assertEquals(floors.get(i).getClass(), JSONObject.class);
          JSONObject floor = (JSONObject) floors.get(i);
          Assert.assertEquals(floor.get("altitude").getClass(), Long.class);
          Assert.assertEquals(floor.get("buildingIdentifier").getClass(), String.class);
          Assert.assertEquals(floor.get("level").getClass(), Long.class);
          Assert.assertEquals(floor.get("mapUrl").getClass(), String.class);
          Assert.assertEquals(floor.get("scale").getClass(), Double.class);
          Assert.assertEquals(floor.get("floorIdentifier").getClass(), String.class);
      }
  }

  public void testBoundsJsonObject(JSONObject bounds) {
      Assert.assertEquals(bounds.get("northEast").getClass(), JSONObject.class);
      Assert.assertEquals(bounds.get("northWest").getClass(), JSONObject.class);
      Assert.assertEquals(bounds.get("southEast").getClass(), JSONObject.class);
      Assert.assertEquals(bounds.get("southWest").getClass(), JSONObject.class);
      testCoordinateJsonObject((JSONObject) bounds.get("northEast"));
      testCoordinateJsonObject((JSONObject) bounds.get("northWest"));
      testCoordinateJsonObject((JSONObject) bounds.get("southEast"));
      testCoordinateJsonObject((JSONObject) bounds.get("southWest"));
  }

  public void testCoordinateJsonObject(JSONObject coordinate) {
      Assert.assertEquals(coordinate.get("latitude").getClass(), Double.class);
      Assert.assertEquals(coordinate.get("longitude").getClass(), Double.class);
  }

  public void testDimensionJsonObject(JSONObject dimensions) {
      Assert.assertEquals(dimensions.get("width").getClass(), Double.class);
      Assert.assertEquals(dimensions.get("height").getClass(), Double.class);
  }
}
