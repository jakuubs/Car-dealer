package main.models;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cars")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "vin")
public class Car {

    @Id
    @Column(name = "vin")
    private String vin;
    @ManyToOne
    @JoinColumn(name = "model_id")
    //@JsonBackReference
    @JsonIdentityReference(alwaysAsId = true)
    private Model model;
    @Column(name = "mileage")
    private long mileage;
    @Column(name = "engine_power")
    private int enginePower;
    @Column(name = "gearbox")
    private String gearbox;
    @Column(name = "fuel")
    private String fuel;
    @OneToMany(mappedBy = "car", fetch = FetchType.EAGER, orphanRemoval = true)
    //@JsonBackReference
    private List<Sale> sales = new ArrayList<>();

    public Car() {}

    public Car(String vin, long mileage, int enginePower, String gearbox, String fuel) {
        this.vin = vin;
        this.mileage = mileage;
        this.enginePower = enginePower;
        this.gearbox = gearbox;
        this.fuel = fuel;
        this.model = model;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public long getMileage() {
        return mileage;
    }

    public void setMileage(long mileage) {
        this.mileage = mileage;
    }

    public int getEnginePower() {
        return enginePower;
    }

    public void setEnginePower(int enginePower) {
        this.enginePower = enginePower;
    }

    public String getGearbox() {
        return gearbox;
    }

    public void setGearbox(String gearbox) {
        this.gearbox = gearbox;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public List<Sale> getSales() {
        return sales;
    }

    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }

    @Override
    public String toString() {
        return "Car{" +
                "vin='" + vin + '\'' +
                ", brand=" + model.getBrand().getName() +
                ", model=" + model.getName() +
                ", mileage=" + mileage +
                ", enginePower=" + enginePower +
                ", gearbox='" + gearbox + '\'' +
                ", fuel='" + fuel + '\'' +
                '}';
    }
}
