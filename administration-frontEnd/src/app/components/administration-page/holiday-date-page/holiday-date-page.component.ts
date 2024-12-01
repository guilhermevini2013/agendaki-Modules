import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {MatPaginator, MatPaginatorModule} from '@angular/material/paginator';
import {MatTableDataSource, MatTableModule} from '@angular/material/table';
import {MatSort, MatSortModule} from '@angular/material/sort';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {HolidayFormComponent} from './holiday-form/holiday-form.component';
import {DateJobService} from "../../../services/dateJob/date-job.service";
import {DateJobHolidayReadDTO} from "../../../models/dateJob/date-job-read-DTO";


export interface UserData {
  date: string;
  isOpen: string;
  scheduleInitial: string;
  scheduleFinal: string;
  breakInitial: string;
  breakFinal: string;
}

@Component({
  selector: 'app-holiday-date-page',
  standalone: true,
  imports: [
    MatFormFieldModule,
    MatInputModule,
    MatTableModule,
    MatSortModule,
    MatPaginatorModule,
    HolidayFormComponent
  ],
  templateUrl: './holiday-date-page.component.html',
  styleUrls: ['./holiday-date-page.component.css']
})
export class HolidayDatePageComponent implements AfterViewInit, OnInit {
  displayedColumns: string[] = ['dateOfHoliday', 'isOpen', 'scheduleInitial', 'scheduleFinal', 'breakInitial', 'breakFinal', 'remove'];
  dataSource: MatTableDataSource<any>;
  protected datesJobHolidays: DateJobHolidayReadDTO[] = [];

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(private dateJobService: DateJobService) {
    this.dataSource = new MatTableDataSource(this.datesJobHolidays);
  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  applyFilter(event: KeyboardEvent) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  ngOnInit(): void {
    this.dateJobService.getAlldateOfJobHoliday().subscribe(value => {
      this.datesJobHolidays = value.body || [];
      this.dataSource.data = this.datesJobHolidays.map( (dateJobHoliday) => {
        return {
          id: dateJobHoliday.id,
          dateOfHoliday: convertToDate(dateJobHoliday.dateOfHoliday),
          isOpen: dateJobHoliday.isOpen ? 'Sim' : 'Não',
          scheduleInitial: dateJobHoliday.scheduleInitial,
          scheduleFinal: dateJobHoliday.scheduleFinal,
          breakInitial: dateJobHoliday.breakInitial,
          breakFinal: dateJobHoliday.breakFinal
        };
      });
    });
  }

  removeHoliday(id: number) {
    this.dateJobService.deleteDateHoliday(id).subscribe();
    window.location.reload();
  }
}
function convertToDate(dateString: string): string {
  const day = dateString.substring(8, 10);
  const month = dateString.substring(5, 7);
  const year = dateString.substring(0, 4);
  return `${day}/${month}/${year}`;
}
