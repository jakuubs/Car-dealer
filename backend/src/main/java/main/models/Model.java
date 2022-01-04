package main.models;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "models")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_model")
    private long id;
    @ManyToOne
    @JoinColumn(name = "brand_name")
    //@JsonBackReference
    @JsonIdentityReference(alwaysAsId = true)
    private Brand brand;
    @Column(name = "name")
    private String name;
    @Column(name = "release_date")
    private int releaseDate;
    @OneToMany(mappedBy = "model", fetch = FetchType.EAGER, orphanRemoval = true)
    //@JsonBackReference
    private List<Car> cars = new ArrayList<>();

    public Model() {}

    public Model(String name, int releaseDate) {
        this.name = name;
        this.releaseDate = releaseDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(int releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    @Override
    public String toString() {
        return "Model{" +
                "id=" + id +
                ", brand=" + brand.getName() +
                ", name='" + name + '\'' +
                ", releaseDate=" + releaseDate +
                '}';
    }
}
