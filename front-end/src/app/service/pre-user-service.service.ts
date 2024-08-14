import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class PreUserServiceService {
  private _baseUrlAPI = "http://localhost:8081"

  constructor(private http: HttpClient) {
  }

  savePreUser(data: any): Observable<any> {
    console.log(data)
     let observable = this.http.post<any>(this._baseUrlAPI + "/financially/api/pre-user", data, {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      }),
    });
     return observable;
  }
}
