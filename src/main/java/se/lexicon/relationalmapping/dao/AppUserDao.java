package se.lexicon.relationalmapping.dao;

import se.lexicon.relationalmapping.entity.AppUser;
import se.lexicon.relationalmapping.entity.Car;

import java.util.List;

public interface AppUserDao extends GenericsCRUD<AppUser, Integer> {

    AppUser findByEmail(String email);
    List<AppUser> findByName(String name);
}
