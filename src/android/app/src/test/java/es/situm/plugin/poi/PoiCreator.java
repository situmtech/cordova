package es.situm.plugin.poi;

import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.util.HashMap;


import es.situm.sdk.model.cartography.Poi;
import es.situm.sdk.model.cartography.PoiCategory;
import es.situm.sdk.model.cartography.Point;
import es.situm.sdk.model.location.CartesianCoordinate;
import es.situm.sdk.model.location.Coordinate;

public class PoiCreator {

    private final ClassLoader classLoader = getClass().getClassLoader();
    private final JSONParser parser = new JSONParser();

    public Poi createOutdoorPoiWithCategory() {
        Coordinate coordinate = new Coordinate(5,2);
        return new Poi.Builder("101", coordinate)
                .category(PoiCategory.DEFAULT)
                .infoHtml("TEST_INFO")
                .name("TEST_NAME")
                .build();
    }

    public Poi createPoiWithBuildingFloorAndCoordinateWithCategory() {
        Point point = new Point( "101", "12", Coordinate.EMPTY, new CartesianCoordinate(2,9));
        return new Poi.Builder(point)
                .category(PoiCategory.DEFAULT)
                .infoHtml("TEST_INFO")
                .name("TEST_NAME")
                .build();
    }

    public Poi createPoiWithCoordinateAndBuildingId() {
        Point point = new Point("101", new Coordinate(5,7));
        return new Poi.Builder(point)
                .category(PoiCategory.DEFAULT)
                .infoHtml("TEST_INFO")
                .name("TEST_NAME")
                .build();
    }

    public Poi createPoiWithBuildingFloorCoordinateAndCartesian() {
        HashMap<String, String> customFields = new HashMap<>();
        customFields.put("building", "101");
        customFields.put("test_field","test");
        Point point = new Point( "101", "12", new Coordinate(37,45), new CartesianCoordinate(2,9));
        return new Poi.Builder(point)
                .category(PoiCategory.DEFAULT)
                .infoHtml("TEST_INFO")
                .customFields(customFields)
                .name("TEST_NAME")
                .build();
    }

    public Poi createPoiWithBuildingFloorAndCoordinates() {
        Coordinate coordinate = new Coordinate(37.4534534534,54.65464564534);
        CartesianCoordinate cartesianCoordinate = new CartesianCoordinate(2,9);
        return new Poi.Builder("101", "12", coordinate, cartesianCoordinate)
                .category(PoiCategory.DEFAULT)
                .infoHtml("TEST_INFO")
                .name("TEST_NAME")
                .build();
    }

    public JSONObject getPoi1() {
        try {
            java.net.URL resource = classLoader.getResource("poi/poi1.json");
            File file = new File(resource.getFile());
            return new JSONObject(parser.parse(new FileReader(file)).toString());
        }catch(Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    public JSONObject getPoi2() {
        try {
            java.net.URL resource = classLoader.getResource("poi/poi2.json");
            File file = new File(resource.getFile());
            return new JSONObject(parser.parse(new FileReader(file)).toString());
        }catch(Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    public JSONObject getPoi3() {
        try {
            java.net.URL resource = classLoader.getResource("poi/poi3.json");
            File file = new File(resource.getFile());
            return new JSONObject(parser.parse(new FileReader(file)).toString());
        }catch(Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    public JSONObject getPoi4() {
        try {
            java.net.URL resource = classLoader.getResource("poi/poi4.json");
            File file = new File(resource.getFile());
            return new JSONObject(parser.parse(new FileReader(file)).toString());
        }catch(Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    public JSONObject getPoi5() {
        try {
            java.net.URL resource = classLoader.getResource("poi/poi5.json");
            File file = new File(resource.getFile());
            return new JSONObject(parser.parse(new FileReader(file)).toString());
        }catch(Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }
}
