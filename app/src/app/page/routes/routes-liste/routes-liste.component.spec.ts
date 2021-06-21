import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RoutesListeComponent } from './routes-liste.component';

describe('RoutesListeComponent', () => {
  let component: RoutesListeComponent;
  let fixture: ComponentFixture<RoutesListeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RoutesListeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RoutesListeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
