import { Component , inject } from '@angular/core';
import { FormBuilder, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButton } from '@angular/material/button';
import { MatDividerModule } from '@angular/material/divider';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatListModule } from '@angular/material/list';
import { MatOption, MatSelect } from '@angular/material/select';
import { MatTabsModule } from '@angular/material/tabs';
import { NgxMaskDirective, NgxMaskPipe } from 'ngx-mask';
import { NgxSpinnerModule } from 'ngx-spinner';
import { WorkerCreateDTO } from '../../../../models/worker-create-dto';
import { ServiceCreateDTO } from '../../../../models/professionalAndService/service-create-dto';
import { NgFor } from '@angular/common';
import {MatSliderModule} from '@angular/material/slider';

import {
  ProfessionalAndServiceService
} from "../../../../services/professionalAndService/professional-and-service.service";

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

export class FormsGroupComponent {
  private _formBuilder = inject(FormBuilder);
  public workers:WorkerCreateDTO[] = [];
  public services:ServiceCreateDTO[] = [];
  constructor(private professionalAndService:ProfessionalAndServiceService) {
  }
  workerFormGroup = this._formBuilder.group({
    name: ['', Validators.required],
    idService: [0,Validators.required]
  });
  defaltTIme:number = 30;

  serviceFormGroup = this._formBuilder.group({
    name: ['', Validators.required],
    price: ['', Validators.required],
    time: [this.defaltTIme, Validators.required],
  });

  protected switchResetForms():void {
    this.serviceFormGroup.reset()
    this.workerFormGroup.reset()
  }

  protected createWorker():void{
    const workerDto:WorkerCreateDTO = {
      name: this.workerFormGroup.value.name!,
    }

    this.workers.push(workerDto);
    this.workerFormGroup.reset()
  }

  protected createService():void{
    const serviceDto:ServiceCreateDTO = {
      name: this.serviceFormGroup.value.name!,
      price: Number(this.serviceFormGroup.value.price!),
      duration: Number(this.serviceFormGroup.value.time!),
    }
    /*this.professionalAndService.insertService(serviceDto).subscribe(next =>{
      if (next.status == 201){
        
    });*/
    this.services.push(serviceDto);
    this.serviceFormGroup.reset()
    this.serviceFormGroup.patchValue({
      time: this.defaltTIme
    });
  }

}
