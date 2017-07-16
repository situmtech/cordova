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

startPositioning:

```javascript
      let buildings = [{
        'building_id' : this.buildingId,
        'building_name' : this.buildingName
      }];
    cordova.plugins.Situm.startPositioning(buildings, callback)
```
stopPositioning:

```javascript
    cordova.plugins.Situm.stopPositioning(callback)
```