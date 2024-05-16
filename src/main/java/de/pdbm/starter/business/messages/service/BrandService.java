package de.pdbm.starter.business.messages.service;

import de.pdbm.starter.business.messages.entity.Brand;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.io.Serializable;
import java.util.List;

@Stateless
public class BrandService implements Serializable {
    @PersistenceContext
    EntityManager em;

    public void save(Brand brand){
        em.persist(brand);
    }

    public Brand findByName(String brandName) {
        TypedQuery<Brand> query = em.createQuery("select b from Brand b where b.brandName = :brandName", Brand.class);
        query.setParameter("brandName", brandName);
        return query.getSingleResult();
    }
    public List<Brand> findPaginated(int page, int size){
        TypedQuery<Brand> query = em.createQuery("select b from Brand b", Brand.class);
        query.setFirstResult((page-1)* size);
        query.setMaxResults(size);
        return query.getResultList();
    }

    public long getBrandCount(){
        return em.createQuery("select count(b) from Brand b", Long.class).getSingleResult();
    }

    public Brand findBrandById(Integer brandId){
        return em.find(Brand.class,brandId);
    }

    public List<Brand> findByBrandName(String brandName) {
        TypedQuery<Brand> query = em.createQuery("SELECT b FROM Brand b WHERE LOWER(b.brandName) LIKE LOWER(:brandName)", Brand.class);
        query.setParameter("brandName", "%" + brandName + "%");
        return query.getResultList();
    }

    public void delete(Brand brand) {
        if (!em.contains(brand)) {
            brand = em.merge(brand);
        }
        em.remove(brand);
    }
}