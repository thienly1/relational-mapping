package se.lexicon.relationalmapping.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int statusId;
    @Column(length = 100)
    private String statusCode;

    @ManyToMany
    private Collection<Car> cars;
}
