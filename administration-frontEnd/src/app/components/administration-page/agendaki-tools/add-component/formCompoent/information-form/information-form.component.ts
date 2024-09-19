import { Component } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common'; // Importar CommonModule para NgIf e NgFor
import { MatDialogModule, MatDialogRef } from '@angular/material/dialog'; // Importar MatDialogModule para MatDialogClose, MatDialogActions, MatDialogContent
import { ComponentCommunicationService } from '../../../../../../services/component-communication.service';
import { InformationComponent } from '../../../sections/information/information.component';
import { MatInput } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';

@Component({
  selector: 'app-information-form',
  standalone: true,
  imports: [
    CommonModule, // Importar CommonModule para usar NgIf e NgFor
    MatDialogModule, // Importar MatDialogModule para usar MatDialogClose, MatDialogActions, MatDialogContent
    ReactiveFormsModule,
    FormsModule,
    MatSelectModule,
    MatFormFieldModule,
    MatInput,
  ],
  templateUrl: './information-form.component.html',
  styleUrls: ['./information-form.component.css', '../styleFormComponent.css']
})
export class InformationFormComponent {
  protected informationList: string[] = [];
  inputValue: string = '';
  formEditInformation: FormGroup = new FormGroup({
    title: new FormControl('',Validators.required),
    content: new FormControl(this.informationList),
    typeSection: new FormControl("CALENDAR"),
    type:new FormControl("calendar")
  });

  constructor(private communicationComponent: ComponentCommunicationService, private dialogRef: MatDialogRef<InformationFormComponent>) {}

  addInformation(): void {
    if (this.inputValue.trim()) {
      this.informationList.push(this.inputValue);
      this.inputValue = '';
    }
  }

  submitForm(): void {
      this.formEditInformation.patchValue({
        content: this.informationList
      });
      this.communicationComponent.triggerAddComponentAction({
        component: InformationComponent,
        data: this.formEditInformation.value
      });
      this.dialogRef.close();
  }
}
