import {Component, inject, OnInit, Type, ViewChild} from '@angular/core';
import { ComponentCommunicationService } from '../../../../services/component-communication.service';
import {MatDialog} from "@angular/material/dialog";
import {FormInputComponent} from "./formCompoent/form-input/form-input.component";
import {PerfilFormComponent} from "./formCompoent/perfil-form/perfil-form.component";
import {CalendarComponent} from "../sections/calendar/calendar.component";
import {CalendarFormComponent} from "./formCompoent/calendar-form/calendar-form.component";
import {PortfolioFormComponent} from "./formCompoent/portfolio-form/portfolio-form.component";
import { InformationFormComponent } from './formCompoent/information-form/information-form.component';
import { FormsModule } from '@angular/forms';
import { PresentationFormComponent } from './formCompoent/presentation-form/presentation-form.component';
import {
  SelectServiceAndProfessionalFormComponent
} from "./formCompoent/select-service-and-professional-form/select-service-and-professional-form.component";
import {PreVisualizerComponent} from "../pre-visualizer/pre-visualizer.component";
import {AgendakiToolsComponent} from "../agendaki-tools.component";
import {TemplateService} from "../../../../services/template/template.service";

@Component({
  selector: 'app-add-component',
  standalone: true,
  imports: [
    FormsModule
  ],
  templateUrl: './add-component.component.html',
  styleUrl: './add-component.component.css'
})
export class AddComponentComponent{
  @ViewChild(PreVisualizerComponent) preVisualizerComponent!: PreVisualizerComponent;

  colorPrimary:string | null=null;
  colorSecundary:string | null=null;
  colorTerciary:string | null=null;

  constructor(private commService: ComponentCommunicationService, private parent: AgendakiToolsComponent, private templateService:TemplateService) {}
  readonly dialog = inject(MatDialog);
  openDialog(form:Type<any>) {
    this.dialog.open(form);
  }

  saveColors():void{
      this.commService.triggerColorPrimary(this.colorPrimary!);
      this.commService.triggerColorSecundary(this.colorSecundary!);
  }

  saveComponents() {
    this.parent.saveComponents();

  }

  protected readonly FormInputComponent = FormInputComponent;
  protected readonly PerfilFormComponent = PerfilFormComponent;
  protected readonly CalendarFormComponent = CalendarFormComponent;
  protected readonly PortfolioFormComponent = PortfolioFormComponent;
  protected readonly InformationFormComponent = InformationFormComponent;
  protected readonly PresentationFormComponent = PresentationFormComponent;
  protected readonly SelectServiceAndProfessionalFormComponent = SelectServiceAndProfessionalFormComponent;
}
