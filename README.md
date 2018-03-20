
---
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
      var directionsOptionsMap = new Object();
      directionsOptionsMap["accessible"] = true/false
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

## :large_blue_diamond: Contributing

```
TODO CONTRIBUTORS ?
```

## :large_blue_diamond: License

```

TODO LICENSING (MIT) ?
```