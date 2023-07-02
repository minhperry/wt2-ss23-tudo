import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewSparksComponent } from './view-sparks.component';

describe('ViewSparksComponent', () => {
  let component: ViewSparksComponent;
  let fixture: ComponentFixture<ViewSparksComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ViewSparksComponent]
    });
    fixture = TestBed.createComponent(ViewSparksComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
