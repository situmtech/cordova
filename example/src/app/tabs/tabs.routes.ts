import { Routes } from '@angular/router';
import { TabsPage } from './tabs.page';

export const routes: Routes = [
  {
    path: 'tabs',
    component: TabsPage,
    children: [
      {
        path: 'sdk',
        loadComponent: () =>
          import('../sdk/sdk.page').then((m) => m.SDKPage),
      },
      {
        path: 'wyf',
        loadComponent: () =>
          import('../wyf/wyf.page').then((m) => m.WYFPage),
      },
      {
        path: '',
        redirectTo: '/tabs/sdk',
        pathMatch: 'full',
      },
    ],
  },
  {
    path: '',
    redirectTo: '/tabs/sdk',
    pathMatch: 'full',
  },
];
