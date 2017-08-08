Situm Cordova Plugin
======

> Current Status:

Android: In development (some calls already implemented)
IOS: Not implemented yet


> Android

**setApiKey:**

```javascript
      cordova.plugins.Situm.setApiKey("email", "api_key");
```

**setUserPass:**

```javascript
       cordova.plugins.Situm.setUserPass("email", "password");
```

**fetchBuildings:**

```javascript
       cordova.plugins.Situm.fetchBuildings((buildings) => {
         // buildings -> array of building objects
       });
```

**fetchFloorsFromBuilding:**

```javascript
      let building = {
        id: 0,                      // integer
        user_id: 0,                 // intger
        name: "name",               // string
        description: "description", // string
        created_at: 00-00-0000,     // datetime
        updated_at: 00-00-0000,     // datetime
        location: location,         // object -> see [http://developers.situm.es/pages/rest/buildings.html](http://developers.situm.es/pages/rest/buildings.html)
        corners: corners,           // object -> see http://developers.situm.es/pages/rest/buildings.html
        dimensions: dimensions,     // object -> see http://developers.situm.es/pages/rest/buildings.html
        rotation: 0.00,             // float
        picture_url: "url",         // string optional
        picture_thumb_url: "url",   // string optional
        server_url: "url",          // string optional
        calibration_model: model,   // object -> see http://developers.situm.es/pages/rest/buildings.html
        info: "info",               // string
        floors: floors,             // array of floors see -> http://developers.situm.es/pages/rest/floors.html
        indoor_pois: indoor_pois,   // array of POIs see -> http://developers.situm.es/pages/rest/indoor_pois.html
        outdoor_pois: outdoor_pois, // array of POIs see -> http://developers.situm.es/pages/rest/outdoor_pois.html
        events: events ,            // array of events see -> http://developers.situm.es/pages/rest/events.html
        paths: paths                // object see -> http://developers.situm.es/pages/rest/paths.html
      };
      cordova.plugins.Situm.fetchFloorsFromBuilding(building, (floors) => {
        // floors -> array of floor objects from given building
      });
```

**fetchIndoorPOIsFromBuilding**

```javascript
      cordova.plugin.Situm.fetchIndoorPOIsFromBuilding(building, (indoorPOIs) => {
        // indoorPOIs -> array of POIs indoor building
      });
```

**fetchOutdoorPOIsFromBuilding**

```javascript
      cordova.plugins.Situm.fetchOutdoorPOIsFromBuilding(building, (outdoorPOIs) => {
        // outdoorPOIs -> array of POIs outdoor building
      });
```

**fetchEventsFromBuilding**

```javascript
      cordova.plugins.Situm.fetchEventsFromBuilding(building, (events) => {
        // events -> array of events
      });
```

**fetchMapFromFloor:**

```javascript
      cordova.plugins.Situm.fetchMapFromFloor(floor, (map) => {
        // map -> image encoded as base64 string
      });
```

**fetchPoiCategories:**

```javascript
       cordova.plugins.Situm.fetchPoiCategories((poiCategories) => {
         // poiCategories -> array of POI categories
       });
```

**startPositioning:**

```javascript
    cordova.plugins.Situm.startPositioning(buildings, callback)
```
**stopPositioning:**

```javascript
    cordova.plugins.Situm.stopPositioning(callback)
```
