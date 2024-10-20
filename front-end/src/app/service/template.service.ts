import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from "@angular/common/http";
import {Observable} from "rxjs";
import {TemplateDTO} from "../form-personalize-view/form-personalize-view.component";
import {
  ProfessionalDTO,
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

  getAllProfessionalsByParamAndService(param: string,idService:number): Observable<HttpResponse<ProfessionalDTO[]>> {
    return this.http.get<ProfessionalDTO[]>(this.baseUrl+`/scheduling/api/professional/public/${param}?service=${idService}`,
      {
        observe: 'response',
        withCredentials: false
      }
    );
  }

  getAllTimesFree(param: string, date:string, idService:number, idProfessional:number): Observable<HttpResponse<string[]>> {
    return this.http.get<string[]>(this.baseUrl+`/scheduling/api/scheduling/public/time/${param}?date=${date}&idService=${idService}&idProfessional=${idProfessional}`,
      {
        observe: 'response',
        withCredentials: false
      }
    );
  }
}
