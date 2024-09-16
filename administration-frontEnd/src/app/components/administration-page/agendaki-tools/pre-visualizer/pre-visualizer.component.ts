import { Component, Type } from '@angular/core';
import {
  CdkDropList,
  CdkDrag,
  CdkDragPlaceholder,
  moveItemInArray,
  CdkDragDrop,
  CdkDragHandle,
  CdkDragStart
} from '@angular/cdk/drag-drop';
import { CommonModule } from '@angular/common';
import {ɵEmptyOutletComponent} from "@angular/router";
import {DynamicComponentContainer} from "../dynamic-component-container/dynamic-component-container.component";
import { ComponentCommunicationService } from '../../../../services/component-communication.service';
import { PresentationComponent } from '../sections/presentation/presentation.component';
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
    CdkDragHandle,
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
  protected primaryColor:string | null = "#ffff";

  drop(event: CdkDragDrop<ComponentWithId[]>) {
    moveItemInArray(this.components, event.previousIndex, event.currentIndex);
  }
  constructor(private commService: ComponentCommunicationService) {}

  ngOnInit() {
    this.commService.addComponentAction$.subscribe(action => {
      this.components.push(action)
    });

    this.commService.colorPrimaryAction$.subscribe(action =>{
      this.primaryColor = action;
    })
  }

  onDragStarted(event: CdkDragStart) {
    const placeholderElement = event.source.getPlaceholderElement();
    const sourceElement = event.source.element.nativeElement;
    placeholderElement.style.height = `${sourceElement.clientHeight}px`;
  }
}
