import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from "@angular/common/http";
import {Observable} from "rxjs";
import {ServiceCreateDTO} from "../../models/professionalAndService/service-create-dto";
import {ServiceReadDto} from "../../models/professionalAndService/service-read-dto";
import {ProfessionalCreateDto} from "../../models/professionalAndService/professional-create-dto";
import {ProfessionalAndServiceReadDTO} from "../../models/professionalAndService/professional-and-service-read-dto";
import {ProfessionalReadDto} from "../../models/professionalAndService/professional-read-dto";

@Injectable({
  providedIn: 'root'
})
export class ProfessionalAndServiceService {

  private baseHttp: string = "http://localhost:8081";

  constructor(private http: HttpClient) {
  }

  insertService(serviceDTO: ServiceCreateDTO): Observable<HttpResponse<void>> {
    return this.http.post<void>(this.baseHttp + "/scheduling/api/service", serviceDTO, {
      withCredentials: true,
      headers: {
        'Content-Type': 'application/json'
      },
      observe: 'response'
    });
  }

  insertProfessional(professionalDTO: ProfessionalCreateDto): Observable<HttpResponse<void>> {
    return this.http.post<void>(this.baseHttp + "/scheduling/api/professional", professionalDTO, {
      withCredentials: true,
      headers: {
        'Content-Type': 'application/json'
      },
      observe: 'response'
    });
  }

  getServices(): Observable<HttpResponse<ServiceReadDto[]>> {
    return this.http.get<ServiceReadDto[]>(this.baseHttp + "/scheduling/api/service", {
      withCredentials: true,
      headers: {
        'Content-Type': 'application/json'
      },
      observe: 'response'
    });
  }

  getProfessionalsAndServices(): Observable<HttpResponse<ProfessionalAndServiceReadDTO[]>> {
    return this.http.get<ProfessionalAndServiceReadDTO[]>(this.baseHttp + "/scheduling/api/professional/all", {
      withCredentials: true,
      headers: {
        'Content-Type': 'application/json'
      },
      observe: 'response'
    });
  }

  disassociateProfessionalAndService(idProfessional: number, idService: number): Observable<void> {
    return this.http.put<void>(this.baseHttp + "/scheduling/api/professional/disassociate", Array.of(idService), {
      withCredentials: true,
      params: {
        id: idProfessional
      },
      headers: {
        'Content-Type': 'application/json'
      }
    })
  }

  deleteService(id: number): Observable<HttpResponse<void>> {
    return this.http.delete<void>(this.baseHttp + "/scheduling/api/service", {
      withCredentials: true,
      params: {
        id: id
      },
      headers: {
        'Content-Type': 'application/json'
      },
      observe: 'response'
    });
  }

  deleteProfessional(id: number): Observable<HttpResponse<void>> {
    return this.http.delete<void>(this.baseHttp + "/scheduling/api/professional", {
      withCredentials: true,
      params: {
        id: id
      },
      headers: {
        'Content-Type': 'application/json'
      },
      observe: 'response'
    });
  }

  getAllProfessionals(): Observable<HttpResponse<ProfessionalReadDto[]>> {
    return this.http.get<ProfessionalReadDto[]>(this.baseHttp + "/scheduling/api/professional", {
      withCredentials: true,
      headers: {
        'Content-Type': 'application/json'
      },
      observe: 'response'
    });
  }
}
