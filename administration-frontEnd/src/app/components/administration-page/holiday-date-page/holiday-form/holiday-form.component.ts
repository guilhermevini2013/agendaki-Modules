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
import {DateJobService} from "../../../../services/dateJob/date-job.service";

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

  constructor(private dateJobService: DateJobService) {
  }

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

  createHolidayNotJobFormGroup = this._formBuilder.group({
    dateHoliday: ['', Validators.required],
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
      scheduleInitial: convertToTime(this.secondFormGroup.value.startWork!),
      scheduleFinal: convertToTime(this.secondFormGroup.value.endWork!),
      breakInitial: convertToTime(this.secondFormGroup.value.startOffHour!),
      breakFinal: convertToTime(this.secondFormGroup.value.endOffHour!),
      dateOfHoliday: convertToDate(this.secondFormGroup.value.dateHoliday!),
      isOpen: true
    }
    this.dateJobService.insertDateHoliday(holidayDto).subscribe();
    window.location.reload();
  }
  protected createHolidayNotJob(): void {
    console.log(this.createHolidayNotJobFormGroup.value.dateHoliday!)
    const holidayDto: HolidayCreateDTO = {
      scheduleInitial: "00:00",
      scheduleFinal: "00:00",
      breakInitial: "00:00",
      breakFinal: "00:00",
      dateOfHoliday: convertToDate(this.createHolidayNotJobFormGroup.value.dateHoliday!),
      isOpen: false
    }
    this.dateJobService.insertDateHoliday(holidayDto).subscribe();
  }

}
function convertToDate(dateString: string): string {
  const day = dateString.substring(0, 2);
  const month = dateString.substring(2, 4);
  const year = dateString.substring(4, 8);
  return `${year}-${month}-${day}`;
}

function convertToTime(time: string): string {
  const hours = time.substring(0, 2);
  const minutes = time.substring(2, 4);
  return `${hours}:${minutes}`;
}
