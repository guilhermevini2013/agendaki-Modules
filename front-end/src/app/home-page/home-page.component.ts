import { Component } from '@angular/core';
import {SectionPrincipalComponent} from "./section-principal/section-principal.component";
import {TemplatesExamplesComponent} from "./templates-examples/templates-examples.component";

@Component({
  selector: 'app-home-page',
  standalone: true,
  imports: [
    SectionPrincipalComponent,
    TemplatesExamplesComponent
  ],
  templateUrl: './home-page.component.html',
  styleUrl: './home-page.component.css'
})
export class HomePageComponent {

}
