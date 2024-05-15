package de.pdbm.starter.business.messages.service;

import de.pdbm.starter.business.messages.entity.Brand;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.io.Serializable;
import java.util.List;

public class BrandService implements Serializable {
    @PersistenceContext
    EntityManager em;

    public void save(Brand brand){
        em.persist(brand);

    }
    public List<Brand> getAllBrand(){
        TypedQuery<Brand> query = em.createQuery(
                "select s from Brand s ", Brand.class
        );
        return query.getResultList();
    }
    public Brand findBrandById(Integer brandId){
        return em.find(Brand.class,brandId);
    }
}