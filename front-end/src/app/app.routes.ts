import {Routes} from '@angular/router';
import {HomePageComponent} from "./home-page/home-page.component";
import {RegisterPageComponent} from './register-page/register-page.component';
import {FormPersonalizeViewComponent} from "./form-personalize-view/form-personalize-view.component";

export const routes: Routes = [
  {path: "", component: HomePageComponent},
  {path: "register", component: RegisterPageComponent},
  {path: "template/:uuidTemplate", component: FormPersonalizeViewComponent}
];
