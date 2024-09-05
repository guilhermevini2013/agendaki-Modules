import { Component } from '@angular/core';
import { ComponentCommunicationService } from '../../../../services/component-communication.service';

@Component({
  selector: 'app-add-component',
  standalone: true,
  imports: [],
  templateUrl: './add-component.component.html',
  styleUrl: './add-component.component.css'
})
export class AddComponentComponent {
  constructor(private commService: ComponentCommunicationService) {}

  addComponent(type: string) {
    this.commService.triggerComponentAction(type);
  }
}
