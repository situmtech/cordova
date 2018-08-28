package es.situm.plugin.circle;

import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;

import es.situm.sdk.model.cartography.Circle;
import es.situm.sdk.model.cartography.Point;
import es.situm.sdk.model.location.CartesianCoordinate;
import es.situm.sdk.model.location.Coordinate;

/**
 * Created by Cristina S. Barreiro on 27/08/2018.
 */
public class CircleCreator {
    private final ClassLoader classLoader = getClass().getClassLoader();
    private final JSONParser parser = new JSONParser();

    public Circle createCircle() {

        Coordinate coordinate = new Coordinate(42.8723364829157, -8.56308907270432);
        CartesianCoordinate cartesianCoordinate = new CartesianCoordinate(22.5865678493934, 24.8444747467106);
        Point point = new Point("1051", "2767", coordinate, cartesianCoordinate);

        return new Circle(point, (float) 2.7458453630437);
    }

    public JSONObject getCircle1() {
        try {
            java.net.URL resource = classLoader.getResource("circle/circle1.json");
            File file = new File(resource.getFile());
            return new JSONObject(parser.parse(new FileReader(file)).toString());
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;

        }
    }
}
