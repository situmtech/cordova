package es.situm.plugin;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class JsonUtils {


    public static Map<String, Object> toMap(JSONObject jsonObject) {
        if (jsonObject == null) {
            return new HashMap<>();
        }
        Map<String, Object> finalMap = new HashMap<>();
        Stack<Object> stack = new Stack<>();
        Stack<Map<String, Object>> mapsStack = new Stack<>();
        stack.push(jsonObject);
        mapsStack.push(finalMap);
        while (!stack.isEmpty()) {
            Object item = stack.pop();
            Map<String, Object> map = mapsStack.pop();
            if (item instanceof JSONObject) {
                Iterator<String> keys = ((JSONObject) item).keys();
                while (keys.hasNext()) {
                    String key = keys.next();
                    Object value = null;
                    try {
                        value = ((JSONObject) item).get(key);
                    } catch (JSONException e) {

                    }
                    if (value instanceof JSONObject) {
                        stack.push(value);
                        Map<String, Object> newMap = new HashMap<>();
                        mapsStack.push(newMap);
                        map.put(key, newMap);
                    } else {
                        map.put(key, value);
                    }
                }
            }
        }
        return finalMap;
    }
}
