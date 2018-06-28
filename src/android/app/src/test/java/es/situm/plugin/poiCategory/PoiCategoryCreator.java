package es.situm.plugin.poiCategory;

import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;

import es.situm.sdk.model.I18nString;
import es.situm.sdk.model.URL;
import es.situm.sdk.model.cartography.PoiCategory;

public class PoiCategoryCreator {

    private final ClassLoader classLoader = getClass().getClassLoader();
    private final JSONParser parser = new JSONParser();

    public PoiCategory createPoiCategory() {
        return new PoiCategory.Builder()
                .code("TEST_CODE")
                .isPublic(true)
                .selectedIcon(new URL("TEST_URL"))
                .unselectedIcon(new URL("TEST_URL"))
                .name(new I18nString.Builder().anyLanguage("TEST_STRING").put("TEST_LANGUAGE","TEST_STRING").build())
                .build();
    }

    public JSONObject getPoiCategory1(){
        try{
            java.net.URL resource = classLoader.getResource("poiCategory/poiCategory1.json");
            File file = new File(resource.getFile());
            return new JSONObject(parser.parse(new FileReader(file)).toString());
        }catch(Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }
}
