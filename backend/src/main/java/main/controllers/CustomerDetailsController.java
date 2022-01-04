package main.controllers;

import main.models.Customer;
import main.models.CustomerDetails;
import main.repositories.CustomerDetailsRepository;
import main.repositories.CustomerRepository;
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
public class CustomerDetailsController {

    private CustomerRepository customerRepository;
    private CustomerDetailsRepository detailsRepository;

    @Autowired
    public CustomerDetailsController(CustomerRepository customerRepository, CustomerDetailsRepository detailsRepository) {
        this.customerRepository = customerRepository;
        this.detailsRepository = detailsRepository;
    }

    @GetMapping(path = "/details", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CustomerDetails> getDetails() {
        List<CustomerDetails> details = detailsRepository.findAll();
        return details;
    }

    @GetMapping(path = "/details/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerDetails> getDetail(@PathVariable long id) {
        return detailsRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(path = "/details/info", produces = MediaType.APPLICATION_JSON_VALUE)
    public HashMap<String, HashMap<String, Integer>> getNumberOfClientsWithFirstLetterOfName() {
        List<String> details = detailsRepository.countCustomersByFirstLetterOfName();
        HashMap<String, Integer> detailsInfo = new HashMap<>();
        HashMap<String, HashMap<String, Integer>> detailsByLetters = new HashMap<>();
        for (String info : details) {
            List<String> infos = Arrays.asList(info.split(","));
            detailsInfo.put(infos.get(0), parseInt(infos.get(1)));
        }
        detailsByLetters.put("clientsByFirstLetterOfName", detailsInfo);
        return detailsByLetters;
    }

    @PostMapping(path = "/customers/{id}/details", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerDetails> saveDetails(@RequestBody CustomerDetails details, @PathVariable long id) {
        if (!customerRepository.existsById(id))
            return ResponseEntity.notFound().build();
        else if (!detailsRepository.existsById(details.getId())) {
            Customer customer = customerRepository.getOne(id);
            details.setCustomer(customer);
            detailsRepository.save(details);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/details/{id}")
                    .buildAndExpand(details.getId())
                    .toUri();
            return ResponseEntity.created(uri)
                    .body(details);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PutMapping("/customers/{id}/details")
    public ResponseEntity<CustomerDetails> updateDetails(@RequestBody CustomerDetails details,
                                                         @PathVariable long id) {
        Customer c = customerRepository.getOne(id);
        if (!customerRepository.existsById(id) || !detailsRepository.existsById(c.getDetails().getId()))
            return ResponseEntity.notFound().build();
        CustomerDetails d = detailsRepository.getOne(c.getDetails().getId());
        details.setId(d.getId());
        if (d == null)
            return ResponseEntity.notFound().build();
        else {
            d.setFirstName(details.getFirstName());
            d.setLastName(details.getLastName());
            d.setAddress(details.getAddress());
            detailsRepository.save(d);
            return ResponseEntity.ok(details);
        }
    }

    @DeleteMapping("/details/{id}")
    public ResponseEntity<CustomerDetails> deleteDetails(@PathVariable long id) {
        customerRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
