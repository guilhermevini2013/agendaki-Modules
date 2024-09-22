import {Component} from '@angular/core';
import {MatIcon} from '@angular/material/icon';

@Component({
  selector: 'app-footer-div',
  standalone: true,
  imports: [MatIcon],
  templateUrl: './footer-div.component.html',
  styleUrl: './footer-div.component.css'
})
export class FooterDivComponent {
  email: string = "ababada@gmail.com";
}
