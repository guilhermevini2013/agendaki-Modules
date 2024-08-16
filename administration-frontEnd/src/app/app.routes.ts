import { Routes } from '@angular/router';
import {LoginPageComponent} from "./components/login-page/login-page.component";
import {
  AdministrationPrincipalComponent
} from "./components/administration-page/administration-principal/administration-principal.component";

export const routes: Routes = [
  {path:"", component:LoginPageComponent},
  {path:"administration", component:AdministrationPrincipalComponent},
];
