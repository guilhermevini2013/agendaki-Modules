import { Injectable } from '@angular/core';
import {HttpClient, HttpResponse} from "@angular/common/http";
import {WeekCreateDTO} from "../../models/dateJob/week-create-dto";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class DateService {
  private baseUrl = 'http://localhost:8081';
  constructor(private http:HttpClient) { }

  insertDateOfJob(dateOfJobDTO: WeekCreateDTO):Observable<HttpResponse<void>>{
    return this.http.post<void>(this.baseUrl+'/scheduling/api/dateJob/common', dateOfJobDTO,
      {
        withCredentials: true,
        observe: 'response'
      }
    );

  }
}
