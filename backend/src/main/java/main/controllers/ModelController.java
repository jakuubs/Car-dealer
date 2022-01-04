package main.controllers;

import main.models.Brand;
import main.models.Car;
import main.models.Model;
import main.repositories.BrandRepository;
import main.repositories.CarRepository;
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
@CrossOrigin(origins = "http://localhost:4200")
public class ModelController {

    private BrandRepository brandRepository;
    private ModelRepository modelRepository;
    private CarRepository carRepository;

    @Autowired
    public ModelController(BrandRepository brandRepository, ModelRepository modelRepository, CarRepository carRepository) {
        this.brandRepository = brandRepository;
        this.modelRepository = modelRepository;
        this.carRepository = carRepository;
    }

    @GetMapping(path = "/models", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Model> getModels() {
        List<Model> models = modelRepository.findAll();
        return models;
    }

    @GetMapping(path = "/models/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Model> getModel(@PathVariable long id) {
        return modelRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(path = "models/{id}/cars", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Car> getCarsOfModel(@PathVariable long id) {
        List<Car> cars = carRepository.findAllByModel_Id(id);
        return cars;
    }

    @GetMapping(path = "models/info", produces = MediaType.APPLICATION_JSON_VALUE)
    public HashMap<String, HashMap<String, Integer>> getModelsInfoByBrand() {
        List<String> models = modelRepository.countModelsFromBrands();
        HashMap<String, Integer> modelsInfo = new HashMap<>();
        HashMap<String, HashMap<String, Integer>> modelsByBrands = new HashMap<>();
        for (String info : models) {
            List<String> infos = Arrays.asList(info.split(","));
            modelsInfo.put(infos.get(0), parseInt(infos.get(1)));
        }
        modelsByBrands.put("modelsByBrands", modelsInfo);
        return modelsByBrands;
    }

    @PostMapping(path = "/brands/{name}/models", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Model> saveModel(@RequestBody Model model, @PathVariable String name) {
        if (!brandRepository.existsById(name))
            return ResponseEntity.notFound().build();
        else if (!modelRepository.existsById(model.getId())) {
            Brand brand = brandRepository.getOne(name);
            model.setBrand(brand);
            modelRepository.save(model);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/models/{id}")
                    .buildAndExpand(model.getId())
                    .toUri();
            return ResponseEntity.created(uri)
                    .body(model);
        } else
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @PutMapping("/brands/{name}/models/{id}")
    public ResponseEntity<Model> updateModel(@RequestBody Model model, @PathVariable String name, @PathVariable long id) {
        if (!brandRepository.existsById(name) || !modelRepository.existsById(id))
            return ResponseEntity.notFound().build();
        Model m = modelRepository.getOne(id);
        model.setId(id);
        if (m == null)
            return ResponseEntity.notFound().build();
        else {
            m.setName(model.getName( ));
            m.setReleaseDate(model.getReleaseDate());
            modelRepository.save(m);
            return ResponseEntity.ok(model);
        }
    }

    @DeleteMapping("/models/{id}")
    public ResponseEntity<Brand> deleteModel(@PathVariable long id) {
        modelRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/brands/{name}/models/{id}")
    public ResponseEntity<Brand> deleteModelByBrand(@PathVariable long id) {
        modelRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
