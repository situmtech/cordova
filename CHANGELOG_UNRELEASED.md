# Unreleased

## Added

- Introduced new methods: `requestLocationUpdates(LocationRequest)`, `onLocationUpdate(function)`, `onLocationStatus(function)`, `onLocationError(function)`, and `removeUpdates()`. This addition aims to standardize the API across native SDKs and other hybrid platforms (React Native, Flutter).
  The new methods serve as replacements for `startPositioning` (with parameterized callbacks) and `stopPositioning`.
  Note that `removeUpdates()` now returns a `Promise` so it can be called with `async/await` syntax.

## Deprecated

- Deprecated the methods startPositioning and `stopPositioning`.

## Changed

- Although the `startPositioning` method has been deprecated, it has been modified to accept a `LocationRequest` object as the standard input. It remains compatible with the previous input format based on arrays (including building and location request).

## Removed

- Eliminated unnecessary dependency on play-services-maps in Android.
