package es.situm.plugin.creators;

import es.situm.sdk.model.URL;
import es.situm.sdk.model.cartography.Floor;

public class FloorCreator {

    public Floor createFloorWithAltitude() {
        return new Floor.Builder()
                .altitude(2.5)
                .buildingIdentifier("101")
                .level(1)
                .mapUrl(new URL("TEST_URL"))
                .scale(10.2)
                .build();
    }

    public Floor createFloorWithoutAltitude() {
        return new Floor.Builder()
                .buildingIdentifier("101")
                .level(1)
                .mapUrl(new URL("TEST_URL"))
                .scale(10.2)
                .build();
    }
}
