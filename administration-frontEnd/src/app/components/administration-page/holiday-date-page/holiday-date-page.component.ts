import {AfterViewInit, Component, ViewChild} from '@angular/core';
import {MatPaginator, MatPaginatorModule} from '@angular/material/paginator';
import {MatTableDataSource, MatTableModule} from '@angular/material/table';
import {MatSort, MatSortModule} from '@angular/material/sort';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {HolidayFormComponent} from './holiday-form/holiday-form.component';


export interface UserData {
  date: string;
  isOpen: string;
  scheduleInitial: string;
  scheduleFinal: string;
  breakInitial: string;
  breakFinal: string;
}

const NAMES: string[] = [
  'Guilherme',
  // ... (same list of names)
];

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
export class HolidayDatePageComponent implements AfterViewInit {
  displayedColumns: string[] = ['date', 'isOpen', 'scheduleInitial', 'scheduleFinal', 'breakInitial', 'breakFinal'];
  dataSource: MatTableDataSource<UserData>;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor() {
    const users = Array.from({length: 100}, (_, k) => createNewUser(k + 1));

    this.dataSource = new MatTableDataSource(users);
  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }
}

function createNewUser(id: number): UserData {
  return {
    date: `2024-08-${String(id).padStart(2, '0')}`, // Example date format
    isOpen: Math.random() > 0.5 ? 'Yes' : 'No',
    scheduleInitial: '09:00',
    scheduleFinal: '17:00',
    breakInitial: '12:00',
    breakFinal: '13:00',
  };
}
