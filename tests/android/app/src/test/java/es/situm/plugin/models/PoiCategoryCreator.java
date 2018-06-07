package es.situm.plugin.models;

import es.situm.sdk.model.I18nString;
import es.situm.sdk.model.URL;
import es.situm.sdk.model.cartography.PoiCategory;

public class PoiCategoryCreator {

    public PoiCategory createPoiCategory() {
        return new PoiCategory.Builder()
                .code("TEST_CODE")
                .isPublic(true)
                .selectedIcon(new URL("TEST_URL"))
                .unselectedIcon(new URL("TEST_URL"))
                .name(new I18nString.Builder().anyLanguage("TEST_STRING").put("TEST_LANGUAGE","TEST_STRING").build())
                .build();
    }
}
