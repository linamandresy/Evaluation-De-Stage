import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RoutesInsertComponent } from './routes-insert.component';

describe('RoutesInsertComponent', () => {
  let component: RoutesInsertComponent;
  let fixture: ComponentFixture<RoutesInsertComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RoutesInsertComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RoutesInsertComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
