import { Component, Type } from '@angular/core';
import { CdkDropList, CdkDrag, CdkDragPlaceholder, moveItemInArray, CdkDragDrop } from '@angular/cdk/drag-drop';
import { CommonModule } from '@angular/common';
import { InputComponent } from '../sections/input/input.component';
import {ɵEmptyOutletComponent} from "@angular/router";
import {DynamicComponentContainer} from "../dynamic-component-container/dynamic-component-container.component";
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
  styleUrls: ['./pre-visualizer.component.css'] // Corrigido de styleUrl para styleUrls
})
export class PreVisualizerComponent {
  components: ComponentWithId[] = [
    {component: InputComponent, data: { label: 'test', placeHolder: 'Enter text', width:"100%", horizontalAlignment:"start" } },
    {component: InputComponent, data: { label: 'test2', placeHolder: 'Enter more text', width:"100%",horizontalAlignment:"center" } },
  ];

  drop(event: CdkDragDrop<ComponentWithId[]>) {
    moveItemInArray(this.components, event.previousIndex, event.currentIndex);
    console.log(this.components)
  }
}
