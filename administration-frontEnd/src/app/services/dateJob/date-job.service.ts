import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from "@angular/common/http";
import {WeekCreateDTO} from "../../models/dateJob/week-create-dto";
import {Observable} from "rxjs";
import {DateJobReadDTO} from "../../models/dateJob/date-job-read-DTO";

@Injectable({
  providedIn: 'root'
})
export class DateJobService {
  private baseUrl = 'http://localhost:8081';

  constructor(private http: HttpClient) {
  }

  insertDateOfJob(dateOfJobDTO: WeekCreateDTO): Observable<HttpResponse<void>> {
    return this.http.post<void>(this.baseUrl + '/scheduling/api/dateJob/common', dateOfJobDTO,
      {
        withCredentials: true,
        observe: 'response'
      }
    );
  }

  getAllDateOfJobCommon(): Observable<HttpResponse<DateJobReadDTO[]>> {
    return this.http.get<DateJobReadDTO[]>(this.baseUrl + '/scheduling/api/dateJob/common',
      {
        withCredentials: true,
        observe: 'response'
      }
    );
  }
}
