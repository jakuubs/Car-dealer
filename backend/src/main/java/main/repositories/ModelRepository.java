package main.repositories;

import main.models.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {

    @Query
    List<Model> findAllByBrand_Name(String name);

    @Query("SELECT m.brand.name, COUNT(m.name) FROM Model m GROUP BY m.brand.name")
    List<String> countModelsFromBrands();
}
