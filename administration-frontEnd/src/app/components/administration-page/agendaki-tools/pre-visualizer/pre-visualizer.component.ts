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
      this.addComponent(action);
    });
  }

  addComponent(type: string) {
    switch (type) {
      case 'perfil':
        this.components.push({component: PerfilComponent, data:{ urlPhotoPerfil: 'https://noticiastu.com/wp-content/uploads/2020/08/Poses-de-Fotos-de-Perfil-1.jpg', bio:'Nutricionista boa de conversa, formada na UNASP e aluna muito boa do curso de desenvolviemnto de sistemas'}});
        break;
      case 'calendar':
        this.components.push({component: CalendarComponent, data: {horizontalAlignment:"center"} });
        break;
      case 'input':
        this.components.push({component: InputComponent, data: { label: 'Telefone', placeHolder: '(00)00000-0000', width:"100%", horizontalAlignment:"center" } });
        this.commService.triggerCountComponent();
        break;
      case 'select':
        this.components.push({component: SelectServiceAndProfessionalComponent, data:{}})
    }
  }

  click(event:Event){
    console.log(event);

  }
}
