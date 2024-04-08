package de.pdbm.starter.business.messages.control;

import de.pdbm.starter.business.messages.entity.Customer;
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

    public List<Product> findPaginated(int page, int size){
        TypedQuery<Product> query = em.createQuery("select c from Product c", Product.class);
        query.setFirstResult(page * size);
        query.setMaxResults(size);
        return query.getResultList();
    }

    public Product findById(Integer id) {
        // Suche ein Produkt in der Datenbank anhand seiner ID
        return em.find(Product.class, id);
    }
    public long getProductCount(){
        return em.createQuery("select count(c) from Product c", Long.class).getSingleResult();
    }


    public Product findByName(String name) {
        // Erstelle eine Abfrage, um ein Produkt anhand seines Namens zu suchen
        TypedQuery<Product> query = em.createQuery(
                "select p from Product p where p.name = :name", Product.class
        );

        // Setze den Parameter für den Produktnamen in der Abfrage
        query.setParameter("name", name);

        // Führe die Abfrage aus und gib das gefundene Produkt zurück
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
