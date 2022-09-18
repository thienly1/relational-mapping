package se.lexicon.relationalmapping.dao;

import org.springframework.stereotype.Repository;
import se.lexicon.relationalmapping.entity.Address;
import se.lexicon.relationalmapping.entity.AppUser;
import se.lexicon.relationalmapping.entity.Car;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class AppUserDaoImpl implements AppUserDao{

    @PersistenceContext
    EntityManager entityManager;


    @Override
    public AppUser findByEmail(String email) {
        TypedQuery<AppUser> selectQuery = entityManager.createQuery("SELECT a FROM AppUser a WHERE a.email= :em", AppUser.class);
        selectQuery.setParameter("em", email);
        return selectQuery.getSingleResult();
    }

    @Override
    public List<AppUser> findByName(String name) {
        TypedQuery<AppUser> selectQuery = entityManager.createQuery("SELECT a FROM AppUser a WHERE a.name= :na", AppUser.class);
        selectQuery.setParameter("na",name);
        return selectQuery.getResultList();
    }


    @Override
    public AppUser save(AppUser appUser) {
        if(appUser==null) throw new IllegalArgumentException("appUser is null, invalid value");
        entityManager.persist(appUser);
        return appUser;
    }

    @Override
    public List<AppUser> findAll() {
        TypedQuery<AppUser> query = entityManager.createQuery("SELECT a FROM AppUser a", AppUser.class);
        return query.getResultList();
    }

    @Override
    public Optional<AppUser> findById(Integer appUserId) {
        AppUser appUser = entityManager.find(AppUser.class,appUserId);
        return Optional.ofNullable(appUser);
    }

    @Override
    public void delete(AppUser appUser) {
        findById(appUser.getUseId()).ifPresent(entityManager::remove);
    }

}
