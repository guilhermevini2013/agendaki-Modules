import { Component } from '@angular/core';
import {SectionPrincipalComponent} from "./section-principal/section-principal.component";
import {TemplatesExamplesComponent} from "./templates-examples/templates-examples.component";
import { PricesDivComponent } from './prices-div/prices-div.component';
import { VideoDivComponent } from './video-div/video-div.component';
import { FooterDivComponent } from '../footer-div/footer-div.component';

@Component({
  selector: 'app-home-page',
  standalone: true,
  imports: [
    SectionPrincipalComponent,
    TemplatesExamplesComponent,
    PricesDivComponent,
    VideoDivComponent,
    FooterDivComponent
  ],
  templateUrl: './home-page.component.html',
  styleUrl: './home-page.component.css'
})
export class HomePageComponent {

}
