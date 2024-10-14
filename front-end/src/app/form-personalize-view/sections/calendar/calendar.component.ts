import {Component, model} from '@angular/core';
import {MatCardModule} from '@angular/material/card';
import {MAT_DATE_LOCALE, provideNativeDateAdapter} from '@angular/material/core';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {NgStyle} from "@angular/common";
import {IMessageSender} from "../IMessageSender";
import {FormsModule} from "@angular/forms";

@Component({
  selector: 'app-calendar',
  standalone: true,
  imports: [MatCardModule, MatDatepickerModule, NgStyle,FormsModule],
  providers: [provideNativeDateAdapter(), {provide: MAT_DATE_LOCALE, useValue: 'pt-BR'}],
  templateUrl: './calendar.component.html',
  styleUrl: './calendar.component.css'
})
export class CalendarComponent extends IMessageSender{
  selected = model<Date | null>(new Date());
  public horizontalAlignment: string = "";
  selectedDate: Date = new Date();

  sendValue(): any {
    return this.selectedDate.toLocaleDateString();
  }

}
