import { Component, Input } from '@angular/core';
import { NgStyle } from "@angular/common";
import { IMessageSender } from "../IMessageSender";
import { FormsModule } from "@angular/forms";
import {FormPersonalizeViewComponent} from "../../form-personalize-view.component";

@Component({
  selector: 'app-input',
  standalone: true,
  imports: [
    NgStyle,
    FormsModule
  ],
  templateUrl: './input.component.html',
  styleUrls: ['./input.component.css']
})
export class InputComponent extends IMessageSender {
  @Input() public id:number = 0;
  @Input() public label: string = "";
  @Input() public placeHolder: string = "";
  @Input() public width: string = "";
  @Input() public horizontalAlignment: string = "";
  public inputValue: string = "";

  sendValue(): any {
    // Garantir que responsesForms esteja definido e seja um array
    FormPersonalizeViewComponent.jsonToSend = {
      ...FormPersonalizeViewComponent.jsonToSend,
      responsesForms: [
        ...(FormPersonalizeViewComponent.jsonToSend.responsesForms && FormPersonalizeViewComponent.jsonToSend.responsesForms.length > 0
          ? FormPersonalizeViewComponent.jsonToSend.responsesForms
          : []), // Se responsesForms estiver vazio ou não existir, inicializa com um array vazio
        { id: this.id, response: this.inputValue }
      ]
    };
    return { value: this.inputValue, id: this.id, type: "NoObj" };
  }
}
