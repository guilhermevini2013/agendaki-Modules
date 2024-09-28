import {Component, Type} from '@angular/core';
import {
  CdkDrag,
  CdkDragDrop,
  CdkDragHandle,
  CdkDragPlaceholder,
  CdkDragStart,
  CdkDropList,
  moveItemInArray
} from '@angular/cdk/drag-drop';
import {CommonModule} from '@angular/common';
import {ɵEmptyOutletComponent} from "@angular/router";
import {DynamicComponentContainer} from "../dynamic-component-container/dynamic-component-container.component";
import {ComponentCommunicationService} from '../../../../services/component-communication.service';
import {TemplateDTO} from "../../../../models/template/template-create-dto";
import {TemplateService} from "../../../../services/template/template.service";
import {InputComponent} from "../sections/input/input.component";
import {InformationComponent} from "../sections/information/information.component";
import {CalendarComponent} from "../sections/calendar/calendar.component";
import {
  SelectServiceAndProfessionalComponent
} from "../sections/select-service-and-professional/select-service-and-professional.component";
import {PortfolioComponent} from "../sections/portfolio/portfolio.component";
import {PresentationComponent} from "../sections/presentation/presentation.component";
import {PerfilComponent} from "../sections/perfil/perfil.component";

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
  components: ComponentWithId[] = [];
  protected primaryColor: string | null = "#fff";
  protected secundaryColor: string | null = "#000";
  protected terciaryColor: string | null = "#fff";

  constructor(private commService: ComponentCommunicationService, private templateService: TemplateService) {
  }

  drop(event: CdkDragDrop<ComponentWithId[]>) {
    moveItemInArray(this.components, event.previousIndex, event.currentIndex);
  }

  ngOnInit() {
    this.commService.addComponentAction$.subscribe(action => {
      this.components.push(action);
    });

    this.commService.colorPrimaryAction$.subscribe(action => {
      this.primaryColor = action;
    });

    this.commService.colorSecundaryAction$.subscribe(action => {
      this.secundaryColor = action;
    });

    this.commService.colorTerciaryAction$.subscribe(action => {
      this.terciaryColor = action;
    });

    this.templateService.getTemplate().subscribe((response) => {
      const template = response.body!;
      this.primaryColor = template?.primaryColor!;
      this.secundaryColor = template?.secondaryColor!;
      console.log(template?.sections!)
      const sortedSections = template?.sections!.sort((a, b) => a.position - b.position);
      sortedSections.forEach((section) => {
        switch (section.type) {
          case 'input':
            this.components.push({component: InputComponent, data: section});
            break;
          case 'help':
            this.components.push({component: InformationComponent, data: section});
            break;
          case 'calendar':
            this.components.push({component: CalendarComponent, data: section});
            break;
          case 'professionalAndService':
            this.components.push({component: SelectServiceAndProfessionalComponent, data: section});
            break;
          case 'portfolio':
            this.components.push({component: PortfolioComponent, data: section});
            break;
          case 'presentation':
            this.components.push({component: PresentationComponent, data: section});
            break;
          case 'profile':
            this.components.push({component: PerfilComponent, data: section});
            break;
        }
      });
    });
  }

  onDragStarted(event: CdkDragStart) {
    const placeholderElement = event.source.getPlaceholderElement();
    const sourceElement = event.source.element.nativeElement;
    placeholderElement.style.height = `${sourceElement.clientHeight}px`;
  }

  getJsonForTemplate() {
    const templateToInsert: TemplateDTO = {
      primaryColor: this.primaryColor!,
      secondaryColor: this.secundaryColor!,
      sections: this.components.map((component, index) => {
        const {data} = component;
        return {
          ...data,
          position: index,
        };
      })
    };
    return templateToInsert;
  }
}
