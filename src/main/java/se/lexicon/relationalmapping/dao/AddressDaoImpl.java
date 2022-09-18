package se.lexicon.relationalmapping.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.relationalmapping.entity.Address;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class AddressDaoImpl implements AddressDao{

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    @Override
    public List<Address> findByStreet(String street) {
        TypedQuery<Address> selectQuery = entityManager.createQuery("SELECT a FROM Address a WHERE a.street= :st", Address.class);
        selectQuery.setParameter("st",street);
        return selectQuery.getResultList();
    }

    @Override
    public List<Address> findByCity(String city) {
        TypedQuery<Address> selectQuery = entityManager.createQuery("SELECT a FROM Address a WHERE a.city= :ct", Address.class);
        selectQuery.setParameter("ct",city);
        return selectQuery.getResultList();
    }

    @Override
    public Address save(Address address) {
        if(address== null) throw new IllegalArgumentException("Address is null, not allowed");
        if(address.getAddressId()<=0){
            entityManager.persist(address);
            return address;
        }
        entityManager.merge(address);
        return address;
    }

    @Override
    public List<Address> findAll() {
        TypedQuery<Address> query = entityManager.createQuery("SELECT a FROM Address a", Address.class);
        return query.getResultList();
    }

    @Override
    public Optional<Address> findById(Integer id) {
        Address address = entityManager.find(Address.class, id);
        return Optional.ofNullable(address);
    }

    @Override
    public void delete(Address address) {
        findById(address.getAddressId()).ifPresent(entityManager::remove);
    }
}
