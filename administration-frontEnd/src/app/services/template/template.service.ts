import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from "@angular/common/http";
import {TemplateDTO} from "../../models/template/template-create-dto";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class TemplateService {
  private baseHttp: string = "http://localhost:8081";

  constructor(private http: HttpClient) {
  }

  insertTemplate(template: TemplateDTO): Observable<HttpResponse<void>> {
    return this.http.post<void>(this.baseHttp + "/scheduling/api/template", template, {
      withCredentials: true,
      observe: 'response'
    });
  }

  getTemplate(): Observable<HttpResponse<TemplateDTO>> {
    return this.http.get<TemplateDTO>(this.baseHttp + "/scheduling/api/template", {
      withCredentials: true,
      observe: 'response'
    });
  }
}
