import { CUSTOM_ELEMENTS_SCHEMA, Component } from '@angular/core';
import { IonHeader, IonToolbar, IonTitle, IonContent } from '@ionic/angular/standalone';

// Declare a cordova variable to avoid typescript errors
declare let cordova: any;

@Component({
  selector: 'app-wyf',
  templateUrl: 'wyf.page.html',
  // Make sure you declare that you are using our custom HTMLElement with:
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  styleUrls: ['wyf.page.scss'],
  standalone: true,
  imports: [IonHeader, IonToolbar, IonTitle, IonContent]
})
export class WYFPage {

  constructor() {}

  ionViewDidEnter() {
    // Use onLoad() as soon as the view is created 
    // to receive the MapViewController of our map and
    // be able to send actions and receive events that occur inside it.
    cordova.plugins.MapView.onLoad((controller: any) => {
      
      // Once the MapView was loaded you can start managing our map by:
      
      // 1. Sending actions like selecting or navigating to a poi in a building:
      // controller.selectPoi('YOUR_POI_IDENTIFIER');
      // controller.navigateToPoi('YOUR_POI_IDENTIFIER');

      // 2. Listen to events that take place inside our map like a poi being selected or deselected:
      controller.onPoiSelected((poiSelectedResult: any) => {
        console.log('EXAMPLE> onPoiSelected -> ', poiSelectedResult);
      });

      controller.onPoiDeselected((poiDeselectedResult: any) => {
        console.log('EXAMPLE> onPoiDeselected -> ', poiDeselectedResult);
      });
    })
  }

}
