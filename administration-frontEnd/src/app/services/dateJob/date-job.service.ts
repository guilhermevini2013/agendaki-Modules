import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from "@angular/common/http";
import {WeekCreateDTO} from "../../models/dateJob/week-create-dto";
import {Observable} from "rxjs";
import {DateJobHolidayReadDTO, DateJobReadDTO} from "../../models/dateJob/date-job-read-DTO";
import {HolidayCreateDTO} from "../../models/holiday-create-dto";

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

  insertDateHoliday(dateOfJobDTO: HolidayCreateDTO): Observable<HttpResponse<void>> {
    return this.http.post<void>(this.baseUrl + '/scheduling/api/dateJob/holiday', dateOfJobDTO,
      {
        withCredentials: true,
        observe: 'response'
      }
    );
  }

  getAlldateOfJobHoliday(): Observable<HttpResponse<DateJobHolidayReadDTO[]>> {
    return this.http.get<DateJobHolidayReadDTO[]>(this.baseUrl + '/scheduling/api/dateJob/holiday',
      {
        withCredentials: true,
        observe: 'response'
      }
    );
  }

  deleteDateHoliday(id: number): Observable<HttpResponse<void>> {
    return this.http.delete<void>(this.baseUrl + '/scheduling/api/dateJob?id=' + id,
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
