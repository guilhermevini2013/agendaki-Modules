import {Component} from '@angular/core';
import {RouterOutlet} from '@angular/router';
import {SectionPrincipalComponent} from './home-page/section-principal/section-principal.component';
import {HeaderNavBarComponent} from "./header-nav-bar/header-nav-bar.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, SectionPrincipalComponent, HeaderNavBarComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {

}
