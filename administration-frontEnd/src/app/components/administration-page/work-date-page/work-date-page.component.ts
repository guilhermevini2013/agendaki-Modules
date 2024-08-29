import { Component, inject } from '@angular/core';
import { WeekCardComponent } from './week-card/week-card.component';

@Component({
  selector: 'app-work-date-page',
  standalone: true,
  imports: [WeekCardComponent],
  templateUrl: './work-date-page.component.html',
  styleUrl: './work-date-page.component.css'
})
export class WorkDatePageComponent {
  
}
