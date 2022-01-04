import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpParams} from '@angular/common/http';
import {Observable, Subject} from 'rxjs';
import {tap} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class CarDealerClientService {
  
  constructor(private httpClient: HttpClient) { }

  private _refreshNeeded$ = new Subject<void>();

  get refreshNeeded$() {
    return this._refreshNeeded$;
  }

  public getBrands(): Observable<Brand[]> {
    return this.httpClient.get<Brand[]>('http://localhost:8080/brands');
  }

  public getBrand(name): Observable<Brand> {
    return this.httpClient.get<Brand>('http://localhost:8080/brands/' + name);
  }

  public getBrandsInfo(): Observable<Map<string, Map<string, number>>> {
    return this.httpClient.get<Map<string, Map<string, number>>>('http://localhost:8080/brands/info');
  }

  public addBrand(brand) {
    return this.httpClient.post('http://localhost:8080/brands', brand)
      .pipe(tap(() => {
        this._refreshNeeded$.next();
      }))
  } 

  public updateBrand(brand) {
    return this.httpClient.put('http://localhost:8080/brands/' + brand.name, brand)
      .pipe(tap(() => {
        this._refreshNeeded$.next();
      }))
  }

  public deleteBrand(name: string) {
    return this.httpClient.delete('http://localhost:8080/brands/' + name)
      .pipe(tap(() => {
        this._refreshNeeded$.next();
      }));
  }

  public getModels(): Observable<Model[]> {
    return this.httpClient.get<Model[]>('http://localhost:8080/models');
  }

  public getModel(id): Observable<Model> {
    return this.httpClient.get<Model>('http://localhost:8080/models/' + id);
  }

  public getModelsInfo(): Observable<Map<string, Map<string, number>>> {
    return this.httpClient.get<Map<string, Map<string, number>>>('http://localhost:8080/models/info');
  }
  
  public addModel(model) {
    var brandName = model.brand;
    delete model.brand;
    return this.httpClient.post('http://localhost:8080/brands/' + brandName + '/models', model)
      .pipe(tap(() => {
        this._refreshNeeded$.next();
      }))
  }

  public updateModel(model) {
    var brandName = model.brand;
    delete model.brand;
    return this.httpClient.put('http://localhost:8080/brands/' + brandName + '/models/' + model.id, model)
      .pipe(tap(() => {
        this._refreshNeeded$.next();
      }))
  }

  public deleteModel(id: number) {
    return this.httpClient.delete('http://localhost:8080/models/' + id)
      .pipe(tap(() => {
        this._refreshNeeded$.next();
      }));
  }

  public getCars(): Observable<Car[]> {
    return this.httpClient.get<Car[]>('http://localhost:8080/cars');
  }

  public getCar(vin): Observable<Car> {
    return this.httpClient.get<Car>('http://localhost:8080/cars/' + vin);
  }

  public getCarsInfo(): Observable<Map<string, Map<string, number>>> {
    return this.httpClient.get<Map<string, Map<string, number>>>('http://localhost:8080/cars/info');
  }

  public addCar(car) {
    var modelId = car.model;
    delete car.model;
    return this.httpClient.post('http://localhost:8080/models/' + modelId + '/cars', car)
      .pipe(tap(() => {
        this._refreshNeeded$.next();
      }))
  }

  public updateCar(car) {
    var modelId = car.model;
    delete car.model;
    return this.httpClient.put('http://localhost:8080/models/' + modelId + '/cars/' + car.vin, car)
      .pipe(tap(() => {
        this._refreshNeeded$.next();
      }))
  }

  public deleteCar(vin: string) {
    return this.httpClient.delete('http://localhost:8080/cars/' + vin)
      .pipe(tap(() => {
        this._refreshNeeded$.next();
      }));
  }

  public getCustomers(): Observable<Customer[]> {
    return this.httpClient.get<Customer[]>('http://localhost:8080/customers');
  }

  public getCustomer(id): Observable<Customer> {
    return this.httpClient.get<Customer>('http://localhost:8080/customers/' + id);
  }

  public getCustomersInfo(): Observable<Map<string, number>> {
    return this.httpClient.get<Map<string, number>>('http://localhost:8080/customers/info');
  }

  public addCustomer(customer) {
    return this.httpClient.post('http://localhost:8080/customers', customer)
      .pipe(tap(() => {
        this._refreshNeeded$.next();
      }))
  }

  public updateCustomer(customer) {
    return this.httpClient.put('http://localhost:8080/customers/' + customer.id, customer)
      .pipe(tap(() => {
        this._refreshNeeded$.next();
      }))
  }

  public deleteCustomer(id: number) {
    return this.httpClient.delete('http://localhost:8080/customers/' + id)
      .pipe(tap(() => {
        this._refreshNeeded$.next();
      }));
  }

  public getDetails(): Observable<CustomerDetails[]> {
    return this.httpClient.get<CustomerDetails[]>('http://localhost:8080/details');
  }

  public getDetail(id): Observable<CustomerDetails> {
    return this.httpClient.get<CustomerDetails>('http://localhost:8080/details/' + id);
  }

  public getDetailsInfo(): Observable<Map<string, Map<string, number>>> {
    return this.httpClient.get<Map<string, Map<string, number>>>('http://localhost:8080/details/info');
  }

  public addDetails(detail) {
    var customerId = detail.customer;
    delete detail.customer;
    return this.httpClient.post('http://localhost:8080/customers/' + customerId + '/details', detail)
      .pipe(tap(() => {
        this._refreshNeeded$.next();
      }))
  }

  public updateDetails(detail) {
    var customerId = detail.customer;
    delete detail.customer;
    return this.httpClient.put('http://localhost:8080/customers/' + customerId + '/details', detail)
      .pipe(tap(() => {
        this._refreshNeeded$.next();
      }))
  }

  public deleteDetails(id: number) {
    return this.httpClient.delete('http://localhost:8080/details/' + id)
      .pipe(tap(() => {
        this._refreshNeeded$.next();
      }));
  }

  public getSales(): Observable<Sale[]> {
    return this.httpClient.get<Sale[]>('http://localhost:8080/sales');
  }

  public getSale(id): Observable<Sale> {
    return this.httpClient.get<Sale>('http://localhost:8080/sales/' + id);
  }

  public getSalesInfo(): Observable<Map<string, number>> {
    return this.httpClient.get<Map<string, number>>('http://localhost:8080/sales/info');
  }

  public addSale(sale) {
    var carVin = sale.car;
    var clientId = sale.customer;
    delete sale.car;
    delete sale.customer;
    return this.httpClient.post('http://localhost:8080/sales', sale,
      {params: new HttpParams().set('vin', carVin).set('id', clientId)})
      .pipe(tap(() => {
        this._refreshNeeded$.next();
      }))
  }

  public updateSale(sale) {
    var carVin = sale.car;
    var clientId = sale.customer;
    delete sale.car;
    delete sale.customer;
    return this.httpClient.put('http://localhost:8080/sales/' + sale.id, sale,
      {params: new HttpParams().set('vin', carVin).set('id', clientId)})
      .pipe(tap(() => {
        this._refreshNeeded$.next();
      }))
  }

  public deleteSale(id: number) {
    return this.httpClient.delete('http://localhost:8080/sales/' + id)
      .pipe(tap(() => {
        this._refreshNeeded$.next();
      }));
  }
}

export interface Brand {
  name: string;
  startDate: number;
  originCountry: string;
  models: Model[];
}

export interface Model {
  id: number;
  brand: string;
  name: string;
  releaseDate: number;
  cars: Car[];
}

export interface Car {
  vin: string;
  model: number;
  mileage: number;
  enginePower: number;
  gearbox: string;
  fuel: string;
  sales: Sale[];
}

export interface Customer {
  id: number;
  email: string;
  phoneNumber: string;
  details: number;
  sales: Sale[];
}

export interface CustomerDetails {
  id: number;
  firstName: string;
  lastName: string;
  address: string;
  customer: number;
}

export interface Sale {
  id: number;
  car: string;
  customer: number;
  date: object;
  price: number;
}
