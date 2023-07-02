import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditSparkComponent } from './edit-spark.component';

describe('EditSparkComponent', () => {
  let component: EditSparkComponent;
  let fixture: ComponentFixture<EditSparkComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EditSparkComponent]
    });
    fixture = TestBed.createComponent(EditSparkComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
