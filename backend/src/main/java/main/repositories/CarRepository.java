package main.repositories;

import main.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, String> {

    @Query
    List<Car> findAllByModel_Id(long id);

    @Query("SELECT c.fuel, COUNT(c.vin) FROM Car c GROUP BY c.fuel")
    List<String> countCarsByFuelType();
}
