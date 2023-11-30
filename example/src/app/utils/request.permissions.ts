declare let cordova: any;

export function requestPermissions(successCb: Function, errorCb: Function) {
  var isAndroid =
    navigator.userAgent.match(/Android/i) &&
    navigator.userAgent.match(/Android/i)!.length > 0;
  var isIOS = /iPhone|iPad|iPod/i.test(navigator.userAgent);

  if (isAndroid) {
    cordova.plugins.diagnostic.requestRuntimePermissions(
      function (permissions: Map<string, string>) {
        console.log('EXAMPLE> permissions statuses: ', permissions);
        successCb();
      },
      function (error: any) {
        errorCb(JSON.stringify(error));
      },
      [
        cordova.plugins.diagnostic.permission.ACCESS_FINE_LOCATION,
        cordova.plugins.diagnostic.permission.BLUETOOTH_CONNECT,
        cordova.plugins.diagnostic.permission.BLUETOOTH_SCAN,
      ]
    );
  } else if (isIOS) {
    cordova.plugins.diagnostic.getLocationAuthorizationStatus(
      (status: string) => {
        if (status == 'authorized') {
          successCb();
        }
      },
      () => {
        // Do nothing
      }
    );

    cordova.plugins.diagnostic.requestLocationAuthorization(
      function (status: string) {
        switch (status) {
          case cordova.plugins.diagnostic.permissionStatus.NOT_REQUESTED:
            errorCb('Permission not requested');
            break;
          case cordova.plugins.diagnostic.permissionStatus.DENIED_ALWAYS:
            errorCb('Permission denied');
            break;
          case cordova.plugins.diagnostic.permissionStatus.GRANTED:
            console.log('Permission granted always');
            successCb();
            break;
          case cordova.plugins.diagnostic.permissionStatus.GRANTED_WHEN_IN_USE:
            console.log('Permission granted only when in use');
            successCb();
            break;
        }
      },
      function (error: any) {
        errorCb(JSON.stringify(error));
      },
      cordova.plugins.diagnostic.locationAuthorizationMode.ALWAYS
    );
  }
}
