import {Component, OnInit} from '@angular/core';
import {NgForOf, NgStyle} from "@angular/common";
import {TemplateService} from "../../../service/template.service";
import {ActivatedRoute} from "@angular/router";
import {FormControl, ReactiveFormsModule} from "@angular/forms";
import {DateService} from '../../../service/date.service';
import {IMessageSender} from "../IMessageSender";
import {FormPersonalizeViewComponent} from "../../form-personalize-view.component";

@Component({
  selector: 'app-select-service-and-professional',
  standalone: true,
  imports: [
    NgStyle,
    NgForOf,
    ReactiveFormsModule
  ],
  templateUrl: './select-service-and-professional.component.html',
  styleUrls: ['./select-service-and-professional.component.css']
})
export class SelectServiceAndProfessionalComponent extends IMessageSender implements OnInit {
  public horizontalAlignment: string = "";
  public services: ServiceDTO[] = [];
  public professionals: ProfessionalDTO[] = [];
  public serviceControl = new FormControl('');
  public professionalControl = new FormControl('');
  public timeControl = new FormControl('');
  public timesFree: string[] = [];

  constructor(
    private templateService: TemplateService,
    private route: ActivatedRoute,
    private dateService: DateService
  ) {
    super();
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      if (params.has('uuidTemplate')) {
        this.templateService.getAllServicesByParam(params.get('uuidTemplate')!).subscribe(response => {
          this.services = response.body!;
          if (this.services.length > 0) {
            // @ts-ignore
            this.serviceControl.setValue(this.services[0].id!);
            this.loadProfessionals(params.get('uuidTemplate')!, this.services[0].id!);
          }
        });
      }
    });

    this.serviceControl.valueChanges.subscribe(serviceId => {
      const uuidTemplate = this.route.snapshot.paramMap.get('uuidTemplate')!;
      this.loadProfessionals(uuidTemplate, Number(serviceId));
      this.updateTimesFree();
    });

    this.professionalControl.valueChanges.subscribe(() => {
      this.updateTimesFree();
    });

    this.dateService.selectedDate$.subscribe(date => {
      this.updateTimesFree(date);
    });
  }

  private updateTimesFree(date: Date = new Date()): void {
    const uuidTemplate = this.route.snapshot.paramMap.get('uuidTemplate')!;
    const serviceId = this.serviceControl.value;
    const professionalId = this.professionalControl.value;
    if (serviceId && professionalId) {
      this.loadTimesFree(uuidTemplate, date, Number(serviceId), Number(professionalId));
    }
  }

  private loadTimesFree(uuidTemplate: string, date: Date, serviceId: number, professionalId: number): void {
    this.templateService.getAllTimesFree(uuidTemplate, date.toISOString().split('T')[0], serviceId, professionalId).subscribe(response => {
      this.timesFree = response.body!;
    });
  }

  private loadProfessionals(uuidTemplate: string, serviceId: number): void {
    this.templateService.getAllProfessionalsByParamAndService(uuidTemplate, serviceId).subscribe(response => {
      this.professionals = response.body!;
      if (this.professionals.length > 0) {
        // @ts-ignore
        this.professionalControl.setValue(this.professionals[0].id!);
        this.updateTimesFree();
      }
    });
  }

  sendValue(): { value: any; id: number; type: string } {
    FormPersonalizeViewComponent.jsonToSend = {
      ...FormPersonalizeViewComponent.jsonToSend,
      idService: this.serviceControl.value,
      idProfessional: this.professionalControl.value,
      startHour: this.timeControl.value
    };
    return {
      id: 0,
      type: "Obj",
      value: {
        idService: this.serviceControl.value,
        idProfessional: this.professionalControl.value,
        startHour: this.timeControl.value
      }
    };
  }
}

export interface ServiceDTO {
  id: number;
  name: string;
}

export interface ProfessionalDTO {
  id: number;
  name: string;
}
