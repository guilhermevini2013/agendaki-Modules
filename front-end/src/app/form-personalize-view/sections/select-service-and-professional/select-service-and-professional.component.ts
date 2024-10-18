import { Component, OnInit } from '@angular/core';
import { NgForOf, NgStyle } from "@angular/common";
import { TemplateService } from "../../../service/template.service";
import { ActivatedRoute } from "@angular/router";
import { FormControl, ReactiveFormsModule } from "@angular/forms";

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
export class SelectServiceAndProfessionalComponent implements OnInit {
  public horizontalAlignment: string = "";
  public services: ServiceDTO[] = [];
  public professionals: ProfessionalDTO[] = [];
  public serviceControl = new FormControl('');
  public professionalControl = new FormControl('');

  constructor(private templateService: TemplateService, private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      if (params.has('uuidTemplate')) {
        this.templateService.getAllServicesByParam(params.get('uuidTemplate')!).subscribe(response => {
          this.services = response.body!;
          // @ts-ignore
          this.serviceControl.setValue(this.services[0].id!);
          // @ts-ignore
          this.loadProfessionals(params.get('uuidTemplate')!, this.services[0].id!);
        });
      }
    });

    this.serviceControl.valueChanges.subscribe(serviceId => {
      const uuidTemplate = this.route.snapshot.paramMap.get('uuidTemplate')!;
      this.loadProfessionals(uuidTemplate, Number(serviceId));
    });

    this.professionalControl.valueChanges.subscribe(selectedValue => {
      console.log(`Selected professional: ${selectedValue}`);
    });
  }

  private loadProfessionals(uuidTemplate: string, serviceId: number): void {
    this.templateService.getAllProfessionalsByParamAndService(uuidTemplate, serviceId).subscribe(response => {
      this.professionals = response.body!;
      if (this.professionals.length > 0) {
        // @ts-ignore
        this.professionalControl.setValue(this.professionals[0].id);
      }
    });
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
