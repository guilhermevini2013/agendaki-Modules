import { Component, Input } from '@angular/core';
import { NgStyle } from "@angular/common";
import { IMessageSender } from "../IMessageSender";
import { FormsModule } from "@angular/forms";

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
  @Input() public label: string = "";
  @Input() public placeHolder: string = "";
  @Input() public width: string = "";
  @Input() public horizontalAlignment: string = "";
  public inputValue: string = "";

  sendValue(): string {
    return this.inputValue;
  }
}
