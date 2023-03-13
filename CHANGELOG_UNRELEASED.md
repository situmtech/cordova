## [Unreleased]

### Changed

- Upgrade targetSdkVersion and compileSdkVersion to api 33. Make sure to have the xml attribute [`android:exported`](https://developer.android.com/guide/topics/manifest/activity-element?hl=es#exported) specified inside your `<activity>` section in your AndroidManifest.xml file.
- Update Android SDK version to [2.85.0](https://situm.com/docs/android-sdk-changelog/#version-2850--march-2-2023)
- Update iOS SDK version to [2.58.0](https://situm.com/docs/ios-sdk-changelog/#version-2580--march-07-2023)
- Fix useRemoteConfig always setting up to true (iOS).
