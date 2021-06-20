import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VilleinsertComponent } from './villeinsert.component';

describe('VilleinsertComponent', () => {
  let component: VilleinsertComponent;
  let fixture: ComponentFixture<VilleinsertComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VilleinsertComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(VilleinsertComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
