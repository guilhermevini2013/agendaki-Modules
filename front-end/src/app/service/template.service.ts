import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from "@angular/common/http";
import {Observable} from "rxjs";
import {TemplateDTO} from "../form-personalize-view/form-personalize-view.component";

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
}
