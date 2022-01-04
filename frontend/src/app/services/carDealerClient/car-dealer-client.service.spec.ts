import { TestBed } from '@angular/core/testing';

import { CarDealerClientService } from './car-dealer-client.service';

describe('CarDealerClientService', () => {
  let service: CarDealerClientService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CarDealerClientService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
