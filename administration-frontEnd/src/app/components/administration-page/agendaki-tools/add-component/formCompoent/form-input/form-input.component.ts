import { Component } from '@angular/core';
import {MatDialogActions, MatDialogClose, MatDialogContent} from "@angular/material/dialog";
import {FormControl, FormGroup, ReactiveFormsModule} from "@angular/forms";
import {ComponentCommunicationService} from "../../../../../../services/component-communication.service";
import {InputComponent} from "../../../sections/input/input.component";
import {MatSliderModule} from '@angular/material/slider';
import {MatSelectModule} from '@angular/material/select';
import {MatFormFieldModule} from '@angular/material/form-field';
import { MatInput } from '@angular/material/input';

@Component({
  selector: 'app-form-input',
  standalone: true,
  imports: [
    MatDialogClose,
    MatDialogActions,
    MatDialogContent,
    ReactiveFormsModule,

    MatSliderModule,

    MatSelectModule,
    MatFormFieldModule,
    MatInput,
  ],
  templateUrl: './form-input.component.html',
  styleUrls: ['./form-input.component.css', '../styleFormComponent.css']
})
export class FormInputComponent {
  alignSelected:string = "center";

  formEditInput:FormGroup = new FormGroup<any>({
    label: new FormControl(''),
    placeHolder: new FormControl(''),
    width: new FormControl(''),
    horizontalAlignment: new FormControl('')
    
  });

  constructor(private communicationComponent:ComponentCommunicationService) {
  }

  submitForm():void{
    if (this.formEditInput.value.width == "") {
      this.formEditInput.value.width = 50
    } 

    this.formEditInput.value.width +="%";

    this.communicationComponent.triggerAddComponentAction({
      component:InputComponent,
      data: this.formEditInput.value
    })
  }
}
