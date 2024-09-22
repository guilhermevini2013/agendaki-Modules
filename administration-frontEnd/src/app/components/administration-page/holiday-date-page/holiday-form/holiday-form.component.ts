import {Component, inject, Input} from '@angular/core';
import {FormBuilder, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
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
import {HolidayCreateDTO} from '../../../../models/holiday-create-dto';

@Component({
  selector: 'app-holiday-form',
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
  templateUrl: './holiday-form.component.html',
  styleUrl: './holiday-form.component.css'
})
export class HolidayFormComponent {
  @Input()
  public weekName: string = "";
  private _formBuilder = inject(FormBuilder);
  firstFormGroup = this._formBuilder.group({
    doWork: ['', Validators.required],
  });

  secondFormGroup = this._formBuilder.group({
    dateHoliday: ['', Validators.required],
    startWork: ['', Validators.required],
    endWork: ['', Validators.required],
    startOffHour: ['', Validators.required],
    endOffHour: ['', Validators.required],
  });

  protected checkIfWork(): string {
    const value = this.firstFormGroup.value?.doWork;
    if (typeof value === 'string') {
      return value;
    } else {
      console.log(typeof value);
      return '0';
    }
  }

  protected createHoliday(): void {
    const holidayDto: HolidayCreateDTO = {
      scheduleInitial: this.secondFormGroup.value.startWork!,
      scheduleFinal: this.secondFormGroup.value.endWork!,
      breakInitial: this.secondFormGroup.value.startOffHour!,
      breakFinal: this.secondFormGroup.value.endOffHour!,
      dateOfHoliday: new Date(this.secondFormGroup.value.dateHoliday!),
      isOpen: true
    }
    console.log(holidayDto);

  }
}
