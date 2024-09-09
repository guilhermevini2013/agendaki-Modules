import { Component } from '@angular/core';
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatDialogClose, MatDialogContent} from "@angular/material/dialog";
import {ComponentCommunicationService} from "../../../../../../services/component-communication.service";
import {CalendarComponent} from "../../../sections/calendar/calendar.component";

@Component({
  selector: 'app-calendar-form',
  standalone: true,
  imports: [
    FormsModule,
    MatDialogClose,
    MatDialogContent,
    ReactiveFormsModule
  ],
  templateUrl: './calendar-form.component.html',
  styleUrls: ['./calendar-form.component.css','../styleFormComponent.css']
})
export class CalendarFormComponent {

  protected formEditCalendar:FormGroup = new FormGroup<any>({
    horizontalAlignment: new FormControl('')
  });

  constructor(private communicationComponent:ComponentCommunicationService) {
  }

  submitForm():void{
    this.communicationComponent.triggerAddComponentAction({
      component:CalendarComponent,
      data:this.formEditCalendar.value
    })
  }
}