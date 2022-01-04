package main.repositories;

import main.models.CustomerDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerDetailsRepository extends JpaRepository<CustomerDetails, Long> {

    @Query
    CustomerDetails findByCustomer_Id(long id);

    @Query("SELECT SUBSTRING(d.lastName, 1, 1), COUNT(d.id) FROM CustomerDetails d GROUP BY SUBSTRING(d.lastName, 1, 1)")
    List<String> countCustomersByFirstLetterOfName();
}
