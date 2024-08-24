import {HttpEvent, HttpHandler, HttpInterceptor, HttpInterceptorFn, HttpRequest} from '@angular/common/http';
import {Injectable} from "@angular/core";
import {finalize, Observable} from "rxjs";
import {NgxSpinnerService} from "ngx-spinner";

@Injectable(
)
export class LoadingInterceptor implements HttpInterceptor{

  constructor(private spinner:NgxSpinnerService) {
  }

  intercept(req: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
  this.spinner.show();
    console.log("chegouuu")
    return next.handle(req).pipe(
      finalize(()=>{
        this.spinner.hide();
      })
    );
  }

}
