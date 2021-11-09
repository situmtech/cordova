package es.situm.plugin.building;

import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;


import es.situm.plugin.DateUtils;
import es.situm.sdk.model.URL;
import es.situm.sdk.model.cartography.Building;
import es.situm.sdk.model.location.Angle;
import es.situm.sdk.model.location.Coordinate;
import es.situm.sdk.model.location.Dimensions;

public class BuildingCreator {

    private final ClassLoader classLoader = getClass().getClassLoader();
    private final JSONParser parser = new JSONParser();
    private final DateFormat dateFormat = DateUtils.dateFormat;

    public Building createBuilding1() {
        Coordinate coordinate = new Coordinate(42.8723472943445, -8.56325268745422);
        Dimensions dimensions = new Dimensions(71.0686153823893, 42.6106416714803);
        Date createdAt = null;
        Date updatedAt = null;
        try {
            createdAt = dateFormat.parse("Wed Jan 04 18:41:43 +0100 2017");
            updatedAt = dateFormat.parse("Wed Sep 12 12:10:25 +0200 2018");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        HashMap<String, String> customFields = new HashMap<>();
        customFields.put("notification_url", "http://testUrl.com");
        customFields.put("pin", "1234");
        customFields.put("positioning-mode", "Trilateration");
        customFields.put("beacons_uuids", "7f6cc424-aae2-47fd-b5e9-476ad15e4733, fa4b8d11-9c13-4ad9-9859-cecd41c58000, 1876b5fa-3e4b-4d09-9252-627032fe9312");
        customFields.put("filter-beacon", "56C98FAD0C9, D5784F5A73CA");
        customFields.put("asset-localization", "True");

        return new Building.Builder()
                .address("")
                .dimensions(dimensions)
                .center(coordinate)
                .infoHtml("<p>http://Prueba/actualizador/recibirAlarmas</p>")
                .name("Ed. Emprendia - Situm")
                .pictureThumbUrl(new URL(""))
                .pictureUrl(new URL(""))
                .rotation(Angle.fromRadians(-3.31881803875501))
                .userIdentifier("-1")
                .identifier("1051")
                .customFields(customFields)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();

    }

    public JSONObject getBuilding1() {
        try {
            java.net.URL resource = classLoader.getResource("building/building1.json");
            File file = new File(resource.getFile());
            return new JSONObject(parser.parse(new FileReader(file)).toString());
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
}
