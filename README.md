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
  * [Methods](#methods)
    * [setApiKey](#--setapikey)
    * [setUserPass](#--setuserpass)
    * [setCacheMaxAge](#--setcachemaxAge)
    * [startPositioning](#--startpositioning)
    * [stopPositioning](#--stoppositioning)
    * [fetchBuildings](#--fetchbuildings)
    * [fetchFloorsFromBuilding](#--fetchfloorsfrombuilding)
    * [fetchIndoorPOIsFromBuilding](#--fetchindoorpoisfrombuilding)
    * [fetchOutdoorPOIsFromBuilding](#--fetchoutdoorpoisfrombuilding)
    * [fetchEventsFromBuilding](#--fetcheventsfrombuilding)
    * [fetchPoiCategories](#--fetchpoicategories)
    * [fetchMapFromFloor](#--fetchmapfromFloor)
    * [fetchPoiCategoryIconNormal](#--fetchpoicategoryiconnormal)
    * [fetchPoiCategoryIconSelected](#--fetchpoicategoryiconselected)
    * [invalidateCache](#--invalidatecache)
    * [requestDirections](#--requestdirections)
    * [requestNavigationUpdates](#--requestnavigationupdates)
    * [updateNavigationWithLocation](#--updatenavigationwithlocation)
    * [removeNavigationUpdates](#--removenavigationupdates)

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

When device ready event is fired, global cordova variable is injected in namespace. Plugins are available in this variable: cordova.plugins. The Situm Cordova Plugin is autowired within this object.

So, all methods are called in the same way, e.g. 'setApiKey':

```javascript
  cordova.plugins.Situm.setApiKey(email, apiKey);
```

#### Methods

##### - setApiKey

###### Log in into your Situm Account. This key is generated in Situm Dashboard. Return true if apiKey was set successfully, otherwise false

```javascript
  setApiKey("your_email@domain.com", "YOUR_API_KEY");
```

##### - setUserPass

###### Provides user's email and password.

```javascript
  setUserPass("email@domain.com", "ourPassword");
```

##### - setCacheMaxAge

###### Sets the maximum age of a cached response in seconds.

```javascript
  setCacheMaxAge(200);
```

##### - startPositioning

###### Starts listen onLocationChanged event on first [building](http://htmlpreview.github.io/?https://github.com/cocodinTech/situm-cordova-plugin/blob/master/www/android/docs/symbols/Building.html) in array

```javascript
  var building = {
    buildingIdentifier: "buildingIdentifier",
    name: "buildingName",
    address: "address",
    bounds: {
      northEast: {
        latitude: 0.00,
        longitude: 0.00
      },
      northWest: {
        latitude: 0.00,
        longitude: 0.00
      },
      southEast: {
        latitude: 0.00,
        longitude: 0.00
      },
      southWest: {
        latitude: 0.00,
        longitude: 0.00
      }
    },
    boundsRotated: {
      northEast: {
        latitude: 0.00,
        longitude: 0.00
      },
      northWest: {
        latitude: 0.00,
        longitude: 0.00
      },
      southEast: {
        latitude: 0.00,
        longitude: 0.00
      },
      southWest: {
        latitude: 0.00,
        longitude: 0.00
      }
    },
    center: {
      latitude: 0.00,
      longitude: 0.00
    },
    dimensions: {
      width: 0.00,
      height: 0.00
    },
    infoHtml: "infoHtml",
    pictureThumbUrl: "pictureThumbUrl",
    pictureUrl: "pictureUrl",
    rotation: 0.00,
    userIdentifier: "userIdentifier",
    customFields: {
      name: "value",
      name: "value",
      ...
    }
  }

  startPositioning([building], position => {
    // position as location object:
    position = {
      accuracy: 0.00,
      bearing: {
        degrees: 0.00,
        degreesClockwise: 0.00,
        radians: 0.00,
        radiansMinusPiPi: 0.00,
      },
      bearingQuality: "bearingQuality",
      buildingIdentifier: "buildingIdentifier",
      cartesianBearing: {
        degrees: 0.00,
        degreesClockwise: 0.00,
        radians: 0.00,
        radiansMinusPiPi: 0.00,
      },
      cartesianCoordinate: {
        x: 0.00,
        y: 0.00 
      },
      coordinate: {
        latitude: 0.00,
        longitude: 0.00
      },
      floorIdentifier: "floorIdentifier",
      position: {
        buildingIdentifier: "buildingIdentifier",
        cartesianCoordinate: {
          x: 0.00,
          y: 0.00 
        },
        coordinate: {
          latitude: 0.00,
          longitude: 0.00
        }
        floorIdentifier: "floorIdentifier"
        isIndoor: true
        isOutdoor: false
      },
      provider: "provider",
      quality: "quality",
      hasBearing: true,
      timestamp: 000000000,
      hasCartesianBearing: true,
      isIndoor: true,
      isOutdoor: false,
      deviceId: "deviceId"
    };
  }, (error) => {
    // If errors will come here
    error = {
      status: 1,
      message: "message"
    }
  });
```

##### - stopPositioning

###### Stop locationListener on current active listener.

```javascript
  stopPositioning(() => {
    // Your code here
  }, (error) => {
    // If errors will come here
    error = {
      status: 1,
      message: "message"
    }
  });
```

##### - fetchBuildings

###### Download all the [buildings](http://htmlpreview.github.io/?https://github.com/cocodinTech/situm-cordova-plugin/blob/master/www/android/docs/symbols/Building.html) for the current user.

```javascript
  fetchBuildings((buildings) => {
    // Array of buildings:
    buildings = [{  
      buildingIdentifier: "buildingIdentifier",
      name: "buildingName",
      address: "address",
      bounds: {
        northEast: {
          latitude: 0.00,
          longitude: 0.00
        },
        northWest: {
          latitude: 0.00,
          longitude: 0.00
        },
        southEast: {
          latitude: 0.00,
          longitude: 0.00
        },
        southWest: {
          latitude: 0.00,
          longitude: 0.00
        }
      },
      boundsRotated: {
        northEast: {
          latitude: 0.00,
          longitude: 0.00
        },
        northWest: {
          latitude: 0.00,
          longitude: 0.00
        },
        southEast: {
          latitude: 0.00,
          longitude: 0.00
        },
        southWest: {
          latitude: 0.00,
          longitude: 0.00
        }
      },
      center: {
        latitude: 0.00,
        longitude: 0.00
      },
      dimensions: {
        width: 0.00,
        height: 0.00
      },
      infoHtml: "infoHtml",
      pictureThumbUrl: "pictureThumbUrl",
      pictureUrl: "pictureUrl",
      rotation: 0.00,
      userIdentifier: "userIdentifier",
      customFields: {
        name: "value",
        name: "value",
        ...
      }
    }, {
      ...
    }];
  }, (error) => {
    // If errors will come here
    error = {
      status: 1,
      message: "message"
    }
  });
```

##### - fetchFloorsFromBuilding

###### Download all the [floors](http://htmlpreview.github.io/?https://github.com/cocodinTech/situm-cordova-plugin/blob/master/www/android/docs/symbols/Floor.html) of a [building](http://htmlpreview.github.io/?https://github.com/cocodinTech/situm-cordova-plugin/blob/master/www/android/docs/symbols/Building.html).

```javascript
  var building = {
    buildingIdentifier: "buildingIdentifier",
    name: "buildingName",
    address: "address",
    bounds: {
      northEast: {
        latitude: 0.00,
        longitude: 0.00
      },
      northWest: {
        latitude: 0.00,
        longitude: 0.00
      },
      southEast: {
        latitude: 0.00,
        longitude: 0.00
      },
      southWest: {
        latitude: 0.00,
        longitude: 0.00
      }
    },
    boundsRotated: {
      northEast: {
        latitude: 0.00,
        longitude: 0.00
      },
      northWest: {
        latitude: 0.00,
        longitude: 0.00
      },
      southEast: {
        latitude: 0.00,
        longitude: 0.00
      },
      southWest: {
        latitude: 0.00,
        longitude: 0.00
      }
    },
    center: {
      latitude: 0.00,
      longitude: 0.00
    },
    dimensions: {
      width: 0.00,
      height: 0.00
    },
    infoHtml: "infoHtml",
    pictureThumbUrl: "pictureThumbUrl",
    pictureUrl: "pictureUrl",
    rotation: 0.00,
    userIdentifier: "userIdentifier",
    customFields: {
      name: "value",
      name: "value",
      ...
    }
  }

  fetchFloorsFromBuilding(building, (floors) => {
    // Array of floors
    floors = [{
      altitude: 0.00,
      buildingIdentifier: "buildingIdentifier",
      level: 1,
      mapUrl: "mapUrl",
      scale: 0.00,
      floorIdentifier: "floorIdentifier"
    }, {
      ...
    }];
  }, (error) => {
    // If errors will come here
    error = {
      status: 1,
      message: "message"
    }
  });
```

##### - fetchIndoorPOIsFromBuilding

###### Download the indoor [POIs](http://htmlpreview.github.io/?https://github.com/cocodinTech/situm-cordova-plugin/blob/master/www/android/docs/symbols/Poi.html) of a [building](http://htmlpreview.github.io/?https://github.com/cocodinTech/situm-cordova-plugin/blob/master/www/android/docs/symbols/Building.html).

```javascript
  var building = {
    buildingIdentifier: "buildingIdentifier",
    name: "buildingName",
    address: "address",
    bounds: {
      northEast: {
        latitude: 0.00,
        longitude: 0.00
      },
      northWest: {
        latitude: 0.00,
        longitude: 0.00
      },
      southEast: {
        latitude: 0.00,
        longitude: 0.00
      },
      southWest: {
        latitude: 0.00,
        longitude: 0.00
      }
    },
    boundsRotated: {
      northEast: {
        latitude: 0.00,
        longitude: 0.00
      },
      northWest: {
        latitude: 0.00,
        longitude: 0.00
      },
      southEast: {
        latitude: 0.00,
        longitude: 0.00
      },
      southWest: {
        latitude: 0.00,
        longitude: 0.00
      }
    },
    center: {
      latitude: 0.00,
      longitude: 0.00
    },
    dimensions: {
      width: 0.00,
      height: 0.00
    },
    infoHtml: "infoHtml",
    pictureThumbUrl: "pictureThumbUrl",
    pictureUrl: "pictureUrl",
    rotation: 0.00,
    userIdentifier: "userIdentifier",
    customFields: {
      name: "value",
      name: "value",
      ...
    }
  }

  fetchIndoorPOIsFromBuilding(building, (pois) => {
    // Array of pois
    pois = [{
      identifier: "identifier",
      buildingIdentifier: "buildingIdentifier",
      cartesianCoordinate: {
        x: 0.00,
        y: 0.00 
      },
      coordinate: {
        latitude: 0.00,
        longitude: 0.00
      },
      floorIdentifier: "floorIdentifier",
      poiName: "poiName",
      position: {
        buildingIdentifier: "buildingIdentifier",
        cartesianCoordinate: {
          x: 0.00,
          y: 0.00 
        },
        coordinate: {
          latitude: 0.00,
          longitude: 0.00
        }
        floorIdentifier: "floorIdentifier"
        isIndoor: true
        isOutdoor: false
      },
      isIndoor: true,
      isOutdoor: false,
      category: {
        poiCategoryCode: "poiCategoryCode",
        poiCategoryName: "poiCateogryName",
        icon_selected: "iconSelectedUrl",
        icon_unselected: "iconUnselectedUrl",
        public: true
      },
      infoHtml: "infoHtml",
      customFields: {
        name: "value",
        name: "value",
        ...
      }
    }, {
      ...
    }]
  }, (error) => {
    // If errors will come here
    error = {
      status: 1,
      message: "message"
    }
  });
```

##### - fetchOutdoorPOIsFromBuilding

###### Download the outdoor [POIs](http://htmlpreview.github.io/?https://github.com/cocodinTech/situm-cordova-plugin/blob/master/www/android/docs/symbols/Poi.html) of a [building](http://htmlpreview.github.io/?https://github.com/cocodinTech/situm-cordova-plugin/blob/master/www/android/docs/symbols/Building.html).

```javascript
  var building = {
    buildingIdentifier: "buildingIdentifier",
    name: "buildingName",
    address: "address",
    bounds: {
      northEast: {
        latitude: 0.00,
        longitude: 0.00
      },
      northWest: {
        latitude: 0.00,
        longitude: 0.00
      },
      southEast: {
        latitude: 0.00,
        longitude: 0.00
      },
      southWest: {
        latitude: 0.00,
        longitude: 0.00
      }
    },
    boundsRotated: {
      northEast: {
        latitude: 0.00,
        longitude: 0.00
      },
      northWest: {
        latitude: 0.00,
        longitude: 0.00
      },
      southEast: {
        latitude: 0.00,
        longitude: 0.00
      },
      southWest: {
        latitude: 0.00,
        longitude: 0.00
      }
    },
    center: {
      latitude: 0.00,
      longitude: 0.00
    },
    dimensions: {
      width: 0.00,
      height: 0.00
    },
    infoHtml: "infoHtml",
    pictureThumbUrl: "pictureThumbUrl",
    pictureUrl: "pictureUrl",
    rotation: 0.00,
    userIdentifier: "userIdentifier",
    customFields: {
      name: "value",
      name: "value",
      ...
    }
  }

  fetchOutdoorPOIsFromBuilding(building, (pois) => {
    // Array of pois
    pois = [{
      identifier: "identifier",
      buildingIdentifier: "buildingIdentifier",
      cartesianCoordinate: {
        x: 0.00,
        y: 0.00 
      },
      coordinate: {
        latitude: 0.00,
        longitude: 0.00
      },
      floorIdentifier: "floorIdentifier",
      poiName: "poiName",
      position: {
        buildingIdentifier: "buildingIdentifier",
        cartesianCoordinate: {
          x: 0.00,
          y: 0.00 
        },
        coordinate: {
          latitude: 0.00,
          longitude: 0.00
        }
        floorIdentifier: "floorIdentifier"
        isIndoor: false
        isOutdoor: true
      },
      isIndoor: false,
      isOutdoor: true,
      category: {
        poiCategoryCode: "poiCategoryCode",
        poiCategoryName: "poiCateogryName",
        icon_selected: "iconSelectedUrl",
        icon_unselected: "iconUnselectedUrl",
        public: true
      },
      infoHtml: "infoHtml",
      customFields: {
        name: "value",
        name: "value",
        ...
      }
    }, {
      ...
    }]
  }, (error) => {
    // If errors will come here
    error = {
      status: 1,
      message: "message"
    }
  });
```

##### - fetchEventsFromBuilding

###### Download the events of a [building](http://htmlpreview.github.io/?https://github.com/cocodinTech/situm-cordova-plugin/blob/master/www/android/docs/symbols/Building.html).

```javascript
  var building = {
    buildingIdentifier: "buildingIdentifier",
    name: "buildingName",
    address: "address",
    bounds: {
      northEast: {
        latitude: 0.00,
        longitude: 0.00
      },
      northWest: {
        latitude: 0.00,
        longitude: 0.00
      },
      southEast: {
        latitude: 0.00,
        longitude: 0.00
      },
      southWest: {
        latitude: 0.00,
        longitude: 0.00
      }
    },
    boundsRotated: {
      northEast: {
        latitude: 0.00,
        longitude: 0.00
      },
      northWest: {
        latitude: 0.00,
        longitude: 0.00
      },
      southEast: {
        latitude: 0.00,
        longitude: 0.00
      },
      southWest: {
        latitude: 0.00,
        longitude: 0.00
      }
    },
    center: {
      latitude: 0.00,
      longitude: 0.00
    },
    dimensions: {
      width: 0.00,
      height: 0.00
    },
    infoHtml: "infoHtml",
    pictureThumbUrl: "pictureThumbUrl",
    pictureUrl: "pictureUrl",
    rotation: 0.00,
    userIdentifier: "userIdentifier",
    customFields: {
      name: "value",
      name: "value",
      ...
    }
  }

  fetchEventsFromBuilding(building, (events) => {
    // Array of Situm events
    events = [{
      buildingIdentifier: 1
      identifier: 1
      floorIdentifier: 1,
      infoHtml: "infoHtml",
      conversionArea: {
        floorIdentifier: 1,
        topLeft: {
          x: 0.00,
          y: 0.00
        },
        topRight: {
          x: 0.00,
          y: 0.00
        },
        bottomLeft: {
          x: 0.00,
          y: 0.00
        },
        bottomRight: {
          x: 0.00,
          y: 0.00
        }
      },
      customFields: {
        name: "value",
        name: "value",
        ...
      },
      radius: 0.00
    }, {
      ...
    }];
  }, (error) => {
    // If errors will come here
    error = {
      status: 1,
      message: "message"
    }
  });
```

##### - fetchPoiCategories

###### Get all [POI categories](http://htmlpreview.github.io/?https://github.com/cocodinTech/situm-cordova-plugin/blob/master/www/android/docs/symbols/PoiCategory.html), download and cache their icons asynchronously.

```javascript
  fetchPoiCategories((poiCategories) => {
    // Array of POI cateogires
    poiCategories = [{
      poiCategoryCode: "poiCategoryCode",
      poiCategoryName: "poiCategoryName",
      icon_selected: "iconSelectedUrl",
      icon_unselected: "iconUnselectedUrl",
      public: true
    }, {
      ...
    }];
  }, (error) => {
    // If errors will come here
    error = {
      status: 1,
      message: "message"
    }
  });
```

##### - fetchMapFromFloor

###### Download the map image of a [floor](http://htmlpreview.github.io/?https://github.com/cocodinTech/situm-cordova-plugin/blob/master/www/android/docs/symbols/Floor.html).

```javascript
  var floors = {
    altitude: 0.00,
    buildingIdentifier: "buildingIdentifier",
    level: 1,
    mapUrl: "mapUrl",
    scale: 0.00,
    floorIdentifier: "floorIdentifier"
  }

  fetchMapFromFloor (floor, (mapImage) => {
    // Map image as a bitmap
  }, (error) => {
    // If errors will come here
    error = {
      status: 1,
      message: "message"
    }
  });
```

##### - fetchPoiCategoryIconNormal

###### Get the normal category icon for a [category](http://htmlpreview.github.io/?https://github.com/cocodinTech/situm-cordova-plugin/blob/master/www/android/docs/symbols/PoiCategory.html).

```javascript
  var poiCategory = {
    poiCategoryCode: "poiCategoryCode",
    poiCategoryName: "poiCategoryName",
    icon_selected: "iconSelectedUrl",
    icon_unselected: "iconUnselectedUrl",
    public: true
  }

  fetchPoiCategoryIconNormal (poiCategory, (iconNormal) => {
    // Icon as a bitmap
  }, (error) => {
    // If errors will come here
    error = {
      status: 1,
      message: "message"
    }
  });
```

##### - fetchPoiCategoryIconSelected

###### Get the selected category icon for a [category](http://htmlpreview.github.io/?https://github.com/cocodinTech/situm-cordova-plugin/blob/master/www/android/docs/symbols/PoiCategory.html).

```javascript
  var poiCategory = {
    poiCategoryCode: "poiCategoryCode",
    poiCategoryName: "poiCategoryName",
    icon_selected: "iconSelectedUrl",
    icon_unselected: "iconUnselectedUrl",
    public: true
  }

  fetchPoiCategoryIconSelected (poiCategory, (iconSelected) => {
    // Icon as a bitmap
  }, (error) => {
    // If errors will come here
    error = {
      status: 1,
      message: "message"
    }
  });
```

##### - invalidateCache

###### Invalidate all the resources in the cache.

```javascript
  invalidateCache((response) => {
    response = {
      status: 0,
      message: "message"
    }
  });
```
##### - requestDirections

###### Calculates a route between two [points](http://htmlpreview.github.io/?https://github.com/cocodinTech/situm-cordova-plugin/blob/master/www/android/docs/symbols/Point.html).

```javascript
  var building = {
    buildingIdentifier: "buildingIdentifier",
    name: "buildingName",
    address: "address",
    bounds: {
      northEast: {
        latitude: 0.00,
        longitude: 0.00
      },
      northWest: {
        latitude: 0.00,
        longitude: 0.00
      },
      southEast: {
        latitude: 0.00,
        longitude: 0.00
      },
      southWest: {
        latitude: 0.00,
        longitude: 0.00
      }
    },
    boundsRotated: {
      northEast: {
        latitude: 0.00,
        longitude: 0.00
      },
      northWest: {
        latitude: 0.00,
        longitude: 0.00
      },
      southEast: {
        latitude: 0.00,
        longitude: 0.00
      },
      southWest: {
        latitude: 0.00,
        longitude: 0.00
      }
    },
    center: {
      latitude: 0.00,
      longitude: 0.00
    },
    dimensions: {
      width: 0.00,
      height: 0.00
    },
    infoHtml: "infoHtml",
    pictureThumbUrl: "pictureThumbUrl",
    pictureUrl: "pictureUrl",
    rotation: 0.00,
    userIdentifier: "userIdentifier",
    customFields: {
      name: "value",
      name: "value",
      ...
    }
  }

  var from = {
    buildingIdentifier: "buildingIdentifier",
    cartesianCoordinate: {
      x: 0.00,
      y: 0.00 
    },
    coordinate: {
      latitude: 0.00,
      longitude: 0.00
    }
    floorIdentifier: "floorIdentifier"
    isIndoor: true
    isOutdoor: false
  }

  var to = {
    buildingIdentifier: "buildingIdentifier",
    cartesianCoordinate: {
      x: 0.00,
      y: 0.00 
    },
    coordinate: {
      latitude: 0.00,
      longitude: 0.00
    }
    floorIdentifier: "floorIdentifier"
    isIndoor: true
    isOutdoor: false
  }

  requestDirections([building, from, to], (route) => {
    // Route Situm object
    route = {
      edges: [{
        distance: 0.00
        distanceToGoal: 0.00,
        from: {
          buildingIdentifier: "buildingIdentifier",
          cartesianCoordinate: {
            x: 0.00,
            y: 0.00 
          },
          coordinate: {
            latitude: 0.00,
            longitude: 0.00
          }
          floorIdentifier: "floorIdentifier"
          isIndoor: false
          isOutdoor: true
        },
        id: 1,
        to: {
          buildingIdentifier: "buildingIdentifier",
          cartesianCoordinate: {
            x: 0.00,
            y: 0.00 
          },
          coordinate: {
            latitude: 0.00,
            longitude: 0.00
          }
          floorIdentifier: "floorIdentifier"
          isIndoor: false
          isOutdoor: true
        },
        isFirst: true
        isLast: false
      }, {
        ...
      }],
      firstStep: {
        distance: 0.00
        distanceToGoal: 0.00,
        from: {
          buildingIdentifier: "buildingIdentifier",
          cartesianCoordinate: {
            x: 0.00,
            y: 0.00 
          },
          coordinate: {
            latitude: 0.00,
            longitude: 0.00
          }
          floorIdentifier: "floorIdentifier"
          isIndoor: false
          isOutdoor: true
        },
        id: 1,
        to: {
          buildingIdentifier: "buildingIdentifier",
          cartesianCoordinate: {
            x: 0.00,
            y: 0.00 
          },
          coordinate: {
            latitude: 0.00,
            longitude: 0.00
          }
          floorIdentifier: "floorIdentifier"
          isIndoor: false
          isOutdoor: true
        },
        isFirst: true
        isLast: false
      },
      from: {
        buildingIdentifier: "buildingIdentifier",
        cartesianCoordinate: {
          x: 0.00,
          y: 0.00 
        },
        coordinate: {
          latitude: 0.00,
          longitude: 0.00
        }
        floorIdentifier: "floorIdentifier"
        isIndoor: false
        isOutdoor: true
      },
      indications: [{
        distance: 0.00,
        distanceToNextLevel: 2
        indicationType: "indicationType",
        orientation: 0.00,
        orientationType: "orientationType",
        stepIdxDestination: 1,
        stepIdxOrigin: 1,
        neededLevelChange: true
      }, {
        ...
      }],
      lastStep: {
        distance: 0.00
        distanceToGoal: 0.00,
        from: {
          buildingIdentifier: "buildingIdentifier",
          cartesianCoordinate: {
            x: 0.00,
            y: 0.00 
          },
          coordinate: {
            latitude: 0.00,
            longitude: 0.00
          }
          floorIdentifier: "floorIdentifier"
          isIndoor: false
          isOutdoor: true
        },
        id: 1,
        to: {
          buildingIdentifier: "buildingIdentifier",
          cartesianCoordinate: {
            x: 0.00,
            y: 0.00 
          },
          coordinate: {
            latitude: 0.00,
            longitude: 0.00
          }
          floorIdentifier: "floorIdentifier"
          isIndoor: false
          isOutdoor: true
        },
        isFirst: false
        isLast: true
      },
      nodes: [{
        buildingIdentifier: "buildingIdentifier",
        cartesianCoordinate: {
          x: 0.00,
          y: 0.00 
        },
        coordinate: {
          latitude: 0.00,
          longitude: 0.00
        }
        floorIdentifier: "floorIdentifier"
        isIndoor: false
        isOutdoor: true
      }, {
        ...
      }],
      points: [{
        buildingIdentifier: "buildingIdentifier",
        cartesianCoordinate: {
          x: 0.00,
          y: 0.00 
        },
        coordinate: {
          latitude: 0.00,
          longitude: 0.00
        }
        floorIdentifier: "floorIdentifier"
        isIndoor: false
        isOutdoor: true
      }, {
        ...
      }],
      to: {
        buildingIdentifier: "buildingIdentifier",
        cartesianCoordinate: {
          x: 0.00,
          y: 0.00 
        },
        coordinate: {
          latitude: 0.00,
          longitude: 0.00
        }
        floorIdentifier: "floorIdentifier"
        isIndoor: false
        isOutdoor: true
      },
      steps: [{
        distance: 0.00
        distanceToGoal: 0.00,
        from: {
          buildingIdentifier: "buildingIdentifier",
          cartesianCoordinate: {
            x: 0.00,
            y: 0.00 
          },
          coordinate: {
            latitude: 0.00,
            longitude: 0.00
          }
          floorIdentifier: "floorIdentifier"
          isIndoor: false
          isOutdoor: true
        },
        id: 1,
        to: {
          buildingIdentifier: "buildingIdentifier",
          cartesianCoordinate: {
            x: 0.00,
            y: 0.00 
          },
          coordinate: {
            latitude: 0.00,
            longitude: 0.00
          }
          floorIdentifier: "floorIdentifier"
          isIndoor: false
          isOutdoor: true
        },
        isFirst: false
        isLast: false
      }, {
        ...
      }]
    }
  }, (error) => {
    // If errors will come here
    error = {
      status: 1,
      message: "message"
    }
  });
```

##### - requestNavigationUpdates

###### Necessary step to request progress. Alone this method does not provide progress object. You must feed navigation API with location, as indicated on updateNavigationWithLocation section.

```javascript
  var navigationOptions = new Object();
  navigationOptions["distanceToIgnoreFirstIndication"] = 0.3; // (Optional) meters;
  navigationOptions["outsideRouteThreshold"] = 10; // (Optional) meters;
  navigationOptions["distanceToGoalThreshold"] = 7; // (Optional) meters;

  requestNavigationUpdates([navigationOptions], (res: any) => {
    // Progress and other navigation status messages can be processed here  
    // Types of results in success cases:
    // 1 - User has arrived destination
    res = {  
      type: "destinationReached",
      message: "Destination reached"
    }
    // 2 - User is outside the route
    res = {  
      type: "userOutsideRoute",
      message: "User outside route"
    }
    // 3 - User is inside the route but has not arrived to destination.
    res = {  
      closestPointInRoute: {  
        isIndoor: true,
        buildingIdentifier: "3087",
        coordinate: {  
          latitude: 42.87227301416988,
          longitude: -8.563636606983739
        },
        floorIdentifier: "4961",
        cartesianCoordinate: {  
          x: 67.91600036621094,
          y: 24.05699920654297
        },
        isOutdoor: false
      },
      distanceToEndStep: 1.038121223449707, // meters
      distanceToGoal: 62.55179214477539, // meters
      currentStepIndex: 0,
      timeToEndStep: 1.038121223449707, // seconds
      currentIndication: {  
        distanceToNextLevel: 0, // if this value is greater than 0 it represent the number of floor to go up. if this value is less than 0 it represents the number of floors to go down. If equal to 0 it means there is no need to change floor.
        distance: 1.038121223449707, // meters
        stepIdxOrigin: 0, // index of the route step (on of the steps on Route object provided by requestDirections)
        stepIdxDestination: 0,
        orientationType: "Left",
        indicationType: "Turn",
        humanReadableMessage: "Turn left and go ahead for 2 meters", // Only iOS, Android version coming
        orientation: 1.8215326070785522, // radians
        neededLevelChange: false, // True if change floor is needed
        nextLevel: 3 // Floor number. The floor level to which the user need to go. Only Android for now. iOS version coming
      },
      type: "progress",
      timeToGoal: 62.55179214477539
    }
  }, (error: any) => {
    // If errors will come here
    error = {
      status: 1,
      message: "message"
    }  
  });
```

##### - updateNavigationWithLocation
    
###### Usually, position variable should be one of the locations provided by the system on the [startPositioning](#--startpositioning) function.

```javascript
  var position = {
    accuracy: 0.00,
    bearing: {
      degrees: 0.00,
      degreesClockwise: 0.00,
      radians: 0.00,
      radiansMinusPiPi: 0.00,
    },
    bearingQuality: "bearingQuality",
    buildingIdentifier: "buildingIdentifier",
    cartesianBearing: {
      degrees: 0.00,
      degreesClockwise: 0.00,
      radians: 0.00,
      radiansMinusPiPi: 0.00,
    },
    cartesianCoordinate: {
      x: 0.00,
      y: 0.00 
    },
    coordinate: {
      latitude: 0.00,
      longitude: 0.00
    },
    floorIdentifier: "floorIdentifier",
    position: {
      buildingIdentifier: "buildingIdentifier",
      cartesianCoordinate: {
        x: 0.00,
        y: 0.00 
      },
      coordinate: {
        latitude: 0.00,
        longitude: 0.00
      }
      floorIdentifier: "floorIdentifier"
      isIndoor: true
      isOutdoor: false
    },
    provider: "provider",
    quality: "quality",
    hasBearing: true,
    timestamp: 000000000,
    hasCartesianBearing: true,
    isIndoor: true,
    isOutdoor: false,
    deviceId: "deviceId"
  };

  updateNavigationWithLocation([position], (result) => {
    console.log(result);
  }, (error) => {
    // If errors will come here
    error = {
      status: 1,
      message: "message"
    }  
  });
```

##### - removeNavigationUpdates

###### When you are no longer interested on Navigation Updates you should call this method to remove internal allocated resources.

```javascript
  removeNavigationUpdates();
```

## :large_blue_diamond: Contributing

```
TODO CONTRIBUTORS ?
```

## :large_blue_diamond: License

```
TODO LICENSING (MIT) ?
```