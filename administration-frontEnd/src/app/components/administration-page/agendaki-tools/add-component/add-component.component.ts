import {Component, inject, OnInit, Type} from '@angular/core';
import { ComponentCommunicationService } from '../../../../services/component-communication.service';
import {MatDialog} from "@angular/material/dialog";
import {FormInputComponent} from "./formCompoent/form-input/form-input.component";
import {PerfilFormComponent} from "./formCompoent/perfil-form/perfil-form.component";

@Component({
  selector: 'app-add-component',
  standalone: true,
  imports: [],
  templateUrl: './add-component.component.html',
  styleUrl: './add-component.component.css'
})
export class AddComponentComponent implements OnInit{
  constructor(private commService: ComponentCommunicationService) {}
  readonly dialog = inject(MatDialog);
  openDialog(form:Type<any>) {
    this.dialog.open(form);
  }
  public totalInput:number = 0;

  ngOnInit(): void {
    this.commService.countComponentAction$.subscribe(() =>{
      this.totalInput++;
    })
  }

  protected readonly FormInputComponent = FormInputComponent;
  protected readonly PerfilFormComponent = PerfilFormComponent;
}
