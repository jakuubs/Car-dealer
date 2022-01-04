package main.repositories;

import main.models.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand, String> {

    @Query("SELECT b.originCountry, COUNT(b.name) FROM Brand b GROUP BY b.originCountry")
    List<String> countBrandsFromCountries();

}
