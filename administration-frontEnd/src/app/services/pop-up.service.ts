import {inject, Injectable} from '@angular/core';
import {MatSnackBar} from "@angular/material/snack-bar";
import {PopUpComponent} from "../components/pop-up/pop-up.component";

@Injectable({
  providedIn: 'root'
})
export class PopUpService {
  private _snackBar = inject(MatSnackBar);
  constructor() { }

  openPopUp(message: string, icon: string): void {
    this._snackBar.openFromComponent(PopUpComponent, {
      duration: 8000,
      horizontalPosition: 'end',
      verticalPosition: 'top',
      data: {
        message: message,
        icon: icon
      }
    });
  }
}
