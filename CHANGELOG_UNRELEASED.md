## Unreleased

### Added
- Added iOS support for Swift Package Manager (SPM).

### Updated

- Increased the minimum supported iOS version to 16.
- SPM is the recommended dependency manager for new iOS integrations.
- With Cordova iOS 8 or newer, this plugin resolves SitumSDK through SPM. Other plugins may continue using their own native dependency manager. Existing Cordova projects using earlier versions, including Cordova iOS 7, continue to use CocoaPods without changes.
- Capacitor projects configured with the SPM iOS template resolve SitumSDK through SPM. Existing Capacitor projects retain their configured dependency manager, including CocoaPods, without changes.
- Updating `@situm/cordova` does not require updating Cordova or Capacitor. Projects that choose to update to Capacitor 8 can use `npx cap migrate` and follow the [Capacitor 8 upgrade guide](https://capacitorjs.com/docs/updating/8-0).
- Projects that choose to change an existing iOS project from CocoaPods to SPM can use `npx cap spm-migration-assistant` and follow the [SPM guide](https://capacitorjs.com/docs/ios/spm), including its required manual Xcode steps.
