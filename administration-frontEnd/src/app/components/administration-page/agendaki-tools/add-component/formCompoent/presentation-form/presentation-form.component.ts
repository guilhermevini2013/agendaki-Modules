import { Component } from '@angular/core';
import {MatDialogActions, MatDialogClose, MatDialogContent} from "@angular/material/dialog";
import {FormControl, FormGroup, ReactiveFormsModule} from "@angular/forms";
import {NgIf} from "@angular/common";
import { MatInput } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { ComponentCommunicationService } from '../../../../../../services/component-communication.service';
import { PresentationComponent } from '../../../sections/presentation/presentation.component';
@Component({
  selector: 'app-presentation-form',
  standalone: true,
  imports: [
    MatDialogClose,
    MatDialogActions,
    MatDialogContent,
    ReactiveFormsModule,
    MatSelectModule,
    MatFormFieldModule,
    MatInput,
    NgIf
  ],
  templateUrl: './presentation-form.component.html',
  styleUrl: './presentation-form.component.css'
})
export class PresentationFormComponent {
  selectedImage: string | ArrayBuffer | null = null;

  formEditPerfil: FormGroup = new FormGroup({
    urlPhoto: new FormControl(this.selectedImage!),
    title: new FormControl(''),
    text: new FormControl('')
  });

  constructor(private communicationComponent: ComponentCommunicationService) {}

  onFileSelected(event: Event): void {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files![0]) {
      const file = input.files![0];
      const reader = new FileReader();
      reader.onload = (e: ProgressEvent<FileReader>) => {
        this.selectedImage = reader.result;
        this.formEditPerfil.patchValue({
          urlPhoto: this.selectedImage
        });
      };
      reader.readAsDataURL(file);
    }
  }

  submitForm(): void {
    this.communicationComponent.triggerAddComponentAction({
      component: PresentationComponent,
      data: this.formEditPerfil.value
    });
  }
}
