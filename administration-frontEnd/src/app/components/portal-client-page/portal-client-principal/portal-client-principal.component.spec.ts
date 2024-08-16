import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PortalClientPrincipalComponent } from './portal-client-principal.component';

describe('PortalClientPrincipalComponent', () => {
  let component: PortalClientPrincipalComponent;
  let fixture: ComponentFixture<PortalClientPrincipalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PortalClientPrincipalComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PortalClientPrincipalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
