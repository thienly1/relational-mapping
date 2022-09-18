package se.lexicon.relationalmapping.dao;

import java.util.List;
import java.util.Optional;

public interface GenericsCRUD<T, ID> {
    T save( T t);
    List<T> findAll();
    Optional<T> findById(ID id);
    void delete(T t);
}
