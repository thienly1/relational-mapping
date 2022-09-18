package se.lexicon.relationalmapping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import se.lexicon.relationalmapping.entity.Car;
import se.lexicon.relationalmapping.entity.Status;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public interface CarRepository extends JpaRepository<Car, Integer> {
    Car findCarByRegNumberIs(String regNumber);

    Collection<Car> findAllByStatusCodesContains(Status status);
    List<Car> findAllByRegDateBefore(LocalDate regDate);
    List<Car> findAllByRegDateAfter(LocalDate regDate);
    List<Car> findAllByRegDateBetween(LocalDate start, LocalDate end);
}
