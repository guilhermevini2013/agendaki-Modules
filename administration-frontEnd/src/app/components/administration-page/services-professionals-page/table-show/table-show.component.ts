import {Component, OnInit, ViewChild} from '@angular/core';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { MatSort, MatSortModule } from '@angular/material/sort';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { HolidayFormComponent } from '../../holiday-date-page/holiday-form/holiday-form.component';
import {
  ProfessionalAndServiceService
} from "../../../../services/professionalAndService/professional-and-service.service";
import {
  ProfessionalAndServiceReadDTO
} from "../../../../models/professionalAndService/professional-and-service-read-dto";

@Component({
  selector: 'app-table-show',
  standalone: true,
  imports: [
    MatFormFieldModule,
    MatInputModule,
    MatTableModule,
    MatSortModule,
    MatPaginatorModule,
    HolidayFormComponent
  ],
  templateUrl: './table-show.component.html',
  styleUrl: './table-show.component.css'
})
export class TableShowComponent implements OnInit{
  displayedColumns: string[] = ['nameProfessional', 'nameService',"action"];
  dataSource: MatTableDataSource<ProfessionalAndServiceReadDTO>;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(private professionalAndServiceService: ProfessionalAndServiceService) {
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
    this.professionalAndServiceService.getProfessionalsAndServices().subscribe(
      response => {
        this.dataSource = new MatTableDataSource(response.body!);
      }
    );
  }

  disassociateProfessional(idProfessional: number, idService: number) {
    this.professionalAndServiceService.disassociateProfessionalAndService(idProfessional, idService).subscribe(
      () => {
        this.ngOnInit();
      }
    );
  }
}


