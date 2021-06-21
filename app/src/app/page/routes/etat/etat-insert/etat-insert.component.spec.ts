import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EtatInsertComponent } from './etat-insert.component';

describe('EtatInsertComponent', () => {
  let component: EtatInsertComponent;
  let fixture: ComponentFixture<EtatInsertComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EtatInsertComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EtatInsertComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
