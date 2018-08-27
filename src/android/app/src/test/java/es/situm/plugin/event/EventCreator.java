package es.situm.plugin.event;

import android.support.annotation.NonNull;

import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import es.situm.sdk.v1.Point2f;
import es.situm.sdk.v1.SitumConversionArea;
import es.situm.sdk.v1.SitumEvent;

public class EventCreator {

    private final ClassLoader classLoader = getClass().getClassLoader();
    private final JSONParser parser = new JSONParser();

    public SitumEvent createEvent1() {
        return new SitumEvent() {
            @Override
            public int getBuildingId() {
                return 1;
            }

            @Override
            public int getId() {
                return 12;
            }

            @Override
            public int getFloor_id() {
                return 1000;
            }

            @Override
            public float getX() {
                return 5;
            }

            @Override
            public float getY() {
                return 10;
            }

            @Override
            public float getRadius() {
                return 15;
            }

            @Override
            public String getName() {
                return "Event";
            }

            @Override
            public String getHtml() {
                return "<p>Test html</p>";
            }

            @Override
            public SitumConversionArea getConversionArea() {
                return new SitumConversionArea(
                        new Point2f(1, 2),
                        new Point2f(3, 4),
                        new Point2f(5, 6),
                        new Point2f(7, 8),
                        1000
                );
            }

            @NonNull
            @Override
            public Map<String, String> getCustomFields() {
                HashMap<String, String> customFields = new HashMap<>();
                customFields.put("lang", "en");
                return customFields;
            }
        };
    }

    public JSONObject getEvent1() {
        try {
            java.net.URL resource = classLoader.getResource("events/event1.json");
            File file = new File(resource.getFile());
            return new JSONObject(parser.parse(new FileReader(file)).toString());
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
}
