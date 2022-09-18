package se.lexicon.relationalmapping.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.relationalmapping.entity.Status;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class StatusDaoImpl implements StatusDao{

    @PersistenceContext
    EntityManager entityManager;

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public Status save(Status status) {
        if(status==null) throw new IllegalArgumentException("status is null");
        entityManager.persist(status);
        return status;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Status> findAll() {
        TypedQuery<Status> query= entityManager.createQuery("SELECT s FROM Status s", Status.class);
        return query.getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Status> findById(Integer integer) {
        Status status = entityManager.find(Status.class, integer);
        return Optional.ofNullable(status);
    }

    @Override
    public void delete(Status status) {
        if(status==null) throw new IllegalArgumentException("status is null");
        if(findById(status.getStatusId()).isPresent()) entityManager.remove(status);

    }

    @Override
    public Status findByStatusCode(String statusCode) {
        TypedQuery<Status> query= entityManager.createQuery("SELECT s FROM Status s WHERE s.statusCode= :st", Status.class);
        query.setParameter("st", statusCode);
        return query.getSingleResult();
    }

}
