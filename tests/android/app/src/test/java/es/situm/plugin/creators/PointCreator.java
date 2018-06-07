package es.situm.plugin.creators;

import es.situm.sdk.model.URL;
import es.situm.sdk.model.cartography.Building;
import es.situm.sdk.model.cartography.Point;
import es.situm.sdk.model.location.Angle;
import es.situm.sdk.model.location.CartesianCoordinate;
import es.situm.sdk.model.location.Coordinate;
import es.situm.sdk.model.location.Dimensions;

public class PointCreator {

    public Point createPointWithCoordinate() {
        return  new Point(new Coordinate(3,6));
    }

    public Point createPointWithCoordinateAndBuildingId() {
        return  new Point("101" , new Coordinate(54,64));
    }

    public Point createPointWithBuildingIdAndFloor() {
        Coordinate coordinate = Coordinate.EMPTY;
        CartesianCoordinate cartesianCoordinate = new CartesianCoordinate(5,7);
        return new Point("101", "12", coordinate, cartesianCoordinate);
    }

    public Point createPointWithBuildingWithAngleFromDegrees() {
        Coordinate coordinate = new Coordinate(12.3,14.0);
        Dimensions dimensions = new Dimensions( 40.5,60.2);
        Building building = new Building.Builder()
                .address("TEST_BUILDING")
                .center(coordinate)
                .dimensions(dimensions)
                .infoHtml("TEST_INFO")
                .name("TEST_NAME")
                .pictureThumbUrl(new URL("TEST_URL"))
                .pictureUrl(new URL("TEST_URL"))
                .rotation(Angle.fromDegrees(45))
                .userIdentifier("TEST_USER")
                .build();
        return new Point(building, "12",new CartesianCoordinate(4,7));
    }

    public Point createPointWithBuildingWithAngleFromRadians() {
        Coordinate coordinate = new Coordinate(12.3,14.0);
        Dimensions dimensions = new Dimensions( 40.5,60.2);
        Building building = new Building.Builder()
                .address("TEST_BUILDING")
                .center(coordinate)
                .dimensions(dimensions)
                .infoHtml("TEST_INFO")
                .name("TEST_NAME")
                .pictureThumbUrl(new URL("TEST_URL"))
                .pictureUrl(new URL("TEST_URL"))
                .rotation(Angle.fromRadians(0.3))
                .userIdentifier("TEST_USER")
                .build();
        return new Point(building, "12",new CartesianCoordinate(4,7));
    }

    public Point createPointWithBuildingWithAddress() {
        Coordinate coordinate = new Coordinate(12.3,14.0);
        Dimensions dimensions = new Dimensions( 40.5,60.2);
        Building building = new Building.Builder()
                .center(coordinate)
                .dimensions(dimensions)
                .name("TEST_NAME")
                .address("TEST_ADDRESS")
                .rotation(Angle.fromRadians(0.3))
                .userIdentifier("TEST_USER")
                .build();
        return new Point(building, "12",new CartesianCoordinate(4,7));
    }

    public Point createPointWithBuildingWithInfo() {
        Coordinate coordinate = new Coordinate(12.3,14.0);
        Dimensions dimensions = new Dimensions( 40.5,60.2);
        Building building = new Building.Builder()
                .center(coordinate)
                .dimensions(dimensions)
                .name("TEST_NAME")
                .rotation(Angle.fromRadians(0.3))
                .userIdentifier("TEST_USER")
                .infoHtml("TEST_INFO")
                .build();
        return new Point(building, "12",new CartesianCoordinate(4,7));
    }

    public Point createPointWithBuildingWithPicture() {
        Coordinate coordinate = new Coordinate(12.3,14.0);
        Dimensions dimensions = new Dimensions( 40.5,60.2);
        Building building = new Building.Builder()
                .center(coordinate)
                .dimensions(dimensions)
                .name("TEST_NAME")
                .rotation(Angle.fromRadians(0.3))
                .userIdentifier("TEST_USER")
                .pictureUrl(new URL("TEST_URL"))
                .pictureThumbUrl(new URL("TEST_URL"))
                .build();
        return new Point(building, "12",new CartesianCoordinate(4,7));
    }
}
