package es.situm.plugin.creators;

import org.json.JSONException;
import org.json.JSONObject;

import es.situm.sdk.model.location.Angle;

public class AngleCreator {

    static final String DEGREES = "degrees";
    static final String RADIANS = "radians";

    public Angle createAngleFromDegrees() {
        return  Angle.fromDegrees(47);
    }

    public JSONObject createAngleFromDegreesJSONObject() throws JSONException{
        Angle angle = createAngleFromDegrees();
        JSONObject angleJSONObject = new JSONObject();
        angleJSONObject.put(DEGREES, angle.degrees());
        angleJSONObject.put(RADIANS, angle.radians());
        return angleJSONObject;
    }

    public Angle createAngleFromRadians() {
        return Angle.fromRadians(1.4);
    }

    public JSONObject createAngleFromRadiansJSONObject() throws JSONException {
        Angle angle = createAngleFromRadians();
        System.out.println(angle);
        JSONObject angleJSONObject = new JSONObject();
        angleJSONObject.put(DEGREES, angle.degrees());
        angleJSONObject.put(RADIANS, angle.radians());
        return angleJSONObject;
    }
}
