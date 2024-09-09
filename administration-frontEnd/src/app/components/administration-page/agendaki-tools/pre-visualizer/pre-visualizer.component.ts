import { Component, Type } from '@angular/core';
import { CdkDropList, CdkDrag, CdkDragPlaceholder, moveItemInArray, CdkDragDrop } from '@angular/cdk/drag-drop';
import { CommonModule } from '@angular/common';
import { InputComponent } from '../sections/input/input.component';
import {ɵEmptyOutletComponent} from "@angular/router";
import {DynamicComponentContainer} from "../dynamic-component-container/dynamic-component-container.component";
import { PerfilComponent } from '../sections/perfil/perfil.component';
import { CalendarComponent } from '../sections/calendar/calendar.component';
import { ComponentCommunicationService } from '../../../../services/component-communication.service';
import {
  SelectServiceAndProfessionalComponent
} from "../sections/select-service-and-professional/select-service-and-professional.component";
import {PortfolioComponent} from "../sections/portfolio/portfolio.component";
export interface ComponentWithId {
  component: Type<any>;
  data: any;

}
@Component({
  selector: 'app-pre-visualizer',
  standalone: true,
  imports: [
    CdkDropList,
    CdkDrag,
    CdkDragPlaceholder,
    CommonModule,
    ɵEmptyOutletComponent,
    DynamicComponentContainer,
  ],
  templateUrl: './pre-visualizer.component.html',
  styleUrls: ['./pre-visualizer.component.css']
})
export class PreVisualizerComponent {
  components: ComponentWithId[] = [
  ];

  drop(event: CdkDragDrop<ComponentWithId[]>) {
    moveItemInArray(this.components, event.previousIndex, event.currentIndex);
  }
  constructor(private commService: ComponentCommunicationService) {}

  ngOnInit() {
    this.commService.addComponentAction$.subscribe(action => {
      this.components.push(action)
    });
  }
}
