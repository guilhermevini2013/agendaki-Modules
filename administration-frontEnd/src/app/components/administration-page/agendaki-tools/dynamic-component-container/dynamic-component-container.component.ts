// dynamic-component-container.component.ts
import {
  Component,
  Input,
  ComponentFactoryResolver,
  ViewChild,
  ViewContainerRef,
  OnInit,
  ComponentRef,
  Type
} from '@angular/core';

@Component({
  selector: 'app-dynamic-component-container',
  template: `
    <ng-template #container class="test"></ng-template>`,
  styleUrls: ['./dynamic-component-container.component.css'],
  standalone: true
})
export class DynamicComponentContainer implements OnInit {
  @Input() component: Type<any> | null = null;
  @Input() data: any;

  @ViewChild('container', { read: ViewContainerRef, static: true }) container!: ViewContainerRef;

  constructor(private componentFactoryResolver: ComponentFactoryResolver) {}

  ngOnInit() {
    if (this.component) {
      this.loadComponent();
    }
  }

  private loadComponent() {
    this.container.clear();
    const componentFactory = this.componentFactoryResolver.resolveComponentFactory(this.component!);
    const componentRef: ComponentRef<any> = this.container.createComponent(componentFactory);

    if (this.data) {
      Object.assign(componentRef.instance, this.data);
    }
  }
}

