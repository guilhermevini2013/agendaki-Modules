import {Routes} from '@angular/router';
import {LoginPageComponent} from "./components/login-page/login-page.component";
import {
  AdministrationPrincipalComponent
} from "./components/administration-page/administration-principal/administration-principal.component";
import {
  PortalClientPrincipalComponent
} from "./components/portal-client-page/portal-client-principal/portal-client-principal.component";
import {ClientOrdersComponent} from './components/portal-client-page/client-orders/client-orders.component';
import {CreateOrderComponent} from './components/portal-client-page/create-order/create-order.component';
import {ManagePageComponent} from './components/administration-page/manage-page/manage-page.component';
import {BlockedPageComponent} from './components/administration-page/blocked-page/blocked-page.component';
import {WorkDatePageComponent} from './components/administration-page/work-date-page/work-date-page.component';
import {HolidayDatePageComponent} from './components/administration-page/holiday-date-page/holiday-date-page.component';
import {AgendakiToolsComponent} from './components/administration-page/agendaki-tools/agendaki-tools.component';
import {
  ServicesProfessionalsPageComponent
} from './components/administration-page/services-professionals-page/services-professionals-page.component';

export const routes: Routes = [
  {
    path: "",
    component: LoginPageComponent
  },

  {
    path: "administration",
    component: AdministrationPrincipalComponent,
    //canActivate: [AuthAdministrationGuard],
    children: [
      {
        path: "manage-page",
        component: ManagePageComponent
      },
      {
        path: "blocked-page",
        component: BlockedPageComponent
      },
      {
        path: "work-date-page",
        component: WorkDatePageComponent
      },
      {
        path: "services-professionals-page",
        component: ServicesProfessionalsPageComponent
      },
      {
        path: "holiday-date-page",
        component: HolidayDatePageComponent
      },
      {
        path: "agendakiTools",
        component: AgendakiToolsComponent
      }
    ]
  },

  {
    path: "portalClient",
    component: PortalClientPrincipalComponent,
    //canActivate: [AuthPortalClientGuard],
    children: [
      {
        path: "create-order",
        component: CreateOrderComponent
      },
      {
        path: "client-orders",
        component: ClientOrdersComponent
      },
    ]
  },

  // {
  //   path:"create-order",
  //   component:CreateOrderComponent
  // },

  // {
  //   path:"client-orders",
  //   loadComponent: () =>
  //           import('./components/portal-client-page/portal-client-principal/create-order/create-order.component')
  //               .then(m => m.CreateOrderComponent)
  // }
];
