import {Component} from '@angular/core';
import {MatDialogActions, MatDialogClose, MatDialogContent, MatDialogRef} from "@angular/material/dialog";
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {ComponentCommunicationService} from "../../../../../../services/component-communication.service";
import {InputComponent} from "../../../sections/input/input.component";
import {MatSliderModule} from '@angular/material/slider';
import {MatSelectModule} from '@angular/material/select';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInput} from '@angular/material/input';

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
  alignSelected: string = "center";

  formEditInput: FormGroup = new FormGroup<any>({
    label: new FormControl('', Validators.required),
    placeHolder: new FormControl('', Validators.required),
    width: new FormControl('', Validators.required),
    horizontalAlignment: new FormControl('', Validators.required),
    type: new FormControl("input")
  });

  constructor(private communicationComponent: ComponentCommunicationService, private dialogRef: MatDialogRef<FormInputComponent>) {
  }

  submitForm(): void {
    if (this.formEditInput.value.width == "") {
      this.formEditInput.value.width = 50
    }

    this.formEditInput.value.width += "%";

    if (this.formEditInput.valid) {
      this.communicationComponent.triggerAddComponentAction({
        component: InputComponent,
        data: this.formEditInput.value
      })
      this.dialogRef.close();
    }
  }
}
