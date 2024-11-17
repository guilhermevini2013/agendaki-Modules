import {Component, OnInit, Type, ViewChildren, QueryList, AfterViewInit} from '@angular/core';
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
export class FormPersonalizeViewComponent implements OnInit, AfterViewInit  {

  public components: ComponentWithId[] = [];
  public primaryColor: string = '';
  public secondaryColor: string = '';
  public static jsonToSend: any = {};

  static dynamicComponents: QueryList<DynamicComponentContainer>;
  static templateService: TemplateService;
  static route: ActivatedRoute;

  @ViewChildren(DynamicComponentContainer) dynamicComponentsQuery!: QueryList<DynamicComponentContainer>;
  FormPersonalizeViewComponent: any = FormPersonalizeViewComponent;

  constructor(private route: ActivatedRoute, private templateService: TemplateService) {
    FormPersonalizeViewComponent.templateService = templateService;
    FormPersonalizeViewComponent.route = route;
  }

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

  ngAfterViewInit() {
    FormPersonalizeViewComponent.dynamicComponents = this.dynamicComponentsQuery;
  }

  static sendForm(): void {
    // @ts-ignore
    this.dynamicComponents.forEach((dynamicComponent: { instance: any; }) => {
      const instance = dynamicComponent.instance;
      if (instance instanceof IMessageSender) {
        instance.sendValue();
      }
    });
    this.route.paramMap.subscribe(params => {
      this.jsonToSend = {
        ...this.jsonToSend,
        idInstance: params.get('uuidTemplate')
      };
      console.log(this.jsonToSend);
      this.templateService.createScheduling(params.get('uuidTemplate')!, this.jsonToSend).subscribe(response => {
      });
    });

    this.jsonToSend = {};
  }
}

export interface TemplateDTO {
  primaryColor: string;
  secondaryColor: string;
  sections: any[];
}
