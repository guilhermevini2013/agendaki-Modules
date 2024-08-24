import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from "@angular/common/http";
import {Observable} from "rxjs";
import {PaymentOrdersDTO} from "../models/payment-orders-dto";
import {PaymentPixCreateDTO} from "../models/payment-pix-create-dto";
import {PaymentInfo} from "../models/payment-info";

@Injectable({
  providedIn: 'root'
})
export class PaymentService {
  private _baseUrlAPI: string = "http://localhost:8081/"
  constructor(private http:HttpClient) { }

  public getAllOrders(): Observable<PaymentOrdersDTO[]>{
    return this.http.get<PaymentOrdersDTO[]>(this._baseUrlAPI + "financially/api/payment/orders",{
      withCredentials: true,
    });
  }

  public createPayment(createPayment:PaymentPixCreateDTO): Observable<HttpResponse<PaymentInfo>> {
    console.log(createPayment)
    return this.http.post<PaymentInfo>(this._baseUrlAPI + "financially/api/payment", createPayment,
      {
        observe: "response",
        withCredentials: true
      })
  }
}
