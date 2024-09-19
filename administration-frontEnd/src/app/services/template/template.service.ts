import { Injectable } from '@angular/core';
import {HttpClient, HttpResponse} from "@angular/common/http";
import {TemplateDTO} from "../../models/template/template-create-dto";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class TemplateService {
  private baseHttp:string = "http://localhost:8081";
  constructor(private http:HttpClient) { }

  // insertTemplate(template:TemplateDTO):Observable<HttpResponse<void>>{
  //   return this.http.post(this.baseHttp+"/scheduling/api/template",template,{
  //     withCredentials: true
  //   });
  // }
}
