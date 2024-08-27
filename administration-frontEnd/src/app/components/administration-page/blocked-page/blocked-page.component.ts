import { Component , AfterViewInit, ViewChild } from '@angular/core';
import {MatPaginator, MatPaginatorModule} from '@angular/material/paginator';
import {MatSort, MatSortModule} from '@angular/material/sort';
import {MatTableDataSource, MatTableModule} from '@angular/material/table';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';

export interface UserData {
  id: string;
  name: string;
}

const NAMES: string[] = [
  'Guilherme',
  'Guilherme',
  'Guilherme',
  'Guilherme',
  'Guilherme',
  'Guilherme',
  'Guilherme',
  'Guilherme',
  'Guilherme',
  'Emma',
  'Emma',
  'Emma',
  'Emma',
  'Emma',
  'Emma',
  'Emma',
  'Emma',
  'Emma',
  'Emma',
];

@Component({
  selector: 'app-blocked-page',
  standalone: true,
  imports: [MatFormFieldModule, MatInputModule, MatTableModule, MatSortModule, MatPaginatorModule],
  templateUrl: './blocked-page.component.html',
  styleUrl: './blocked-page.component.css'
})
export class BlockedPageComponent implements AfterViewInit {
  displayedColumns: string[] = ['id', 'name'];
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
  const name =
    NAMES[Math.round(Math.random() * (NAMES.length - 1))] +
    ' ' +
    NAMES[Math.round(Math.random() * (NAMES.length - 1))].charAt(0) +
    '.';

  return {
    id: id.toString(),
    name: name,
  };
}
