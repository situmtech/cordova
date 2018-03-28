Situm Cordova Plugin
======

> Current Status:

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
    + [requestNavigationUpdates](#requestnavigationupdates)
    + [updateNavigationWithLocation](#updatenavigationwithlocation)
    + [removeNavigationUpdates](#removenavigationupdates)

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
      directionsOptionsMap["startingAngle"] = position.bearing.degrees; // Optional: only if you want to go from a location. Value in degrees

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
              "indoorProvider": "INPHONE",
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


Navigation

If you want to provide indications in real time to a user you should use the Navigation APIs. Navigation APIs require a route and location updates to compute the progress of the completed route, if a user has reached the destination or if a user is outside the route and it must be recomputed.


### requestNavigationUpdates

Necessary step to request progress. Alone this method does not provide progress object. You must feed navigation API with location, as indicated on #updateNavigationWithLocation section.

```javascript
    var navigationOptions = new Object();
    // navigationOptions["distanceToIgnoreFirstIndication"] = 0.3; // (Optional) meters;
    // navigationOptions["outsideRouteThreshold"] = 10; // (Optional) meters;
    // navigationOptions["distanceToGoalThreshold"] = 7; // (Optional) meters;
    cordova.plugins.Situm.requestNavigationUpdates(
      [navigationOptions], 
      (res: any) => {
        // Progress and other navigation status messages can be processed here
        
      }
    , (error: any) => {
      console.log(error)
    });
    );
```

Types of results in success cases:

1) User has arrived destination (an object of type "destinationReached" is returned)

```javascript
{  
   "type":"destinationReached",
   "message":"Destination reached"
}
```

2) User is outside the route (an object of type ")
```javascript
{  
   "type":"userOutsideRoute",
   "message":"User outside route"
}
```

3) User is inside the route but has not arrived to destination (an object of type "progress" is returned).

```javascript
{  
   "closestPointInRoute":{  
      "isIndoor":true,
      "buildingIdentifier":"3087",
      "coordinate":{  
         "longitude":-8.563636606983739,
         "latitude":42.87227301416988
      },
      "floorIdentifier":"4961",
      "cartesianCoordinate":{  
         "x":67.91600036621094,
         "y":24.05699920654297
      },
      "isOutdoor":false
   },
   "distanceToEndStep":1.038121223449707, // meters
   "distanceToGoal":62.55179214477539, // meters
   "currentStepIndex":0,
   "timeToEndStep":1.038121223449707, // seconds
   "currentIndication":{  
      "distanceToNextLevel":0, // if this value is greater than 0 it represent the number of floor to go up. if this value is less than 0 it represents the number of floors to go down. If equal to 0 it means there is no need to change floor.
      "distance":1.038121223449707, // meters
      "stepIdxOrigin":0, // index of the route step (on of the steps on Route object provided by requestDirections)
      "stepIdxDestination":0,
      "orientationType":"Left",
      "indicationType":"Turn",
      "humanReadableMessage":"Turn left and go ahead for 2 meters",
      "orientation":1.8215326070785522, // radians
      "neededLevelChange":false, // True if change floor is needed
      "nextLevel":3 // Floor number. The floor level to which the user need to go.
   },
   "type":"progress",
   "timeToGoal":62.55179214477539
}
```


### updateNavigationWithLocation

```javascript
    cordova.plugins.Situm.updateNavigationWithLocation([position], function(result) {
                  console.log(result);
                } , function (error) {
                  console.log(error);
                });
```
Usually, position variable should be one of the locations provided by the system on the #startpositioning function.

### removeNavigationUpdates

When you are no longer interested on Navigation Updates you should call this method to remove internal allocated resources.

```javascript
    cordova.plugins.Situm.removeNavigationUpdates();
```

### Run tests

In order to create and run tests the following steps should be reproduced (this is a summary of a more in detail instructions on how to do this https://kerrishotts.github.io/pgday/workshops/2017/campp/testing.html).

