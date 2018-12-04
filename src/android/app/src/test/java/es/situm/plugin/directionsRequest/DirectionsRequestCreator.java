package es.situm.plugin.directionsRequest;

import org.json.JSONArray;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.net.URL;

import es.situm.sdk.directions.DirectionsRequest;

public class DirectionsRequestCreator {
    private final ClassLoader classLoader = getClass().getClassLoader();
    private final JSONParser parser = new JSONParser();

    public DirectionsRequest createDirectionsRequest1() {
        return  new DirectionsRequest.Builder().minimizeFloorChanges(true).accessibilityMode(DirectionsRequest.AccessibilityMode.CHOOSE_SHORTEST).build();
    }

    public DirectionsRequest createDirectionsRequest2() {
        return  new DirectionsRequest.Builder().minimizeFloorChanges(true).accessibilityMode(DirectionsRequest.AccessibilityMode.CHOOSE_SHORTEST).build();
    }

    public DirectionsRequest createDirectionsRequest3() {
        return  new DirectionsRequest.Builder().minimizeFloorChanges(true).accessibilityMode(DirectionsRequest.AccessibilityMode.ONLY_ACCESSIBLE).build();
    }

    public DirectionsRequest createDirectionsRequest4() {
        return  new DirectionsRequest.Builder().minimizeFloorChanges(true).accessibilityMode(DirectionsRequest.AccessibilityMode.ONLY_ACCESSIBLE).build();
    }

    public DirectionsRequest createDirectionsRequest5() {
        return  new DirectionsRequest.Builder().minimizeFloorChanges(true).accessibilityMode(DirectionsRequest.AccessibilityMode.ONLY_ACCESSIBLE).build();
    }

    public DirectionsRequest createDirectionsRequest6() {
        return  new DirectionsRequest.Builder().minimizeFloorChanges(true).accessibilityMode(DirectionsRequest.AccessibilityMode.CHOOSE_SHORTEST).build();
    }

    public JSONArray getDirectionsRequest1(){
        try{
            URL resource = classLoader.getResource("directionsRequest/directionsRequest1.json");
            File file = new File(resource.getFile());
            return new JSONArray(parser.parse(new FileReader(file)).toString());
        }catch(Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    public JSONArray getDirectionsRequest2(){
        try{
            URL resource = classLoader.getResource("directionsRequest/directionsRequest2.json");
            File file = new File(resource.getFile());
            return new JSONArray(parser.parse(new FileReader(file)).toString());
        }catch(Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    public JSONArray getDirectionsRequest3(){
        try{
            URL resource = classLoader.getResource("directionsRequest/directionsRequest3.json");
            File file = new File(resource.getFile());
            return new JSONArray(parser.parse(new FileReader(file)).toString());
        }catch(Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    public JSONArray getDirectionsRequest4(){
        try{
            URL resource = classLoader.getResource("directionsRequest/directionsRequest4.json");
            File file = new File(resource.getFile());
            return new JSONArray(parser.parse(new FileReader(file)).toString());
        }catch(Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    public JSONArray getDirectionsRequest5(){
        try{
            URL resource = classLoader.getResource("directionsRequest/directionsRequest5.json");
            File file = new File(resource.getFile());
            return new JSONArray(parser.parse(new FileReader(file)).toString());
        }catch(Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    public JSONArray getDirectionsRequest6(){
        try{
            URL resource = classLoader.getResource("directionsRequest/directionsRequest6.json");
            File file = new File(resource.getFile());
            return new JSONArray(parser.parse(new FileReader(file)).toString());
        }catch(Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }
}
