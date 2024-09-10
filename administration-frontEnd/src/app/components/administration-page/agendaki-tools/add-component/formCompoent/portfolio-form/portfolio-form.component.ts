import { Component } from '@angular/core';
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatDialogClose, MatDialogContent} from "@angular/material/dialog";
import {ComponentCommunicationService} from "../../../../../../services/component-communication.service";
import {PerfilComponent} from "../../../sections/perfil/perfil.component";
import {NgForOf} from "@angular/common";
import {PortfolioComponent} from "../../../sections/portfolio/portfolio.component";
import { MatSelectModule } from '@angular/material/select';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInput } from '@angular/material/input';

@Component({
  selector: 'app-portfolio-form',
  standalone: true,
  imports: [
    FormsModule,
    MatDialogClose,
    MatDialogContent,
    ReactiveFormsModule,
    NgForOf,
    MatSelectModule,
    MatFormFieldModule,
    MatInput,
  ],
  templateUrl: './portfolio-form.component.html',
  styleUrls: ['./portfolio-form.component.css','../styleFormComponent.css']
})
export class PortfolioFormComponent {
  selectedFiles: { name: string, base64: string }[] = [];

  formEditPortfolio: FormGroup = new FormGroup({
    urlsPhotos: new FormControl<string[]>([]),
    title: new FormControl('')
  });

  constructor(private communicationComponent: ComponentCommunicationService) {}

  onFileSelected(event: Event): void {
    const input = event.target as HTMLInputElement;
    if (input.files) {
      const files = Array.from(input.files);
      const fileReaders: FileReader[] = [];
      const newFiles: { name: string, base64: string }[] = [];

      files.forEach(file => {
        const reader = new FileReader();
        reader.onload = (e: ProgressEvent<FileReader>) => {
          if (reader.result) {
            newFiles.push({
              name: file.name,
              base64: reader.result as string
            });

            if (newFiles.length === files.length) {
              this.selectedFiles = [...this.selectedFiles, ...newFiles];
              this.formEditPortfolio.patchValue({
                urlsPhotos: this.selectedFiles.map(file => file.base64)
              });
            }
          }
        };
        reader.readAsDataURL(file);
        fileReaders.push(reader);
      });
    }
  }

  submitForm(): void {
    console.log(this.formEditPortfolio.value.urlsPhotos);
    this.communicationComponent.triggerAddComponentAction({
      component: PortfolioComponent,
      data: this.formEditPortfolio.value
    });
  }
}
