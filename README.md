Situm Cordova Plugin
======

> Current Status:

Version 1.1.0
Android: Services, Communication, Location, Directions and Navigation modules
IOS: Services, Communication, Location, Directions and Navigation modules


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
        address: "address"
        buildingIdentifier: 0,                      // integer
        bounds: {
          northEast: {
            latitude: 0.0,
            longitude: 0.0
          },
          northWest: {
            latitude: 0.0,
            longitude: 0.0
          },
          southEast: {
            latitude: 0.0,
            longitude: 0.0
          },
          southWest: {
            latitude: 0.0,
            longitude: 0.0
          }
        },
        boundsRotated: {
          northEast: {
            latitude: 0.0,
            longitude: 0.0
          },
          northWest: {
            latitude: 0.0,
            longitude: 0.0
          },
          southEast: {
            latitude: 0.0,
            longitude: 0.0
          },
          southWest: {
            latitude: 0.0,
            longitude: 0.0
          }
        },
        center: {
          latitude: 0.0,
          longitude: 0.0
        },
        userIdentifier: 0,                 // intger
        name: "name",               // string
        dimensions: {
          height: 10,
          width: 10
        },     // object -> see http://developers.situm.es/pages/rest/buildings.html
        rotation: 0.00,             // float
        pictureUrl: "url",         // string optional
        pictureThumbUrl: "url",   // string optional
        infoHtml: "info",               // string
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
      let floor = {
        altitude: 2,
        buildingIdentifier: 5432,
        floorIdentifier: 1246,
        level: 0,
        scale: 20.1388,
        map_url: "http://dashboard.situm.es/uploads/situm/floor/map/995/80eadc8f-df52-48e1-aa0b-2e5abd1262dd.PNG",
      };
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

**fetchPoiCategoryIconNormal**

```javascript
      let category = {
          poiCategoryCode: "situm-coffee",
          poiCategoryName: "Cafetería",
          icon_unselected: "/uploads/situm/poi_category/icon/1/31cc9fdf-6820-447d-ac13-a0a4d0642d3b.png",
          icon_selected: "/uploads/situm/poi_category/selected_icon/1/eb7c6c7e-33ba-40c5-ae22-e993b64d1439.png",
          public: true
      };  
      cordova.plugins.Situm.fetchPoiCategoryIconNormal(category, (icon) => {

      });
```

**fetchPoiCategoryIconSelected**

```javascript
      let category = {
          poiCategoryCode: "situm-coffee",
          poiCategoryName: "Cafetería",
          icon_unselected: "/uploads/situm/poi_category/icon/1/31cc9fdf-6820-447d-ac13-a0a4d0642d3b.png",
          icon_selected: "/uploads/situm/poi_category/selected_icon/1/eb7c6c7e-33ba-40c5-ae22-e993b64d1439.png",
          public: true
      };  
      cordova.plugins.Situm.fetchPoiCategoryIconSelected(category, (icon) => {

      });
```

**invalidateCache**

```javascript
      cordova.plugins.Situm.invalidateCache((res) => {
          // res -> string message
      });
```

**requestDirections**

```javascript
      // from & to -> POI object
      from = {
        "buildingIdentifier": "BUILDING ID",
        "floorIdentifier": "FLOOR ID",
        "coordinate": {
          "latitude": 0.0,
          "longitude": 0.0
        },
        "cartesianCoordinate": {
          "x": 0.0,
          "y": 0.0
        }
      }
      to = {
        "buildingIdentifier": "BUILDING ID",
        "floorIdentifier": "FLOOR ID",
        "coordinate": {
          "latitude": 0.0,
          "longitude": 0.0
        },
        "cartesianCoordinate": {
          "x": 0.0,
          "y": 0.0
        }
      }
      cordova.plugins.Situm.requestDirections([building, from, to], (res) => {
        // res -> route object, we can draw in map with our map provider
      });
```

**startPositioning:**

```javascript
    cordova.plugins.Situm.startPositioning(requestParameters, (response) => {
      // requestParameters -> array containing building information and request:    
      // response -> object with the actual position. this function send us a new response when event to position changed is fired
    });
    
    requestParameters:
       [
          {
              "buildingIdentifier": 1051,
              "name": "Ed. Emprendia - Situm"
          },
          {
              "buildingIdentifier": 1051,
              "interval": 1000,
              "indoorProvider": "SUPPORT",
              "useBle": true,
              "useWifi": true,
              "motionMode": "BY_FOOT",
              "useForegroundService": true,
              "useDeadReckoning": true,
              "outdoorLocationOptions": 
              {
                  "continuousMode": true,
                  "userDefinedThreshold": false,
                  "burstInterval": 1,
                  "averageSnrThreshold": 25.0
              },
              "beaconFilters": [
                  {
                      "uuid": "54"
                  },
                  {
                      "uuid": "68"
                  }
              ],
              "smallestDisplacement": 1.0,
              "realtimeUpdateInterval": 1000
          }
       ]
```

**stopPositioning:**

```javascript
    cordova.plugins.Situm.stopPositioning(() => {
      // stop positioning
    });
```
