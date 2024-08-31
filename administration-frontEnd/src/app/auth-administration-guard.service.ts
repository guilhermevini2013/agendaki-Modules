import {
  CanActivate,
  Router,
} from '@angular/router';
import {inject, Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {CookieService} from "ngx-cookie-service";
import {PopUpService} from "./services/pop-up.service";

@Injectable({
  providedIn: 'root'
})
export class AuthAdministrationGuard implements CanActivate{
  constructor(private router: Router,private cookieService:CookieService,private popUpService:PopUpService) {}

  canActivate(): Observable<boolean> | Promise<boolean> | boolean {
    if (this.cookieService.check("jwtAdministration")) {
      return true;
    } else {
      this.router.navigate(['/']);
      this.popUpService.openPopUp("Seu login expirou, faça novamente.","error")
      return false;
    }
  }
}
