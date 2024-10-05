import {Component} from '@angular/core';
import {NgStyle} from "@angular/common";

@Component({
  selector: 'app-select-service-and-professional',
  standalone: true,
  imports: [
    NgStyle
  ],
  templateUrl: './select-service-and-professional.component.html',
  styleUrl: './select-service-and-professional.component.css'
})
export class SelectServiceAndProfessionalComponent {
  public horizontalAlignment: string = "";
}
