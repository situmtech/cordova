package es.situm.plugin.routeStep;

import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;

import es.situm.sdk.model.URL;
import es.situm.sdk.model.cartography.Building;
import es.situm.sdk.model.cartography.Point;
import es.situm.sdk.model.directions.RouteStep;
import es.situm.sdk.model.location.Angle;
import es.situm.sdk.model.location.CartesianCoordinate;
import es.situm.sdk.model.location.Coordinate;
import es.situm.sdk.model.location.Dimensions;

public class RouteStepCreator {

    private final ClassLoader classLoader = getClass().getClassLoader();
    private final JSONParser parser = new JSONParser();

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
        Point from = new Point("101", new Coordinate(5,10));
        Point to = new Point("101", new Coordinate(7,  3));
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
        Point from = new Point("101", new Coordinate(5, 7));
        Point to = new Point("101", new Coordinate(6, 4));
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

    public JSONObject getRouteStep1(){
        try{
            java.net.URL resource = classLoader.getResource("routeStep/routeStep1.json");
            File file = new File(resource.getFile());
            return new JSONObject(parser.parse(new FileReader(file)).toString());
        }catch(Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    public JSONObject getRouteStep2(){
        try{
            java.net.URL resource = classLoader.getResource("routeStep/routeStep2.json");
            File file = new File(resource.getFile());
            return new JSONObject(parser.parse(new FileReader(file)).toString());
        }catch(Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    public JSONObject getRouteStep3(){
        try{
            java.net.URL resource = classLoader.getResource("routeStep/routeStep3.json");
            File file = new File(resource.getFile());
            return new JSONObject(parser.parse(new FileReader(file)).toString());
        }catch(Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    public JSONObject getRouteStep4(){
        try{
            java.net.URL resource = classLoader.getResource("routeStep/routeStep4.json");
            File file = new File(resource.getFile());
            return new JSONObject(parser.parse(new FileReader(file)).toString());
        }catch(Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    public JSONObject getRouteStep5(){
        try{
            java.net.URL resource = classLoader.getResource("routeStep/routeStep5.json");
            File file = new File(resource.getFile());
            return new JSONObject(parser.parse(new FileReader(file)).toString());
        }catch(Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }
}
