import { Component } from '@angular/core';
import { MatIcon } from '@angular/material/icon';
import {FormRegisterComponent} from "./form-register/form-register.component";

@Component({
  selector: 'app-register-page',
  standalone: true,
  imports: [MatIcon, FormRegisterComponent],
  templateUrl: './register-page.component.html',
  styleUrl: './register-page.component.css'
})
export class RegisterPageComponent {

}
