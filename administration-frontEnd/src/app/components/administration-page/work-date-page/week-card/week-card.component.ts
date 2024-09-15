import {Component, inject, Input, OnInit} from '@angular/core';
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
import {NgxSpinnerModule} from 'ngx-spinner';
import {DayOfWeek} from '../../../../models/dateJob/dayOfWeek';
import {WeekCreateDTO} from '../../../../models/dateJob/week-create-dto';
import {DateJobService} from '../../../../services/dateJob/date-job.service';
import {DateJobReadDTO} from '../../../../models/dateJob/date-job-read-DTO';
import {PopUpService} from "../../../../services/pop-up.service";

@Component({
  selector: 'app-week-card',
  standalone: true,
  imports: [
    CommonModule,
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
  styleUrls: ['./week-card.component.css']
})
export class WeekCardComponent implements OnInit {
  private _formBuilder = inject(FormBuilder);
  public weekName: string = "";
  @Input()
  public weekEnum: DayOfWeek = DayOfWeek.NONE;

  @Input()
  public dayOfWeekInf: DateJobReadDTO | null = null;

  firstFormGroup = this._formBuilder.group({
    doWork: ['', Validators.required],
  });

  secondFormGroup = this._formBuilder.group({
    startWork: ['', Validators.required],
    endWork: ['', Validators.required],
    startOffHour: ['', Validators.required],
    endOffHour: ['', Validators.required],
  })

  constructor(private dateJobService: DateJobService, private popUpService: PopUpService) {
  }

  ngOnInit(): void {
    if (this.dayOfWeekInf) {
      this.firstFormGroup.patchValue({
        doWork: this.dayOfWeekInf.isOpen ? '1' : '2',
      });

      this.secondFormGroup.patchValue({
        startWork: this.dayOfWeekInf.scheduleInitial,
        endWork: this.dayOfWeekInf.scheduleFinal,
        startOffHour: this.dayOfWeekInf.breakInitial,
        endOffHour: this.dayOfWeekInf.breakFinal,
      });
    }

    switch (this.weekEnum) {
      case DayOfWeek.FRIDAY:
        this.weekName = "Sexta";
        break;
      case DayOfWeek.MONDAY:
        this.weekName = "Segunda";
        break;
      case DayOfWeek.SATURDAY:
        this.weekName = "Sábado";
        break;
      case DayOfWeek.SUNDAY:
        this.weekName = "Domingo";
        break;
      case DayOfWeek.THURSDAY:
        this.weekName = "Quinta";
        break;
      case DayOfWeek.TUESDAY:
        this.weekName = "Terça";
        break;
      case DayOfWeek.WEDNESDAY:
        this.weekName = "Quarta";
        break;
    }
  }

  protected checkIfWork(): string {
    const value = this.firstFormGroup.value?.doWork;
    if (typeof value === 'string') {
      return value;
    } else {
      console.log(typeof value);
      return '0';
    }
  }

  protected alterDayOfWeek(isOpen: boolean): void {
    if (this.secondFormGroup.valid) {
      const day: WeekCreateDTO = {
        dayOfWeek: this.weekEnum,
        scheduleInitial: this.formatTimeString(this.secondFormGroup.value.startWork!),
        scheduleFinal: this.formatTimeString(this.secondFormGroup.value.endWork!),
        breakInitial: this.formatTimeString(this.secondFormGroup.value.startOffHour!),
        breakFinal: this.formatTimeString(this.secondFormGroup.value.endOffHour!),
        isOpen: isOpen
      }
      this.dateJobService.insertDateOfJob(day).subscribe(next => {
        isOpen ? this.popUpService.openPopUp(this.weekName + " alterado com sucesso!", "check") : this.popUpService.openPopUp(this.weekName + " alterado para folga!", "check");
      });
    }
  }

  private formatTimeString(value: string): string {
    const cleanValue = value.replace(/\D/g, '');
    const hours = cleanValue.slice(0, 2);
    const minutes = cleanValue.slice(2, 4);
    return `${hours}:${minutes}`;
  }
}
