import {Component, inject} from '@angular/core';
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import {CommonModule} from '@angular/common'; // Importar CommonModule para NgIf e NgFor
import {MAT_DIALOG_DATA, MatDialogModule, MatDialogRef} from '@angular/material/dialog'; // Importar MatDialogModule para MatDialogClose, MatDialogActions, MatDialogContent
import {ComponentCommunicationService} from '../../../../../../services/component-communication.service';
import {InformationComponent} from '../../../sections/information/information.component';
import {MatInput} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatSelectModule} from '@angular/material/select';
import { DialogData } from '../form-input/form-input.component';

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
  readonly data = inject<DialogData>(MAT_DIALOG_DATA);

  inputValue: string = '';
  protected informationList: string[] = [];
  formEditInformation: FormGroup = new FormGroup({
    title: new FormControl('', Validators.required),
    content: new FormControl(this.informationList),
    type: new FormControl("help")
  });

  constructor(private communicationComponent: ComponentCommunicationService, private dialogRef: MatDialogRef<InformationFormComponent>) {
  }

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

    if (this.data) {
      this.dialogRef.close(this.formEditInformation.value);
    } else {
      this.communicationComponent.triggerAddComponentAction({
        component: InformationComponent,
        data: this.formEditInformation.value
      })

      this.dialogRef.close();
    }
  }
}
