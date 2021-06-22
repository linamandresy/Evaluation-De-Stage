import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PortionsUpdateComponent } from './portions-update.component';

describe('PortionsUpdateComponent', () => {
  let component: PortionsUpdateComponent;
  let fixture: ComponentFixture<PortionsUpdateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PortionsUpdateComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PortionsUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
