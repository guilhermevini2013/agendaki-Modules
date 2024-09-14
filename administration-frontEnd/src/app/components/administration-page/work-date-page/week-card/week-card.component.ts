import { Component, Input, inject, input } from '@angular/core';
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
import { DayOfWeek } from '../../../../models/dateJob/dayOfWeek';
import { WeekCreateDTO } from '../../../../models/dateJob/week-create-dto';
import {DateService} from "../../../../services/dateJob/date.service";

@Component({
  selector: 'app-week-card',
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
    NgxSpinnerModule,
  ],
  templateUrl: './week-card.component.html',
  styleUrl: './week-card.component.css'
})
export class WeekCardComponent {
  private _formBuilder = inject(FormBuilder);
  @Input()
  public weekName:string = "";
  @Input()
  public weekEnum:DayOfWeek = DayOfWeek.NONE;

  firstFormGroup = this._formBuilder.group({
    doWork: ['', Validators.required],
  });

  constructor(private dateJobService:DateService) {
  }

  secondFormGroup = this._formBuilder.group({
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

  protected notWorkingDay():void {
    this.secondFormGroup.reset()

    //TODO: enviar para api
  }

  protected createDayOfWeek(): void {
    if (this.secondFormGroup.valid) {
      const day: WeekCreateDTO = {
        dayOfWeek: this.weekEnum,
        scheduleInitial: this.formatTimeString(this.secondFormGroup.value.startWork!),
        scheduleFinal: this.formatTimeString(this.secondFormGroup.value.endWork!),
        breakInitial: this.formatTimeString(this.secondFormGroup.value.startOffHour!),
        breakFinal: this.formatTimeString(this.secondFormGroup.value.endOffHour!)
      }
      console.log(day)
      this.dateJobService.insertDateOfJob(day).subscribe();
    }

  }

  private formatTimeString(value: string): string {
    const cleanValue = value.replace(/\D/g, '');
    const hours = cleanValue.slice(0, 2);
    const minutes = cleanValue.slice(2, 4);
    return `${hours}:${minutes}`;
  }
}
