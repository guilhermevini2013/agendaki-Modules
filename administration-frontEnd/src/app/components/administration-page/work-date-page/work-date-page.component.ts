import { Component, inject } from '@angular/core';
import { WeekCardComponent } from './week-card/week-card.component';
import { DayOfWeek } from '../../../models/dateJob/dayOfWeek';

@Component({
  selector: 'app-work-date-page',
  standalone: true,
  imports: [WeekCardComponent],
  templateUrl: './work-date-page.component.html',
  styleUrl: './work-date-page.component.css'
})
export class WorkDatePageComponent {
  get DayOfWeek() {
    return DayOfWeek;
  }
}
