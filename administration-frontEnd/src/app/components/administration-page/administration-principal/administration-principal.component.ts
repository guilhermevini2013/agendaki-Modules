import {Component, OnInit} from '@angular/core';
import {Router, RouterLink, RouterLinkActive, RouterOutlet} from "@angular/router";
import {MatIcon} from '@angular/material/icon';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatListModule} from '@angular/material/list';
import {NgFor, NgIf} from '@angular/common';
import {FormLoginComponent} from "../../login-page/form-login/form-login.component";

@Component({
  selector: 'app-administration-principal',
  standalone: true,
  imports: [MatSidenavModule, MatToolbarModule, MatIcon, NgFor, NgIf, MatListModule, RouterOutlet, RouterLink, RouterLinkActive],
  templateUrl: './administration-principal.component.html',
  styleUrl: './administration-principal.component.css'
})
export class AdministrationPrincipalComponent implements OnInit {

  constructor(private router: Router) {
  }

  ngOnInit(): void {
  }

  protected readonly FormLoginComponent = FormLoginComponent;
}
