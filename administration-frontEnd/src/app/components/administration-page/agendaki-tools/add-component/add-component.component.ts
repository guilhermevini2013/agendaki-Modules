import {Component, OnInit} from '@angular/core';
import { ComponentCommunicationService } from '../../../../services/component-communication.service';

@Component({
  selector: 'app-add-component',
  standalone: true,
  imports: [],
  templateUrl: './add-component.component.html',
  styleUrl: './add-component.component.css'
})
export class AddComponentComponent implements OnInit{
  constructor(private commService: ComponentCommunicationService) {}

  addComponent(type: string) {
    this.commService.triggerAddComponentAction(type);
  }
  public totalInput:number = 0;

  ngOnInit(): void {
    this.commService.countComponentAction$.subscribe(() =>{
      this.totalInput++;
    })
  }
}
