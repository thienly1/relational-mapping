package se.lexicon.relationalmapping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.relationalmapping.entity.Address;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Integer> {

    List<Address> findAllByCity(String city);
}
