import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PortionsListeComponent } from './portions-liste.component';

describe('PortionsListeComponent', () => {
  let component: PortionsListeComponent;
  let fixture: ComponentFixture<PortionsListeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PortionsListeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PortionsListeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
