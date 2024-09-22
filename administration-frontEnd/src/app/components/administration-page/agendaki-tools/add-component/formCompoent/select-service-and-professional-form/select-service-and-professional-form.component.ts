import { Component } from '@angular/core';
import {MatDialogContent, MatDialogRef} from "@angular/material/dialog";
import {MatFormField, MatLabel} from "@angular/material/form-field";
import {MatOption} from "@angular/material/core";
import {MatSelect} from "@angular/material/select";
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {ComponentCommunicationService} from "../../../../../../services/component-communication.service";
import {CalendarComponent} from "../../../sections/calendar/calendar.component";
import {
  SelectServiceAndProfessionalComponent
} from "../../../sections/select-service-and-professional/select-service-and-professional.component";

@Component({
  selector: 'app-select-service-and-professional-form',
  standalone: true,
    imports: [
        MatDialogContent,
        MatFormField,
        MatLabel,
        MatOption,
        MatSelect,
        ReactiveFormsModule
    ],
  templateUrl: './select-service-and-professional-form.component.html',
  styleUrls: ['./select-service-and-professional-form.component.css','../styleFormComponent.css']
})
export class SelectServiceAndProfessionalFormComponent {
  protected formEditSelectServiceAndProfessional:FormGroup = new FormGroup<any>({
    horizontalAlignment: new FormControl('',Validators.required),
    type:new FormControl("professionalAndService")
  });

  constructor(private communicationComponent:ComponentCommunicationService, private dialogRef: MatDialogRef<SelectServiceAndProfessionalFormComponent>) {
  }

  submitForm():void{
    if(this.formEditSelectServiceAndProfessional.valid){
      this.communicationComponent.triggerAddComponentAction({
        component:SelectServiceAndProfessionalComponent,
        data:this.formEditSelectServiceAndProfessional.value
      })
      this.dialogRef.close();
    }
  }
}
