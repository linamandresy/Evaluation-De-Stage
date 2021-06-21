import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EtatListeComponent } from './etat-liste.component';

describe('EtatListeComponent', () => {
  let component: EtatListeComponent;
  let fixture: ComponentFixture<EtatListeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EtatListeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EtatListeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
