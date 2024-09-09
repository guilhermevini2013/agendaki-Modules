import { Component } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common'; // Importar CommonModule para NgIf e NgFor
import { MatDialogModule } from '@angular/material/dialog'; // Importar MatDialogModule para MatDialogClose, MatDialogActions, MatDialogContent
import { ComponentCommunicationService } from '../../../../../../services/component-communication.service';
import { InformationComponent } from '../../../sections/information/information.component';

@Component({
  selector: 'app-information-form',
  standalone: true,
  imports: [
    CommonModule, // Importar CommonModule para usar NgIf e NgFor
    MatDialogModule, // Importar MatDialogModule para usar MatDialogClose, MatDialogActions, MatDialogContent
    ReactiveFormsModule,
    FormsModule
  ],
  templateUrl: './information-form.component.html',
  styleUrls: ['./information-form.component.css', '../styleFormComponent.css']
})
export class InformationFormComponent {
  protected informationList: string[] = [];
  inputValue: string = '';
  formEditInformation: FormGroup = new FormGroup({
    title: new FormControl(''),
    informationList: new FormControl(this.informationList)
  });

  constructor(private communicationComponent: ComponentCommunicationService) {}

  addInformation(): void {
    if (this.inputValue.trim()) {
      this.informationList.push(this.inputValue);
      this.inputValue = '';
    }
  }

  submitForm(): void {
    this.formEditInformation.patchValue({
      informationList: this.informationList
    });
    this.communicationComponent.triggerAddComponentAction({
      component: InformationComponent,
      data: this.formEditInformation.value
    });
  }
}
