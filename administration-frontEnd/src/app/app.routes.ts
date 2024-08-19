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

export const routes: Routes = [
  {path:"", component:LoginPageComponent},
  {path:"administration", component:AdministrationPrincipalComponent, canActivate: [AuthAdministrationGuard]},
  {path:"portalClient",component:PortalClientPrincipalComponent, canActivate: [AuthPortalClientGuard]}
];
