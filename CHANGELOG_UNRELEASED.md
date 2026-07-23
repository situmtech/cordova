## Unreleased

### Updated
- Updated Android example app targetSdkVersion and compileSdkVersion from 33 to 37.
- Updated gradle wrapper version from 8.4 to 8.13.
- Updated AGP (com.android.application) from 8.0.0 to 8.13.2.

### Removed
- Removed the legacy routing and navigation bridge between the MapView and the native SDK, as the MapView now always uses its own routing and navigation library.

### Changed
- Deprecated the `use-viewer-navigation` MapView attribute. It is now ignored and kept only for backward compatibility.
