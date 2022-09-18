package se.lexicon.relationalmapping.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.relationalmapping.entity.Car;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class CarDaoImpl implements CarDao{

    @PersistenceContext
    EntityManager entityManager;

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public Car save(Car car) {
        if(car==null) throw new IllegalArgumentException("car is null");
        entityManager.persist(car);
        return car;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Car> findAll() {
        TypedQuery query = entityManager.createQuery("SELECT c FROM Car c", Car.class);
        return query.getResultList();
    }
    @Transactional(readOnly = true)
    @Override
    public Optional<Car> findById(Integer id) {
        Car car = entityManager.find(Car.class, id);
        return Optional.ofNullable(car);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void delete(Car car) {
        findById(car.getCarId()).ifPresent(entityManager::remove);
    }
    @Override
    public List<Car> findByBrand(String brand) {
        TypedQuery query = entityManager.createQuery("SELECT c FROM Car c WHERE c.brand= ?1", Car.class);
        query.setParameter(1, brand);
        return query.getResultList();
    }

    @Override
    public List<Car> findByRedDateBetween(LocalDate end, LocalDate start) {
        TypedQuery query = entityManager.createQuery("SELECT c FROM Car c WHERE c.regDate between ?1 and ?2", Car.class);
        query.setParameter(1, start);
        query.setParameter(2,end);
        return query.getResultList();
    }
}
