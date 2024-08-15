import { Component } from '@angular/core';
import { MatIcon } from '@angular/material/icon';
import {FormRegisterComponent} from "./form-register/form-register.component";
import {FooterRegisterComponent} from "./footer-register/footer-register.component";

@Component({
  selector: 'app-register-page',
  standalone: true,
  imports: [MatIcon, FormRegisterComponent, FooterRegisterComponent],
  templateUrl: './register-page.component.html',
  styleUrl: './register-page.component.css'
})
export class RegisterPageComponent {

}
