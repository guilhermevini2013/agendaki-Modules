import {Component, inject} from '@angular/core';
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {NgIf} from "@angular/common";
import {PreUserServiceService} from "../../service/pre-user-service.service";
import {
  MatSnackBar,
  MatSnackBarHorizontalPosition,
  MatSnackBarVerticalPosition,
} from '@angular/material/snack-bar';
import { PopUpComponent } from '../pop-up/pop-up.component';

@Component({
  selector: 'app-form-register',
  standalone: true,
  imports: [ReactiveFormsModule, NgIf, PopUpComponent],
  templateUrl: './form-register.component.html',
  styleUrl: './form-register.component.css'
})
export class FormRegisterComponent {
  private _snackBar = inject(MatSnackBar);
  horizontalPosition: MatSnackBarHorizontalPosition = 'end';
  verticalPosition: MatSnackBarVerticalPosition = 'top';

  protected registerForm: FormGroup = new FormGroup<any>({
      name: new FormControl("", [Validators.minLength(3), Validators.maxLength(70)]),
      tradeName: new FormControl("", [Validators.minLength(3), Validators.maxLength(50)]),
      password: new FormControl("", [Validators.minLength(6), Validators.maxLength(20)]),
      email: new FormControl("", [Validators.email]),
      tellPhone: new FormControl("", [Validators.pattern("^\\(\\d{2}\\)\\s\\d{5}-\\d{4}$")])
    }
  );

  private preUserService:PreUserServiceService;

  constructor(preUserService:PreUserServiceService) {
    this.preUserService = preUserService;
  }

  get name() {
    return this.registerForm.get('name');
  }

  get tradeName(){
    return this.registerForm.get('tradeName')
  }

  get tellPhone(){
    return this.registerForm.get('tellPhone')
  }

  get email(){
    return this.registerForm.get('email')
  }

  get password(){
    return this.registerForm.get('password')
  }

  onSubmit() {
    if (this.registerForm.valid) {
      console.log(this.registerForm.value);
      this.preUserService.savePreUser(this.registerForm.value).subscribe(
        response => {

        },
        error =>{
          // this._snackBar.open(error.statusText, 'Fechar', {
          //   horizontalPosition: this.horizontalPosition,
          //   verticalPosition: this.verticalPosition,
          // });

          this._snackBar.openFromComponent(PopUpComponent, {
            duration: 5000,
            horizontalPosition: this.horizontalPosition,
            verticalPosition: this.verticalPosition,
          });
        }
      );
    } 
  }

}
