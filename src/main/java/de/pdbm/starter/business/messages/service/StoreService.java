package de.pdbm.starter.business.messages.service;

import de.pdbm.starter.business.messages.entity.Store;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.io.Serializable;

import static jakarta.persistence.PersistenceContextType.TRANSACTION;

public class StoreService implements Serializable {
    @PersistenceContext(type = TRANSACTION)
    EntityManager em;

    public Store findStoreById(Integer id) {
        return em.find(Store.class, id);
    }
}
