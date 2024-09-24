import {Component, HostListener, ViewChild} from '@angular/core';
import {PreVisualizerComponent} from './pre-visualizer/pre-visualizer.component';
import {AddComponentComponent} from './add-component/add-component.component';
import { NgIf } from '@angular/common';
import { MatIcon, MatIconModule } from '@angular/material/icon';
import { MatButton, MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-agendaki-tools',
  standalone: true,
  imports: [PreVisualizerComponent, AddComponentComponent, NgIf, MatIconModule, MatButtonModule],
  templateUrl: './agendaki-tools.component.html',
  styleUrl: './agendaki-tools.component.css'
})
export class AgendakiToolsComponent {
  @ViewChild(PreVisualizerComponent) preVisualizerComponent!: PreVisualizerComponent;

  getJsonForTemplateByPreVisualizer() {
    return this.preVisualizerComponent.getJsonForTemplate();
  }

  screenSize: number = 0;

  constructor() {
    this.checkScreenSize(window.innerWidth);
  }

  @HostListener('window:resize', ['$event'])
  onResize(event: Event) {
    this.checkScreenSize((event.target as Window).innerWidth);
  }

  checkScreenSize(width: number) {
    this.screenSize = width;
  }
}
