# Situm Cordova Plugin &middot; [![npm](https://img.shields.io/npm/dm/situm-cordova-plugin-official-development.svg)]() [![npm](https://img.shields.io/npm/v/situm-cordova-plugin-official-development.svg)]()

[![](https://situm.es/assets/svg/logo-situm.svg)](https://www.situm.es)

---

## - Table of contents

* [Description](#large_blue_diamond-description)
* [Setup](#large_blue_diamond-setup-your-account)
* [Pre-requisites](#large_blue_diamond-installing-pre-requisites)
* [Install plugin](#large_blue_diamond-installing-the-plugin)
* [Using plugin](#large_blue_diamond-using-the-plugin)
  * [Accesing plugin object](#accessing-plugin-object)
  * [setApiKey](#--setapikey)
  * [setUserPass](#--setUserPass)
  * [setCacheMaxAge](#--setCacheMaxAge)
  * [startPositioning](#--startPositioning)
  * [stopPositioning](#--stopPositioning)
  * [fetchBuildings](#--fetchBuildings)
  * [fetchFloorsFromBuilding](#--fetchFloorsFromBuilding)
  * [fetchIndoorPOIsFromBuilding](#--fetchIndoorPOIsFromBuilding)
  * [fetchOutdoorPOIsFromBuilding](#--fetchOutdoorPOIsFromBuilding)

---

## :large_blue_diamond: Description

###### Situm Cordova Plugin is a set of utilities that allow any developer to build Cordova location based apps using Situm's indoor positioning system. Among many other capabilities, apps developed with Situm Cordova Plugin will be able to:

###### · Obtain information related to buildings where Situm's positioning system is already configured: floorplans, points of interest, geotriggered events, etc.

###### · Retrieve the location of the smartphone inside these buildings (position, orientation, and floor where the smartphone is).

###### · Compute a route from a point A (e.g. where the smartphone is) to a point B (e.g. any point of interest within the building).

###### · Trigger notifications when the user enters a certain area.

---

## :large_blue_diamond: Setup your account

###### In this tutorial, we will guide you step by step to set up your first Cordova application using Cordova Situm Plugin. Before starting to write code, we recommend you to set up an account in our Dashboard (https://dashboard.situm.es), retrieve your API KEY and configure your first building.

###### 1. Go to the [sign in form](http://dashboard.situm.es/accounts/register) and enter your username and password to sign in.

###### 2. Go to the [account section](https://dashboard.situm.es/accounts/profile) and on the bottom, click on “generate one” to generate your API KEY.

###### 3. Go to the [buildings section](http://dashboard.situm.es/buildings) and create your first building.

###### 4. Download Situm Mapping Tool in Play Store (Only Android devices) and calibrate your building. Check out our user guide for detailed information.

###### 5. You are ready for building your own Cordova applications. Please check next steps about requirements

---

## :large_blue_diamond: Installing pre-requisites

###### - Configure cordova:

* https://cordova.apache.org/docs/en/latest/guide/cli/index.html#installing-the-cordova-cli

###### - Cordova requirements:

* Android: https://cordova.apache.org/docs/en/latest/guide/platforms/android/#installing-the-requirements

* iOS: https://cordova.apache.org/docs/en/latest/guide/platforms/android/#installing-the-requirements

---

## :large_blue_diamond: Installing the plugin

###### In this we assume that you have already created an hybrid application with your favorite framework (Ionic, Phonegap, Appcelerator, Telerik...). After that there are some different ways to install the plugin:

#### 1) Manually from npm:

```bash
$ cordova plugin add situm-cordova-plugin-official-development
```

#### 2) Defined in config.xml for automatic installation:

```xml
  <plugin name="situm-cordova-plugin-official-development" source="npm">

  </plugin>
```

#### 3) With Cordova CLI utility from master (or another branch):

```
$ cordova plugin add https://github.com/situmtech/situm-cordova-plugin.git
```

## :large_blue_diamond: Using the Plugin

#### Accessing plugin object

```javascript

When device ready event is fired, global cordova variable is injected in namespace. Plugins are available in this variable: cordova.plugins. The Situm Cordova Plugin is autowired within this object.

So, all methods are called in the same way, e.g. 'setApiKey':

```
cordova.plugins.Situm.setApiKey(email, apiKey);
```

#### - setApiKey

###### Log in into your Situm Account. This key is generated in Situm Dashboard. Return true if apiKey was set successfully, otherwise false

```javascript
setApiKey("your_email@domain.com", "YOUR_API_KEY");
```

#### - setUserPass

###### Provides user's email and password.

```javascript
setUserPass("email@domain.com", "ourPassword");
```

#### - setCacheMaxAge

###### Sets the maximum age of a cached response in seconds.

```javascript
setCacheMaxAge(200);
```

#### - startPositioning

###### Starts listen onLocationChanged event on first [building](http://htmlpreview.github.io/?https://github.com/cocodinTech/situm-cordova-plugin/blob/master/www/android/docs/symbols/Building.html) in array

```javascript
startPositioning([building]);
```

#### - stopPositioning

###### Stop locationListener on current active listener.

```javascript
stopPositioning();
```

#### - fetchBuildings

###### Download all the [buildings](http://htmlpreview.github.io/?https://github.com/cocodinTech/situm-cordova-plugin/blob/master/www/android/docs/symbols/Building.html) for the current user.

```javascript
fetchBuildings();
```

#### - fetchFloorsFromBuilding

###### Download all the [floors](http://htmlpreview.github.io/?https://github.com/cocodinTech/situm-cordova-plugin/blob/master/www/android/docs/symbols/Floor.html) of a [building](http://htmlpreview.github.io/?https://github.com/cocodinTech/situm-cordova-plugin/blob/master/www/android/docs/symbols/Building.html).

```javascript
fetchFloorsFromBuilding(building);
```

#### - fetchIndoorPOIsFromBuilding

###### Download the indoor [POIs](http://htmlpreview.github.io/?https://github.com/cocodinTech/situm-cordova-plugin/blob/master/www/android/docs/symbols/Poi.html) of a [building](http://htmlpreview.github.io/?https://github.com/cocodinTech/situm-cordova-plugin/blob/master/www/android/docs/symbols/Building.html).

```javascript
fetchIndoorPOIsFromBuilding(building);
```

#### - fetchOutdoorPOIsFromBuilding

###### Download the outdoor [POIs](http://htmlpreview.github.io/?https://github.com/cocodinTech/situm-cordova-plugin/blob/master/www/android/docs/symbols/Poi.html) of a [building](http://htmlpreview.github.io/?https://github.com/cocodinTech/situm-cordova-plugin/blob/master/www/android/docs/symbols/Building.html).

```javascript
fetchOutdoorPOIsFromBuilding(building);
```

## :large_blue_diamond: Contributing

```
TODO CONTRIBUTORS ?
```

## :large_blue_diamond: License

```
TODO LICENSING (MIT) ?
```
