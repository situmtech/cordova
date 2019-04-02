package es.situm.plugin.locationRequest;

import org.json.JSONArray;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.net.URL;

import es.situm.sdk.location.LocationRequest;

public class LocationRequestCreator {
    private final ClassLoader classLoader = getClass().getClassLoader();
    private final JSONParser parser = new JSONParser();


    public LocationRequest createLocationRequest1() {
        return new LocationRequest.Builder().buildingIdentifier("1051").build();
    }
    public LocationRequest createLocationRequest2() {
        return new LocationRequest.Builder().buildingIdentifier("1051").interval(1000).smallestDisplacement(1.0f).autoEnableBleDuringPositioning(false).build();
    }

    public JSONArray getLocationRequest1(){
        try{
            URL resource = classLoader.getResource("locationRequest/locationRequest1.json");
            File file = new File(resource.getFile());
            return new JSONArray(parser.parse(new FileReader(file)).toString());
        }catch(Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    public JSONArray getLocationRequest2(){
        try{
            URL resource = classLoader.getResource("locationRequest/locationRequest2.json");
            File file = new File(resource.getFile());
            return new JSONArray(parser.parse(new FileReader(file)).toString());
        }catch(Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }
}
