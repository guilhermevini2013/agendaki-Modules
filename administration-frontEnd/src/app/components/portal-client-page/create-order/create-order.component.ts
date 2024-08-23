import { Component, inject } from '@angular/core';
import {FormBuilder, Validators, FormsModule, ReactiveFormsModule, FormGroup} from '@angular/forms';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatStepperModule} from '@angular/material/stepper';
import {MatButtonModule} from '@angular/material/button';
import {MatRadioModule} from '@angular/material/radio';
import {MatExpansionModule} from '@angular/material/expansion';
import {MatDividerModule} from '@angular/material/divider';
import {MatIconModule} from '@angular/material/icon';
import {DatePipe, NgIf} from '@angular/common';
import {MatListModule} from '@angular/material/list';
import {MatButtonToggleModule} from '@angular/material/button-toggle';
import {NgxMaskDirective, NgxMaskPipe} from 'ngx-mask';


@Component({
  selector: 'app-create-order',
  standalone: true,
  imports: [MatButtonModule,
    MatStepperModule,
    FormsModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatRadioModule,
    MatExpansionModule,
    MatListModule, 
    MatIconModule,
    MatDividerModule, 
    DatePipe,
    MatButtonToggleModule,
    NgIf,
    NgxMaskDirective, 
    NgxMaskPipe
  ],
  templateUrl: './create-order.component.html',
  styleUrl: './create-order.component.css'
})
export class CreateOrderComponent {
  private _formBuilder = inject(FormBuilder);
  
  firstFormGroup = this._formBuilder.group({
    typeSignature: ['', Validators.required],
  });
 
  secondFormGroup = this._formBuilder.group({
    typePayment: ['', Validators.required],
  });
  formTo: FormGroup | undefined;
 
  formGroupBank = this._formBuilder.group({
    cpf: ['', Validators.required],
    street: ['', Validators.required],
    number: ['', Validators.required],
    city: ['', Validators.required],
    cep: ['', Validators.required],
  });
  
  formGroupPix = this._formBuilder.group({
    cpf: ['', Validators.required],
  });
  checkTypePayment():string {
    const value = this.secondFormGroup.value?.typePayment;
    if (typeof value === 'string') {
        if(value == '1'){
          this.formTo = this.formGroupPix
        }else{
          this.formTo = this.formGroupBank
        }
        return value;
    } else {
        console.log(typeof value)
        return '0';
    }
  }
}
