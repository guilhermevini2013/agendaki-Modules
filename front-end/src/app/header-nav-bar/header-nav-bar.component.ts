import {Component} from '@angular/core';
import {RouterLink} from '@angular/router';

@Component({
  selector: 'app-header-nav-bar',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './header-nav-bar.component.html',
  styleUrl: './header-nav-bar.component.css'
})
export class HeaderNavBarComponent {

}
