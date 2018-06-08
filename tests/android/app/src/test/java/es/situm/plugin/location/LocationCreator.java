package es.situm.plugin.location;

import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.net.URL;

import es.situm.sdk.model.cartography.Point;
import es.situm.sdk.model.location.Angle;
import es.situm.sdk.model.location.CartesianCoordinate;
import es.situm.sdk.model.location.Coordinate;
import es.situm.sdk.model.location.Location;

public class LocationCreator {

    private final ClassLoader classLoader = getClass().getClassLoader();
    private final JSONParser parser = new JSONParser();

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


    public JSONObject getLocation1(){
        try{
            URL resource = classLoader.getResource("location/location1.json");
            File file = new File(resource.getFile());
            return new JSONObject(parser.parse(new FileReader(file)).toString());
        }catch(Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    public JSONObject getLocation2(){
        try{
            URL resource = classLoader.getResource("location/location2.json");
            File file = new File(resource.getFile());
            return new JSONObject(parser.parse(new FileReader(file)).toString());
        }catch(Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    public JSONObject getLocation3(){
        try{
            URL resource = classLoader.getResource("location/location3.json");
            File file = new File(resource.getFile());
            return new JSONObject(parser.parse(new FileReader(file)).toString());
        }catch(Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    public JSONObject getLocation4(){
        try{
            URL resource = classLoader.getResource("location/location4.json");
            File file = new File(resource.getFile());
            return new JSONObject(parser.parse(new FileReader(file)).toString());
        }catch(Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    public JSONObject getLocation5(){
        try{
            URL resource = classLoader.getResource("location/location5.json");
            File file = new File(resource.getFile());
            return new JSONObject(parser.parse(new FileReader(file)).toString());
        }catch(Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    public JSONObject getLocation6(){
        try{
            URL resource = classLoader.getResource("location/location6.json");
            File file = new File(resource.getFile());
            return new JSONObject(parser.parse(new FileReader(file)).toString());
        }catch(Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    public JSONObject getLocation7(){
        try{
            URL resource = classLoader.getResource("location/location7.json");
            File file = new File(resource.getFile());
            return new JSONObject(parser.parse(new FileReader(file)).toString());
        }catch(Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    public JSONObject getLocation8(){
        try{
            URL resource = classLoader.getResource("location/location8.json");
            File file = new File(resource.getFile());
            return new JSONObject(parser.parse(new FileReader(file)).toString());
        }catch(Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    public JSONObject getLocation9(){
        try{
            URL resource = classLoader.getResource("location/location9.json");
            File file = new File(resource.getFile());
            return new JSONObject(parser.parse(new FileReader(file)).toString());
        }catch(Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    public JSONObject getLocation10(){
        try{
            URL resource = classLoader.getResource("location/location10.json");
            File file = new File(resource.getFile());
            return new JSONObject(parser.parse(new FileReader(file)).toString());
        }catch(Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }
}