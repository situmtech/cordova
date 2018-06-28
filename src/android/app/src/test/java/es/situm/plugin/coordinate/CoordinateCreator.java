package es.situm.plugin.coordinate;

import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.net.URL;

import es.situm.sdk.model.location.Coordinate;

public class CoordinateCreator {

    private final ClassLoader classLoader = getClass().getClassLoader();
    private final JSONParser parser = new JSONParser();

    public Coordinate createCoordinate() {
        return new Coordinate(23,43);
    }

    public JSONObject getCoordinate1() {
        try{
            URL resource = classLoader.getResource("coordinate/coordinate1.json");
            File file = new File(resource.getFile());
            return new JSONObject(parser.parse(new FileReader(file)).toString());
        }catch(Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }

}