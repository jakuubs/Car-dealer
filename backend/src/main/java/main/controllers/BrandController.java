package main.controllers;

import main.models.Brand;
import main.models.Model;
import main.repositories.BrandRepository;
import main.repositories.ModelRepository;
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
@RequestMapping("/brands")
@CrossOrigin(origins = "http://localhost:4200")
public class BrandController {

    private BrandRepository brandRepository;
    private ModelRepository modelRepository;

    @Autowired
    public BrandController(BrandRepository brandRepository, ModelRepository modelRepository) {
        this.brandRepository = brandRepository;
        this.modelRepository = modelRepository;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Brand> getBrands() {
        List<Brand> brands = brandRepository.findAll();
        return brands;
    }

    @GetMapping(path = "/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Brand> getBrand(@PathVariable String name) {
        return brandRepository.findById(name)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(path = "/{name}/models", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Model> getModelsOfBrand(@PathVariable String name) {
        List<Model> models = modelRepository.findAllByBrand_Name(name);
        return models;
    }

    @GetMapping(path = "/info", produces = MediaType.APPLICATION_JSON_VALUE)
    public HashMap<String, HashMap<String, Integer>> getBrandsInfoByCountry() {
        List<String> brands = brandRepository.countBrandsFromCountries();
        HashMap<String, Integer> brandsInfo = new HashMap<>();
        HashMap<String, HashMap<String, Integer>> brandsByCountries = new HashMap<>();
        for (String info : brands) {
            List<String> infos = Arrays.asList(info.split(","));
            brandsInfo.put(infos.get(0), parseInt(infos.get(1)));
        }
        brandsByCountries.put("brandsByCountry", brandsInfo);
        return brandsByCountries;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Brand> saveBrand(@RequestBody Brand brand) {
        if (brand.getName() == null || brand.getName() == "")
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        if (!brandRepository.existsById(brand.getName())) {
            brandRepository.save(brand);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{name}")
                    .buildAndExpand(brand.getName())
                    .toUri();
            return ResponseEntity.created(uri)
                    .body(brand);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PutMapping("/{name}")
    public ResponseEntity<Brand> updateBrand(@RequestBody Brand brand, @PathVariable String name) {
        Brand b = brandRepository.getOne(name);
        if (b == null)
            return ResponseEntity.notFound().build();
        else {
            b.setOriginCountry(brand.getOriginCountry());
            b.setStartDate(brand.getStartDate());
            brandRepository.save(b);
            return ResponseEntity.ok(brand);
        }
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Brand> deleteBrand(@PathVariable String name) {
        brandRepository.deleteById(name);
        return ResponseEntity.noContent().build();
    }
}
