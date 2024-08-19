import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-administration-principal',
  standalone: true,
  imports: [],
  templateUrl: './administration-principal.component.html',
  styleUrl: './administration-principal.component.css'
})
export class AdministrationPrincipalComponent implements OnInit{

  constructor(private router:Router) {
  }

  ngOnInit(): void {
  }

}
