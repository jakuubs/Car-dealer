package main.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "brands")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")
public class Brand {

    @Id
    @Column(name = "name_brand")
    private String name;
    @Column(name = "start_year")
    private int startDate;
    @Column(name = "origin_country")
    private String originCountry;
    @OneToMany(mappedBy = "brand", fetch = FetchType.EAGER, orphanRemoval = true)
    //@JsonBackReference
    private List<Model> models = new ArrayList<>();

    public Brand() {}

    public Brand(String name, int startDate, String originCountry) {
        this.name = name;
        this.startDate = startDate;
        this.originCountry = originCountry;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStartDate() {
        return startDate;
    }

    public void setStartDate(int startDate) {
        this.startDate = startDate;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    public List<Model> getModels() {
        return models;
    }

    public void setModels(List<Model> models) {
        this.models = models;
    }

    @Override
    public String toString() {
        return "Brand{" +
                "name=" + name +
                ", startDate=" + startDate +
                ", originCountry='" + originCountry + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Brand brand = (Brand) o;
        return getStartDate() == brand.getStartDate() &&
                getName().equals(brand.getName()) &&
                Objects.equals(getOriginCountry(), brand.getOriginCountry());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getStartDate(), getOriginCountry());
    }
}
