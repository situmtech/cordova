Situm Cordova Plugin
======

> Current Status:

Android: In development (some calls already implemented)
IOS: Not implemented yet


> Android

setApiKey:

```javascript
      cordova.plugins.Situm.setApiKey("email", "api_key");
```

setUserPass:

```javascript
       cordova.plugins.Situm.setUserPass("email", "password");
```

fetchBuildings:

```javascript
       cordova.plugins.Situm.fetchBuildings(callback);
```

fetchFloorsFromBuilding:

```javascript
      building: A building object, must be the same structure that original building java object.
      See: <a href="http://developers.situm.es/sdk_documentation/android/javadoc/2.7.0/">http://developers.situm.es/sdk_documentation/android/javadoc/2.7.0/</a>
      cordova.plugins.Situm.fetchFloorsFromBuilding(building, callback);
```

fetchMapFromFloor:

```javascript
      cordova.plugins.Situm.fetchMapFromFloor(floor, callback);
```

fetchPoiCategories:

```javascript
       cordova.plugins.Situm.fetchPoiCategories(callback);
```

startPositioning:

```javascript
    building: A building object, must be the same structure that original building java object.
    See: <a href="http://developers.situm.es/sdk_documentation/android/javadoc/2.7.0/">http://developers.situm.es/sdk_documentation/android/javadoc/2.7.0/</a>
    cordova.plugins.Situm.startPositioning(buildings, callback)
```
stopPositioning:

```javascript
    cordova.plugins.Situm.stopPositioning(callback)
```
