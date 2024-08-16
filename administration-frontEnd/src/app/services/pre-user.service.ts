import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpResponse} from "@angular/common/http";
import {PreUserAuthDTO} from "../models/preUserAuthDTO";
import {PreUserTokenDTO} from "../models/pre-user-token-dto";
import {Observable, tap} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class PreUserService {
  private _baseUrlAPI: string = "http://localhost:8081/"

  constructor(private http: HttpClient) {
  }

  public authPreUser(preUser: PreUserAuthDTO): Observable<HttpResponse<PreUserTokenDTO>> {
    return this.http.post<PreUserTokenDTO>(this._baseUrlAPI + "financially/api/pre-user/auth", preUser, {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      }),
      observe: 'response'
    }).pipe(
      tap(response =>{
        localStorage.setItem("tokenPortalClient",<string>response.body?.token);
      })
    );
  }
}
