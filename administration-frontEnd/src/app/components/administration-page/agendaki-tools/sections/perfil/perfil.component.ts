import {Component, Input} from '@angular/core';

@Component({
  selector: 'app-perfil',
  standalone: true,
  imports: [],
  templateUrl: './perfil.component.html',
  styleUrl: './perfil.component.css'
})
export class PerfilComponent {
  @Input() imageToBase64: string | ArrayBuffer | null = null;
  @Input() bio: string = "";

}
