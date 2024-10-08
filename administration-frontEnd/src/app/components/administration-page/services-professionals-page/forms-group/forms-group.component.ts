import {Component, inject, OnInit} from '@angular/core';
import {FormBuilder, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import {MatButton} from '@angular/material/button';
import {MatDividerModule} from '@angular/material/divider';
import {MatExpansionModule} from '@angular/material/expansion';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatIconModule} from '@angular/material/icon';
import {MatInputModule} from '@angular/material/input';
import {MatListModule} from '@angular/material/list';
import {MatOption, MatSelect} from '@angular/material/select';
import {MatTabsModule} from '@angular/material/tabs';
import {NgxMaskDirective, NgxMaskPipe} from 'ngx-mask';
import {NgxSpinnerModule} from 'ngx-spinner';
import {ProfessionalCreateDto} from '../../../../models/professionalAndService/professional-create-dto';
import {ServiceCreateDTO} from '../../../../models/professionalAndService/service-create-dto';
import {NgFor} from '@angular/common';
import {MatSliderModule} from '@angular/material/slider';

import {
  ProfessionalAndServiceService
} from "../../../../services/professionalAndService/professional-and-service.service";
import {ServiceReadDto} from "../../../../models/professionalAndService/service-read-dto";
import {ProfessionalReadDto} from "../../../../models/professionalAndService/professional-read-dto";

@Component({
  selector: 'app-forms-group',
  standalone: true,
  imports: [
    NgFor,
    MatTabsModule,
    MatButton,
    FormsModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatSelect,
    MatOption,
    MatInputModule,
    MatExpansionModule,
    MatListModule,
    MatIconModule,
    MatDividerModule,
    NgxMaskDirective,
    NgxMaskPipe,
    NgxSpinnerModule, // Include NgxSpinnerModule here
    MatSliderModule,
  ],
  templateUrl: './forms-group.component.html',
  styleUrl: './forms-group.component.css'
})

export class FormsGroupComponent implements OnInit {
  defaultTime: number = 30;
  protected professionals: ProfessionalReadDto[] = [];
  protected services: ServiceReadDto[] = [];
  private _formBuilder = inject(FormBuilder);
  workerFormGroup = this._formBuilder.group({
    name: ['', Validators.required],
    idService: [[], Validators.required]
  });
  serviceFormGroup = this._formBuilder.group({
    name: ['', Validators.required],
    price: ['', Validators.required],
    time: [this.defaultTime, Validators.required],
  });
  deleteWorkerFormGroup = this._formBuilder.group({
    id: [0, Validators.required],
  });
  deleteServiceFormGroup = this._formBuilder.group({
    id: [0, Validators.required],
  });

  constructor(private professionalAndService: ProfessionalAndServiceService) {
  }

  ngOnInit(): void {
    this.professionalAndService.getServices().subscribe(next => {
      if (next.status == 200) {
        this.services = next.body!;
      }
    });

    this.professionalAndService.getAllProfessionals().subscribe(next => {
      this.professionals = next.body!;
    });
  }

  deleteService() {
    if (this.deleteServiceFormGroup.valid) {
      this.professionalAndService.deleteService(this.deleteServiceFormGroup.value.id!).subscribe();
      window.location.reload();
    }
  }

  deleteProfessional() {
    if (this.deleteWorkerFormGroup.valid) {
      this.professionalAndService.deleteProfessional(this.deleteWorkerFormGroup.value.id!).subscribe();
      window.location.reload();
    }
  }

  protected createWorker(): void {
    if (this.workerFormGroup.valid) {
      const workerDto: ProfessionalCreateDto = {
        name: this.workerFormGroup.value.name!,
        servicesToJobIDs: this.workerFormGroup.value.idService! as number[]
      }
      this.professionalAndService.insertProfessional(workerDto).pipe().subscribe(next => {
        if (next.status == 201) {
          this.workerFormGroup.reset();
        }
      });
      window.location.reload();
    }
  }

  protected createService(): void {
    if (this.serviceFormGroup.valid) {
      const serviceDto: ServiceCreateDTO = {
        name: this.serviceFormGroup.value.name!,
        price: Number(this.serviceFormGroup.value.price!),
        duration: Number(this.serviceFormGroup.value.time!),
      }
      this.professionalAndService.insertService(serviceDto).subscribe(next => {
        if (next.status == 201) {
          this.serviceFormGroup.reset()
          this.serviceFormGroup.patchValue({
            time: this.defaultTime
          });
        }
      });
      this.serviceFormGroup.reset();
      window.location.reload();
    }
  }
}

