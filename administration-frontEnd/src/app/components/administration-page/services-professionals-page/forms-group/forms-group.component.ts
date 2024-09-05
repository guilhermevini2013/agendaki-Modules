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
import { ServiceCreateDTO } from '../../../../models/service-create-dto';

@Component({
  selector: 'app-forms-group',
  standalone: true,
  imports: [
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
  ],
  templateUrl: './forms-group.component.html',
  styleUrl: './forms-group.component.css'
})
export class FormsGroupComponent {
  private _formBuilder = inject(FormBuilder);

  workerFormGroup = this._formBuilder.group({
    name: ['', Validators.required],
  });

  serviceFormGroup = this._formBuilder.group({
    name: ['', Validators.required],
    price: ['', Validators.required],
    time: ['', Validators.required],
  });

  protected createWorker():void{
    const workerDto:WorkerCreateDTO = {
      name: this.workerFormGroup.value.name!,
    }
   
    console.log(workerDto);
  }

  protected createService():void{
    const serviceDto:ServiceCreateDTO = {
      name: this.serviceFormGroup.value.name!,
      price: this.serviceFormGroup.value.price!,
      time: this.serviceFormGroup.value.time !,
    }
   
    console.log(serviceDto);
  }
}
