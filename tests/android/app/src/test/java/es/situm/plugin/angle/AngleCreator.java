package es.situm.plugin.angle;


import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

import es.situm.sdk.model.location.Angle;

public class AngleCreator {

    private static final JSONParser parser = new JSONParser();

    public Angle createAngleFromDegrees() {
        return  Angle.fromDegrees(47);
    }

    public Angle createAngleFromRadians() {
        return Angle.fromRadians(1.4);
    }

    public JSONObject getAngle1() {
        try {
            return (JSONObject) parser.parse(new FileReader("./Angle_1.json"));
        }catch(Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }
}
