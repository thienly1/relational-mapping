package se.lexicon.relationalmapping.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int statusId;
    @Column(length = 100)
    private String statusCode;

    @ManyToMany
//    @JoinTable(name = "cars_status", joinColumns = @JoinColumn(name = "cars_id"),
//            inverseJoinColumns = @JoinColumn(name = "status_id"))
    private Collection<Car> cars;
    protected Status() {
    }
    public Status(String statusCode) {
        this.statusCode = statusCode;
    }
    public void addCar(Car car){
        if(car== null) throw new IllegalArgumentException("car is null");
        if(cars==null) cars= new ArrayList<>();
        if(car.getStatusCodes()==null) car.setStatusCodes(new ArrayList<>());
        cars.add(car);
        car.getStatusCodes().add(this);
    }
    public void removeCarFromCollection(Car car){
        if(car==null) throw new IllegalArgumentException("Car is null, invalid value");
        if(cars!=null){
            if(cars.contains(car)){
                cars.remove(car);
                car.getStatusCodes().remove(this);
            }
        }
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public Collection<Car> getCars() {
        return cars;
    }

    public void setCars(Collection<Car> cars) {
        this.cars = cars;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Status status = (Status) o;
        return getStatusId() == status.getStatusId() && Objects.equals(getStatusCode(), status.getStatusCode()) && Objects.equals(getCars(), status.getCars());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStatusId(), getStatusCode(), getCars());
    }

    @Override
    public String toString() {
        return "Status{" +
                "statusId=" + statusId +
                ", statusCode='" + statusCode + '\'' +
                ", cars=" + cars +
                '}';
    }
}
