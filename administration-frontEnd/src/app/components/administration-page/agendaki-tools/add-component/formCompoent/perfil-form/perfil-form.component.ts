import { Component } from '@angular/core';
import {MatDialogActions, MatDialogClose, MatDialogContent, MatDialogRef} from "@angular/material/dialog";
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {NgIf} from "@angular/common";
import {ComponentCommunicationService} from "../../../../../../services/component-communication.service";
import {PerfilComponent} from "../../../sections/perfil/perfil.component";
import { MatInput } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';

@Component({
  selector: 'app-perfil-form',
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
  templateUrl: './perfil-form.component.html',
  styleUrls: ['./perfil-form.component.css','../styleFormComponent.css']
})
export class PerfilFormComponent {
  selectedImage: string | ArrayBuffer | null = null;

  formEditPerfil: FormGroup = new FormGroup({
    imageToBase64: new FormControl(this.selectedImage!,Validators.required),
    text: new FormControl('',Validators.required),
    horizontalAlignment: new FormControl(''),
    type:new FormControl("profile")
  });

  constructor(private communicationComponent: ComponentCommunicationService,private dialogRef: MatDialogRef<PerfilFormComponent>) {}

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
    if(this.selectedImage != null){
      if(this.formEditPerfil.valid){
        this.communicationComponent.triggerAddComponentAction({
          component: PerfilComponent,
          data: this.formEditPerfil.value
        });
        this.dialogRef.close();
      }
    }
  }
}
