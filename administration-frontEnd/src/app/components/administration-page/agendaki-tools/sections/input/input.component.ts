import {Component} from '@angular/core';
import {NgStyle} from "@angular/common";
import {ComponentCommunicationService} from '../../../../../services/component-communication.service';

@Component({
  selector: 'app-input',
  standalone: true,
  imports: [
    NgStyle
  ],
  templateUrl: './input.component.html',
  styleUrl: './input.component.css'
})
export class InputComponent {
  public label: string = "";
  public placeHolder: string = "";
  public width: string = "";
  public horizontalAlignment: string = "";

  protected terciaryColor: string | null = "#000";

  constructor(private commService: ComponentCommunicationService) {
  }

  ngOnInit() {
    this.commService.colorTerciaryAction$.subscribe(action => {
      this.terciaryColor = action;
    });
  }
}
