import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { CarDealerClientService } from 'src/app/services/carDealerClient/car-dealer-client.service';

@Component({
  selector: 'app-update',
  templateUrl: './update.component.html',
  styleUrls: ['./update.component.css']
})
export class UpdateComponent implements OnInit {

  successAlert = false;
  errorAlert = false;

  constructor(public router: Router, private carDealerClientService: CarDealerClientService) { }

  ngOnInit(): void {
    if (this.router.url.includes('brand')) {
      this.carDealerClientService.getBrand(this.router.url.substring(14))
      .subscribe(result => {
        this.updateBrand = new FormGroup({
          name: new FormControl(result.name),
          startDate: new FormControl(result.startDate),
          originCountry: new FormControl(result.originCountry)
        })
      })
    } else if (this.router.url.includes('model')) {
      this.carDealerClientService.getModel(this.router.url.substring(14))
      .subscribe(result => {
        this.updateModel = new FormGroup({
          id: new FormControl(result.id),
          brand: new FormControl(result.brand),
          name: new FormControl(result.name),
          releaseDate: new FormControl(result.releaseDate)
        })
      })
    } else if (this.router.url.includes('car')) {
      this.carDealerClientService.getCar(this.router.url.substring(12))
      .subscribe(result => {
        this.updateCar = new FormGroup({
          vin: new FormControl(result.vin),
          model: new FormControl(result.model),
          mileage: new FormControl(result.mileage),
          enginePower: new FormControl(result.enginePower),
          gearbox: new FormControl(result.gearbox),
          fuel: new FormControl(result.fuel)
        })
      })
    } else if (this.router.url.includes('customer')) {
      this.carDealerClientService.getCustomer(this.router.url.substring(17))
      .subscribe(result => {
        this.updateCustomer = new FormGroup({
          id: new FormControl(result.id),
          email: new FormControl(result.email),
          phoneNumber: new FormControl(result.phoneNumber)
        })
      })
    } else if (this.router.url.includes('details')) {
      this.carDealerClientService.getDetail(this.router.url.substring(16))
      .subscribe(result => {
        this.updateDetails = new FormGroup({
          id: new FormControl(result.id),
          customer: new FormControl(result.customer),
          firstName: new FormControl(result.firstName),
          lastName: new FormControl(result.lastName),
          address: new FormControl(result.address)
        })
      })
    } else if (this.router.url.includes('sale')) {
      this.carDealerClientService.getSale(this.router.url.substring(13))
      .subscribe(result => {
        this.updateSale = new FormGroup({
          id: new FormControl(result.id),
          car: new FormControl(result.car),
          customer: new FormControl(result.customer),
          date: new FormControl(result.date),
          price: new FormControl(result.price)
        })
      })
    }
  }

  updateBrand = new FormGroup({
    name: new FormControl(''),
    startDate: new FormControl(''),
    originCountry: new FormControl('')
  })

  updateModel = new FormGroup({
    id: new FormControl(''),
    brand: new FormControl(''),
    name: new FormControl(''),
    releaseDate: new FormControl('')
  })

  updateCar = new FormGroup({
    vin: new FormControl(''),
    model: new FormControl(''),
    mileage: new FormControl(''),
    enginePower: new FormControl(''),
    gearbox: new FormControl(''),
    fuel: new FormControl('')
  })

  updateCustomer = new FormGroup({
    id: new FormControl(),
    email: new FormControl(''),
    phoneNumber: new FormControl('')
  })

  updateDetails = new FormGroup({
    id: new FormControl(''),
    customer: new FormControl(''),
    firstName: new FormControl(''),
    lastName: new FormControl(''),
    address: new FormControl('')
  })

  updateSale = new FormGroup({
    id: new FormControl(this.router.url.substring(13)),
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

  onUpdateBrand() {
    this.carDealerClientService.updateBrand(this.updateBrand.value)
      .subscribe(
        response => {
          console.log(response);
          this.successAlert = true;
        },
        error => {
          console.error(error)
          this.errorAlert = true;
        })
  }

  onUpdateModel() {
    this.carDealerClientService.updateModel(this.updateModel.value)
      .subscribe(
        response => {
          console.log(response);
          this.successAlert = true;
        },
        error => {
          console.error(error)
          this.errorAlert = true;
        })
  }

  onUpdateCar() {
    this.carDealerClientService.updateCar(this.updateCar.value)
      .subscribe(
        response => {
          console.log(response);
          this.successAlert = true;
        },
        error => {
          console.error(error)
          this.errorAlert = true;
        })
  }

  onUpdateCustomer() {
    this.carDealerClientService.updateCustomer(this.updateCustomer.value)
      .subscribe(
        response => {
          console.log(response);
          this.successAlert = true;
        },
        error => {
          console.error(error)
          this.errorAlert = true;
        })
  }

  onUpdateDetails() {
    this.carDealerClientService.updateDetails(this.updateDetails.value)
      .subscribe(
        response => {
          console.log(response);
          this.successAlert = true;
        },
        error => {
          console.error(error)
          this.errorAlert = true;
        })
  }

  onUpdateSale() {
    this.carDealerClientService.updateSale(this.updateSale.value)
      .subscribe(
        response => {
          console.log(response);
          this.successAlert = true;
        },
        error => {
          console.error(error)
          this.errorAlert = true;
        })
  }
}
