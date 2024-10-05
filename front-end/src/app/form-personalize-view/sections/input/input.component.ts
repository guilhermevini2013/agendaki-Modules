import {Component} from '@angular/core';
import {NgStyle} from "@angular/common";

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

  ngOnInit() {

  }
}
