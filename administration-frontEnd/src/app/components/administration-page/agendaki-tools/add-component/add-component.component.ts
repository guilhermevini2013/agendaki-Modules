import {Component, inject, OnInit, Type} from '@angular/core';
import { ComponentCommunicationService } from '../../../../services/component-communication.service';
import {MatDialog} from "@angular/material/dialog";
import {FormInputComponent} from "./formCompoent/form-input/form-input.component";
import {PerfilFormComponent} from "./formCompoent/perfil-form/perfil-form.component";
import {CalendarComponent} from "../sections/calendar/calendar.component";
import {CalendarFormComponent} from "./formCompoent/calendar-form/calendar-form.component";
import {PortfolioFormComponent} from "./formCompoent/portfolio-form/portfolio-form.component";
import { InformationFormComponent } from './formCompoent/information-form/information-form.component';

@Component({
  selector: 'app-add-component',
  standalone: true,
  imports: [],
  templateUrl: './add-component.component.html',
  styleUrl: './add-component.component.css'
})
export class AddComponentComponent implements OnInit{
  constructor(private commService: ComponentCommunicationService) {}
  readonly dialog = inject(MatDialog);
  openDialog(form:Type<any>) {
    this.dialog.open(form);
  }
  public totalInput:number = 0;

  ngOnInit(): void {
    this.commService.countComponentAction$.subscribe(() =>{
      this.totalInput++;
    })
  }

  protected readonly FormInputComponent = FormInputComponent;
  protected readonly PerfilFormComponent = PerfilFormComponent;
  protected readonly CalendarComponent = CalendarComponent;
  protected readonly CalendarFormComponent = CalendarFormComponent;
  protected readonly PortfolioFormComponent = PortfolioFormComponent;
  protected readonly InformationFormComponent = InformationFormComponent;
}
