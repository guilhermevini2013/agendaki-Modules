import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpResponse} from "@angular/common/http";
import {catchError, map, Observable, throwError} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class PreUserServiceService {
  private _baseUrlAPI = "http://localhost:8081"

  constructor(private http: HttpClient) {
  }

  savePreUser(data: any): Observable<any> {
    return this.http.post<any>(`${this._baseUrlAPI}/financially/api/pre-user`, data, {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      }),
      observe: 'response'
    }).pipe(
      map((response: HttpResponse<any>) => ({
        status: response.status
      })),
      catchError(error => {
        let errorMessage: string = '';
        if (error.status === 400) {
          errorMessage = 'E-mail já cadastrado, tente novamente.';
        } else if (error.status === 500) {
          errorMessage = 'Erro no servidor. Tente novamente mais tarde.';
        }
        return throwError({status: error.status, message: errorMessage});
      })
    );
  }
}
