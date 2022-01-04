package main.controllers;

import main.models.Car;
import main.models.Model;
import main.models.Sale;
import main.repositories.CarRepository;
import main.repositories.ModelRepository;
import main.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static java.lang.Integer.parseInt;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CarController {

    private ModelRepository modelRepository;
    private CarRepository carRepository;
    private SaleRepository saleRepository;

    @Autowired
    public CarController(ModelRepository modelRepository, CarRepository carRepository, SaleRepository saleRepository) {
        this.modelRepository = modelRepository;
        this.carRepository = carRepository;
        this.saleRepository = saleRepository;
    }

    @GetMapping(path = "/cars", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Car> getCars() {
        List<Car> models = carRepository.findAll();
        return models;
    }

    @GetMapping(path = "/cars/{vin}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Car> getCar(@PathVariable String vin) {
        return carRepository.findById(vin)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(path = "cars/{vin}/sales", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Sale> getSalesOfCar(@PathVariable String vin) {
        List<Sale> sales = saleRepository.findAllByCar_Vin(vin);
        return sales;
    }

    @GetMapping(path = "cars/info", produces = MediaType.APPLICATION_JSON_VALUE)
    public HashMap<String, HashMap<String, Integer>> getCarsInfoByFuelType() {
        List<String> cars = carRepository.countCarsByFuelType();
        HashMap<String, Integer> carsInfo = new HashMap<>();
        HashMap<String, HashMap<String, Integer>> carsByFuel = new HashMap<>();
        for (String info : cars) {
            List<String> infos = Arrays.asList(info.split(","));
            carsInfo.put(infos.get(0), parseInt(infos.get(1)));
        }
        carsByFuel.put("carsByFuelType", carsInfo);
        return carsByFuel;
    }

    @PostMapping(path = "/models/{id}/cars", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Car> saveCar(@RequestBody Car car, @PathVariable long id) {
        if (!modelRepository.existsById(id))
            return ResponseEntity.notFound().build();
        else if (!carRepository.existsById(car.getVin())) {
            Model model = modelRepository.getOne(id);
            car.setModel(model);
            carRepository.save(car);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/cars/{vin}")
                    .buildAndExpand(car.getVin())
                    .toUri();
            return ResponseEntity.created(uri)
                    .body(car);
        } else
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @PutMapping("/models/{id}/cars/{vin}")
    public ResponseEntity<Car> updateCar(@RequestBody Car car, @PathVariable long id, @PathVariable String vin) {
        if (!modelRepository.existsById(id) || !carRepository.existsById(vin))
            return ResponseEntity.notFound().build();
        Car c = carRepository.getOne(vin);
        if (c == null)
            return ResponseEntity.notFound().build();
        else {
            c.setMileage(car.getMileage());
            c.setEnginePower(car.getEnginePower());
            c.setGearbox(car.getGearbox());
            c.setFuel(car.getFuel());
            carRepository.save(c);
            return ResponseEntity.ok(car);
        }
    }

    @DeleteMapping("/cars/{vin}")
    public ResponseEntity<Car> deleteCar(@PathVariable String vin) {
        carRepository.deleteById(vin);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/models/{id}/cars/{vin}")
    public ResponseEntity<Car> deleteCarByModel(@PathVariable String vin) {
        carRepository.deleteById(vin);
        return ResponseEntity.noContent().build();
    }
}
