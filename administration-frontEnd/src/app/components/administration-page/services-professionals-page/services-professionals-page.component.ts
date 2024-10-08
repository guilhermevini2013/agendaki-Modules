import {Component} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MatButton} from '@angular/material/button';
import {MatDividerModule} from '@angular/material/divider';
import {MatExpansionModule} from '@angular/material/expansion';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatIconModule} from '@angular/material/icon';
import {MatInputModule} from '@angular/material/input';
import {MatListModule} from '@angular/material/list';
import {MatOption, MatSelect} from '@angular/material/select';
import {MatTabsModule} from '@angular/material/tabs';
import {NgxMaskDirective, NgxMaskPipe} from 'ngx-mask';
import {NgxSpinnerModule} from 'ngx-spinner';
import {FormsGroupComponent} from './forms-group/forms-group.component';
import {TableShowComponent} from './table-show/table-show.component';

@Component({
  selector: 'app-services-professionals-page',
  standalone: true,
  imports: [
    MatTabsModule,
    MatButton,
    FormsModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatSelect,
    MatOption,
    MatInputModule,
    MatExpansionModule,
    MatListModule,
    MatIconModule,
    MatDividerModule,
    NgxMaskDirective,
    NgxMaskPipe,
    NgxSpinnerModule, // Include NgxSpinnerModule here

    FormsGroupComponent,
    TableShowComponent
  ],
  templateUrl: './services-professionals-page.component.html',
  styleUrl: './services-professionals-page.component.css'
})
export class ServicesProfessionalsPageComponent {

}
