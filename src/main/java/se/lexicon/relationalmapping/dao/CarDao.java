package se.lexicon.relationalmapping.dao;


import se.lexicon.relationalmapping.entity.Car;

import java.time.LocalDate;
import java.util.List;

public interface CarDao extends GenericsCRUD<Car, Integer>{
    List<Car> findByBrand(String brand);
    List<Car> findByRedDateBetween(LocalDate end, LocalDate start);
}
