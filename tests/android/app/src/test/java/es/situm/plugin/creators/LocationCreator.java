package es.situm.plugin.creators;

import es.situm.sdk.model.cartography.Point;
import es.situm.sdk.model.location.Angle;
import es.situm.sdk.model.location.CartesianCoordinate;
import es.situm.sdk.model.location.Coordinate;
import es.situm.sdk.model.location.Location;

public class LocationCreator {

    public Location createLocationWithBuildingFloorAndCartesianCoordinates() {
        Point point = new Point("101", "12", Coordinate.EMPTY, new CartesianCoordinate(45, 46));
        return new es.situm.sdk.model.location.Location.Builder(14676784, "TEST_PROVIDER", point, 5)
                .build();
    }

    public Location createLocationWithCoordinate () {
        Point point = new Point(new Coordinate(8.6, 10.5));
        return new Location.Builder(14676784, "TEST_PROVIDER", point, 5).build();
    }

    public Location createLocationWithBuildingAndCoordinate() {
        Point point = new Point("101", new Coordinate(8.6, 10.5));
        return new es.situm.sdk.model.location.Location.Builder(14676784, "TEST_PROVIDER", point, 5)
                .build();
    }

    public Location locationWithCartesianBearing() {
        Point point = new Point("101", "12", Coordinate.EMPTY, new CartesianCoordinate(45, 46));
        return  new es.situm.sdk.model.location.Location.Builder(14676784, "TEST_PROVIDER", point, 5)
                .cartesianBearing(Angle.fromRadians(2), Angle.fromDegrees(92), es.situm.sdk.model.location.Location.Quality.HIGH)
                .build();
    }

    public Location locationWithoutCartesianBearing() {
        Point point = new Point("101", "12", Coordinate.EMPTY, new CartesianCoordinate(45, 46));
        return  new es.situm.sdk.model.location.Location.Builder(14676784, "TEST_PROVIDER", point, 5)
                .build();
    }

    public Location locationWithBearing() {
        Point point = new Point(new Coordinate(8.6, 10.5));
        return  new es.situm.sdk.model.location.Location.Builder(14676784, "TEST_PROVIDER", point, 5)
                .bearing(Angle.fromDegrees(92))
                .build();
    }

    public Location locationWithoutBearing() {
        Point point = new Point("101", "12", Coordinate.EMPTY, new CartesianCoordinate(45, 46));
        return  new es.situm.sdk.model.location.Location.Builder(14676784, "TEST_PROVIDER", point, 5)
                .build();
    }

    public Location indoorLocationWithIndoorBearingQualityLow() {
        Point point = new Point("101", "12", Coordinate.EMPTY, new CartesianCoordinate(45, 46));
        return  new es.situm.sdk.model.location.Location.Builder(14676784, "TEST_PROVIDER", point, 5)
                .cartesianBearing(Angle.fromRadians(2), Angle.fromDegrees(92), es.situm.sdk.model.location.Location.Quality.LOW)
                .build();
    }

    public Location indoorLocationWithIndoorBearingQualityHigh() {
        Point point = new Point("101", "12", Coordinate.EMPTY, new CartesianCoordinate(45, 46));
        return  new es.situm.sdk.model.location.Location.Builder(14676784, "TEST_PROVIDER", point, 5)
                .cartesianBearing(Angle.fromRadians(2), Angle.fromDegrees(92), es.situm.sdk.model.location.Location.Quality.HIGH)
                .build();
    }

    public Location outdoorLocation() {
        Point point = new Point(new Coordinate(8.6, 10.5));
        return  new es.situm.sdk.model.location.Location.Builder(14676784, "TEST_PROVIDER", point, 5)
                .bearing(Angle.fromDegrees(92))
                .build();
    }


}