package es.situm.plugin.creators;

import es.situm.sdk.model.location.Bounds;
import es.situm.sdk.model.location.Coordinate;

public class BoundsCreator {

    public Bounds createBoundsWithArray() {
        Coordinate[] coordinates = {new Coordinate(2,4), new Coordinate(6,3),
                new Coordinate(2,6),new Coordinate(5,8)};
        return new Bounds(coordinates);
    }

    public Bounds createBounds() {
        return new Bounds(new Coordinate(2,4), new Coordinate(6,3),
                new Coordinate(2,6),new Coordinate(5,8));
    }
}
