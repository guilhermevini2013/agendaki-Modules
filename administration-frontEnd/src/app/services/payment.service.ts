import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from "@angular/common/http";
import {Observable} from "rxjs";
import {PaymentOrdersDTO} from "../models/payment-orders-dto";
import {PaymentPixCreateDTO} from "../models/payment-pix-create-dto";
import {PaymentInfo} from "../models/payment-info";
import {PaymentBankCreateDTO} from "../models/payment-bank-create-dto";
import {PaymentBankDetailsDTO} from "../models/payment-bank-details-dto";

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

  public createPaymentPix(createPayment:PaymentPixCreateDTO): Observable<HttpResponse<PaymentInfo>> {
    return this.http.post<PaymentInfo>(this._baseUrlAPI + "financially/api/payment", createPayment,
      {
        observe: "response",
        withCredentials: true
      })
  }

  public createPaymentBank(createPayment:PaymentBankCreateDTO): Observable<HttpResponse<PaymentBankDetailsDTO>>{
    return this.http.post<PaymentBankDetailsDTO>(this._baseUrlAPI + "financially/api/payment",createPayment,{
      observe: "response",
      withCredentials: true
    })
  }
}
