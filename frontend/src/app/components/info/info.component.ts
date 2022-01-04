import { Component, OnInit } from '@angular/core';
import { CarDealerClientService } from 'src/app/services/carDealerClient/car-dealer-client.service';

@Component({
  selector: 'app-info',
  templateUrl: './info.component.html',
  styleUrls: ['./info.component.css']
})
export class InfoComponent implements OnInit {

  brandsInfo: Map<string, number>;
  modelsInfo: Map<string, number>;
  carsInfo: Map<string, number>;
  customersInfo: Map<string, number>;
  detailsInfo: Map<string, number>;
  salesInfo: Map<string, number>;

  constructor(private carDealerClientService: CarDealerClientService) { }

  ngOnInit(): void {
    this.getAllBrandsInfo();
    this.getAllModelsInfo();
    this.getAllCarsInfo();
    this.getAllCustomersInfo();
    this.getAllDetailsInfo();
    this.getAllSalesInfo();

    this.carDealerClientService.refreshNeeded$.subscribe(() => {
      this.getAllBrandsInfo();
      this.getAllModelsInfo();
      this.getAllCarsInfo();
      this.getAllCustomersInfo();
      this.getAllDetailsInfo();
      this.getAllSalesInfo();
    })
  }

  getAllBrandsInfo() {
    this.carDealerClientService.getBrandsInfo().subscribe(value => { 
      this.brandsInfo = value['brandsByCountry'];
    });      
  }

  getAllModelsInfo() {
    this.carDealerClientService.getModelsInfo().subscribe(value => { 
      this.modelsInfo = value['modelsByBrands'];
    });      
  }

  getAllCarsInfo() {
    this.carDealerClientService.getCarsInfo().subscribe(value => { 
      this.carsInfo = value['carsByFuelType'];
    });      
  }

  getAllCustomersInfo() {
    this.carDealerClientService.getCustomersInfo().subscribe(value => { 
      this.customersInfo = value;
    }); 
  }

  getAllDetailsInfo() {
    this.carDealerClientService.getDetailsInfo().subscribe(value => { 
      this.detailsInfo = value['clientsByFirstLetterOfName'];
    }); 
  }

  getAllSalesInfo() {
    this.carDealerClientService.getSalesInfo().subscribe(value => { 
      this.salesInfo = value;
    }); 
  }
}
