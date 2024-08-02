import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { SectionPrincipalComponent } from './section-principal/section-principal.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, SectionPrincipalComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  
}
