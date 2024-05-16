package de.pdbm.starter.business.messages.service;

import de.pdbm.starter.business.messages.entity.Brand;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
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

    public void delete(Brand brand) {
        if (!em.contains(brand)) {
            brand = em.merge(brand);
        }
        List<Long> referencedProductIds = this.getReferencedProductId(brand);
        if (referencedProductIds.size() > 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Hinweis", "das Brand ist referenced by diese Products, " + referencedProductIds));
        } else {
            em.remove(brand);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Erfolg", "Brand erfolgreich gelöscht."));
        }
    }

    public List<Long> getReferencedProductId(Brand brand){
        TypedQuery<Long> query = em.createQuery(
                "select p.id FROM Product p where p.brand = :brand",Long.class
        );
        query.setParameter("brand", brand);
        return query.getResultList();
    }

    public void update(Brand brand){
        em.merge(brand);
    }

    public List<Brand> getAllBrand(){
        TypedQuery<Brand> query = em.createQuery(
                "select s from Brand s ", Brand.class
        );
        return query.getResultList();
    }
}