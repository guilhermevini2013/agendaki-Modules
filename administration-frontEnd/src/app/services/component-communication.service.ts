import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { SectionProperty } from '../models/sectionProperty';
import {
  ComponentWithId
} from "../components/administration-page/agendaki-tools/pre-visualizer/pre-visualizer.component";

@Injectable({
  providedIn: 'root'
})
export class ComponentCommunicationService {
  private componentSubjectToAdd = new Subject<ComponentWithId>();
  addComponentAction$ = this.componentSubjectToAdd.asObservable();
  triggerAddComponentAction(action: ComponentWithId) {
    this.componentSubjectToAdd.next(action);
  }

  private colorPrimary = new Subject<string>();
  colorPrimaryAction$ = this.colorPrimary.asObservable();

  triggerColorPrimary(color:string):void{
    this.colorPrimary.next(color);
  }

  private colorSecundary = new Subject<string>();
  colorSecundaryAction$ = this.colorSecundary.asObservable();

  triggerColorSecundary(color:string):void{
    this.colorSecundary.next(color);
  }
}
