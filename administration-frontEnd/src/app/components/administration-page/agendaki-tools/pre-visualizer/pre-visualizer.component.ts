import { Component, Type } from '@angular/core';
import { CdkDropList, CdkDrag, CdkDragPlaceholder, moveItemInArray, CdkDragDrop } from '@angular/cdk/drag-drop';
import { CommonModule } from '@angular/common';
import { InputComponent } from '../sections/input/input.component';
export interface ComponentWithId {
  id: string;
  component: Type<any>;
}
@Component({
  selector: 'app-pre-visualizer',
  standalone: true,
  imports: [
    CdkDropList,
    CdkDrag,
    CdkDragPlaceholder,
    CommonModule, 
  ],
  templateUrl: './pre-visualizer.component.html',
  styleUrls: ['./pre-visualizer.component.css'] // Corrigido de styleUrl para styleUrls
})
export class PreVisualizerComponent {
  components: ComponentWithId[] = [
    { id: 'input', component: InputComponent },
    { id: 'input2', component: InputComponent },
  ];

  drop(event: CdkDragDrop<Type<any>[]>) {
    moveItemInArray(this.components, event.previousIndex, event.currentIndex);
    console.log(this.components);
    
  }
}
