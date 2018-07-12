package es.situm.plugin.building;

import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.Date;

import es.situm.sdk.model.cartography.Building;
import es.situm.sdk.model.location.Angle;
import es.situm.sdk.model.location.Coordinate;
import es.situm.sdk.model.location.Dimensions;

public class BuildingCreator {

    private final JSONParser parser = new JSONParser();
    private final ClassLoader classLoader = getClass().getClassLoader();

    public Building createBuilding() {
        Coordinate center = new Coordinate(23,43);
        Dimensions dimensions = new Dimensions(5,7);
        Angle rotation = Angle.fromDegrees(47);
        return  new Building.Builder()
                .address("Dirección 1")
                .center(center)
                .dimensions(dimensions)
                .infoHtml("Additional Info")
                .name("Building Name")
                .pictureThumbUrl(new es.situm.sdk.model.URL("URL thumbnail"))
                .pictureUrl(new es.situm.sdk.model.URL("URL picture"))
                .rotation(rotation)
                .userIdentifier("User ID")
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();
    }

    public JSONObject getBuilding1(){
        try{
            URL resource = classLoader.getResource("building/building1.json");
            File file = new File(resource.getFile());
            return new JSONObject(parser.parse(new FileReader(file)).toString());
        }catch(Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }
}
