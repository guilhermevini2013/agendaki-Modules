import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {PaymentOrdersDTO} from "../models/payment-orders-dto";

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
}
