package main.repositories;

import main.models.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query
    List<Sale> findAllByCar_Vin(String vin);

    @Query
    List<Sale> findAllByCustomer_Id(long id);

    @Query("SELECT SUM(s.price) FROM Sale s")
    int getTotalIncome();
}
