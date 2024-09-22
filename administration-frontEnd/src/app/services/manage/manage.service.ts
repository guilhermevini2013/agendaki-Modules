import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from "@angular/common/http";
import {Observable} from "rxjs";
import {AllSchedulingReadDTO} from "../../models/manage/AllSchedulingReadDTO";

@Injectable({
  providedIn: 'root'
})
export class ManageService {
  private baseHttp: string = "http://localhost:8081";

  constructor(private http: HttpClient) {
  }

  getAllScheduling(): Observable<HttpResponse<AllSchedulingReadDTO>> {
    return this.http.get<AllSchedulingReadDTO>(this.baseHttp + "/scheduling/api/scheduling/private", {
      withCredentials: true,
      headers: {
        'Content-Type': 'application/json'
      },
      observe: 'response'
    });
  }
}
