import {Component, Input, OnInit} from '@angular/core';
import {NgForOf} from "@angular/common";

@Component({
  selector: 'app-portfolio',
  standalone: true,
  imports: [
    NgForOf
  ],
  templateUrl: './portfolio.component.html',
  styleUrl: './portfolio.component.css'
})
export class PortfolioComponent{
  @Input() urlsPhotos: string[] | null = null; // expects an array of base64 strings
  @Input() title: string = '';
}
