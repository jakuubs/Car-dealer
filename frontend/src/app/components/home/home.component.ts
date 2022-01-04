import { QueryList } from '@angular/core';
import { ElementRef } from '@angular/core';
import { Component, OnInit, ViewChildren } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { CarDealerClientService } from 'src/app/services/carDealerClient/car-dealer-client.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})

export class HomeComponent implements OnInit {

  options = [
    'Brand',
    'Model',
    'Car',
    'Customer',
    'Details',
    'Sale'
  ];

  selected = 'Brand';

  successAlert = false;
  errorAlert = false;

  @ViewChildren("txt") inputAreas: QueryList<ElementRef>;

  constructor(private carDealerClientService: CarDealerClientService) { }

  ngOnInit(): void {
  }

  addBrand = new FormGroup({
    name: new FormControl(''),
    startDate: new FormControl(''),
    originCountry: new FormControl('')
  })

  addModel = new FormGroup({
    brand: new FormControl(''),
    name: new FormControl(''),
    releaseDate: new FormControl('')
  })

  addCar = new FormGroup({
    vin: new FormControl(''),
    model: new FormControl(''),
    mileage: new FormControl(''),
    enginePower: new FormControl(''),
    gearbox: new FormControl(''),
    fuel: new FormControl('')
  })

  addCustomer = new FormGroup({
    email: new FormControl(''),
    phoneNumber: new FormControl('')
  })

  addDetails = new FormGroup({
    customer: new FormControl(''),
    firstName: new FormControl(''),
    lastName: new FormControl(''),
    address: new FormControl('')
  })

  addSale = new FormGroup({
    car: new FormControl(''),
    customer: new FormControl(''),
    date: new FormControl(''),
    price: new FormControl('')
  })

  closeSuccessAlert() {
    this.successAlert = false;
  }

  closeErrorAlert() {
    this.errorAlert = false;
  }

  onAddBrand() {
    this.carDealerClientService.addBrand(this.addBrand.value)
      .subscribe(
        response => {
          console.log(response);
          this.successAlert = true;
          this.addBrand.reset({});
          this.inputAreas.find((item, idx) => {
            return idx === 0;
          }).nativeElement.focus();
        },
        error => {
          console.error(error)
          this.errorAlert = true;
          this.addBrand.reset({});
          this.inputAreas.find((item, idx) => {
            return idx === 0;
          }).nativeElement.focus();
        })
  }

  onAddModel() {
    this.carDealerClientService.addModel(this.addModel.value)
      .subscribe(
        response => {
          console.log(response);
          this.successAlert = true;
          this.addModel.reset({});
          this.inputAreas.find((item, idx) => {
            return idx === 0;
          }).nativeElement.focus();
        },
        error => {
          console.error(error)
          this.errorAlert = true;
          this.addModel.reset({});
          this.inputAreas.find((item, idx) => {
            return idx === 0;
          }).nativeElement.focus();
        })
  }

  onAddCar() {
    this.carDealerClientService.addCar(this.addCar.value)
      .subscribe(
        response => {
          console.log(response);
          this.successAlert = true;
          this.addCar.reset({});
          this.inputAreas.find((item, idx) => {
            return idx === 0;
          }).nativeElement.focus();
        },
        error => {
          console.error(error)
          this.errorAlert = true;
          this.addCar.reset({});
          this.inputAreas.find((item, idx) => {
            return idx === 0;
          }).nativeElement.focus();
        })
  }

  onAddCustomer() {
    this.carDealerClientService.addCustomer(this.addCustomer.value)
      .subscribe(
        response => {
          console.log(response);
          this.successAlert = true;
          this.addCustomer.reset({});
          this.inputAreas.find((item, idx) => {
            return idx === 0;
          }).nativeElement.focus();
        },
        error => {
          console.error(error)
          this.errorAlert = true;
          this.addCustomer.reset({});
          this.inputAreas.find((item, idx) => {
            return idx === 0;
          }).nativeElement.focus();
        })
  }

  onAddDetails() {
    this.carDealerClientService.addDetails(this.addDetails.value)
      .subscribe(
        response => {
          console.log(response);
          this.successAlert = true;
          this.addDetails.reset({});
          this.inputAreas.find((item, idx) => {
            return idx === 0;
          }).nativeElement.focus();
        },
        error => {
          console.error(error)
          this.errorAlert = true;
          this.addDetails.reset({});
          this.inputAreas.find((item, idx) => {
            return idx === 0;
          }).nativeElement.focus();
        })
  }

  onAddSale() {
    this.carDealerClientService.addSale(this.addSale.value)
      .subscribe(
        response => {
          console.log(response);
          this.successAlert = true;
          this.addSale.reset({});
          this.inputAreas.find((item, idx) => {
            return idx === 0;
          }).nativeElement.focus();
        },
        error => {
          console.error(error)
          this.errorAlert = true;
          this.addSale.reset({});
          this.inputAreas.find((item, idx) => {
            return idx === 0;
          }).nativeElement.focus();
        })
  }
}
