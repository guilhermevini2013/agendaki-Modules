import {Component, ViewChild} from '@angular/core';
import {PreVisualizerComponent} from './pre-visualizer/pre-visualizer.component';
import {AddComponentComponent} from './add-component/add-component.component';

@Component({
  selector: 'app-agendaki-tools',
  standalone: true,
  imports: [PreVisualizerComponent, AddComponentComponent],
  templateUrl: './agendaki-tools.component.html',
  styleUrl: './agendaki-tools.component.css'
})
export class AgendakiToolsComponent {
  @ViewChild(PreVisualizerComponent) preVisualizerComponent!: PreVisualizerComponent;

  getJsonForTemplateByPreVisualizer() {
    return this.preVisualizerComponent.getJsonForTemplate();
  }
}
