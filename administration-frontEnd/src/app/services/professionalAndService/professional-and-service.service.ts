import { Injectable } from '@angular/core';
import {HttpClient, HttpResponse} from "@angular/common/http";
import {Observable} from "rxjs";
import {ServiceCreateDTO} from "../../models/professionalAndService/service-create-dto";

@Injectable({
  providedIn: 'root'
})
export class ProfessionalAndServiceService {

  private baseHttp:string = "http://localhost:8081";

  constructor(private http:HttpClient) { }

  insertService(serviceDTO:ServiceCreateDTO):Observable<HttpResponse<void>>{
    return this.http.post<void>(this.baseHttp+"/scheduling/api/service",serviceDTO,{
      withCredentials:true,
      headers:{
        'Content-Type':'application/json'
      },
      observe:'response'
    });
  }
}
