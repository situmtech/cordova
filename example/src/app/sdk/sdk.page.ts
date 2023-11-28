import { CUSTOM_ELEMENTS_SCHEMA, Component, NgZone } from '@angular/core';
import {
  IonHeader,
  IonToolbar,
  IonTitle,
  IonContent,
  IonCard,
  IonButton,
  IonCardHeader,
  IonCardContent,
  IonCardTitle,
  IonItem,
  IonLabel,
  IonList,
  LoadingController,
  IonIcon,
} from '@ionic/angular/standalone';
import * as Constants from '../../constants';
import { NgFor, NgIf } from '@angular/common';
import { locate, pin, stop, navigate } from 'ionicons/icons';
import { addIcons } from 'ionicons';

declare let cordova: any;

@Component({
  selector: 'app-sdk',
  templateUrl: 'sdk.page.html',
  styleUrls: ['sdk.page.scss'],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  standalone: true,
  imports: [
    IonHeader,
    IonToolbar,
    IonTitle,
    IonContent,
    IonCard,
    IonCardHeader,
    IonCardTitle,
    IonCardContent,
    IonButton,
    IonLabel,
    IonItem,
    IonList,
    IonIcon,
    NgFor,
    NgIf
  ],
})
export class SDKPage {
  currentStatus: string = 'NOT STARTED';
  currentPositioningInfo: string = 'NOT STARTED';
  buildings: Array<any> | undefined;
  positioning: boolean = false;
  loading: HTMLIonLoadingElement | undefined;
  floors: any[] | undefined;
  location: any;
  navigation: any;
  indications: any[] | undefined;
  navBuilding: any;

  constructor(
    private ngZone: NgZone,
    public loadingController: LoadingController,
  ) {
    addIcons({ locate, pin, stop, navigate });
  }

  ionViewDidEnter() {
    cordova.plugins.Situm.setApiKey(Constants.API_USER, Constants.API_KEY);
  }

  locationRequest = {
    buildingIdentifier: '',
    useDeadReckoning: false,
    interval: 1000,
    indoorProvider: 'INPHONE',
    useBle: true,
    useWifi: true,
    motionMode: 'BY_FOOT',
    useForegroundService: true,
    outdoorLocationOptions: {
      userDefinedThreshold: false,
      computeInterval: 1,
      averageSnrThreshold: 25.0,
    },
    beaconFilters: [],
    smallestDisplacement: 0.0,
    realtimeUpdateInterval: 1000,
  };

  async loadBuildings() {
    // Fetch buildings:
    this.setLoading(true);
    cordova.plugins.Situm.fetchBuildings(
      (res: any) => {
        this.buildings = res;
        this.setStatus('BUILDINGS LOADED');
        this.setInfo(res);
        this.setLoading(false);
      },
      (err: any) => {
        this.setStatus('ERROR LOADING BUILDINGS');
        this.setInfo(err);
        this.setLoading(false);
      }
    );
    console.log('Call to loadBuildings() performed.');
  }

  async startPositioning(building: any) {
    if (this.positioning) {
      return;
    }

    this._requestPermissions(() => {
      this.doStartPositioning(building);
      console.log('Call to startPositioning() performed.');
    }, (errorMessage: any) => {
      console.error("Something did happen while asking for permission: ", errorMessage);
    });
  }

  private async doStartPositioning(building: any) {
    if (!building) {
      building = {
        buildingIdentifier: '',
        name: 'Global Mode',
      };
    }
    // Start positioning:
    this.setPositioning(true);
    this.setStatus('CALLED');
    this.setInfo('');
    this.locationRequest.buildingIdentifier = building.buildingIdentifier;
    cordova.plugins.Situm.startPositioning(
      [building, this.locationRequest],
      (res: any) => {
        if (res && res.statusName) {
          this.setStatus(res.statusName);
        }
        if (res && res.position) {
          this.setStatus('POSITIONING');
          this.setInfo(res);
          this.location = res;
          this.navBuilding = building;
        }
      },
      (err: any) => {
        this.setStatus('ERROR');
        this.setInfo(err);
        this.setPositioning(false);
      }
    );
  }

  private _requestPermissions(successCb: Function, errorCb: Function) {
    var isAndroid =
      navigator.userAgent.match(/Android/i) && navigator.userAgent.match(/Android/i)!.length > 0;
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

  async stopPositioning() {
    cordova.plugins.Situm.stopPositioning(
      () => {
        this.setPositioning(false);
        this.setStatus('STOPPED');
        this.setInfo('');
      },
      (err: any) => {
        this.setStatus('ERROR');
        this.setInfo(err);
      }
    );
  }

  private async setStatus(status: string) {
    this.ngZone.run(() => {
      this.currentStatus = status;
    });
  }

  private async setInfo(jsonRes: string) {
    this.ngZone.run(() => {
      this.currentPositioningInfo =
        jsonRes == '' || jsonRes == undefined
          ? ''
          : JSON.stringify(jsonRes, null, 2);
    });
  }

  private async setPositioning(positioning: boolean) {
    this.ngZone.run(() => {
      this.positioning = positioning;
      this.indications = [];
      this.navigation = null;
    });
  }

  private async setLoading(showLoading: boolean) {
    if (showLoading) {
      this.loading = await this.loadingController.create({
        cssClass: 'my-custom-class',
        message: 'Loading...',
      });
      await this.loading.present();
    } else {
      setTimeout(() => {
        this.loading && this.loading.dismiss();
      }, 1000);
    }
  }

  // ==============================================================================================

  public fetchBuildingInfo(b: any) {
    this.setLoading(true);
    cordova.plugins.Situm.fetchBuildingInfo(
      b,
      (res: any) => {
        this.setStatus('LOADED BUILDING INFO');
        this.setInfo(res);
        this.ngZone.run(() => {
          this.floors = res.floors;
        });
        this.setLoading(false);
      },
      (err: any) => {
        this.setStatus('ERROR');
        this.setInfo(err);
        this.setLoading(false);
      }
    );
  }

  public fetchPois(b: any) {
    this.setLoading(true);
    cordova.plugins.Situm.fetchIndoorPOIsFromBuilding(b, (res: any) => {
      this.setStatus('LOADED INDOOR POIS');
      this.setInfo(res);
      this.setLoading(false);
    });
  }

  public fetchGeofences(b: any) {
    this.setLoading(true);
    cordova.plugins.Situm.fetchGeofencesFromBuilding(
      b,
      (geofences: any) => {
        this.setStatus('LOADED GEOFENCES FROM BUILDING');
        this.setInfo(geofences);
        this.setLoading(false);
      },
      (err: any) => {
        this.setStatus('ERROR');
        this.setInfo(err);
        this.setLoading(false);
      }
    );
  }

  public requestNav() {
    this.setLoading(true);
    cordova.plugins.Situm.fetchIndoorPOIsFromBuilding(
      this.navBuilding,
      (res: any) => {
        if (res.length == 0) {
          this.setStatus('NO POIS NO NAV.');
          this.setInfo('');
          this.setLoading(false);
        } else {
          cordova.plugins.Situm.requestDirections(
            [this.navBuilding, this.location, res[0], {}],
            (route: any) => {
              this.ngZone.run(() => {
                this.navigation = `Nav to ${res[0].poiName}:`;
                this.indications = route.indications;
              });
              this.setLoading(false);
            },
            (err: any) => {
              this.ngZone.run(() => {
                this.navigation = `Nav to ${
                  res[0].poiName
                }, error = ${JSON.stringify(err)}`;
              });
              this.setLoading(false);
            }
          );
        }
      }
    );
  }
}
