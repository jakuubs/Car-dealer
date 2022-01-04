package main.controllers;

import main.models.Car;
import main.models.Customer;
import main.models.Sale;
import main.repositories.CarRepository;
import main.repositories.CustomerRepository;
import main.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class SaleController {

    private SaleRepository saleRepository;
    private CarRepository carRepository;
    private CustomerRepository customerRepository;

    @Autowired
    public SaleController(SaleRepository saleRepository, CarRepository carRepository, CustomerRepository customerRepository) {
        this.saleRepository = saleRepository;
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
    }

    @GetMapping(path = "/sales", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Sale> getSales() {
        List<Sale> sales = saleRepository.findAll();
        return sales;
    }

    @GetMapping(path = "/sales/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Sale> getSale(@PathVariable long id) {
        return saleRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(path = "/sales/info", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Integer> getTotalIncome() {
        Map<String, Integer> totalIncome = Map.of("totalIncome", saleRepository.getTotalIncome());
        return totalIncome;
    }

    @PostMapping(path = "/sales", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Sale> saveSaleWithCarAndCustomer(@RequestBody Sale sale,
                                                           @RequestParam(required = false) String vin,
                                                           @RequestParam(required = false) Long id) {
        Car car = null;
        Customer customer = null;
        if (vin != null) {
            if (!carRepository.existsById(vin))
                return ResponseEntity.notFound().build();
            else {
                car = carRepository.getOne(vin);
                sale.setCar(car);
            }
        }
        if (id != null) {
            if (!customerRepository.existsById(id))
                return ResponseEntity.notFound().build();
            else {
                customer = customerRepository.getOne(id);
                sale.setCustomer(customer);
            }
        }
        if (!saleRepository.existsById(sale.getId())) {
            saleRepository.save(sale);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/sales/{id}")
                    .buildAndExpand(sale.getId())
                    .toUri();
            return ResponseEntity.created(uri)
                    .body(sale);
        } else
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @PutMapping(path = "sales/{saleId}")
    public ResponseEntity<Sale> updateSale(@RequestBody Sale sale, @PathVariable long saleId,
                                           @RequestParam(required = false) String vin,
                                           @RequestParam(required = false) Long id) {
        Sale s = saleRepository.getOne(saleId);
        sale.setId(s.getId());
        if (s == null)
            return ResponseEntity.notFound().build();

        Car car = null;
        Customer customer = null;
        if (vin != null) {
            if (!carRepository.existsById(vin))
                return ResponseEntity.notFound().build();
            else {
                car = carRepository.getOne(vin);
                s.setCar(car);
            }
        }
        if (id != null) {
            if (!customerRepository.existsById(id))
                return ResponseEntity.notFound().build();
            else {
                customer = customerRepository.getOne(id);
                s.setCustomer(customer);
            }
        }

        s.setDate(sale.getDate());
        s.setPrice(sale.getPrice());

        saleRepository.save(s);
        return ResponseEntity.ok(sale);
    }

    @DeleteMapping("/sales/{id}")
    public ResponseEntity<Sale> deleteSale(@PathVariable long id) {
        saleRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
