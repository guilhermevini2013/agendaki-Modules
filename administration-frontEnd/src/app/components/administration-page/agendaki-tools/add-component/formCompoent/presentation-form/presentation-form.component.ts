import {Component, inject} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogActions, MatDialogClose, MatDialogContent, MatDialogRef} from "@angular/material/dialog";
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {NgIf} from "@angular/common";
import {MatInput} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatSelectModule} from '@angular/material/select';
import {ComponentCommunicationService} from '../../../../../../services/component-communication.service';
import {PresentationComponent} from '../../../sections/presentation/presentation.component';
import { DialogData } from '../form-input/form-input.component';

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
  styleUrls: ['./presentation-form.component.css', '../styleFormComponent.css']
})
export class PresentationFormComponent {
  readonly data = inject<DialogData>(MAT_DIALOG_DATA);
  selectedImage: string | ArrayBuffer | null = null;

  formEditPerfil: FormGroup = new FormGroup({
    imageToBase64: new FormControl(this.selectedImage!, Validators.required),
    text: new FormControl('', Validators.required),
    paragraph: new FormControl('', Validators.required),
    type: new FormControl("presentation")
  });

  constructor(private communicationComponent: ComponentCommunicationService, private dialogRef: MatDialogRef<PresentationFormComponent>) {
  }

  onFileSelected(event: Event): void {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files![0]) {
      const file = input.files![0];
      const reader = new FileReader();
      reader.onload = (e: ProgressEvent<FileReader>) => {
        this.selectedImage = reader.result;
        this.formEditPerfil.patchValue({
          imageToBase64: this.selectedImage
        });
      };
      reader.readAsDataURL(file);
    }
  }

  submitForm(): void {
    if (this.selectedImage != null) {
      if (this.formEditPerfil.valid) {
        if (this.data) {
          this.dialogRef.close(this.formEditPerfil.value);
        } else {
          this.communicationComponent.triggerAddComponentAction({
            component: PresentationComponent,
            data: this.formEditPerfil.value
          })
  
          this.dialogRef.close();
        }
      }
    }
  }
}
