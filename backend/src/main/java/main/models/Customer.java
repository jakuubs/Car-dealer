package main.models;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private long id;
    @Column(name = "e_mail")
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;
    @OneToOne(mappedBy = "customer", orphanRemoval = true)
    @JsonBackReference
    private CustomerDetails details;
    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER, orphanRemoval = true)
    //@JsonBackReference
    private List<Sale> sales = new ArrayList<>();

    public Customer() {}

    public Customer(String email, String phoneNumber) {
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public CustomerDetails getDetails() {
        return details;
    }

    public void setDetails(CustomerDetails details) {
        this.details = details;
    }

    public List<Sale> getSales() {
        return sales;
    }

    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", phone_number='" + phoneNumber + '\'' +
                ", details=" + details.getFirstName() + " " + details.getLastName() +
                '}';
    }
}
