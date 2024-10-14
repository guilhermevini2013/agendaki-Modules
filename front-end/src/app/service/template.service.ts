import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from "@angular/common/http";
import {Observable} from "rxjs";
import {TemplateDTO} from "../form-personalize-view/form-personalize-view.component";
import {
  ServiceDTO
} from "../form-personalize-view/sections/select-service-and-professional/select-service-and-professional.component";

@Injectable({
  providedIn: 'root'
})
export class TemplateService {
  private baseUrl = 'http://localhost:8081';

  constructor(private http: HttpClient) {
  }

  getTemplateByParam(param: string): Observable<HttpResponse<TemplateDTO>> {
    return this.http.get<TemplateDTO>(this.baseUrl+`/scheduling/api/template/${param}`,
      {
        observe: 'response',
        withCredentials: false
      }
    );
  }

  getAllServicesByParam(param: string): Observable<HttpResponse<ServiceDTO[]>> {
    return this.http.get<ServiceDTO[]>(this.baseUrl+`/scheduling/api/service/public/${param}`,
      {
        observe: 'response',
        withCredentials: false
      }
    );
  }
}
