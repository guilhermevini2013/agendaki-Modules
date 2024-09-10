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

  private componentSubjectToCount = new Subject<void>();
  countComponentAction$ = this.componentSubjectToCount.asObservable();

  private sectionProperty = new Subject<SectionProperty>();
  sectionPropertyAction$ = this.sectionProperty.asObservable();

  triggerAddComponentAction(action: ComponentWithId) {
    this.componentSubjectToAdd.next(action);
  }

  triggerCountComponent():void{
    this.componentSubjectToCount.next()
  }

  triggerSectionProperty(value: SectionProperty):void{
    this.sectionProperty.next(value);
  }

  private colorPrimary = new Subject<string>();
  colorPrimaryAction$ = this.colorPrimary.asObservable();

  triggerColorPrimary(color:string):void{
    this.colorPrimary.next(color);
  }
}
