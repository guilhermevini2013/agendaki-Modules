import { Component, AfterViewInit, ViewChild, OnInit } from '@angular/core';
import {MatPaginator, MatPaginatorModule} from '@angular/material/paginator';
import {MatSort, MatSortModule} from '@angular/material/sort';
import {MatTableDataSource, MatTableModule} from '@angular/material/table';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { ManageService } from '../../../services/manage/manage.service';
import { NgForOf } from '@angular/common';
import { Scheduling } from '../../../models/manage/AllSchedulingReadDTO';

@Component({
  selector: 'app-manage-page',
  standalone: true,
  imports: [
    MatFormFieldModule,
    MatInputModule,
    MatTableModule,
    MatSortModule,
    MatPaginatorModule,
    NgForOf
  ],
  templateUrl: './manage-page.component.html',
  styleUrls: ['./manage-page.component.css']
})
export class ManagePageComponent implements AfterViewInit, OnInit {
  displayedColumns: string[] = [];
  dataSource: MatTableDataSource<Scheduling>;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(private manageService: ManageService) {
    this.dataSource = new MatTableDataSource();
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
    this.manageService.getAllScheduling().subscribe(response => {
      this.displayedColumns = response.body?.columns!;
      this.dataSource.data = response.body?.allScheduling!;
    });
  }

  getColumnValue(row: Scheduling, column: string): string {
    const response = row.responseScheduling.find(res => res.nameColum === column);
    return response ? response.value : '';
  }
}
