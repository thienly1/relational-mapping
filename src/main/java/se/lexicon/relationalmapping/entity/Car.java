package se.lexicon.relationalmapping.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;

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

    @ManyToMany(mappedBy = "cars")
    private Collection<Status> statusCodes;

}
