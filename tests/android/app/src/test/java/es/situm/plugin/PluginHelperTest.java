package es.situm.plugin;

import org.json.JSONException;
import org.junit.Test;

import es.situm.sdk.model.cartography.Building;
import es.situm.sdk.model.location.Coordinate;
import es.situm.sdk.model.location.Dimensions;

public class PluginHelperTest {

    @Test
    public void fetchBuildingsTest() {
        try {
            Building building = null;
            Coordinate center = new Coordinate(15.84, 8.73);
            Dimensions dimesnsions = new Dimensions(20.44, 9.81);
            building = new Building.Builder().address("Camiño de adran 2").name("Cocodin").center(center).dimensions(dimesnsions).build();
            SitumMapperTest.buildingToJsonObjectTest(building);
        }catch(JSONException e) {
            System.err.println(e.getMessage());
        }
    }

}