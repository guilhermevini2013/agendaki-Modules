import {NgFor} from '@angular/common';
import {Component} from '@angular/core';

@Component({
  selector: 'app-information',
  standalone: true,
  imports: [NgFor],
  templateUrl: './information.component.html',
  styleUrl: './information.component.css'
})
export class InformationComponent {
  protected content: string[] = []
  protected title: string = "";

}
