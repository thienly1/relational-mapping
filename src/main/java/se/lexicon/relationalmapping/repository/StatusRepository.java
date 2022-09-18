package se.lexicon.relationalmapping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.relationalmapping.entity.Status;

public interface StatusRepository extends JpaRepository<Status, Integer> {
}
