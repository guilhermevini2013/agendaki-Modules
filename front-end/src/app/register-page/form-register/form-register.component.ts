import {Component, inject} from '@angular/core';
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {NgIf} from "@angular/common";
import {PreUserServiceService} from "../../service/pre-user-service.service";
import {MatSnackBar, MatSnackBarHorizontalPosition, MatSnackBarVerticalPosition,} from '@angular/material/snack-bar';
import {PopUpComponent} from '../pop-up/pop-up.component';
import {MatProgressBar} from "@angular/material/progress-bar";
import {NgxMaskDirective, NgxMaskPipe} from 'ngx-mask';

@Component({
  selector: 'app-form-register',
  standalone: true,
  imports: [ReactiveFormsModule, NgIf, PopUpComponent, MatProgressBar, NgxMaskDirective, NgxMaskPipe],
  templateUrl: './form-register.component.html',
  styleUrl: './form-register.component.css'
})
export class FormRegisterComponent {
  private _snackBar = inject(MatSnackBar);
  horizontalPosition: MatSnackBarHorizontalPosition = 'end';
  verticalPosition: MatSnackBarVerticalPosition = 'top';

  protected isLoading: boolean = false;

  protected registerForm: FormGroup = new FormGroup<any>({
      name: new FormControl("", [Validators.minLength(3), Validators.maxLength(70)]),
      tradeName: new FormControl("", [Validators.minLength(3), Validators.maxLength(50)]),
      password: new FormControl("", [Validators.minLength(6), Validators.maxLength(20)]),
      email: new FormControl("", [Validators.email]),
      tellPhone: new FormControl("", Validators.minLength(11))
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
      this.isLoading = true;
      console.log(this.registerForm.value.tellPhone)

      this.preUserService.savePreUser(this.registerForm.value).subscribe(
        response => {
          switch (response.status) {
            case 201:
              this.openPopUp("Conta criada com sucesso!", "check_circle")
              this.isLoading = false;
              this.registerForm.reset();
              break;
          }
        },
        error => {
          this.openPopUp("Erro no servidor. Tente novamente mais tarde.", "error")
          this.isLoading = false;
        }
      );
    }
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
