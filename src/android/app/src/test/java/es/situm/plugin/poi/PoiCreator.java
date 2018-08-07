package es.situm.plugin.poi;

import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;

import es.situm.sdk.model.I18nString;
import es.situm.sdk.model.URL;
import es.situm.sdk.model.cartography.Poi;
import es.situm.sdk.model.cartography.PoiCategory;
import es.situm.sdk.model.cartography.Point;
import es.situm.sdk.model.location.CartesianCoordinate;
import es.situm.sdk.model.location.Coordinate;

public class PoiCreator {

    private final ClassLoader classLoader = getClass().getClassLoader();
    private final JSONParser parser = new JSONParser();

    public Poi createPoi() {
        PoiCategory category = new PoiCategory.Builder()
                .code("TEST_CODE")
                .isPublic(true)
                .selectedIcon(new URL("TEST_URL"))
                .unselectedIcon(new URL("TEST_URL"))
                .name(new I18nString.Builder().anyLanguage("TEST_STRING").put("TEST_LANGUAGE","TEST_STRING").build())
                .build();
        Coordinate coordinate = new Coordinate(23,43);
        CartesianCoordinate cartesianCoordinate = new CartesianCoordinate(5,7);
        Point point = new Point("101", "12", coordinate, cartesianCoordinate);
        return new Poi.Builder(point)
                .category(category)
                .name("TEST_NAME")
                .point(point)
                .infoHtml("TEST_INFO")
                .build();
    }

    public JSONObject getPoi1(){
        try{
            java.net.URL resource = classLoader.getResource("poi/poi1.json");
            File file = new File(resource.getFile());
            return new JSONObject(parser.parse(new FileReader(file)).toString());
        }catch(Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }
}
