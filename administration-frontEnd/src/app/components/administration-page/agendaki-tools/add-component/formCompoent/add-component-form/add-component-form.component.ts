import { Component, Type, ViewChild, inject } from '@angular/core';
import {MatDialog, MatDialogActions, MatDialogClose, MatDialogContent, MatDialogModule, MatDialogRef, MatDialogTitle} from "@angular/material/dialog";
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {ComponentCommunicationService} from "../../../../../../services/component-communication.service";
import {InputComponent} from "../../../sections/input/input.component";
import {MatSliderModule} from '@angular/material/slider';
import {MatSelectModule} from '@angular/material/select';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInput, MatInputModule} from '@angular/material/input';
import { FormInputComponent } from '../form-input/form-input.component';
import { PerfilFormComponent } from '../perfil-form/perfil-form.component';
import { CalendarFormComponent } from '../calendar-form/calendar-form.component';
import { PortfolioFormComponent } from '../portfolio-form/portfolio-form.component';
import { InformationFormComponent } from '../information-form/information-form.component';
import { PresentationFormComponent } from '../presentation-form/presentation-form.component';
import { SelectServiceAndProfessionalFormComponent } from '../select-service-and-professional-form/select-service-and-professional-form.component';
import { AgendakiToolsComponent } from '../../../agendaki-tools.component';
import { TemplateService } from '../../../../../../services/template/template.service';
import { PreVisualizerComponent } from '../../../pre-visualizer/pre-visualizer.component';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-add-component-form',
  standalone: true,
  imports: [
    MatFormFieldModule,
    MatInputModule,
    FormsModule,
    MatButtonModule,
    MatDialogTitle,
    MatDialogContent,
    MatDialogActions,
    MatDialogClose,
    FormsModule,
    MatDialogModule
  ],
  templateUrl: './add-component-form.component.html',
  styleUrl: './add-component-form.component.css'
})
export class AddComponentFormComponent {
  @ViewChild(PreVisualizerComponent) preVisualizerComponent!: PreVisualizerComponent;


  readonly dialog = inject(MatDialog);

  protected readonly FormInputComponent = FormInputComponent;
  protected readonly PerfilFormComponent = PerfilFormComponent;
  protected readonly CalendarFormComponent = CalendarFormComponent;
  protected readonly PortfolioFormComponent = PortfolioFormComponent;
  protected readonly InformationFormComponent = InformationFormComponent;
  protected readonly PresentationFormComponent = PresentationFormComponent;
  protected readonly SelectServiceAndProfessionalFormComponent = SelectServiceAndProfessionalFormComponent;

  constructor(private communicationComponent: ComponentCommunicationService, private dialogRef: MatDialogRef<FormInputComponent>) {
  }

  openDialog(form: Type<any>) {
    this.dialog.open(form);
  }

}
