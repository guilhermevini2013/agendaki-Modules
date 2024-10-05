import {Component, Input} from '@angular/core';

@Component({
  selector: 'app-presentation',
  standalone: true,
  imports: [],
  templateUrl: './presentation.component.html',
  styleUrl: './presentation.component.css'
})
export class PresentationComponent {
  text: string = "";
  paragraph: string = "";
  @Input() imageToBase64: string | ArrayBuffer | null = null;
}
