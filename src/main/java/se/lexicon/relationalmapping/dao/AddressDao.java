package se.lexicon.relationalmapping.dao;

import se.lexicon.relationalmapping.entity.Address;

import java.util.List;

public interface AddressDao extends GenericsCRUD<Address, Integer> {

    List<Address> findByStreet(String street);
    List<Address> findByCity(String city);
}
