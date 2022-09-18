package se.lexicon.relationalmapping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.relationalmapping.entity.Address;
import se.lexicon.relationalmapping.entity.AppUser;

import java.util.List;

public interface AppUserRepository extends JpaRepository<AppUser, Integer> {

    AppUser findByEmailIgnoreCase(String email);
    AppUser findAppUserByEmailAndPassword(String email, String password);
    List<AppUser> findByNameContaining(String name);
    List<AppUser> findAllByAddress_AddressId(int id);
    List<AppUser> findAllByAddress_City(String city);
}
