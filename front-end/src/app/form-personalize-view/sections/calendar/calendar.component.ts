import {Component, EventEmitter, Input, model, Output} from '@angular/core';
import {MatCardModule} from '@angular/material/card';
import {MAT_DATE_LOCALE, provideNativeDateAdapter} from '@angular/material/core';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {NgStyle} from "@angular/common";
import {IMessageSender} from "../IMessageSender";
import {FormsModule} from "@angular/forms";
import {DateService} from "../../../service/date.service";
import {FormPersonalizeViewComponent} from "../../form-personalize-view.component";

@Component({
  selector: 'app-calendar',
  standalone: true,
  imports: [MatCardModule, MatDatepickerModule, NgStyle,FormsModule],
  providers: [provideNativeDateAdapter(), {provide: MAT_DATE_LOCALE, useValue: 'pt-BR'}],
  templateUrl: './calendar.component.html',
  styleUrl: './calendar.component.css'
})
export class CalendarComponent extends IMessageSender{
  @Input() public id:number = 0;
  selected = model<Date | null>(new Date());
  public horizontalAlignment: string = "";
  selectedDate: Date = new Date();
  @Output() dateSelected = new EventEmitter<Date>();
  constructor(private dateService: DateService) {
    super();}

  sendValue(): any {
    FormPersonalizeViewComponent.jsonToSend = {
      ...FormPersonalizeViewComponent.jsonToSend,
      date: this.selectedDate.toLocaleDateString()
    };
    return {value: this.selectedDate, id: this.id ,type: "NoObj"};
  }

  onDateChange(date: Date | null): void {
    this.selectedDate = date!;
    this.dateService.setSelectedDate(this.selectedDate);

  }
}
