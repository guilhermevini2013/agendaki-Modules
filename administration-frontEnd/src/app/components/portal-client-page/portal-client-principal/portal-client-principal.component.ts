import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-portal-client-principal',
  standalone: true,
  imports: [],
  templateUrl: './portal-client-principal.component.html',
  styleUrl: './portal-client-principal.component.css'
})
export class PortalClientPrincipalComponent implements OnInit{

  constructor(private router:Router) {
  }

  ngOnInit(): void {
  }


}
