import {Component, OnInit} from '@angular/core';
import {Router, RouterLink, RouterLinkActive, RouterOutlet} from '@angular/router';
import {LoginPageComponent} from "./components/login-page/login-page.component";
import {NgIf} from "@angular/common";
import {NgxSpinnerComponent} from "ngx-spinner";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, LoginPageComponent, NgIf, RouterLink, RouterLinkActive, NgxSpinnerComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {


  constructor(private router: Router) {
  }

  ngOnInit(): void {

  }
}
