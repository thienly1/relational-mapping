package se.lexicon.relationalmapping.entity;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
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
    @Column
    private LocalDate regDate;

    @ManyToOne
    @JoinColumn(name = "use_id", referencedColumnName = "useId")
    private AppUser owner;

    @ManyToMany(mappedBy = "cars", fetch = FetchType.LAZY)
    private Collection<Status> statusCodes;

    protected Car() {
    }

    public Car(String regNumber, String brand, String model) {
        this.regNumber = regNumber;
        this.brand = brand;
        this.model = model;
    }

    public Car(String regNumber, String brand, String model, LocalDate regDate) {
        this.regNumber = regNumber;
        this.brand = brand;
        this.model = model;
        this.regDate = regDate;
    }

    public void addStatusToCollection(Status status){
        if(status==null) throw new IllegalArgumentException("status is null, invalid value");
        if(statusCodes==null) statusCodes = new ArrayList<>();
        if(status.getCars()==null) status.setCars(new HashSet<>());
        statusCodes.add(status);
        status.getCars().add(this);
    }
    public void removeStatusFromCollection(Status status){
        if(status==null) throw new IllegalArgumentException("status is null, invalid value");
        if(statusCodes!=null){
            if(statusCodes.contains(status)){
                statusCodes.remove(status);
                status.getCars().remove(this);
            }
        }
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
//                ", statusCodes=" + statusCodes +
                '}';
    }
}
