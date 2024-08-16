import {Component, inject} from '@angular/core';
import {PreUserService} from "../../../services/pre-user.service";
import {MatSnackBar, MatSnackBarHorizontalPosition, MatSnackBarVerticalPosition} from "@angular/material/snack-bar";
import {PopUpComponent} from "../../pop-up/pop-up.component";
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {PreUserAuthDTO} from "../../../models/preUserAuthDTO";
import {NgIf} from "@angular/common";

@Component({
  selector: 'app-form-login',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    NgIf
  ],
  templateUrl: './form-login.component.html',
  styleUrl: './form-login.component.css'
})
export class FormLoginComponent {
  private _snackBar = inject(MatSnackBar);
  horizontalPosition: MatSnackBarHorizontalPosition = 'end';
  verticalPosition: MatSnackBarVerticalPosition = 'top';
  constructor(private preUserService:PreUserService) {
  }

  protected loginForm:FormGroup = new FormGroup<any>(
    {
      email: new FormControl('',[Validators.email,Validators.required]),
      password: new FormControl('')
    }
  )

  protected authUserToPortalClient():void{
    this.preUserService.authPreUser({email:this.loginForm.value.email, password:this.loginForm.value.password}).subscribe(
      response =>{
        switch (response.status) {
          case 422:
            this.openPopUp("Email ou senha incorreto.","error")
            break;
        }
      },
      error => {
        switch (error.status) {
          case 422:
            this.openPopUp("Email ou senha incorreto.","error")
            break;
        }
      },
    );
  }

  get email(){
    return this.loginForm.get("email");
  }

  openPopUp(message: string, icon: string): void {
    this._snackBar.openFromComponent(PopUpComponent, {
      duration: 8000,
      horizontalPosition: this.horizontalPosition,
      verticalPosition: this.verticalPosition,
      data: {
        message: message,
        icon: icon
      }
    });
  }
}
