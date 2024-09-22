import {Injectable} from '@angular/core';
import {Subject} from 'rxjs';
import {
  ComponentWithId
} from "../components/administration-page/agendaki-tools/pre-visualizer/pre-visualizer.component";

@Injectable({
  providedIn: 'root'
})
export class ComponentCommunicationService {
  private componentSubjectToAdd = new Subject<ComponentWithId>();
  addComponentAction$ = this.componentSubjectToAdd.asObservable();
  private colorPrimary = new Subject<string>();
  colorPrimaryAction$ = this.colorPrimary.asObservable();
  private colorSecundary = new Subject<string>();
  colorSecundaryAction$ = this.colorSecundary.asObservable();
  private colorTerciary = new Subject<string>();
  colorTerciaryAction$ = this.colorTerciary.asObservable();

  triggerAddComponentAction(action: ComponentWithId) {
    this.componentSubjectToAdd.next(action);
  }

  triggerColorPrimary(color: string): void {
    this.colorPrimary.next(color);
  }

  triggerColorSecundary(color: string): void {
    this.colorSecundary.next(color);
  }

  triggerColorTerciary(color: string): void {
    this.colorTerciary.next(color);
  }
}
