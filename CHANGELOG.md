# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](http://keepachangelog.com/en/1.0.0/)
and this project adheres to [Semantic Versioning](http://semver.org/spec/v2.0.0.html).

All non released changes should be in CHANGELOG_UNRELEASED.md file

---------
## [1.18.2] - 2023-01-04

## Changed
* Updated iOS SDK to version [2.55.0](https://situm.com/docs/ios-sdk-changelog).
* Updated Android SDK to version [2.83.3](https://situm.com/docs/android-sdk-changelog).

## [1.18.1] - 2022-06-23

## Changed
* Updated Android SDK to version 2.76.1.
* Updated iOS SDK to version 2.52.3

## [1.18.0] - 2022-04-06

# Added
* New functionality: Remote configuration. Now you can load the configuration from the Dashboard and the SDK will automatically use it if you enable it. You can learn more about this and how to use it here.

# Update
- Android SDK to version 2.73.0 and iOS SDK version 2.52.1.

## [1.17.4] - 2022-03-03

### Changed
* Updated iOS SDK to 2.52.0.

## [1.17.3] - 2022-02-24

**Improved**

- We have changed the structure of the main README file so that it is easier to read and includes all the info needed to get this plugin working in a project.

## [1.17.2] - 2022-01-31

### Changed
* Updated Android SDK to 2.72.0.
* Updated min cordova-android platform version to 10.0.0.
* Situm SDK now compiles and targets sdkVersion 31 (Android 12). To work properly on Android 12 devices and above, the host app must:
    * Target android api 31 or above. In your project `config.xml` file, add `<preference name="android-targetSdkVersion" value="31" />` to the Android platform configuration.
    * Request the runtime permissions `BLUETOOTH_SCAN` and `BLUETOOTH_CONNECT`, in addition to the `ACCESS_COARSE_LOCATION` that was already necessary (and `ACCESS_FINE_LOCATION` if you are using [Global Mode](https://situm.com/docs/how-does-situm-work/#5-toc-title)).
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


## [1.17.1] - 2021-12-29

### Changed
* Updated iOS SDK to 2.51.5.

## [1.17.0] - 2021-12-15

### Changed
* Updated Android SDK to 2.70.4.
* Updated iOS SDK to 2.51.4.
* Migrated to AndroidX for the Android platform. Android Support Library is no longer supported and this means a compatibility break with respect to previous versions.


## [1.16.3] - 2021-08-06

### Fixed
* Error parsing locations in the navigation

## [1.16.2] - 2021-02-04
### Updated
* update Situm SDK Android to version 2.67.1
* update Situm SDK iOS to version 2.50.4

## [1.16.1] - 2019-12-27

### Fixed
* Fix a bug that caused a crash when the phone had selected certain timezones


## [1.16.0] - 2019-11-28

### Added
* floor property to Floor object (deprecate level)
* included functions to retrieve geofences from a building
* retrieve information of a building in a single function
* retrieve realtime locations

### Updated
* update Situm SDK Android to version 2.43.0

## [1.15.2] - 2019-07-29

### Fixed
* New release to remove .git directory from plugin (added due to a npm bug)


## [1.15.1] - 2019-07-22

#### Changed
* Update iOS SDK to 2.33.0
* Update Android SDK to 2.40.0


## [1.15.0] - 2019-07-08

#### Added
* Add field `name` to Floor class in both Android & iOS

#### Changed
* Update iOS SDK to 2.32.0
* Update Android SDK to 2.39.0


## [1.14.5] - 2019-06-17
## [1.14.4] - 2019-06-13
* Empty releases due to support branch



## [1.14.3] - 2019-05-31

### Changed
* Update iOS SDK to 2.31.1
* Update Android SDK to 2.37.6


## [1.14.2] - 2019-04-24

### Changed
* Updated iOS SDK to 2.30.1


## [1.14.1] - 2019-04-16

### Changed
* Updated iOS SDK to 2.29.1
* Updated Android SDK to 2.37.0


## [1.14.0] - 2019-04-10

### Added

* Added "useBarometer" to location request

### Changed

* Updated iOS sdk to 2.29.0
* Updated Android sdk to 2.36.0


## [1.13.0] - 2019-04-03

### Added
* Add support for `autoEnableBleDuringPositioning` in Android
* Add support for `smallestDisplacement` on iOS devices

### Changed
* Update iOS SDK to 2.28.0


## [1.12.2] - 2019-03-29

### Added

* iOS wrapper now correctly parses interval in locationRequest

### Changed

* Updated iOS SDK to 2.27.0


## [1.12.1] - 2019-03-28

### Changed
* Updated Android SDK 2.35.1


## [1.12.0] - 2019-03-26

### Added

* Added new param `segments` to object `Route`
* Added new params `points` and `segments` to object `NavigationProgress`

### Changed

* Updated iOS SDK to 2.26.1
* Updated Android SDK to 2.35.0
* The param `points` from `Route` (in iOS) is now obtained from the new homonym method from iOS SDK


## [1.11.8] - 2019-03-22

### Changed
* Update iOS SDK to version 2.25.1


## [1.11.7] - 2019-03-20

### Changed

* Updated iOS SDK version to 2.25.0


## [1.11.5] - 2019-03-18

### Changed

* Updated iOS SDK to 2.24.1


## [1.11.3] - 2019-03-14

### Changed
* Updated Android SDK to 2.34.1


## [1.11.2] - 2019-03-14

### Changed

* Updated iOS SDK to 2.24.0


## [1.11.1] - 2019-03-13

### Changed

* Updated iOS sdk to 2.23.0


## [1.10.8] - 2019-03-04

### Changed

* Updated iOS sdk to 2.21.11


## [1.10.7] - 2019-03-04

### Changed

* Updated iOS SDK to 2.21.10


## [1.10.6] - 2019-02-27

### Changed

* Updated iOS sdk to 2.21.8
* Updated Android sdk to 2.32.1

### Fixed

* Fix crash when calling requestDirections() in Android with 3 parameters


## [1.10.5] - 2019-02-20

### Changed

* Updated iOS SDK to 2.21.6
* Updated Android SDK to 2.31.4


## [1.10.4] - 2019-02-13

### Changed

* Updated iOS SDK to 2.21.5
* Updated Android SDK to 2.31.3


## [1.10.3] - 2019-02-11

### Changed

* Update iOS SDK to 2.21.2


## [1.10.2] - 2019-01-31

### Changed

* Update iOS SDK to 2.21.1


## [1.10.1] - 2019-01-30

### Changed

* Update iOS SDK to 2.21.0


## [1.10.0] - 2019-01-23

### Changed

* Update iOS SDK to 2.20.0
* Fix not starting the positioning after an error when calling startPositioning() again without calling stopPositioning()


## [1.9.6] - 2019-01-17

### Changed

* Updated Android SDK to 2.31.1
* Updated iOS SDK to 2.19.0


## [1.9.5] - 2019-01-14

### Changed

* Update Android SDK to version 2.31.0


## [1.9.4] - 2019-01-14

### Changed

* Update iOS SDK to version 2.18.5


## [1.9.3] - 2019-01-08

### Changed

* Update iOS SDK to version 2.18.4

## [1.9.2] - 2018-12-21

### Changed

* Update iOS SDK to version 2.18.3
* Update Android SDK to version 2.30.4


## [1.9.1] - 2018-12-18

### Changed
* Update iOS SDK to version 2.18.0
* Fix `computedRoute` not being stored


## [1.9.0] - 2018-12-04

### Changed
* Integrate SitumSDK for iOS through Cocoapods
* Update iOS SDK version to 2.17.2
* Update Android SDK version to 2.30.1
* Parse `realtimeUpdateInterval` as a String
* Parse accessibilityMode


## [1.8.9] - 2018-11-12

### Fixed
* Fixed error when starting positioning using LocationRequest

### Changed
* Use legacy build system

## [1.8.8] - 2018-11-06

### Changed
* Update iOS SDK version to 2.16.3


## [1.8.7] - 2018-11-02

### Changed
* Update iOS SDK version to 2.16.2
* Update Android SDK version to 2.27.0


## [1.8.6] - 2018-10-23

### Added
* Add GPS option in order to use GPS in iOS

## [1.8.5] - 2018-10-16

### Changed
* Update iOS SDK version to 2.15.0

## [1.8.4] - 2018-10-02

### Changed
* Update Android SDK version to 2.25.5

## [1.8.0] - 2018-08-29

### Added
* Add `trigger` and `conversion` to `SitumEvent`.

### Changed
* Update iOS SDK version to 2.12.0
* Update Android SDK version to 2.25.0

## [1.7.6] - 2018-08-27

### Added
* Add `x` and `y` to `SitumEvent`.

### Changed
* Update iOS SDK version to 2.11.0
