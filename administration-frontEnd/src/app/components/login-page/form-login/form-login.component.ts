import {Component, inject} from '@angular/core';
import {PreUserService} from "../../../services/pre-user.service";
import {MatSnackBar, MatSnackBarHorizontalPosition, MatSnackBarVerticalPosition} from "@angular/material/snack-bar";
import {PopUpComponent} from "../../pop-up/pop-up.component";
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {NgIf} from "@angular/common";
import {Router} from "@angular/router";
import {TypePage} from "./TypePage";

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
  constructor(private preUserService:PreUserService,private router:Router) {
  }

  protected loginForm:FormGroup = new FormGroup<any>(
    {
      email: new FormControl('',[Validators.email,Validators.required]),
      password: new FormControl('')
    }
  )
  protected submitFormLogin(event:Event):void{
    event.preventDefault();
    const form = event.target as HTMLFormElement;
    const button = form.querySelector('button:focus') as HTMLButtonElement;
    const action = button?.value;
    if (this.loginForm.valid) {
      if (action === 'administration') {
        this.authUser(TypePage.ADMINISTRATION)
      } else if (action === 'portal-client') {
        this.authUser(TypePage.PORTAL_CLIENT);
      }
    }
  }

  protected authUser(typePageToAuth:TypePage):void{
    this.preUserService.authPreUser({email:this.loginForm.value.email, password:this.loginForm.value.password},typePageToAuth).subscribe(
      response =>{
        switch (response.status) {
          case 422:
            this.openPopUp("Email ou senha incorreto.","error")
            break;
          case 200:
            if (typePageToAuth == TypePage.ADMINISTRATION){
              this.router.navigate(["/administration"])
            }else if (typePageToAuth == TypePage.PORTAL_CLIENT){
              this.router.navigate(["/portalClient"])
            }
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
