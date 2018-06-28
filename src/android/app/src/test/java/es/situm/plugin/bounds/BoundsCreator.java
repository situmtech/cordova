package es.situm.plugin.bounds;

import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.net.URL;

import es.situm.sdk.model.location.Bounds;
import es.situm.sdk.model.location.Coordinate;

public class BoundsCreator {

    private final JSONParser parser = new JSONParser();
    private final ClassLoader classLoader = getClass().getClassLoader();

    public Bounds createBoundsWithArray() {
        Coordinate[] coordinates = {new Coordinate(2,4), new Coordinate(6,3),
                new Coordinate(2,6),new Coordinate(5,8)};
        return new Bounds(coordinates);
    }

    public Bounds createBounds() {
        return new Bounds(new Coordinate(2,4), new Coordinate(6,3),
                new Coordinate(2,6),new Coordinate(5,8));
    }

    public JSONObject getBounds1(){
        try{
            URL resource = classLoader.getResource("bounds/bounds1.json");
            File file = new File(resource.getFile());
            return new JSONObject(parser.parse(new FileReader(file)).toString());
        }catch(Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    public JSONObject getBounds2(){
        try{
            URL resource = classLoader.getResource("bounds/bounds2.json");
            File file = new File(resource.getFile());
            return new JSONObject(parser.parse(new FileReader(file)).toString());
        }catch(Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }
}
