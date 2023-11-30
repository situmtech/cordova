import { Component, NgZone } from '@angular/core';
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
  IonIcon,
  IonTextarea,
  IonRow,
  IonPicker,
  IonThumbnail,
} from '@ionic/angular/standalone';
import { NgFor, NgIf } from '@angular/common';
import { locate, cloudDownload, map } from 'ionicons/icons';
import { addIcons } from 'ionicons';

import { requestPermissions } from '../utils/request.permissions';
import * as Constants from '../../constants';

declare let cordova: any;

@Component({
  selector: 'app-sdk',
  templateUrl: 'sdk.page.html',
  styleUrls: ['sdk.page.scss'],
  standalone: true,
  imports: [
    IonHeader,
    IonToolbar,
    IonTitle,
    IonThumbnail,
    IonContent,
    IonCard,
    IonCardHeader,
    IonCardTitle,
    IonCardContent,
    IonButton,
    IonLabel,
    IonItem,
    IonList,
    IonRow,
    IonIcon,
    IonTextarea,
    IonPicker,
    NgFor,
    NgIf,
  ],
})
export class SDKPage {
  buildings: Array<any> | undefined;
  selectedBuilding: any | undefined;
  pois: any | undefined;
  currentPoi: any | undefined;

  constructor(private ngZone: NgZone) {
    addIcons({ locate, cloudDownload, map });
  }

  ionViewDidEnter() {
    // Authenticate yourself in our SDK to be able to start positioning, retrieve data, ...
    // Make sure you are authenticated before calling any other method of our SDK.
    cordova.plugins.Situm.setApiKey(Constants.API_USER, Constants.API_KEY);

    this._retrieveSpecifiedBuilding(Constants.BUILDING_IDENTIFIER);
  }

  // ==============================================================================================
  // =                                    POSITIONING                                             =
  // ==============================================================================================

  async startPositioning() {
    if (this.positioning) {
      return;
    }
    this._setInfo('');
    // You might want to know how we ask the all the permissions,
    // so take a look at /src/app/utils/request.permission.ts
    requestPermissions(
      () => {
        this.doStartPositioning();
      },
      (errorMessage: any) => {
        this._setInfo(
          'Something did happen while asking for permission: ' + errorMessage
        );
      }
    );
  }

  private doStartPositioning() {
    // Start positioning in the building specified in the /src/constants.ts you created before:
    cordova.plugins.Situm.startPositioning(
      // In case you have multiple buildings that the user could visit,
      // you might want to start positioning in all your buildings using global mode
      // by specifying an empty identifier:
      //
      // buildingIdentifier: ''
      [{ buildingIdentifier: Constants.BUILDING_IDENTIFIER }],
      (res: any) => {
        if (res && res.statusName) {
          this._setStatus(res.statusName);
        }
        if (res && res.position) {
          this._setPositioning(true);
          this._setStatus('POSITIONING');
          this._setInfo(res);
        }
      },
      (err: any) => {
        this._setPositioning(false);
        this._setStatus('ERROR');
        this._setInfo(err);
      }
    );
  }

  async stopPositioning() {
    cordova.plugins.Situm.stopPositioning(
      () => {
        this._setPositioning(false);
        this._setStatus('STOPPED');
        this._setInfo('');
      },
      (err: any) => {
        this._setStatus('ERROR');
        this._setInfo(err);
      }
    );
  }

  // ==============================================================================================
  // =                                 FETCH RESOURCES                                            =
  // ==============================================================================================

  public fetchBuildingInfo() {
    cordova.plugins.Situm.fetchBuildingInfo(
      this.selectedBuilding,
      (res: any) => {
        this._setStatus('LOADED BUILDING INFO');
        this._setInfo(res);
      },
      (err: any) => {
        this._setStatus('ERROR FETCHING BUILDING INFO');
        this._setInfo(err);
      }
    );

    this._setStatus('FETCHING BUILDING INFO ...');
  }

  public fetchPois() {
    cordova.plugins.Situm.fetchIndoorPOIsFromBuilding(
      this.selectedBuilding,
      (res: any) => {
        this.pois = res;
        this._setStatus('LOADED BUILDING INDOOR POIS');
        this._setInfo(res);
      },
      (err: any) => {
        this._setStatus('ERROR FETCHING INDOOR POIS');
        this._setInfo(err);
      }
    );

    this._setStatus('FETCHING BUILDING INDOOR POIS ...');
  }

  public fetchPoiCategories() {
    cordova.plugins.Situm.fetchPoiCategories(
      (poiCategories: any) => {
        this._setStatus('LOADED POI CATEGORIES');
        this._setInfo(poiCategories);
      },
      (err: any) => {
        this._setStatus('ERROR FETCHING POI CATEGORIES');
        this._setInfo(err);
      }
    );

    this._setStatus('FETCHING POI CATEGORIES ...');
  }

