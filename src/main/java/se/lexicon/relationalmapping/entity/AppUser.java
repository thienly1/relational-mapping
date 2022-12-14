package se.lexicon.relationalmapping.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int useId;
    @Column(length = 200, nullable = false, unique = true)
    private String email;
    @Column(length = 200,nullable = false)
    private String name;
    @Column(length = 100,nullable = false)
    private String password;
    @OneToOne(cascade = CascadeType.PERSIST)
//    @JoinColumn(name = "address_id", referencedColumnName = "addressId")
    private Address address;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.PERSIST)
    private Collection<Car> ownedCars;

    protected AppUser() {
    }

    public AppUser(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }
    public void addNewCar(Car car){
        if(car==null) throw new IllegalArgumentException("car is null, invalid value");
        if(ownedCars==null) ownedCars= new ArrayList<>();
        ownedCars.add(car);
        car.setOwner(this);
    }
    public void removeCar(Car car){
        if(car==null) throw new IllegalArgumentException("car is null, invalid value");
        if(ownedCars!=null){
            if(ownedCars.contains(car)){
                car.setOwner(null);
                ownedCars.remove(car);
            }

        }

    }


    public int getUseId() {
        return useId;
    }

    public void setUseId(int useId) {
        this.useId = useId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Collection<Car> getOwnedCars() {
        return ownedCars;
    }

    public void setOwnedCars(Collection<Car> ownedCars) {
        this.ownedCars = ownedCars;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppUser appUser = (AppUser) o;
        return getUseId() == appUser.getUseId() && Objects.equals(getEmail(), appUser.getEmail()) && Objects.equals(getName(), appUser.getName()) && Objects.equals(getPassword(), appUser.getPassword()) && Objects.equals(getAddress(), appUser.getAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUseId(), getEmail(), getName(), getPassword(), getAddress());
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "useId=" + useId +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", address=" + address +
                '}';
    }
}
