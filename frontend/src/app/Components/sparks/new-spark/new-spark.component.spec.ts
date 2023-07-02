import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewSparkComponent } from './new-spark.component';

describe('NewSparkComponent', () => {
  let component: NewSparkComponent;
  let fixture: ComponentFixture<NewSparkComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [NewSparkComponent]
    });
    fixture = TestBed.createComponent(NewSparkComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
