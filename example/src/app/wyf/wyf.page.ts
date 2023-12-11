import { CUSTOM_ELEMENTS_SCHEMA, Component } from '@angular/core';
import {
  IonHeader,
  IonToolbar,
  IonTitle,
  IonContent,
} from '@ionic/angular/standalone';

@Component({
  selector: 'app-wyf',
  templateUrl: 'wyf.page.html',
  // Make sure you declare that you are using our custom HTMLElement with:
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  styleUrls: ['wyf.page.scss'],
  standalone: true,
  imports: [IonHeader, IonToolbar, IonTitle, IonContent],
})
export class WYFPage {
  constructor() {}
}
