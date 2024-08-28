import { Component, inject } from '@angular/core';
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatStepperModule} from '@angular/material/stepper';
import {MatButtonModule} from '@angular/material/button';
import {MatRadioButton, MatRadioGroup, MatRadioModule} from '@angular/material/radio';
import {MatExpansionModule} from '@angular/material/expansion';
import {MatDividerModule} from '@angular/material/divider';
import {MatIconModule} from '@angular/material/icon';
import {CommonModule, DatePipe, NgIf} from '@angular/common';
import {MatListModule} from '@angular/material/list';
import {MatButtonToggleModule} from '@angular/material/button-toggle';
import {NgxMaskDirective, NgxMaskPipe} from 'ngx-mask';
import {NgxSpinnerModule} from "ngx-spinner";

@Component({
  selector: 'app-work-date-page',
  standalone: true,
  imports: [
    CommonModule, // Use CommonModule instead of BrowserModule
    MatButtonModule,
    MatStepperModule,
    MatRadioButton,
    MatRadioGroup,
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
    NgxMaskPipe,
    NgxSpinnerModule, // Include NgxSpinnerModule here
  ],
  templateUrl: './work-date-page.component.html',
  styleUrl: './work-date-page.component.css'
})
export class WorkDatePageComponent {
  private _formBuilder = inject(FormBuilder);
  doWork:boolean = false ;

  firstFormGroupMonday = this._formBuilder.group({
    doWork: ['', Validators.required],
  });

  secondFormGroupMonday = this._formBuilder.group({
    startWork: ['', Validators.required],
    endWork: ['', Validators.required],
    startOffHour: ['', Validators.required],
    endOffHour: ['', Validators.required],
  });

  firstFormGroupTuesday = this._formBuilder.group({
    doWork: ['', Validators.required],
  });

  secondFormGroupTuesday = this._formBuilder.group({
    startWork: ['', Validators.required],
    endWork: ['', Validators.required],
    startOffHour: ['', Validators.required],
    endOffHour: ['', Validators.required],
  });

  firstFormGroupWednesday = this._formBuilder.group({
    doWork: ['', Validators.required],
  });

  secondFormGroupWednesday = this._formBuilder.group({
    startWork: ['', Validators.required],
    endWork: ['', Validators.required],
    startOffHour: ['', Validators.required],
    endOffHour: ['', Validators.required],
  });

  firstFormGroupThursday = this._formBuilder.group({
    doWork: ['', Validators.required],
  });

  secondFormGroupThursday = this._formBuilder.group({
    startWork: ['', Validators.required],
    endWork: ['', Validators.required],
    startOffHour: ['', Validators.required],
    endOffHour: ['', Validators.required],
  });

  firstFormGroupFriday = this._formBuilder.group({
    doWork: ['', Validators.required],
  });

  secondFormGroupFriday = this._formBuilder.group({
    startWork: ['', Validators.required],
    endWork: ['', Validators.required],
    startOffHour: ['', Validators.required],
    endOffHour: ['', Validators.required],
  });

  firstFormGroupSaturday = this._formBuilder.group({
    doWork: ['', Validators.required],
  });

  secondFormGroupSaturday = this._formBuilder.group({
    startWork: ['', Validators.required],
    endWork: ['', Validators.required],
    startOffHour: ['', Validators.required],
    endOffHour: ['', Validators.required],
  });

  firstFormGroupSunday = this._formBuilder.group({
    doWork: ['', Validators.required],
  });

  secondFormGroupSunday = this._formBuilder.group({
    startWork: ['', Validators.required],
    endWork: ['', Validators.required],
    startOffHour: ['', Validators.required],
    endOffHour: ['', Validators.required],
  });

  protected checkIfWork(): string {
    const value = this.firstFormGroupMonday.value?.doWork;
    if (typeof value === 'string') {
      return value;
    } else {
      console.log(typeof value);
      return '0';
    }
  }
}
