import {
  Component,
  ComponentFactoryResolver,
  ComponentRef,
  Input,
  OnInit,
  Type,
  ViewChild,
  ViewContainerRef
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

  @ViewChild('container', {read: ViewContainerRef, static: true}) container!: ViewContainerRef;
  public instance: any;

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
    this.instance = componentRef.instance;

    if (this.data) {
      Object.assign(this.instance, this.data);
    }
  }
}
