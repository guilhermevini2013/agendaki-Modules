import { NgFor } from '@angular/common';
import { Component } from '@angular/core';

@Component({
  selector: 'app-information',
  standalone: true,
  imports: [NgFor],
  templateUrl: './information.component.html',
  styleUrl: './information.component.css'
})
export class InformationComponent {
  protected informationList:string[]=[]
  protected title:string = "";

}
