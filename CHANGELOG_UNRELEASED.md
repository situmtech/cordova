## Unreleased

### Changed
* Updated Android SDK to 2.72.0.
* Updated min cordova-android platform version to 10.0.0.
* Situm SDK now compiles and targets sdkVersion 31 (Android 12). To work properly on Android 12 devices and above, the host app must:
    * Target android api 31 or above. In your project `config.xml` file, add `<preference name="android-targetSdkVersion" value="31" />` to the Android platform configuration.
    * Request the runtime permissions `BLUETOOTH_SCAN` and `BLUETOOTH_CONNECT`, in addition to the `ACCESS_FINE_LOCATION` that was already necessary.
    * Add `android:exported="true"` to all the intent-filtered components of your `AndroidManifest.xml` file. You can add the following configuration to your `config.xml` to automate this process:
    ```xml
      <edit-config
          file="app/src/main/AndroidManifest.xml"
          target="/manifest/application/activity[@android:name='MainActivity']"
          mode="merge">
        <activity android:exported="true"/>
      </edit-config>
    ```
