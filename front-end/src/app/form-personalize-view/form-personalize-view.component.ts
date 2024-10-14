import { Component, OnInit, Type, ViewChildren, QueryList } from '@angular/core';
import { ActivatedRoute } from "@angular/router";
import { TemplateService } from "../service/template.service";
import { InputComponent } from "./sections/input/input.component";
import { InformationComponent } from "./sections/information/information.component";
import { CalendarComponent } from "./sections/calendar/calendar.component";
import { SelectServiceAndProfessionalComponent } from "./sections/select-service-and-professional/select-service-and-professional.component";
import { PortfolioComponent } from "./sections/portfolio/portfolio.component";
import { PresentationComponent } from "./sections/presentation/presentation.component";
import { PerfilComponent } from "./sections/perfil/perfil.component";
import { NgForOf, NgStyle } from "@angular/common";
import { DynamicComponentContainer } from "./dynamic-component-container/dynamic-component-container.component";
import { IMessageSender } from "./sections/IMessageSender";

export interface ComponentWithId {
  component: Type<any>;
  data: any;
}

@Component({
  selector: 'app-form-personalize-view',
  standalone: true,
  imports: [
    NgForOf,
    DynamicComponentContainer,
    NgStyle
  ],
  templateUrl: './form-personalize-view.component.html',
  styleUrls: ['./form-personalize-view.component.css']
})
export class FormPersonalizeViewComponent implements OnInit {

  public components: ComponentWithId[] = [];
  public primaryColor: string = '';
  public secondaryColor: string = '';

  @ViewChildren(DynamicComponentContainer) dynamicComponents!: QueryList<DynamicComponentContainer>;

  constructor(private route: ActivatedRoute, private templateService: TemplateService) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      if (params.has('uuidTemplate')) {
        this.templateService.getTemplateByParam(params.get('uuidTemplate')!).subscribe(response => {
            const template = response.body!;
            this.primaryColor = template?.primaryColor!;
            this.secondaryColor = template?.secondaryColor!;
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
          },
          error => {
            console.error(error);
          });
      }
    });
  }

  sendForm(): void {
    this.dynamicComponents.forEach((dynamicComponent) => {
      const instance = dynamicComponent.instance;
      if (instance instanceof IMessageSender) {
        console.log(`Message: ${instance.sendValue()}`);
      }
    });
  }
}

export interface TemplateDTO {
  primaryColor: string;
  secondaryColor: string;
  sections: any[];
}
