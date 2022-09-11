package se.lexicon.relationalmapping.entity;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int carId;
    @Column(length = 100)
    private String regNumber;
    @Column(length = 100,nullable = false)
    private String brand;
    @Column(length = 100)
    private String model;
    @CreatedDate
    private LocalDate regDate;

    @ManyToOne
    @JoinColumn(name = "use_id", referencedColumnName = "useId")
    private AppUser owner;

    @ManyToMany(mappedBy = "cars")
    private Collection<Status> statusCodes;

    protected Car() {
    }

    public Car(String regNumber, String brand, String model, AppUser owner) {
        this.regNumber = regNumber;
        this.brand = brand;
        this.model = model;
        this.owner = owner;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public LocalDate getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDate regDate) {
        this.regDate = regDate;
    }

    public AppUser getOwner() {
        return owner;
    }

    public void setOwner(AppUser owner) {
        this.owner = owner;
    }

    public Collection<Status> getStatusCodes() {
        return statusCodes;
    }

    public void setStatusCodes(Collection<Status> statusCodes) {
        this.statusCodes = statusCodes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return getCarId() == car.getCarId() && Objects.equals(getRegNumber(), car.getRegNumber()) && Objects.equals(getBrand(), car.getBrand()) && Objects.equals(getModel(), car.getModel()) && Objects.equals(getRegDate(), car.getRegDate()) && Objects.equals(getOwner(), car.getOwner()) && Objects.equals(getStatusCodes(), car.getStatusCodes());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCarId(), getRegNumber(), getBrand(), getModel(), getRegDate(), getOwner(), getStatusCodes());
    }

    @Override
    public String toString() {
        return "Car{" +
                "carId=" + carId +
                ", regNumber='" + regNumber + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", regDate=" + regDate +
                ", owner=" + owner +
                ", statusCodes=" + statusCodes +
                '}';
    }
}
