package de.pdbm.starter.business.messages.control;

import de.pdbm.starter.business.messages.entity.Brand;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.io.Serializable;

public class BrandService implements Serializable {
    @PersistenceContext
    EntityManager em;

    public void save(Brand brand){
        em.persist(brand);

    }

    public Brand findBrandById(Integer brandId){
        return em.find(Brand.class,brandId);
    }
}