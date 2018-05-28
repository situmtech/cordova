# Situm Cordova Plugin &middot; [![npm](https://img.shields.io/npm/dm/situm-cordova-plugin-official.svg)](https://www.npmjs.com/package/situm-cordova-plugin-official) [![npm](https://img.shields.io/npm/v/situm-cordova-plugin-official.svg)](https://www.npmjs.com/package/situm-cordova-plugin-official) [![npm](https://img.shields.io/npm/l/situm-cordova-plugin-official.svg)](https://opensource.org/licenses/MIT)

[![](https://situm.es/assets/svg/logo-situm.svg)](https://www.situm.es)

---

## Table of contents

  * [Description](#description)
  * [Setup your account](#setup-your-account)
  * [Installing pre-requisites](#installing-pre-requisites)
  * [Installing the plugin](#installing-the-plugin)
  * [Using the Plugin](#using-the-plugin)
    + [Accessing plugin object](#accessing-plugin-object)
    + [Methods](#methods)
      - [setApiKey](#--setapikey)
      - [setUserPass](#--setuserpass)
      - [setCacheMaxAge](#--setcachemaxage)
      - [startPositioning](#--startpositioning)
      - [stopPositioning](#--stoppositioning)
      - [fetchBuildings](#--fetchbuildings)
      - [fetchFloorsFromBuilding](#--fetchfloorsfrombuilding)
      - [fetchIndoorPOIsFromBuilding](#--fetchindoorpoisfrombuilding)
      - [fetchOutdoorPOIsFromBuilding](#--fetchoutdoorpoisfrombuilding)
      - [fetchEventsFromBuilding](#--fetcheventsfrombuilding)
      - [fetchPoiCategories](#--fetchpoicategories)
      - [fetchMapFromFloor](#--fetchmapfromfloor)
      - [fetchPoiCategoryIconNormal](#--fetchpoicategoryiconnormal)
      - [fetchPoiCategoryIconSelected](#--fetchpoicategoryiconselected)
      - [invalidateCache](#--invalidatecache)
      - [requestDirections](#--requestdirections)
      - [requestNavigationUpdates](#--requestnavigationupdates)
      - [updateNavigationWithLocation](#--updatenavigationwithlocation)
      - [removeNavigationUpdates](#--removenavigationupdates)
  * [License](#license)
  * [More information](#more-information)

---

## Description

Situm Cordova Plugin is a set of utilities that allow any developer to build Cordova location based apps using Situm's indoor positioning system. Among many other capabilities, apps developed with Situm Cordova Plugin will be able to:

* Obtain information related to buildings where Situm's positioning system is already configured: floorplans, points of interest, geotriggered events, etc.

* Retrieve the location of the smartphone inside these buildings (position, orientation, and floor where the smartphone is).

* Compute a route from a point A (e.g. where the smartphone is) to a point B (e.g. any point of interest within the building).

* Trigger notifications when the user enters a certain area.

---

## Setup your account

In this tutorial, we will guide you step by step to set up your first Cordova application using Cordova Situm Plugin. Before starting to write code, we recommend you to set up an account in our Dashboard (https://dashboard.situm.es), retrieve your API KEY and configure your first building.

1. Go to the [sign in form](http://dashboard.situm.es/accounts/register) and enter your username and password to sign in.

2. Go to the [account section](https://dashboard.situm.es/accounts/profile) and on the bottom, click on “generate one” to generate your API KEY.

3. Go to the [buildings section](http://dashboard.situm.es/buildings) and create your first building.

4. Download Situm Mapping Tool in Play Store (Only Android devices) and calibrate your building. Check out our user guide for detailed information.

5. You are ready for building your own Cordova applications. Please check next steps about requirements

---

## Installing pre-requisites

### Configure cordova:

* https://cordova.apache.org/docs/en/latest/guide/cli/index.html#installing-the-cordova-cli

### Cordova requirements:

* Android: https://cordova.apache.org/docs/en/latest/guide/platforms/android/#installing-the-requirements

* iOS: https://cordova.apache.org/docs/en/latest/guide/platforms/android/#installing-the-requirements

---

## Installing the plugin

In this we assume that you have already created an hybrid application with your favorite framework (Ionic, Phonegap, Appcelerator, Telerik...). After that there are some different ways to install the plugin:

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

## Using the Plugin

### Accessing plugin object

When device ready event is fired, global cordova variable is injected in namespace. Plugins are available in this variable: cordova.plugins. The Situm Cordova Plugin is autowired within this object.

So, all methods are called in the same way, e.g. 'setApiKey':

```javascript
  cordova.plugins.Situm.setApiKey(email, apiKey);
```

### Methods

#### - setApiKey

Log in into your Situm Account. This key is generated in Situm Dashboard. Return true if apiKey was set successfully, otherwise false

```javascript
  setApiKey("your_email@domain.com", "YOUR_API_KEY");
```

#### - setUserPass

Provides user's email and password.

```javascript
  setUserPass("email@domain.com", "ourPassword");
```

#### - setCacheMaxAge

Sets the maximum age of a cached response in seconds.

```javascript
  setCacheMaxAge(200);
```

#### - startPositioning

Starts the positioning on the [locationRequest](http://developers.situm.es/sdk_documentation/cordova/jsdoc/1.3.10/symbols/LocationRequest.html), a [building](http://developers.situm.es/sdk_documentation/cordova/jsdoc/1.3.10/symbols/Building.html) mey be send as first parameter (deprecated), returns a [location](http://developers.situm.es/sdk_documentation/cordova/jsdoc/1.3.10/symbols/Location.html).

```javascript
  startPositioning([building, locationRequest], position => {
    // position as location object
  }, (error) => {
    // If errors will come here
  });
```

#### - stopPositioning

Stop locationListener on current active listener.

```javascript
  stopPositioning(() => {
    // Your code here
  }, (error) => {
    // If errors will come here
  });
```

#### - fetchBuildings

Download all the [buildings](http://developers.situm.es/sdk_documentation/cordova/jsdoc/1.3.10/symbols/Building.html) for the current user.

```javascript
  fetchBuildings((buildings) => {
    // Array of buildings
  }, (error) => {
    // If errors will come here
  });
```

#### - fetchFloorsFromBuilding

Download all the [floors](http://developers.situm.es/sdk_documentation/cordova/jsdoc/1.3.10/symbols/Floor.html) of a [building](http://developers.situm.es/sdk_documentation/cordova/jsdoc/1.3.10/symbols/Building.html).

```javascript
  fetchFloorsFromBuilding(building, (floors) => {
    // Array of floors
  }, (error) => {
    // If errors will come here
  });
```

#### - fetchIndoorPOIsFromBuilding

Download the indoor [POIs](http://developers.situm.es/sdk_documentation/cordova/jsdoc/1.3.10/symbols/Poi.html) of a [building](http://developers.situm.es/sdk_documentation/cordova/jsdoc/1.3.10/symbols/Building.html).

```javascript
  fetchIndoorPOIsFromBuilding(building, (pois) => {
    // Array of pois
  }, (error) => {
    // If errors will come here
  });
```

#### - fetchOutdoorPOIsFromBuilding

Download the outdoor [POIs](http://developers.situm.es/sdk_documentation/cordova/jsdoc/1.3.10/symbols/Poi.html) of a [building](http://developers.situm.es/sdk_documentation/cordova/jsdoc/1.3.10/symbols/Building.html).

```javascript
  fetchOutdoorPOIsFromBuilding(building, (pois) => {
    // Array of pois
  }, (error) => {
    // If errors will come here
  });
```

#### - fetchEventsFromBuilding

Download the events of a [building](http://developers.situm.es/sdk_documentation/cordova/jsdoc/1.3.10/symbols/Building.html).

```javascript
  fetchEventsFromBuilding(building, (events) => {
    // Array of Situm events
  }, (error) => {
    // If errors will come here
  });
```

#### - fetchPoiCategories

Get all [POI categories](http://developers.situm.es/sdk_documentation/cordova/jsdoc/1.3.10/symbols/PoiCategory.html), download and cache their icons asynchronously.

```javascript
  fetchPoiCategories((poiCategories) => {
    // Array of POI cateogires
  }, (error) => {
    // If errors will come here
  });
```

#### - fetchMapFromFloor

Download the map image of a [floor](http://developers.situm.es/sdk_documentation/cordova/jsdoc/1.3.10/symbols/Floor.html).

```javascript
  fetchMapFromFloor (floor, (mapImage) => {
    // Map image as a bitmap
  }, (error) => {
    // If errors will come here
  });
```

#### - fetchPoiCategoryIconNormal

Get the normal category icon for a [POICategory](http://developers.situm.es/sdk_documentation/cordova/jsdoc/1.3.10/symbols/PoiCategory.html).

```javascript
  fetchPoiCategoryIconNormal (poiCategory, (iconNormal) => {
    // Icon as a bitmap
  }, (error) => {
    // If errors will come here
  });
```

#### - fetchPoiCategoryIconSelected

Get the selected category icon for a [POICategory](http://developers.situm.es/sdk_documentation/cordova/jsdoc/1.3.10/symbols/PoiCategory.html).

```javascript
  fetchPoiCategoryIconSelected (poiCategory, (iconSelected) => {
    // Icon as a bitmap
  }, (error) => {
    // If errors will come here
  });
```

#### - invalidateCache

Invalidate all the resources in the cache.

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

Necessary step to request progress. Alone this method does not provide progress object. You must feed navigation API with location, as indicated on updateNavigationWithLocation section.

```javascript
  var navigationOptions = new Object();
  navigationOptions["distanceToIgnoreFirstIndication"] = 0.3; // (Optional) meters;
  navigationOptions["outsideRouteThreshold"] = 10; // (Optional) meters;
  navigationOptions["distanceToGoalThreshold"] = 7; // (Optional) meters;
  navigationOptions["distanceToFloorChangeThreshold"] = 10; // (Optional) meters;
  navigationOptions["distanceToChangeIndicationThreshold"] = 5; // (Optional) meters

  requestNavigationUpdates([navigationOptions], (res: any) => {
    // Progress and other navigation status messages can be processed here  
  }, (error: any) => {
    // If errors will come here 
  });
```

#### - updateNavigationWithLocation
    
Usually, position variable should be one of the locations provided by the system on the [startPositioning](#--startpositioning) function.

```javascript
  updateNavigationWithLocation([position], (result) => {
    // Result
  }, (error) => {
    // If errors will come here
  });
```

#### - removeNavigationUpdates

When you are no longer interested on Navigation Updates you should call this method to remove internal allocated resources.

```javascript
  removeNavigationUpdates();
```

## License

Situm-Cordova-Plugin is licensed under [MIT License](https://opensource.org/licenses/MIT)

## More information

More info is available at our [Developers Page](http://developers.situm.es/pages/cordova/).
For any other question, contact us [here](mailto:support@situm.es)