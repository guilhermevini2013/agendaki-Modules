import {Component, Input, inject, Inject} from '@angular/core';
import {
  MAT_SNACK_BAR_DATA,
  MatSnackBarAction,
  MatSnackBarActions,
  MatSnackBarLabel,
  MatSnackBarRef,
} from '@angular/material/snack-bar';
import {MatButtonModule} from '@angular/material/button';
import {MatIcon} from "@angular/material/icon";

@Component({
  selector: 'app-pop-up',
  standalone: true,
    imports: [MatButtonModule, MatSnackBarLabel, MatSnackBarActions, MatSnackBarAction, MatIcon],
  templateUrl: './pop-up.component.html',
  styleUrl: './pop-up.component.css'
})
export class PopUpComponent {
  constructor(@Inject(MAT_SNACK_BAR_DATA) public data: any) {}

  snackBarRef = inject(MatSnackBarRef);
}
