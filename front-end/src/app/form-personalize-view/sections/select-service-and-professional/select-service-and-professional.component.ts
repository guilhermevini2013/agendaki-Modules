import {Component, OnInit} from '@angular/core';
import {NgForOf, NgStyle} from "@angular/common";
import {TemplateService} from "../../../service/template.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-select-service-and-professional',
  standalone: true,
  imports: [
    NgStyle,
    NgForOf
  ],
  templateUrl: './select-service-and-professional.component.html',
  styleUrl: './select-service-and-professional.component.css'
})
export class SelectServiceAndProfessionalComponent implements OnInit{
  public horizontalAlignment: string = "";
  public services: ServiceDTO[] = [];
  constructor(private templateService: TemplateService,private route: ActivatedRoute,) {
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      if (params.has('uuidTemplate')) {
        this.templateService.getAllServicesByParam(params.get('uuidTemplate')!).subscribe(response => {
          this.services = response.body!;
        });
      }
    });
  }

}

export interface ServiceDTO {
  id: number;
  name: string;
}
