import { TestBed } from '@angular/core/testing';

import { PortionsService } from './portions.service';

describe('PortionsService', () => {
  let service: PortionsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PortionsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
