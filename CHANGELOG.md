# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](http://keepachangelog.com/en/1.0.0/)
and this project adheres to [Semantic Versioning](http://semver.org/spec/v2.0.0.html).

All non released changes should be in CHANGELOG_UNRELEASED.md file

---------
## [1.17.0] - 2021-12-15

## Unreleased

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
