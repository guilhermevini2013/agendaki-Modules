import {Component, OnInit} from '@angular/core';
import { MatIcon } from '@angular/material/icon';
import {PaymentService} from "../../../services/payment.service";

@Component({
  selector: 'app-client-orders',
  standalone: true,
  imports: [MatIcon],
  templateUrl: './client-orders.component.html',
  styleUrl: './client-orders.component.css'
})
export class ClientOrdersComponent implements OnInit{

  constructor(private paymentService:PaymentService) {
  }

  ngOnInit(): void {
    this.paymentService.getAllOrders().subscribe(
      value => {
        console.log(value)
      }
    )
  }


}