  public fetchGeofences() {
    cordova.plugins.Situm.fetchGeofencesFromBuilding(
      this.selectedBuilding,
      (geofences: any) => {
        this._setStatus('LOADED BUILDING GEOFENCES');
        this._setInfo(geofences);
      },
      (err: any) => {
        this._setStatus('ERROR FETCHING GEOFENCES');
        this._setInfo(err);
      }
    );

    this._setStatus('FETCHING BUILDING GEOFENCES ...');
  }

  // ==============================================================================================
  // =                                    CARTOGRAPHY                                             =
  // ==============================================================================================

  public selectPoi() {
    if (!this.currentPoi) {
      this._setInfo('Select a POI before calling selectPoi() ');
      return;
    }

    cordova.plugins.MapViewController.selectPoi(this.currentPoi.identifier);
  }

  public navigateToPoi() {
    if (!this.currentPoi) {
      this._setInfo('Select a POI before calling navigateToPoi() ');
      return;
    }
    cordova.plugins.MapViewController.navigateToPoi(
      this.currentPoi.identifier,
      'CHOOSE_SHORTEST' // 'ONLY_ACCESSIBLE' | 'ONLY_NOT_ACCESSIBLE_FLOOR_CHANGES' | undefined
    );
  }

  // ==============================================================================================
  // =                                 INTERNAL APP METHODS                                       =
  // ==============================================================================================

  currentStatus: string = 'NOT STARTED';
  positioning: boolean = false;
  currentPositioningInfo: string = 'NOT STARTED';

  private _setStatus(status: string) {
    this.ngZone.run(() => {
      this.currentStatus = status;
    });
  }

  private _setInfo(jsonRes: string) {
    console.log(JSON.stringify(jsonRes, null, 2));
    this.ngZone.run(() => {
      this.currentPositioningInfo =
        jsonRes == '' || jsonRes == undefined
          ? ''
          : JSON.stringify(jsonRes, null, 2);
    });
  }

  private _setPositioning(positioning: boolean) {
    this.ngZone.run(() => {
      this.positioning = positioning;
    });
  }

  private _retrieveSpecifiedBuilding(buildingIdentifier: string) {
    // We are initially fetching the building specified in Constants.BUILDING_IDENTIFIER
    this._loadBuildings((buildings: any) => {
      this.selectedBuilding = buildings.find(
        (b: any) => b.buildingIdentifier == buildingIdentifier
      );
      // And its POIs
      this._loadPoisFrom(this.selectedBuilding, (pois: any) => {
        // Finally, we also populate the POI picker
        this._populatePOIPicker(pois);
      });
    });
  }

  private _loadBuildings(successCb: Function) {
    if (this.buildings) return;
    // Fetch all buildings:
    cordova.plugins.Situm.fetchBuildings(
      (res: any) => {
        this.buildings = res;
        console.log('EXAMPLE> buildings loaded');
        console.info('EXAMPLE> data:\n', res);
        successCb(this.buildings);
      },
      (err: any) => {
        console.error(
          'EXAMPLE> error while fetching all buildings, error:\n',
          err
        );
      }
    );
    console.log('EXAMPLE> fetching all buildings ...');
  }

  private _loadPoisFrom(b: any, successCb: Function) {
    // Fetch indoor pois from the building of Constants.BUILDING_IDENTIFIER:
    cordova.plugins.Situm.fetchIndoorPOIsFromBuilding(
      b,
      (res: any) => {
        this.pois = res;
        console.log('EXAMPLE> indoor pois loaded');
        console.info('EXAMPLE> data:\n', res);
        successCb(this.pois);
      },
      (err: any) => {
        console.error(
          `EXAMPLE> error while fetching indoor pois, error:\n ${err}`
        );
      }
    );

    console.log(
      `EXAMPLE> fetching indoor pois from ${b.buildingIdentifier} - ${b.name} ...`
    );
  }

  // ion-picker

  isPOIPickerVisible = true;

  public pickerColumns = [
    {
      name: 'pois',
      options: [
        {
          text: 'Do fetchPois() before selecting a POI',
          value: 'empty',
        },
      ],
    },
  ];

  public pickerButtons = [
    {
      text: 'Cancel',
      role: 'cancel',
    },
    {
      text: 'Confirm',
      handler: (value: any) => {
        console.log('You selected: ', value);
        this.ngZone.run(() => {
          this.currentPoi = value.pois.value;
        });
      },
    },
  ];

  private _populatePOIPicker(pois: any) {
    this.pickerColumns[0].options = [];
    for (let poi of pois) {
      this.pickerColumns[0].options.push({
        text: poi.poiName,
        value: poi,
      });
    }
    this.pickerColumns[0].options.sort((a, b) => {
      const textA = a.text.toLowerCase();
      const textB = b.text.toLowerCase();
      return textA.localeCompare(textB);
    });
  }
}
