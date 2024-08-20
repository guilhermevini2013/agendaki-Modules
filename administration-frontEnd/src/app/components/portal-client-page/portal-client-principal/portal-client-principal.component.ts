import { MediaMatcher } from '@angular/cdk/layout';
import { NgFor, NgIf } from '@angular/common';
import { ChangeDetectorRef, Component} from '@angular/core';
import { MatIcon } from '@angular/material/icon';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatListModule} from '@angular/material/list';
import { RouterOutlet } from '@angular/router';


@Component({
  selector: 'app-portal-client-principal',
  standalone: true,
  imports: [MatSidenavModule,MatToolbarModule,MatIcon,NgFor,NgIf,MatListModule,RouterOutlet],
  templateUrl: './portal-client-principal.component.html',
  styleUrl: './portal-client-principal.component.css'
})
export class PortalClientPrincipalComponent{
  
  

  constructor() {

  }

  ngOnDestroy(): void {
  
  }

}
