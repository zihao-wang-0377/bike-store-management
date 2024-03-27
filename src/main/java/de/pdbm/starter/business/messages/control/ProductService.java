package de.pdbm.starter.business.messages.control;

import de.pdbm.starter.business.messages.entity.Product;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.io.Serializable;
import java.util.List;

import static jakarta.persistence.PersistenceContextType.TRANSACTION;

@Stateless
public class ProductService implements Serializable {
    @PersistenceContext (type = TRANSACTION)
    EntityManager em;

    public void save(Product product){
        em.persist(product);
    }

    public List<Product> findAll(){
        TypedQuery<Product> query = em.createQuery(
                "select p from Product p", Product.class
        );
        return query.getResultList();
    }

    public Product findById(Integer id){
        Product product = em.find(Product.class, id);
        return product;
    }

    public Product findByName(String name) {
        TypedQuery<Product> query = em.createQuery(
                "select p from Product p where p.name = :name", Product.class
        );
        query.setParameter("name", name);
        return query.getSingleResult();
    }

    public void delete(Product product) {
        em.remove(product);
    }

    public void deleteById(Integer id) {
        Product product = em.find(Product.class, id);
        em.remove(product);
    }

    public void deleteByName(String name) {
        Product product = findByName(name);
        em.remove(product);
    }

    public void update(Product product) {
        em.merge(product);
    }
}
