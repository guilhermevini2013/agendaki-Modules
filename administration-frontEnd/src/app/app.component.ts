import {Component, Injector, OnInit} from '@angular/core';
import {Router, RouterOutlet} from '@angular/router';
import { LoginPageComponent } from "./components/login-page/login-page.component";
import {NgIf} from "@angular/common";
import {PreUserService} from "./services/pre-user.service";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, LoginPageComponent, NgIf],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit{


  constructor(private preUserService:PreUserService, private router:Router) {
  }

  ngOnInit(): void {
    if (localStorage.getItem("tokenPortalClient") == null){
      this.router.navigate(['/']);
    }
  }
}
