import {Component, HostListener, Type, ViewChild, inject} from '@angular/core';
import {PreVisualizerComponent} from './pre-visualizer/pre-visualizer.component';
import {AddComponentComponent} from './add-component/add-component.component';
import { NgIf } from '@angular/common';
import { MatIcon, MatIconModule } from '@angular/material/icon';
import { MatButton, MatButtonModule } from '@angular/material/button';
import { SelectServiceAndProfessionalFormComponent } from './add-component/formCompoent/select-service-and-professional-form/select-service-and-professional-form.component';
import { PresentationFormComponent } from './add-component/formCompoent/presentation-form/presentation-form.component';
import { InformationFormComponent } from './add-component/formCompoent/information-form/information-form.component';
import { PortfolioFormComponent } from './add-component/formCompoent/portfolio-form/portfolio-form.component';
import { CalendarFormComponent } from './add-component/formCompoent/calendar-form/calendar-form.component';
import { PerfilFormComponent } from './add-component/formCompoent/perfil-form/perfil-form.component';
import { FormInputComponent } from './add-component/formCompoent/form-input/form-input.component';
import { MatDialog } from '@angular/material/dialog';
import { AddComponentFormComponent } from './add-component/formCompoent/add-component-form/add-component-form.component';

@Component({
  selector: 'app-agendaki-tools',
  standalone: true,
  imports: [PreVisualizerComponent, AddComponentComponent, NgIf, MatIconModule, MatButtonModule],
  templateUrl: './agendaki-tools.component.html',
  styleUrl: './agendaki-tools.component.css'
})
export class AgendakiToolsComponent {
  @ViewChild(PreVisualizerComponent) preVisualizerComponent!: PreVisualizerComponent;

  readonly dialog = inject(MatDialog);
  protected readonly AddComponentFormComponent = AddComponentFormComponent;

  openDialog(form: Type<any>) {
    this.dialog.open(form);
  }

  getJsonForTemplateByPreVisualizer() {
    return this.preVisualizerComponent.getJsonForTemplate();
  }

  screenSize: number = 0;

  constructor() {
    this.checkScreenSize(window.innerWidth);
  }

  @HostListener('window:resize', ['$event'])
  onResize(event: Event) {
    this.checkScreenSize((event.target as Window).innerWidth);
  }

  checkScreenSize(width: number) {
    this.screenSize = width;
  }
}
