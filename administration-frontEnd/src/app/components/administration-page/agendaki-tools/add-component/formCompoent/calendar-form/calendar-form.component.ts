import {Component, inject} from '@angular/core';
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {MAT_DIALOG_DATA, MatDialogClose, MatDialogContent, MatDialogRef} from "@angular/material/dialog";
import {ComponentCommunicationService} from "../../../../../../services/component-communication.service";
import {CalendarComponent} from "../../../sections/calendar/calendar.component";
import {MatSelectModule} from '@angular/material/select';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInput} from '@angular/material/input';
import { DialogData } from '../form-input/form-input.component';

@Component({
  selector: 'app-calendar-form',
  standalone: true,
  imports: [
    FormsModule,
    MatDialogClose,
    MatDialogContent,
    ReactiveFormsModule,
    MatSelectModule,
    MatFormFieldModule,
    MatInput,
  ],
  templateUrl: './calendar-form.component.html',
  styleUrls: ['./calendar-form.component.css', '../styleFormComponent.css']
})
export class CalendarFormComponent {
  readonly data = inject<DialogData>(MAT_DIALOG_DATA);

  protected formEditCalendar: FormGroup = new FormGroup<any>({
    horizontalAlignment: new FormControl('', Validators.required),
    type: new FormControl("calendar")
  });

  constructor(private communicationComponent: ComponentCommunicationService, private dialogRef: MatDialogRef<CalendarFormComponent>) {
  }

  submitForm(): void {
    if (this.formEditCalendar.valid) {
      if (this.data) {
        this.dialogRef.close(this.formEditCalendar.value);
      } else {
        this.communicationComponent.triggerAddComponentAction({
          component: CalendarComponent,
          data: this.formEditCalendar.value
        })

        this.dialogRef.close();
      }
    }
  }
}
