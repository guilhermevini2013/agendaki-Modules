import {
  CanActivate,
  Router,
} from '@angular/router';
import {Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {CookieService} from "ngx-cookie-service";

@Injectable({
  providedIn: 'root'
})
export class AuthAdministrationGuard implements CanActivate{
  constructor(private router: Router,private cookieService:CookieService) {}

  canActivate(): Observable<boolean> | Promise<boolean> | boolean {
    if (this.cookieService.check("jwtAdministration")) {
      return true;
    } else {
      this.router.navigate(['/']);
      return false;
    }
  }
}
