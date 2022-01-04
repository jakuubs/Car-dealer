package main.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

import javax.persistence.*;

@Entity
@Table(name = "sales")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sale")
    private long id;
    @ManyToOne
    @JoinColumn(name = "car_vin")
    //@JsonBackReference(value = "sale_car")
    @JsonIdentityReference(alwaysAsId = true)
    private Car car;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    //@JsonBackReference(value = "sale_customer")
    @JsonIdentityReference(alwaysAsId = true)
    private Customer customer;
    @Column(name = "sale_date")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;
    @Column(name = "price")
    private long price;

    public Sale() {}

    public Sale(LocalDate date, long price) {
        this.date = date;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "id=" + id +
                ", car=" + car.getVin() +
                ", customer=" + customer.getId() +
                ", date=" + date +
                ", price=" + price +
                '}';
    }
}
