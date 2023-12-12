import {
  CUSTOM_ELEMENTS_SCHEMA,
  Component,
  ElementRef,
  ViewChild,
} from '@angular/core';
import {
  IonHeader,
  IonToolbar,
  IonTitle,
  IonContent,
  IonThumbnail,
} from '@ionic/angular/standalone';

import * as Constants from 'src/constants';

@Component({
  selector: 'app-wyf',
  templateUrl: 'wyf.page.html',
  // Make sure you declare that you are using our custom HTMLElement with:
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  styleUrls: ['wyf.page.scss'],
  standalone: true,
  imports: [IonHeader, IonToolbar, IonTitle, IonThumbnail, IonContent],
})
export class WYFPage {
  @ViewChild('mapView') mapView: ElementRef | undefined;

  // Internal code
  // Set the credentials inside the HTMLElement with the values in constants.ts
  ngAfterViewInit() {
    this.mapView?.nativeElement.setAttribute(
      'viewer-domain',
      Constants.VIEWER_DOMAIN
    );

    this.mapView?.nativeElement.setAttribute(
      'situm-api-key',
      Constants.API_KEY
    );

    this.mapView?.nativeElement.setAttribute(
      'building-identifier',
      Constants.BUILDING_IDENTIFIER
    );
  }
}
