import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { SectionProperty } from '../models/sectionProperty';

@Injectable({
  providedIn: 'root'
})
export class ComponentCommunicationService {
  private componentSubjectToAdd = new Subject<string>();
  addComponentAction$ = this.componentSubjectToAdd.asObservable();

  private componentSubjectToCount = new Subject<void>();
  countComponentAction$ = this.componentSubjectToCount.asObservable();

  private sectionProperty = new Subject<SectionProperty>();
  sectionPropertyAction$ = this.sectionProperty.asObservable();

  triggerAddComponentAction(action: string) {
    this.componentSubjectToAdd.next(action);
  }

  triggerCountComponent():void{
    this.componentSubjectToCount.next()
  }

  triggerSectionProperty(value: SectionProperty):void{
    this.sectionProperty.next(value);
  }
}
