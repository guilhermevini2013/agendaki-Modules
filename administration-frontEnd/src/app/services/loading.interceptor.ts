import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Injectable} from "@angular/core";
import {finalize, Observable} from "rxjs";
import {NgxSpinnerService} from "ngx-spinner";
import {Router} from "@angular/router";
import {PopUpService} from "./pop-up.service";

@Injectable(
)
export class LoadingInterceptor implements HttpInterceptor {
  constructor(private spinner: NgxSpinnerService, private router: Router, private popUpService: PopUpService) {
  }

  intercept(req: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    this.spinner.show();
    return next.handle(req).pipe(
      /* catchError((error: HttpErrorResponse) => {
         if (error.status == 403 || 401) {
           this.router.navigate(["/"])
           this.popUpService.openPopUp("Seu login expirou, faça novamente.", "error")
         }
         return throwError(() => error);
       }),*/
      finalize(() => {
        this.spinner.hide();
      })
    );
  }

}
