package es.situm.plugin.event;

import android.support.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

import es.situm.sdk.v1.Point2f;
import es.situm.sdk.v1.SitumConversionArea;
import es.situm.sdk.v1.SitumEvent;

public class EventCreator {

    public SitumEvent createEvent() {
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
                        new Point2f(1,2),
                        new Point2f(3,4),
                        new Point2f(5,6),
                        new Point2f(7,8),
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
}
