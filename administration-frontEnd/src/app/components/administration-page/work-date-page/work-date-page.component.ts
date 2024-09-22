import {Component, OnInit} from '@angular/core';
import {WeekCardComponent} from './week-card/week-card.component';
import {DateJobService} from "../../../services/dateJob/date-job.service";
import {DateJobReadDTO} from "../../../models/dateJob/date-job-read-DTO";
import {NgForOf} from "@angular/common";

@Component({
  selector: 'app-work-date-page',
  standalone: true,
  imports: [WeekCardComponent, NgForOf],
  templateUrl: './work-date-page.component.html',
  styleUrl: './work-date-page.component.css'
})
export class WorkDatePageComponent implements OnInit {
  protected daysOfWeek: DateJobReadDTO[] = []

  constructor(private dateJobService: DateJobService) {
  }

  ngOnInit(): void {
    this.dateJobService.getAllDateOfJobCommon().subscribe(
      (response) => {
        this.daysOfWeek = response.body!;
      },
      (error) => {
        console.log(error);
      }
    );
  }
}
