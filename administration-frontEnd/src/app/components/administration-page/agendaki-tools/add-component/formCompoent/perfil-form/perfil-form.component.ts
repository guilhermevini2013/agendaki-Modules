import { Component } from '@angular/core';
import {MatDialogActions, MatDialogClose, MatDialogContent} from "@angular/material/dialog";
import {FormControl, FormGroup, ReactiveFormsModule} from "@angular/forms";
import {NgIf} from "@angular/common";
import {ComponentCommunicationService} from "../../../../../../services/component-communication.service";
import {PerfilComponent} from "../../../sections/perfil/perfil.component";

@Component({
  selector: 'app-perfil-form',
  standalone: true,
  imports: [
    MatDialogClose,
    MatDialogActions,
    MatDialogContent,
    ReactiveFormsModule,
    NgIf
  ],
  templateUrl: './perfil-form.component.html',
  styleUrls: ['./perfil-form.component.css','../styleFormComponent.css']
})
export class PerfilFormComponent {
  selectedImage: string | ArrayBuffer | null = null;

  formEditPerfil: FormGroup = new FormGroup({
    urlPhotoPerfil: new FormControl(this.selectedImage!),
    bio: new FormControl(''),
    horizontalAlignment: new FormControl('')
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
          urlPhotoPerfil: this.selectedImage
        });
      };
      reader.readAsDataURL(file);
    }
  }

  submitForm(): void {
    this.communicationComponent.triggerAddComponentAction({
      component: PerfilComponent,
      data: this.formEditPerfil.value
    });
  }
}