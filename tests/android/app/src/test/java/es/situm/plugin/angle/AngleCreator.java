package es.situm.plugin.angle;

import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.net.URL;

import es.situm.sdk.model.location.Angle;

public class AngleCreator {

    private final JSONParser parser = new JSONParser();
    private final ClassLoader classLoader = getClass().getClassLoader();

    public Angle createAngleFromDegrees() {
        return  Angle.fromDegrees(47);
    }

    public Angle createAngleFromRadians() {
        return Angle.fromRadians(1.4);
    }

    public JSONObject getAngle1(){
        try{
            URL resource = classLoader.getResource("angle/angle1.json");
            File file = new File(resource.getFile());
            return new JSONObject(parser.parse(new FileReader(file)).toString());
        }catch(Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    public JSONObject getAngle2(){
        try{
            URL resource = classLoader.getResource("angle/angle2.json");
            File file = new File(resource.getFile());
            return new JSONObject(parser.parse(new FileReader(file)).toString());
        }catch(Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }
}
