import { Routes } from '@angular/router';
import {LoginPageComponent} from "./components/login-page/login-page.component";
import {
  AdministrationPrincipalComponent
} from "./components/administration-page/administration-principal/administration-principal.component";
import {
  PortalClientPrincipalComponent
} from "./components/portal-client-page/portal-client-principal/portal-client-principal.component";

export const routes: Routes = [
  {path:"", component:LoginPageComponent},
  {path:"administration", component:AdministrationPrincipalComponent},
  {path:"portalClient",component:PortalClientPrincipalComponent}
];
