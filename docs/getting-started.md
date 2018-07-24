<h1>Quick start guide</h1>

<div id="guide__introduction" class="guide__section">
<h2>Introduction</h2>
</div>

In this tutorial, we will guide you step by step to set up your first Cordova application using Cordova Situm Plugin. 
Before starting to write code, we recommend you to set up an account on our Dashboard ([https://dashboard.situm.es](https://dashboard.situm.es)), retrieve your API KEY and configure your first building.

1. Go to the [sign in form](http://dashboard.situm.es/accounts/register) and enter your username and password to sign in.

2. Go to the [account section](https://dashboard.situm.es/accounts/profile) and on the bottom, click on “generate one” to generate your API KEY.

3. Go to the [buildings section](http://dashboard.situm.es/buildings) and create your first building.

4. Download Situm Mapping Tool in Play Store (Only Android devices) and calibrate your building. Check out our user guide for detailed information.

5. You are ready to build your own Cordova applications. Please check the next steps for requirements:


<div id="guide__pre_requisites" class="guide__section">
<h2>Installing pre-requisites</h2>
</div>

### Configure cordova:

* [Installing the Cordova CLI](https://cordova.apache.org/docs/en/latest/guide/cli/index.html#installing-the-cordova-cli)

### Cordova requirements:

* [Android](https://cordova.apache.org/docs/en/latest/guide/platforms/android/#installing-the-requirements)

* [iOS](https://cordova.apache.org/docs/en/latest/guide/platforms/android/#installing-the-requirements)


<div id="guide__installing_the_plugin" class="guide__section">
<h2>Installing the plugin</h2>
</div>

We assume that you have already created a hybrid application with your favorite framework (Ionic, PhoneGap, Appcelerator, Telerik...). There are some different ways to install the plugin:

### 1) Manually from npm:

```bash
$ cordova plugin add situm-cordova-plugin-official
```

### 2) Defined in config.xml for automatic installation:

```xml
  <plugin name="situm-cordova-plugin-official" source="npm">

  </plugin>
```

### 3) With Cordova CLI utility from master (or another branch):

```
$ cordova plugin add https://github.com/situmtech/situm-cordova-plugin.git
```

<div id="guide__using_the_plugin" class="guide__section">
<h2>Using the plugin</h2>
</div>

### Accessing plugin object

When the device ready event is fired, the global cordova variable is injected into the namespace. Plugins are available in this variable: cordova.plugins. The Situm Cordova Plugin is autowired within this object.

So, all methods are called in the same way, e.g. 'setApiKey':

```javascript
  cordova.plugins.Situm.setApiKey(email, apiKey);
```

<div id="guide__methods" class="guide__section">
<h2>Methods</h2>
</div>

#### - setApiKey

Allows you to log in to your Situm Account. This key is generated in Situm Dashboard. Returns true if apiKey was set successfully, otherwise false

```javascript
  setApiKey("your_email@domain.com", "YOUR_API_KEY");
```

#### - setUserPass

Provides user's email and password.

```javascript
  setUserPass("email@domain.com", "yourPassword");
````

#### - setCacheMaxAge

Sets the maximum age of a cached response in seconds. Some information, such as floor plans, floors, POIs, etc. is stored in the cache. After the cache expires, we will fetch this information from the server again.

```javascript
  setCacheMaxAge(200);
```

#### - startPositioning

Starts the positioning on the [locationRequest](http://developers.situm.es/sdk_documentation/cordova/jsdoc/1.3.10/symbols/LocationRequest.html), a [building](http://developers.situm.es/sdk_documentation/cordova/jsdoc/1.3.10/symbols/Building.html) should be sent as the first parameter and a locationRequest should be sent as the second one. Returns a set of locationStatus and [location](http://developers.situm.es/sdk_documentation/cordova/jsdoc/1.3.10/symbols/Location.html) through the success callback.

```javascript
  startPositioning([building, locationRequest], position => {
    // position as location object:
  }, (error) => {
    // If errors will come here
  });
```

#### - stopPositioning

Stops locationListener on current active listener.

```javascript
  stopPositioning(() => {
    // Your code here
  }, (error) => {
    // If errors will come here
  });
```

#### - fetchBuildings

Downloads all the [buildings](http://developers.situm.es/sdk_documentation/cordova/jsdoc/1.3.10/symbols/Building.html) for the current user.

```javascript
  fetchBuildings((buildings) => {
    // Array of buildings
  }, (error) => {
    // If errors will come here
  });
```

#### - fetchFloorsFromBuilding

Downloads all the [floors](http://developers.situm.es/sdk_documentation/cordova/jsdoc/1.3.10/symbols/Floor.html) of a [building](http://developers.situm.es/sdk_documentation/cordova/jsdoc/1.3.10/symbols/Building.html).

```javascript
  fetchFloorsFromBuilding(building, (floors) => {
    // Array of floors
  }, (error) => {
    // If errors will come here
  });
```

#### - fetchIndoorPOIsFromBuilding

Downloads the indoor [POIs](http://developers.situm.es/sdk_documentation/cordova/jsdoc/1.3.10/symbols/Poi.html) of a [building](http://developers.situm.es/sdk_documentation/cordova/jsdoc/1.3.10/symbols/Building.html).

```javascript
  fetchIndoorPOIsFromBuilding(building, (pois) => {
    // Array of pois
  }, (error) => {
    // If errors will come here
  });
```

#### - fetchOutdoorPOIsFromBuilding

Downloads the outdoor [POIs](http://developers.situm.es/sdk_documentation/cordova/jsdoc/1.3.10/symbols/Poi.html) of a [building](http://developers.situm.es/sdk_documentation/cordova/jsdoc/1.3.10/symbols/Building.html).

```javascript
  fetchOutdoorPOIsFromBuilding(building, (pois) => {
    // Array of pois
  }, (error) => {
    // If errors will come here
  });
```

#### - fetchEventsFromBuilding

Downloads the events of a [building](http://developers.situm.es/sdk_documentation/cordova/jsdoc/1.3.10/symbols/Building.html).

```javascript
  fetchEventsFromBuilding(building, (events) => {
    // Array of Situm events
  }, (error) => {
    // If errors will come here
  });
```

#### - fetchPoiCategories

Gets all [POI categories](http://developers.situm.es/sdk_documentation/cordova/jsdoc/1.3.10/symbols/PoiCategory.html), download and cache their icons asynchronously.

```javascript
  fetchPoiCategories((poiCategories) => {
    // Array of POI cateogires
  }, (error) => {
    // If errors will come here
  });
```

#### - fetchMapFromFloor

Downloads the map image of a [floor](http://developers.situm.es/sdk_documentation/cordova/jsdoc/1.3.10/symbols/Floor.html).

```javascript
  fetchMapFromFloor (floor, (mapImage) => {
    // Map image as a bitmap
  }, (error) => {
    // If errors will come here
  });
```

#### - fetchPoiCategoryIconNormal

Gets the normal category icon for a [category](http://developers.situm.es/sdk_documentation/cordova/jsdoc/1.3.10/symbols/PoiCategory.html).

```javascript
  fetchPoiCategoryIconNormal (poiCategory, (iconNormal) => {
    // Icon as a bitmap
  }, (error) => {
    // If errors will come here
  });
```

#### - fetchPoiCategoryIconSelected

Gets the selected category icon for a [category](http://developers.situm.es/sdk_documentation/cordova/jsdoc/1.3.10/symbols/PoiCategory.html).

```javascript
  fetchPoiCategoryIconSelected (poiCategory, (iconSelected) => {
    // Icon as a bitmap
  }, (error) => {
    // If errors will come here
  });
```

#### - invalidateCache

Invalidates all the resources in the cache.

```javascript
  invalidateCache((response) => {});
```
#### - requestDirections

Calculates a route between two [points](http://developers.situm.es/sdk_documentation/cordova/jsdoc/1.3.10/symbols/Point.html).

```javascript
  requestDirections([building, from, to], (route) => {
    // Route Situm object
  }, (error) => {
    // If errors will come here
  });
```

#### - requestNavigationUpdates

Requests navigation updates. This method should be called after `requestDirections` in order to provide navigation updates when a new position is computed.

```javascript
var navigationOptions = new Object();
  navigationOptions.distanceToFloorChangeThreshold = 0; // number in meters
  navigationOptions.distanceToChangeIndicationThreshold = 0; // number in meters
  navigationOptions.distanceToGoalThreshold = 0; // number in meters
  navigationOptions.outsideRouteThreshold = 0; // number in meters
  navigationOptions.indicationsInterval = 0; // number in millis
  navigationOptions.timeToFirstIndication = 0; // number in millis
  navigationOptions.roundIndicationsStep = 0; // number in meters
  navigationOptions.timeToIgnoreUnexpectedFloorChanges = 0; // number in millis
  requestNavigationUpdates([navigationOptions], (res: any) => {
    // Progress and other navigation status messages can be processed here  
  }, (error: any) => {
    // If errors will come here 
  });
```

#### - updateNavigationWithLocation

Updates navigation progress with a location, which would usually be provided by the [`startPositioning`](#--startpositioning) method. Returns progress and other navigation status messages. This method should be called after `requestNavigationUpdates`.

```javascript
  updateNavigationWithLocation([position], (result) => {
    // Result
  }, (error) => {
    // If errors will come here
  });
```

#### - removeNavigationUpdates

Removes all location updates. This removes the internal state of the manager, including the listener provided in `requestNavigationUpdates`,so it won't receive more progress updates.

```javascript
  removeNavigationUpdates();
```

## License

Situm-Cordova-Plugin is licensed under [MIT License](https://opensource.org/licenses/MIT)
