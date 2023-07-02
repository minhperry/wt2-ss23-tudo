import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SparkCardComponent } from './spark-card.component';

describe('SparkCardComponent', () => {
  let component: SparkCardComponent;
  let fixture: ComponentFixture<SparkCardComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SparkCardComponent]
    });
    fixture = TestBed.createComponent(SparkCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
