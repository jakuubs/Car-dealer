import { Component, OnInit } from '@angular/core';

import {
  Brand,
  Car,
  CarDealerClientService,
  Customer,
  CustomerDetails,
  Model, Sale
} from '../../services/carDealerClient/car-dealer-client.service';

@Component({
  selector: 'app-database',
  templateUrl: './database.component.html',
  styleUrls: ['./database.component.css']
})
export class DatabaseComponent implements OnInit {

  brands: Brand[];
  models: Model[];
  cars: Car[];
  customers: Customer[];
  details: CustomerDetails[];
  sales: Sale[];

  options = [
    'Brand',
    'Model',
    'Car',
    'Customer',
    'Details',
    'Sales'
  ];

  selected = 'Brand';

  totalRecords: number;
  page = 1;

  constructor(private carDealerClientService: CarDealerClientService) {
  }

  ngOnInit(): void {

    this.getAllBrands();
    this.getAllModels();
    this.getAllCars();
    this.getAllCustomers();
    this.getAllDetails();
    this.getAllSales();

    this.carDealerClientService.refreshNeeded$.subscribe(() => {
      this.getAllBrands();
      this.getAllModels();
      this.getAllCars();
      this.getAllCustomers();
      this.getAllDetails();
      this.getAllSales();
    });
  }

  private getAllBrands() {
    this.carDealerClientService.getBrands().subscribe(value => {
      this.brands = value;
    });
  }

  onDeleteBrand(name: string): void {
    if (confirm('Are you sure to delete brand ' + name + '?')) {
      this.carDealerClientService.deleteBrand(name).subscribe(value => {
        console.log(name);
      });
    }
  }

  private getAllModels() {
    this.carDealerClientService.getModels().subscribe(value => {
      this.models = value;
    });
  }

  onDeleteModel(id: number): void {
    if (confirm('Are you sure to delete model ' + id + '?')) {
      this.carDealerClientService.deleteModel(id).subscribe(value => {
        console.log(id);
      });
    }
  }

  private getAllCars() {
    this.carDealerClientService.getCars().subscribe(value => {
      this.cars = value;
    });
  }

  onDeleteCar(vin: string): void {
    if (confirm('Are you sure to delete car ' + vin + '?')) {
      this.carDealerClientService.deleteCar(vin).subscribe(value => {
        console.log(vin);
      })
    }
  }

  private getAllCustomers() {
    this.carDealerClientService.getCustomers().subscribe(value => {
      this.customers = value;
    });
  }

  onDeleteCustomer(id: number): void {
    if (confirm('Are you sure to delete customer ' + id + '?')) {
      this.carDealerClientService.deleteCustomer(id).subscribe(value => {
        console.log(id);
      })
    }
  }

  private getAllDetails() {
    this.carDealerClientService.getDetails().subscribe(value => {
      this.details = value;
    });
  }

  onDeleteDetails(id: number): void {
    if (confirm('Are you sure to delete customer details ' + id + '?')) {
      this.carDealerClientService.deleteDetails(id).subscribe(value => {
        console.log(id);
      })
    }
  }

  private getAllSales() {
    this.carDealerClientService.getSales().subscribe(value => {
      this.sales = value;
    });
  }

  onDeleteSale(id: number): void {
    if (confirm('Are you sure to delete sale ' + id + '?')) {
      this.carDealerClientService.deleteSale(id).subscribe(value => {
        console.log(id);
      })
    }
  }
}