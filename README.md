<p align="center"> <img width="233" src="https://situm.com/wp-content/themes/situm/img/logo-situm.svg" style="margin-bottom:1rem" />
<h1 align="center">Situm Cordova Plugin</h1>
</p>

Situm Cordova Plugin is a set of utilities that allow any developer to build Cordova location based apps using Situm's indoor positioning system. Among many other capabilities, apps developed with Situm Cordova Plugin will be able to:

- Obtain information related to buildings where Situm's positioning system is already configured: floorplans, points of interest, geotriggered events, etc.
- Retrieve the location of the smartphone inside these buildings (position, orientation, and floor where the smartphone is).
- Compute a route from a point A (e.g. where the smartphone is) to a point B (e.g. any point of interest within the building).
- Trigger notifications when the user enters a certain area.

<div align="center" style="text-align:center">

[![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)](https://opensource.org/licenses/MIT)
[![npm](https://img.shields.io/npm/v/@situm/cordova.svg)](https://www.npmjs.com/package/@situm/cordova)
[![npm](https://img.shields.io/npm/dm/@situm/cordova.svg)](https://www.npmjs.com/package/@situm/cordova)

</div>

## Table of contents

- [Getting started](#getting-started)
- [Versioning](#versioning)
- [Submitting contributions](#submitting-contributions)
- [License](#license)
- [Documentation](#documentation)
- [Development](#development)
- [More information](#more-information)
- [Support information](#support-information)

---

## Getting started

- Set up your Situm account following [these steps](https://situm.com/docs/01-introduction/#3-toc-title).
- [Configure](https://situm.com/docs/a-basic-cordova-app/) this plugin in your project.
- [API Reference](https://developers.situm.com/sdk_documentation/cordova/jsdoc/latest/situm) will help you use a particular class or method.

---

## Versioning

Please refer to [CHANGELOG.md](https://github.com/situmtech/cordova/blob/master/CHANGELOG.md) for a list of notables changes for each version of the plugin.

You can also see the [tags on this repository](https://github.com/situmtech/cordova/tags).

---

## Submitting contributions

You will need to sign a Contributor License Agreement (CLA) before making a submission. [Learn more here](https://situm.com/contributions/).

---

## License

Situm Cordova Plugin is licensed under [MIT License](https://opensource.org/licenses/MIT). See [LICENSE.txt](https://github.com/situmtech/cordova/blob/master/LICENSE) file for further details.

---

## Documentation

- **General documentation**. You can find in our [documentation site](https://situm.com/docs/a-basic-cordova-app/) all the information you need to configure this plugin in your project and get it up and running in no time.
- **API reference**. Check out the [plugin reference](https://developers.situm.com/sdk_documentation/cordova/jsdoc/latest/situm) and learn how to use a particular class or method.
- **Examples**. In [example/](./example/README.md) you can find a sample app implementing this plugin. Take a look at how the examples are implemented, so you can figure out how to adapt it to your project.
- **Cordova Wayfinding plugin**. If you are looking for a wayfinding solution using Cordova, check out [this repo](https://github.com/situmtech/cordova).

### Methods

> [!NOTE]
> This plugin is currently under development. There may be methods not implemented yet. Also there may be some API changes as development progresses.

### Situm class

#### - setApiKey

Log in into your Situm Account. This key is generated in Situm Dashboard. Return true if apiKey was set successfully, otherwise false

```js
cordova.plugins.Situm.setApiKey('SITUM_EMAIL', 'SITUM_API_KEY')
```

#### - setUserPass

Provides user's email and password. Return true if apiKey was set successfully, otherwise false

```js
cordova.plugins.Situm.setUserPass('SITUM_EMAIL', 'SITUM_USER_PASS')
```

#### - setRemoteConfig

Set the remote configuration state which allows to use the configuration (location request) stored on the web to find the location of the user.

```js
cordova.plugins.Situm.setUseRemoteConfig(true)
```

#### - setCacheMaxAge

Sets the maximum age of a cached response in seconds.

```js
cordova.plugins.Situm.setCacheMaxAge(1 * 60 * 60) // 1 hour
```

#### - startPositioning

> [!WARN]
> This method is deprecated, instead use [requestLocationUpdates](#--requestLocationUpdates).

Starts the positioning system. In the success callback it can return:

- [Location](https://developers.situm.com/sdk_documentation/cordova/jsdoc/latest/global.html#Location)
- [LocationStatus](https://developers.situm.com/sdk_documentation/cordova/jsdoc/latest/global.html#LocationStatus)

```js
  locationOptions = {
      buildingIdentifier = "BUILDING_ID"
  };

cordova.plugins.Situm.startPositioning(locationOptions, (res: any) => {
      if (res && res.statusName) {
        // Returns location status
      }
      if (res && res.position) {
        // Return location object
      }
    }, (err: any) => {
      //Return error as an string.If this happens the positioning is stopped
    });
```

#### - requestLocationUpdates

Starts the positioning system. You can customize the positioning system by specifying a [LocationRequest](https://developers.situm.com/sdk_documentation/cordova/jsdoc/latest/global.html#LocationRequest) in the method parameters.

Use [onLocationUpdate()](#--onLocationUpdate) to receive updates of the user's location,
[onLocationStatus()](#--onLocationStatus) to receive updates of the positioning status
and [onLocationError()](#--onLocationError) to receive the possible errors that take place when positioning.

```js
cordova.plugins.Situm.requestLocationUpdates(
  {
      buildingIdentifier = "YOUR_BUILDING_IDENTIFIER"
  });
```

#### - onLocationUpdate

Use this callback to stay aware about the user's [Location](https://developers.situm.com/sdk_documentation/cordova/jsdoc/latest/global.html#Location).

```js
cordova.plugins.Situm.onLocationUpdate((location: any) => {
  console.log('The user has moved to: ' + location.position)
})
```

#### - onLocationStatus

Use this callback to stay aware about the [LocationStatus](https://developers.situm.com/sdk_documentation/cordova/jsdoc/latest/global.html#LocationStatus) of the positioning system.

```js
cordova.plugins.Situm.onLocationStatus((status: string) => {
  console.log('New positioning status: ' + status)
})
```

#### - onLocationError

Use this callback to stay aware about the errors the user might face when the positioning system starts.
See [onLocationError()](https://developers.situm.com/sdk_documentation/cordova/jsdoc/latest/situm#.onLocationError)

```js
cordova.plugins.Situm.onLocationError((err: any) => {
  console.log('Error received when positioning: ' + err)
})
```

#### - stopPositioning

Stop the positioning system on current active listener.

```js
cordova.plugins.Situm.stopPositioning()
```

#### - fetchBuildings

Download all the buildings for the current user.Returns an array of [buildings](https://developers.situm.com/sdk_documentation/cordova/jsdoc/latest/global.html#Building)

```js
cordova.plugins.Situm.fetchBuildings(
  (res: any) => {
    // Return an array of buildings
  },
  (err: any) => {
    // returns error string
  }
)
```

#### - fetchBuildingInfo

Download the information (floors, pois, ...) [of a building](https://developers.situm.com/sdk_documentation/cordova/jsdoc/latest/global.html#BuildingInfo)

```js
cordova.plugins.Situm.fetchBuildingInfo(
  building,
  (res: any) => {
    // Return the buildingInfo
  },
  (err: any) => {
    // returns error string
  }
)
```

#### - fetchFloorsFromBuilding

Download all the [floors](https://developers.situm.com/sdk_documentation/cordova/jsdoc/latest/global.html#Floor) of a building.

```js
cordova.plugins.Situm.fetchFloorsFromBuilding(
  building,
  (res: any) => {
    // Return an array of floors
  },
  (err: any) => {
    // returns error string
  }
)
```

#### - fetchIndoorPOIsFromBuilding

Download the indoor [POIs](https://developers.situm.com/sdk_documentation/cordova/jsdoc/latest/global.html#POI) of a building.

```js
cordova.plugins.Situm.fetchIndoorPOIsFromBuilding(
  building,
  (res: any) => {
    // Return an array of indoor POIs
  },
  (err: any) => {
    // returns error string
  }
)
```

#### - fetchOutdoorPOIsFromBuilding

Download the outdoor [POIs](https://developers.situm.com/sdk_documentation/cordova/jsdoc/latest/global.html#POI) of a building.

```js
cordova.plugins.Situm.fetchOutdoorPOIsFromBuilding(
  building,
  (res: any) => {
    // Return an array of outdoor POIs
  },
  (err: any) => {
    // returns error string
  }
)
```

#### - fetchEventsFromBuilding

Download the [events](https://developers.situm.com/sdk_documentation/cordova/jsdoc/latest/global.html#SitumEvent) of a building.

```js
cordova.plugins.Situm.fetchEventsFromBuilding(
  building,
  (res: any) => {
    // Return an array of events
  },
  (err: any) => {
    // returns error string
  }
)
```

#### - fetchPoiCategories

Get all [POI Categories](https://developers.situm.com/sdk_documentation/cordova/jsdoc/latest/global.html#PoiCategory), download and cache their icons asynchronously.

```js
cordova.plugins.Situm.fetchPoiCategories(
  (res: any) => {
    // Return an array of POI categories
  },
  (err: any) => {
    // returns error string
  }
)
```

#### - fetchMapFromFloor

Download the map image of a floor.

```js
cordova.plugins.Situm.fetchMapFromFloor(
  floor,
  (res: any) => {
    // Return an image as an string encoded in Base64
  },
  (err: any) => {
    // returns error string
  }
)
```

#### - fetchPoiCategoryIconNormal

Get the normal category icon for a POICategory.

```js
cordova.plugins.Situm.fetchPoiCategoryIconNormal(
  category,
  (res: any) => {
    // Return an image as an string encoded in Base64
  },
  (err: any) => {
    // returns error string
  }
)
```

#### - fetchPoiCategoryIconSelected

Get the selected category icon for a POICategory.

```js
cordova.plugins.Situm.fetchPoiCategoryIconSelected(
  category,
  (res: any) => {
    // Return an image as an string encoded in Base64
  },
  (err: any) => {
    // returns error string
  }
)
```

#### - fetchGeofencesFromBuilding

Get all [geofences](https://developers.situm.com/sdk_documentation/cordova/jsdoc/latest/global.html#Geofence) from the building.

```js
cordova.plugins.Situm.fetchGeofencesFromBuilding(
  building,
  (res: any) => {
    // Return an array of geofences
  },
  (err: any) => {
    // returns error string
  }
)
```

#### - invalidateCache

Invalidate all the resources in the cache.

```js
cordova.plugins.Situm.invalidateCache()
```

#### - requestDirections

Calculates a route between two points. This route is the one that will be used when you call requestNavigationUpdates. If this method is called multiple times the last Route will be used.
You can change the options to generate the Route with [DirectionOptions](https://developers.situm.com/sdk_documentation/cordova/jsdoc/latest/global.html#DirectionsOptions)
Returns a [Route](https://developers.situm.com/sdk_documentation/cordova/jsdoc/latest/global.html#Route)

```js
directionRequest = [
  building, // Building in which you're positioning
  from, // Point where you want to start the route. You can pass a Point or a Location
  to, // Point where you want to finish the route
  {}, // Options to generate the route
]

cordova.plugins.Situm.requestDirections(
  directionsRequest,
  (route: any) => {
    //Return a Route
  },
  (err: any) => {
    // returns error string
  }
)
```

#### - requestNavigationUpdates

Necessary step to request progress. Alone this method does not provide progress object. You must feed navigation API with location, as indicated on updateNavigationWithLocation section.
When you start feeding locations you can receive [NavigationProgress](https://developers.situm.com/sdk_documentation/cordova/jsdoc/latest/global.html#NavigationProgress) or other of the results described below

```js
// Navigation request with example values
navigationRequest = [
  (distanceToGoalThreshold = 10),
  (distanceToFloorChangeThreshold = 5),
]
cordova.plugins.Situm.requestNavigationUpdates(
  navigationRequest,
  (navigation: any) => {
    /**
     * This callback can return four different things:
     * 1. A message notifying about the success starting the navigation
     * 2. A json with the NavigationProgress. The Json will also have a field "type" with the value "progress" so you can know when this happens.
     * 3. A json with the field "type" and the value "destinationReached". This happens when the navigation finish because you reached the end.
     * 4. A json with the field "type" and the value "userOutsideRoute". This happens when the user deviate from the route. You can notify them so they return to the correct path.
     */
  },
  (error: any) => {
    //returns error string
  }
)
```

#### - updateNavigationWithLocation

Usually, position variable should be one of the locations provided by the system on the [startPositioning](#--startpositioning) function.

```js
cordova.plugins.Situm.updateNavigationWithLocation(currentLocation)
```

#### - removeNavigationUpdates

When you are no longer interested on Navigation Updates you should call this method to remove internal allocated resources.

```js
cordova.plugins.Situm.removeNavigationUpdates()
```

#### - requestRealTimeUpdates

Emits the [real time](https://developers.situm.com/sdk_documentation/cordova/jsdoc/latest/global.html#RealTimeData) location of devices

```js
const request = {
  building: building, //Building in which you want to be notified
  pollTime: 3000, // time in milliseconds
}
cordova.plugins.Situm.SitumPlugin.requestRealTimeUpdates(
  request,
  (locations: any) => {
    // returns the locations of the other devices in real time
  },
  (error: any) => {
    // returns error string
  }
)
```

#### - removeRealTimeUpdates

When you are no longer interested on realtime location Updates you should call this method to remove internal allocated resources.

```js
cordova.plugins.Situm.removeRealTimeUpdates()
```

#### - onEnterGeofences

Get notified about users entering geofences. Take into account:

- This method must be called **before** the positioning is started.
- Positioning geofences (with `trainer_metadata` custom field [configured in the dashboard](https://situm.com/docs/special-custom-fields/#activating-the-uncalibrated-indoor-geolocation-mode)) won't be notified.
- This callback works only with indoor locations. Any outdoor location will produce a call to [onExitedGeofences](#--onExitedGeofences) with the last positioned geofences as argument.
- This callback will return an array of [geofences](https://developers.situm.com/sdk_documentation/cordova/jsdoc/latest/global.html#Geofence)

```js
cordova.plugins.Situm.onEnterGeofences((geofences: any) => {
  // Returns an array of geofences
  // e.g. [{"polygonPoints": [], "customFields": {}, "updatedAt": "Thu Jan 01 01:00:00 +0100 1970", "buildingIdentifier": "1234", "floorIdentifier": "123456", "code": "", "createdAt": "Thu Jan 01 01:00:00 +0100 1970", "infoHtml": "", "name": "My Geofence", "identifier": "12345678-aaaa-bbbb-cccc-12345678abcd"}]
})
```

#### - onExitGeofences

Get notified about exiting geofences. Take into account the considerations described at [onEnterGeofences](#--onEnterGeofences).

- This callback will return an array of [geofences](https://developers.situm.com/sdk_documentation/cordova/jsdoc/latest/global.html#Geofence)

```js
cordova.plugins.Situm.onExitGeofences((geofences: any) => {
  // Returns an array of geofences
  // e.g. [{"polygonPoints": [], "customFields": {}, "updatedAt": "Thu Jan 01 01:00:00 +0100 1970", "buildingIdentifier": "1234", "floorIdentifier": "123456", "code": "", "createdAt": "Thu Jan 01 01:00:00 +0100 1970", "infoHtml": "", "name": "My Geofence", "identifier": "12345678-aaaa-bbbb-cccc-12345678abcd"}]
})
```

### MapView class

#### - onLoad

Get notified about the \<map-view\> HTMLElement component being loaded.

- This callback will return the controller of MapView, [MapViewController](https://developers.situm.com/sdk_documentation/cordova/jsdoc/latest/mapviewcontrollerimpl), to start managing the map-view as you may want.

```js
cordova.plugins.MapView.onLoad((controller: any) => {
  // Once the MapView was loaded you can start managing our map by:

  // 1. Sending actions like selecting or navigating to a poi in a building:
  // controller.selectPoi('YOUR_POI_IDENTIFIER');
  // controller.navigateToPoi('YOUR_POI_IDENTIFIER');

  // 2. Listen to events that take place inside our map like a poi being selected or deselected:
  controller.onPoiSelected((poiSelectedResult: any) => {
    console.log('EXAMPLE> onPoiSelected -> ', poiSelectedResult)
  })

  controller.onPoiDeselected((poiDeselectedResult: any) => {
    console.log('EXAMPLE> onPoiDeselected -> ', poiDeselectedResult)
  })
})
```

### MapViewController class

#### - selectPoi

Select a POI in the map.

- You can find out what identifier has a certain POI of your building by previously retrieving them with [**cordova.plugins.Situm.fetchIndoorPOIsFromBuilding()**](#--fetchIndoorPOIsFromBuilding)

```js
cordova.plugins.MapView.onLoad((controller: any) => {
  // Once the MapView was loaded you can select a POI in our map by calling:
  controller.selectPoi('YOUR_POI_IDENTIFIER')
})
```

#### - navigateToPoi

Navigate to a POI in the map when positioning indoors inside a building.

- You can also specify the route you want to calculate by specifying the accessibilityMode parameter. See the different [accessibility modes](https://developers.situm.com/sdk_documentation/cordova/jsdoc/latest/mapviewcontrollerimpl#navigateToPoi) you can choose.

```js
cordova.plugins.MapView.onLoad((controller: any) => {
  // Once the MapView was loaded you can navigate to a POI in our map.
  // Make sure to call this method once the user is positioning indoors in your building,
  // otherwise this method will have no effect.
  controller.navigateToPoi('YOUR_POI_IDENTIFIER')

  // controller.navigateToPoi("YOUR_POI_IDENTIFIER", "CHOOSE_SHORTEST");
})
```

#### - onPoiSelected

Listen to the selection event of a POI.

- This method will be called when some POI is selected, either by the user clicking a POI inside the MapView or by calling [controller.selectPoi()](#--selectPoi).
- This callbacks returns a [PoiSelectedResult](https://developers.situm.com/sdk_documentation/cordova/jsdoc/latest/global#PoiSelectedResult).

```js
cordova.plugins.MapView.onLoad((controller: any) => {
  // Once the MapView was loaded you can listen to a POI being selected.
  controller.onPoiSelected((poiSelectedResult: any) => {
    console.log('EXAMPLE> onPoiSelected -> ', poiSelectedResult)
  })
})
```

#### - onPoiSelected

Listen to listen to a POI being deselected.

- This callbacks returns a [PoiDeselectedResult](https://developers.situm.com/sdk_documentation/cordova/jsdoc/latest/global#PoiDeselectedResult).

```js
cordova.plugins.MapView.onLoad((controller: any) => {
  // Once the MapView was loaded you can listen to a POI being deselected.
  controller.onPoiDeselected((poiDeselectedResult: any) => {
    console.log('EXAMPLE> onPoiDeselected -> ', poiDeselectedResult)
  })
})
```

---

## Development

### Run javascript tests

    1.  Install mocha and expect.js:

    ```javascript
    npm install mocha --save
    npm install expect.js --save
    ```

    2. In js tests folder run:

```javascript
mocha test
```

#### Dependencies

- [mocha](https://www.npmjs.com/package/mocha), needed to run tests.
- [expect.js](https://www.npmjs.com/package/expect.js), needed to do assertions.

### Note for Android platform

Situm SDK for Android now compiles and targets sdkVersion 31 (Android 12). To work properly on Android 12 devices and above, the host app must:

- Target android api 31 or above. In your project `config.xml` file, add `<preference name="android-targetSdkVersion" value="31" />` to the Android platform configuration.
- Request the runtime permissions `ACCESS_COARSE_LOCATION`, `BLUETOOTH_SCAN` and `BLUETOOTH_CONNECT` (plus `ACCESS_FINE_LOCATION` if you are using [Global Mode](https://situm.com/docs/how-does-situm-work/#5-toc-title)). Remember to also add them to the Android platform section of your `config.xml` file:

```xml
  <config-file parent="/manifest" target="AndroidManifest.xml">
    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
    <uses-permission android:name="android.permission.BLUETOOTH_SCAN" />
    <uses-permission android:name="android.permission.BLUETOOTH_CONNECT" />
  </config-file>
```

- Add `android:exported="true"` to all the intent-filtered components of your `AndroidManifest.xml` file. You can add the following configuration to your `config.xml` to automate this process:

```xml
  <edit-config
      file="app/src/main/AndroidManifest.xml"
      target="/manifest/application/activity[@android:name='MainActivity']"
      mode="merge">
    <activity android:exported="true"/>
  </edit-config>
```

- Make sure the `widget` root element of your `config.xml` file declares the Android namespace:

```xml
  <widget id="..." version="..."
    ...
    xmlns:android="http://schemas.android.com/apk/res/android">
```

- If you find problems, also make sure the Gradle JDK points to version 11 in your project configuration (recommended Android Studio embedded JDK).

### Capacitor compatibility

This plugin is compatible with Capacitor 3.0.

**Issue**: In iOS, there is a known issue with capacitor-cli 3.2.5 and static cordova plugins https://github.com/ionic-team/capacitor/issues/5142. To solve it use a different version of capacitor cli.

If the problem persists, add this plugin to the `staticPlugins` section of your `capacitor.config.json` file:

```json
{
  "cordova": {
    "staticPlugins": ["@situm/cordova"]
  }
}
```

## More information

More info is available at our [Developers Page](https://situm.com/docs/01-introduction/).

## Support information

For any question or bug report, please send an email to [support@situm.es](mailto:support@situm.es)
