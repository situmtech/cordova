<div style="text-align:center">

# Situm Cordova Plugin
[![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)](https://opensource.org/licenses/MIT)
[![npm](https://img.shields.io/npm/v/situm-cordova-plugin-official.svg)](https://www.npmjs.com/package/situm-cordova-plugin-official)
[![npm](https://img.shields.io/npm/dm/situm-cordova-plugin-official.svg)](https://www.npmjs.com/package/situm-cordova-plugin-official)
</div>
<div style="float:right; margin-left: 1rem;">

[![](https://situm.com/wp-content/themes/situm/img/logo-situm.svg)](https://www.situm.es)
</div>

Situm Cordova Plugin is a set of utilities that allow any developer to build Cordova location based apps using Situm's indoor positioning system. Among many other capabilities, apps developed with Situm Cordova Plugin will be able to:
* Obtain information related to buildings where Situm's positioning system is already configured: floorplans, points of interest, geotriggered events, etc.
* Retrieve the location of the smartphone inside these buildings (position, orientation, and floor where the smartphone is).
* Compute a route from a point A (e.g. where the smartphone is) to a point B (e.g. any point of interest within the building).
* Trigger notifications when the user enters a certain area.


## Table of contents

  * [Getting started](#getting-started)
  * [Versioning](#versioning)
  * [Submitting contributions](#submitting-contributions)
  * [License](#license)
  * [Documentation](#documentation)
  * [Development](#development)
  * [More information](#more-information)
  * [Support information](#support-information)

---

## Getting started

- Set up your Situm account following [these steps](https://situm.com/docs/01-introduction/#3-toc-title).
- [Configure](https://situm.com/docs/04-a-basic-cordova-app/) this plugin in your project.
- [API Reference](https://developers.situm.com/sdk_documentation/cordova/jsdoc/latest/situm) will help you use a particular class or method.

---

## Versioning

Please refer to [CHANGELOG.md](https://github.com/situmtech/situm-cordova-plugin/blob/master/CHANGELOG.md) for a list of notables changes for each version of the plugin.

You can also see the [tags on this repository](https://github.com/situmtech/situm-cordova-plugin/tags).

---

## Submitting contributions

You will need to sign a Contributor License Agreement (CLA) before making a submission. [Learn more here](https://situm.com/contributions/). 

---

## License

Situm-Cordova-Plugin is licensed under [MIT License](https://opensource.org/licenses/MIT). See [LICENSE.txt](https://github.com/situmtech/situm-cordova-plugin/blob/master/LICENSE) file for further details.

---

## Documentation

- **General documentation**. You can find in our [documentation site](https://situm.com/docs/04-a-basic-cordova-app/) all the information you need to configure this plugin in your project and get it up and running in no time.
- **API reference**. Check out the [plugin reference](https://developers.situm.com/sdk_documentation/cordova/jsdoc/latest/situm) and learn how to use a particular class or method.
- **Examples**. In [this repo](https://github.com/situmtech/situm-cordova-getting-started) you can find a sample app implementing this plugin. Take a look at how the examples are implemented, so you can figure out how to adapt it to your project.
- **Cordova Wayfinding plugin**. If you are looking for a wayfinding solution using Cordova, check out [this repo](https://github.com/situmtech/situm-cordova-plugin-wayfinding).

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
  * Target android api 31 or above. In your project `config.xml` file, add `<preference name="android-targetSdkVersion" value="31" />` to the Android platform configuration.
  * Request the runtime permissions `ACCESS_COARSE_LOCATION`, `BLUETOOTH_SCAN` and `BLUETOOTH_CONNECT` (plus `ACCESS_FINE_LOCATION` if you are using [Global Mode](https://situm.com/docs/how-does-situm-work/#5-toc-title)). Remember to also add them to the Android platform section of your `config.xml` file:
  ```xml
    <config-file parent="/manifest" target="AndroidManifest.xml">
      <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
      <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" /> <!-- In Global mode -->
      <uses-permission android:name="android.permission.BLUETOOTH_SCAN" />
      <uses-permission android:name="android.permission.BLUETOOTH_CONNECT" />
    </config-file>
  ```
  * Add `android:exported="true"` to all the intent-filtered components of your `AndroidManifest.xml` file. You can add the following configuration to your `config.xml` to automate this process:
  ```xml
    <edit-config
        file="app/src/main/AndroidManifest.xml"
        target="/manifest/application/activity[@android:name='MainActivity']"
        mode="merge">
      <activity android:exported="true"/>
    </edit-config>
  ```
  * Make sure the `widget` root element of your `config.xml` file declares the Android namespace:
  ```xml
    <widget id="..." version="..."
      ...
      xmlns:android="http://schemas.android.com/apk/res/android">
  ```
  * If you find problems, also make sure the Gradle JDK points to version 11 in your project configuration (recommended Android Studio embedded JDK).



### Capacitor compatibility

This plugin is compatible with Capacitor 3.0.

**Issue**: In iOS, there is a known issue with capacitor-cli 3.2.5 and static cordova plugins https://github.com/ionic-team/capacitor/issues/5142. To solve it use a different version of capacitor cli. 




## More information

More info is available at our [Developers Page](https://situm.com/docs/01-introduction/).

## Support information
For any question or bug report, please send an email to [support@situm.es](mailto:support@situm.es)
