package es.situm.plugin.floor;

import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.net.URL;
import es.situm.sdk.model.cartography.Floor;

public class FloorCreator {

    private final ClassLoader classLoader = getClass().getClassLoader();
    private final JSONParser parser = new JSONParser();

    public Floor createFloorWithAltitude() {
        return new Floor.Builder()
                .altitude(2.5)
                .buildingIdentifier("101")
                .level(1)
                .name("testName")
                .mapUrl(new es.situm.sdk.model.URL("TEST_URL"))
                .scale(10.2)
                .build();
    }

    public Floor createFloorWithoutAltitude() {
        return new Floor.Builder()
                .buildingIdentifier("101")
                .level(1)
                .name("testName")
                .mapUrl(new es.situm.sdk.model.URL("TEST_URL"))
                .scale(10.2)
                .build();
    }

    public JSONObject getFloor1(){
        try{
            URL resource = classLoader.getResource("floor/floor1.json");
            File file = new File(resource.getFile());
            return new JSONObject(parser.parse(new FileReader(file)).toString());
        }catch(Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    public JSONObject getFloor2(){
        try{
            URL resource = classLoader.getResource("floor/floor2.json");
            File file = new File(resource.getFile());
            return new JSONObject(parser.parse(new FileReader(file)).toString());
        }catch(Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }
}
