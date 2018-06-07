package es.situm.plugin;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import es.situm.plugin.creators.AngleCreator;
import es.situm.sdk.model.location.Angle;

@RunWith(JUnit4.class)
public class SitumMapperTest {

  AngleCreator angleCreator = new AngleCreator();

  @Test
  public void angleCreatorTest(){
    try {
      JSONObject angleJSONObject = angleCreator.createAngleFromDegreesJSONObject();
      System.out.println(angleJSONObject);
      angleJSONObject = angleCreator.createAngleFromRadiansJSONObject();
      System.out.println(angleJSONObject);
    }catch(JSONException e) {
      System.err.println(e);
    }
  }
}
