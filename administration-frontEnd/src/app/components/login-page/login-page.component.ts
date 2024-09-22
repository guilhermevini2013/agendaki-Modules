import {Component} from '@angular/core';
import {MatIconModule} from '@angular/material/icon';
import {FormLoginComponent} from './form-login/form-login.component';

@Component({
  selector: 'app-login-page',
  standalone: true,
  imports: [MatIconModule, FormLoginComponent],
  templateUrl: './login-page.component.html',
  styleUrl: './login-page.component.css'
})
export class LoginPageComponent {


}
