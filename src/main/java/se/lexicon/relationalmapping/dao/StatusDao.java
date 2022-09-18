package se.lexicon.relationalmapping.dao;

import se.lexicon.relationalmapping.entity.Status;

public interface StatusDao extends GenericsCRUD<Status, Integer> {
    Status findByStatusCode(String statusCode);
}
