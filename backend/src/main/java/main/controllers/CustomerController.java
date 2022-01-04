package main.controllers;

import main.models.Customer;
import main.models.CustomerDetails;
import main.models.Sale;
import main.repositories.CustomerDetailsRepository;
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
@RequestMapping("/customers")
@CrossOrigin(origins = "http://localhost:4200")
public class CustomerController {

    private CustomerRepository customerRepository;
    private CustomerDetailsRepository detailsRepository;
    private SaleRepository saleRepository;

    @Autowired
    public CustomerController(CustomerRepository customerRepository, CustomerDetailsRepository detailsRepository, SaleRepository saleRepository) {
        this.customerRepository = customerRepository;
        this.detailsRepository = detailsRepository;
        this.saleRepository = saleRepository;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Customer> getCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers;
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> getCustomer(@PathVariable long id) {
        return customerRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(path = "/{id}/details", produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomerDetails getDetailsOfCustomer(@PathVariable long id) {
        CustomerDetails details = detailsRepository.findByCustomer_Id(id);
        return details;
    }

    @GetMapping(path = "/{id}/sales", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Sale> getSalesOfCustomer(@PathVariable long id) {
        List<Sale> sales = saleRepository.findAllByCustomer_Id(id);
        return sales;
    }

    @GetMapping(path = "/info", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Long> countCustomers() {
        Map<String, Long> allCustomers = Map.of("numberOfCustomers", customerRepository.count());
        return allCustomers;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer) {
        if (!customerRepository.existsById(customer.getId())) {
            customerRepository.save(customer);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(customer.getId())
                    .toUri();
            return ResponseEntity.created(uri)
                    .body(customer);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer, @PathVariable long id) {
        Customer c = customerRepository.getOne(id);
        customer.setId(c.getId());
        if (c == null)
            return ResponseEntity.notFound().build();
        else {
            c.setEmail(customer.getEmail());
            c.setPhoneNumber(customer.getPhoneNumber());
            customerRepository.save(c);
            return ResponseEntity.ok(customer);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable long id) {
        customerRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
