import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PortionsInsertComponent } from './portions-insert.component';

describe('PortionsInsertComponent', () => {
  let component: PortionsInsertComponent;
  let fixture: ComponentFixture<PortionsInsertComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PortionsInsertComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PortionsInsertComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
