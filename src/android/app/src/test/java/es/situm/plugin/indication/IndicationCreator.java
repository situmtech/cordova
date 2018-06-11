package es.situm.plugin.indication;

import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.net.URL;

import es.situm.sdk.model.directions.Indication;

public class IndicationCreator {

    private final ClassLoader classLoader = getClass().getClassLoader();
    private final JSONParser parser = new JSONParser();

    public Indication createIndication() {
        return new Indication.Builder()
                .setDistance(5)
                .setDistanceToNextLevel(16)
                .setInstructionType(Indication.Action.GO_AHEAD)
                .setNextLevel(null)
                .setOrientation(14.5)
                .setOrientationType(Indication.Orientation.STRAIGHT)
                .setStepIdxDestination(5)
                .setStepIdxOrigin(4)
                .build();
    }

    public JSONObject getIndication1(){
        try{
            URL resource = classLoader.getResource("indication/indication1.json");
            File file = new File(resource.getFile());
            return new JSONObject(parser.parse(new FileReader(file)).toString());
        }catch(Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }
}
