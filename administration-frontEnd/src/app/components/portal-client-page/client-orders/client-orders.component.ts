import {Component, OnInit} from '@angular/core';
import { MatIcon } from '@angular/material/icon';
import {PaymentService} from "../../../services/payment.service";
import {PaymentOrdersDTO} from "../../../models/payment-orders-dto";
import {NgForOf, NgIf} from "@angular/common";

@Component({
  selector: 'app-client-orders',
  standalone: true,
  imports: [MatIcon, NgForOf, NgIf],
  templateUrl: './client-orders.component.html',
  styleUrl: './client-orders.component.css'
})
export class ClientOrdersComponent implements OnInit{

  protected ordersList:PaymentOrdersDTO[]=[]

  constructor(private paymentService:PaymentService) {
  }

  ngOnInit(): void {
    this.paymentService.getAllOrders().subscribe(
      value => {
        this.ordersList= value
      }
    )
  }


}
