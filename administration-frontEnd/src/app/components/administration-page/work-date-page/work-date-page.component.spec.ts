import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WorkDatePageComponent } from './work-date-page.component';

describe('WorkDatePageComponent', () => {
  let component: WorkDatePageComponent;
  let fixture: ComponentFixture<WorkDatePageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [WorkDatePageComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(WorkDatePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
