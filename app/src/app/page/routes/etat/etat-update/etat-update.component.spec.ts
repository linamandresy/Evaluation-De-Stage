import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EtatUpdateComponent } from './etat-update.component';

describe('EtatUpdateComponent', () => {
  let component: EtatUpdateComponent;
  let fixture: ComponentFixture<EtatUpdateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EtatUpdateComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EtatUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
