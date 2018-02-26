Situm Cordova Plugin
======

> Current Status:

Version 1.3.3
Android: Services, Communication, Location, Directions and Navigation modules
IOS: Services, Communication, Location, Directions and Navigation modules
## Table of contents
  * [Android](#android)
    + [setApiKey](#setapikey)
    + [setUserPass](#setuserpass)
    + [fetchBuildings](#fetchbuildings)
    + [fetchFloorsFromBuilding](#fetchfloorsfrombuilding)
    + [fetchIndoorPOIsFromBuilding](#fetchindoorpoisfrombuilding)
    + [fetchOutdoorPOIsFromBuilding](#fetchoutdoorpoisfrombuilding)
    + [fetchEventsFromBuilding](#fetcheventsfrombuilding)
    + [fetchMapFromFloor](#fetchmapfromfloor)
    + [fetchPoiCategories](#fetchpoicategories)
    + [fetchPoiCategoryIconNormal](#fetchpoicategoryiconnormal)
    + [fetchPoiCategoryIconSelected](#fetchpoicategoryiconselected)
    + [invalidateCache](#invalidatecache)
    + [requestDirections](#requestdirections)
    + [startPositioning](#startpositioning)
    + [stopPositioning](#stoppositioning)

## Android

### setApiKey

```javascript
      cordova.plugins.Situm.setApiKey("email", "api_key");
```

### setUserPass

```javascript
       cordova.plugins.Situm.setUserPass("email", "password");
```

### fetchBuildings

```javascript
       cordova.plugins.Situm.fetchBuildings((buildings) => {
         // buildings -> array of building objects
       });
```

### fetchFloorsFromBuilding

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

### fetchIndoorPOIsFromBuilding

```javascript
      cordova.plugin.Situm.fetchIndoorPOIsFromBuilding(building, (indoorPOIs) => {
        // indoorPOIs -> array of POIs indoor building
      });
```

### fetchOutdoorPOIsFromBuilding

```javascript
      cordova.plugins.Situm.fetchOutdoorPOIsFromBuilding(building, (outdoorPOIs) => {
        // outdoorPOIs -> array of POIs outdoor building
      });
```

### fetchEventsFromBuilding

```javascript
      cordova.plugins.Situm.fetchEventsFromBuilding(building, (events) => {
        // events -> array of events
      });
```

### fetchMapFromFloor

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

### fetchPoiCategories

```javascript
       cordova.plugins.Situm.fetchPoiCategories((poiCategories) => {
         // poiCategories -> array of POI categories
       });
```

### fetchPoiCategoryIconNormal

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

### fetchPoiCategoryIconSelected

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

### invalidateCache

```javascript
      cordova.plugins.Situm.invalidateCache((res) => {
          // res -> string message
      });
```

**setCacheMaxAge**
```javascript
      cordova.plugins.Situm.setCacheMaxAge(cacheMaxAge, (res) => { // cacheMaxAge: time in seconds. For example if 1 day is required then 1 day * (24 hours / day) * (60 min/hour) * (60 seconds / minute) = 86.400 seconds
          // res -> string message
      });
```

### requestDirections
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
      var directionsOptionsMap = new Object();
      directionsOptionsMap["accessible"] = true/false

      cordova.plugins.Situm.requestDirections([building, from, to, directionsOptionsMap], (res) => {
        // res -> route object, we can draw in map with our map provider
      });
```

### startPositioning

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


NOTE: if deadReckoning is required then buildingIdentifier property is mandatory. Only available on Android for now.

A description of each parameter can be found below: 
- **buildingIdentifier**: Building identifier of the building where you will start positioning.
- **interval**: Desired interval for location updates, in milliseconds.This interval is inexact, the service will try to obtain location updates for your application at this interval.
- **indoorProvider**: Indoor location provider. `INPHONE` or `SUPPORT`
- **useBle**: Set if you want to use BLE for positioning.
- **useWiFi**: Set if you want to use WiFi for positioning.
- **motionMode**: (EXPERIMENTAL) Set the motion mode. `BY_CAR` or `BY_FOOT`
- **useForegroundService**: (EXPERIMENTAL) Run the service in foreground.
- **useDeadReckoning**: (EXPERIMENTAL) Set if you want to use dead reckoning to get fast position updates using only the inertial sensors, between the server position updates.
- **outdoorLocationOptions**: Outdoor location options.
	- **continuousMode**: Set the environment detection continuous mode (true) or burst mode (false).
	- **userDefinedThreshold**: Set to true if the snr threshold to use is defined by the user.
	- **burstInterval**: The interval to scan for GPS and detect the environment (in seconds).
	- **averageSnrThreshold**: When detecting the environment, threshold over which must be the average SNR of the satellites to detect outdoor.
- **beaconFilters**: Adds beacon filters to be handled during scan time, otherwise only Situm beacons will be scanned.
	- **uuid**: Beacon uuid
- **smallestDisplacement**: Minimum displacement between location updates (in meters).
- **realtimeUpdateInterval**: Interval to upload locations to the realtime.

### stopPositioning

```javascript
    cordova.plugins.Situm.stopPositioning(() => {
      // stop positioning
    });
```
