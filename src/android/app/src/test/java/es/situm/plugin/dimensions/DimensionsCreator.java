package es.situm.plugin.dimensions;

import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.net.URL;

import es.situm.sdk.model.location.Dimensions;

public class DimensionsCreator {

    private final ClassLoader classLoader = getClass().getClassLoader();
    private final JSONParser parser = new JSONParser();

    public Dimensions createDimensions() {
        return new Dimensions(5,7);
    }

    public JSONObject getDimensions1(){
        try{
            URL resource = classLoader.getResource("dimensions/dimensions1.json");
            File file = new File(resource.getFile());
            return new JSONObject(parser.parse(new FileReader(file)).toString());
        }catch(Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }
}