import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpResponse} from "@angular/common/http";
import {PreUserAuthDTO} from "../models/preUserAuthDTO";
import {PreUserTokenDTO} from "../models/pre-user-token-dto";
import {Observable, tap} from "rxjs";
import {TypePage} from "../components/login-page/form-login/TypePage";

@Injectable({
  providedIn: 'root'
})
export class PreUserService {
  private _baseUrlAPI: string = "http://localhost:8081/"

  constructor(private http: HttpClient) {
  }

  public authPreUser(preUser: PreUserAuthDTO, typePageToAuth: TypePage): Observable<HttpResponse<PreUserTokenDTO>> {
    let pathToAuth:string = "";
    if (typePageToAuth == TypePage.PORTAL_CLIENT){
      pathToAuth = "financially/api/pre-user/auth";
    }else if (typePageToAuth == TypePage.ADMINISTRATION){
      pathToAuth = "scheduling/api/user/auth"
    }
    return this.http.post<PreUserTokenDTO>(this._baseUrlAPI + pathToAuth, preUser, {
      withCredentials: true,
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      }),
      observe: 'response'
    });
  }
}
