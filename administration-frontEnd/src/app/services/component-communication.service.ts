import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ComponentCommunicationService {
  private componentSubject = new Subject<string>();
  componentAction$ = this.componentSubject.asObservable();
  triggerComponentAction(action: string) {
    this.componentSubject.next(action);
  }
}
