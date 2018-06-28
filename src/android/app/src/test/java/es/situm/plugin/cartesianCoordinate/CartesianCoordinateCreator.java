package es.situm.plugin.cartesianCoordinate;

import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.net.URL;

import es.situm.sdk.model.location.CartesianCoordinate;

public class CartesianCoordinateCreator {

    private final ClassLoader classLoader = getClass().getClassLoader();
    private final JSONParser parser = new JSONParser();

    public CartesianCoordinate createCartesianCoordinate() {
        return new CartesianCoordinate(5,7);
    }

    public JSONObject getCartesianCoordinate1() {
        try{
            URL resource = classLoader.getResource("cartesianCoordinate/cartesianCoordinate1.json");
            File file = new File(resource.getFile());
            return new JSONObject(parser.parse(new FileReader(file)).toString());
        }catch(Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }
}
