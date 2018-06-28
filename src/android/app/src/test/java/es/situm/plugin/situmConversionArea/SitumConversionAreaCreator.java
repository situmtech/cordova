package es.situm.plugin.situmConversionArea;

import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;

import es.situm.sdk.v1.Point2f;
import es.situm.sdk.v1.SitumConversionArea;

public class SitumConversionAreaCreator {

    private final ClassLoader classLoader = getClass().getClassLoader();
    private final JSONParser parser = new JSONParser();

    public SitumConversionArea createSitumConversionArea() {
        Point2f topRight = new Point2f(5,6);
        Point2f topLeft = new Point2f(5,4);
        Point2f bottomRight = new Point2f(3,6);
        Point2f bottomLeft = new Point2f(3,4);
        return new SitumConversionArea(topRight, topLeft, bottomLeft, bottomRight, 12);
    }

    public JSONObject getSitumConversionArea1(){
        try{
            java.net.URL resource = classLoader.getResource("situmConversionArea/situmConversionArea1.json");
            File file = new File(resource.getFile());
            return new JSONObject(parser.parse(new FileReader(file)).toString());
        }catch(Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }
}
