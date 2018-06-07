package es.situm.plugin.models;

import es.situm.sdk.model.URL;
import es.situm.sdk.model.cartography.Building;
import es.situm.sdk.model.cartography.Point;
import es.situm.sdk.model.directions.RouteStep;
import es.situm.sdk.model.location.Angle;
import es.situm.sdk.model.location.CartesianCoordinate;
import es.situm.sdk.model.location.Coordinate;
import es.situm.sdk.model.location.Dimensions;

public class RouteStepCreator {

    public RouteStep createRouteStepWithCoordinate() {
        Point from = new Point(new Coordinate(2,5));
        Point to = new Point(new Coordinate(3,4));
        return new RouteStep.Builder()
                .distance(23.4)
                .distanceToEnd(27)
                .distanceToFloorChange(13.6)
                .from(from)
                .id(2)
                .isLast(false)
                .to(to)
                .build();
    }

    public RouteStep createRouteStepWithCoordinateAndBuildingId() {
        Point from = new Point("101", new Coordinate(2,5));
        Point to = new Point("101", new Coordinate(3,4));
        return new RouteStep.Builder()
                .distance(23.4)
                .distanceToEnd(27)
                .distanceToFloorChange(13.6)
                .from(from)
                .id(2)
                .isLast(false)
                .to(to)
                .build();
    }

    public RouteStep createRouteStepWithCoordinateBuildingIdAndFloor() {
        Point from = new Point("101", "12", Coordinate.EMPTY, new CartesianCoordinate(3,5));
        Point to = new Point("101", "12", Coordinate.EMPTY, new CartesianCoordinate(3,4));
        return new RouteStep.Builder()
                .distance(23.4)
                .distanceToEnd(27)
                .distanceToFloorChange(13.6)
                .from(from)
                .id(2)
                .isLast(false)
                .to(to)
                .build();
    }

    public RouteStep createRouteStepWithBuildingWithAngleFromDegree() {
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
        Point from = new Point(building, "12", new CartesianCoordinate(3,5));
        Point to = new Point(building, "12", new CartesianCoordinate(3,4));
        return new RouteStep.Builder()
                .distance(23.4)
                .distanceToEnd(27)
                .distanceToFloorChange(13.6)
                .from(from)
                .id(2)
                .isLast(false)
                .to(to)
                .build();
    }

    public RouteStep createRouteStepWithBuildingWithAngleFromRadians() {
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
                .rotation(Angle.fromRadians(0.4))
                .userIdentifier("TEST_USER")
                .build();
        Point from = new Point(building, "12", new CartesianCoordinate(3,5));
        Point to = new Point(building, "12", new CartesianCoordinate(3,4));
        return new RouteStep.Builder()
                .distance(23.4)
                .distanceToEnd(27)
                .distanceToFloorChange(13.6)
                .from(from)
                .id(2)
                .isLast(false)
                .to(to)
                .build();
    }
}
