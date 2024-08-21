import { Routes } from '@angular/router';
import {LoginPageComponent} from "./components/login-page/login-page.component";
import {
  AdministrationPrincipalComponent
} from "./components/administration-page/administration-principal/administration-principal.component";
import {
  PortalClientPrincipalComponent
} from "./components/portal-client-page/portal-client-principal/portal-client-principal.component";
import {AuthPortalClientGuard} from "./auth-portal-client-guard.service";
import {AuthAdministrationGuard} from "./auth-administration-guard.service";
import { ClientOrdersComponent } from './components/portal-client-page/client-orders/client-orders.component';
import { CreateOrderComponent } from './components/portal-client-page/create-order/create-order.component';

export const routes: Routes = [
  {
    path:"", 
    component:LoginPageComponent
  },

  {
    path:"administration",
     component:AdministrationPrincipalComponent
  },

  {
    path:"portalClient",
    component:PortalClientPrincipalComponent,

    children:[
      {
        path:"create-order",
        component:CreateOrderComponent
      },
      {
        path:"client-orders",
        component:ClientOrdersComponent
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
