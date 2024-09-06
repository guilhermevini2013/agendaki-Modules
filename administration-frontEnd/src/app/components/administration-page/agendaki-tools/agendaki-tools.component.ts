import { Component } from '@angular/core';
import { PropertyComponent } from './property/property.component';
import { PreVisualizerComponent } from './pre-visualizer/pre-visualizer.component';
import { AddComponentComponent } from './add-component/add-component.component';

@Component({
  selector: 'app-agendaki-tools',
  standalone: true,
  imports: [PropertyComponent,PreVisualizerComponent,AddComponentComponent],
  templateUrl: './agendaki-tools.component.html',
  styleUrl: './agendaki-tools.component.css'
})
export class AgendakiToolsComponent {

}