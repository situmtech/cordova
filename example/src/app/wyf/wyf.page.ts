import { CUSTOM_ELEMENTS_SCHEMA, Component } from '@angular/core';
import { IonHeader, IonToolbar, IonTitle, IonContent } from '@ionic/angular/standalone';

declare let cordova: any;

@Component({
  selector: 'app-wyf',
  templateUrl: 'wyf.page.html',
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  styleUrls: ['wyf.page.scss'],
  standalone: true,
  imports: [IonHeader, IonToolbar, IonTitle, IonContent]
})
export class WYFPage {

  constructor() {}

  ionViewDidEnter() {
    cordova.plugins.MapView.onLoad((controller: any) => {
      controller.selectPoi('YOUR_POI_IDENTIFIER');
    })
  }

}
