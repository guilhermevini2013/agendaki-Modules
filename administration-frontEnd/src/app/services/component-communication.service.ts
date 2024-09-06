import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ComponentCommunicationService {
  private componentSubjectToAdd = new Subject<string>();
  addComponentAction$ = this.componentSubjectToAdd.asObservable();

  private componentSubjectToCount = new Subject<void>();
  countComponentAction$ = this.componentSubjectToCount.asObservable();

  triggerAddComponentAction(action: string) {
    this.componentSubjectToAdd.next(action);
  }

  triggerCountComponent():void{
    this.componentSubjectToCount.next()
  }
}
