import { Component } from '@angular/core';
import {MatDialogActions, MatDialogClose, MatDialogContent} from "@angular/material/dialog";
import {FormControl, FormGroup, ReactiveFormsModule} from "@angular/forms";
import {ComponentCommunicationService} from "../../../../../../services/component-communication.service";
import {InputComponent} from "../../../sections/input/input.component";

@Component({
  selector: 'app-form-input',
  standalone: true,
  imports: [
    MatDialogClose,
    MatDialogActions,
    MatDialogContent,
    ReactiveFormsModule
  ],
  templateUrl: './form-input.component.html',
  styleUrls: ['./form-input.component.css', '../styleFormComponent.css']
})
export class FormInputComponent {

  formEditInput:FormGroup = new FormGroup<any>({
    label: new FormControl(''),
    placeHolder: new FormControl(''),
    width: new FormControl(''),
    horizontalAlignment: new FormControl('')
  });

  constructor(private communicationComponent:ComponentCommunicationService) {
  }

  submitForm():void{
    console.log(this.formEditInput.value)

    this.communicationComponent.triggerAddComponentAction({
      component:InputComponent,
      data: this.formEditInput.value
    })
  }
}
