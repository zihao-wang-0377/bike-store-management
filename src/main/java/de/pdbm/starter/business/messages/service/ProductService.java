package de.pdbm.starter.business.messages.service;

import de.pdbm.starter.business.messages.entity.Customer;
import de.pdbm.starter.business.messages.entity.Product;
import jakarta.ejb.Stateless;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.io.Serializable;
import java.util.ArrayList;
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
        query.setFirstResult((page -1) * size);
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
        if (!em.contains(product)) {
            product = em.merge(product);
        }
        List<Long> referencedProductIds = this.getReferencedProductId(product);
        if (referencedProductIds != null && !referencedProductIds.isEmpty()){

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Fehler", ": das Customer ist referenced by diese Orders, Sie können das nicht einfach wegmachen.Bitte setzen Sie die Customer_id von diesen Orders" + referencedProductIds + "auf null bevor Sie es löschen: " ));
        } else {
            em.remove(product);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Erfolg", "Kunde erfolgreich gelöscht."));
        }
    }
    public List<Long> getReferencedProductId(Product product){
        TypedQuery<Long> query1 = em.createQuery(
                "select s.stockPk FROM Stock s where s.stockPk.product_id = :product",Long.class
        );
        query1.setParameter("product", product.getId());
        List<Long> result1 = query1.getResultList();
        TypedQuery<Long> query2 = em.createQuery(
                "select o.orderItemPk FROM OrderItem o where o.product = :product",Long.class
        );

        query2.setParameter("product", product);
        List<Long> result2 = query2.getResultList();

        // Zusammenführen der Ergebnisse
        List<Long> combinedResults = new ArrayList<>(result1);
        combinedResults.addAll(result2);

        return combinedResults;
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