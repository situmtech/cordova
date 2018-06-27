package es.situm.plugin;

import org.junit.Test;

import es.situm.plugin.poiCategory.PoiCategoryCreator;
import es.situm.sdk.model.cartography.PoiCategory;
import es.situm.sdk.model.location.Coordinate;
import es.situm.plugin.coordinate.CoordinateCreator;

public class PluginHelperTest {

    CoordinateCreator coordinateCreator = new CoordinateCreator();
    PoiCategoryCreator poiCategoryCreator = new PoiCategoryCreator();

    @Test
    public void fetchBuildingsTest() {
        try {
            PoiCategory category = poiCategoryCreator.createPoiCategory();
            System.out.println(category);
            Coordinate center = coordinateCreator.createCoordinate();
            System.out.println(center);
        }catch(Exception e) {
            System.err.println(e.getMessage());
        }
    }

}