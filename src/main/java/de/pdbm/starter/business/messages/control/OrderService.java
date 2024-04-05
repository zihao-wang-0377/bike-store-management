package de.pdbm.starter.business.messages.control;

import de.pdbm.starter.business.messages.entity.Order;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.io.Serializable;
import java.util.List;

import static jakarta.persistence.PersistenceContextType.TRANSACTION;

@Stateless
public class OrderService implements Serializable {
    @PersistenceContext(type = TRANSACTION )
    EntityManager em;

    public void save(Order order){
        // Persistiere (speichere) die übergebene Bestellung in der Datenbank
        em.persist(order);
    }

    public List<Order> findAll(){
        // Erstelle eine Abfrage, um alle Bestellungen und ihre Kunden aus der Datenbank abzurufen
        TypedQuery<Order> query = em.createQuery(
                "select o from Order o join fetch o.customer", Order.class
        );

        // Führe die Abfrage aus und gib die Ergebnisliste zurück
        return query.getResultList();
    }

    public Order findById(Integer id){
        // Suche eine Bestellung in der Datenbank anhand ihrer ID
        return em.find(Order.class, id);
    }


    public void delete(Order order) {
        em.remove(order);
    }

    public void update(Order order) {
        em.merge(order);
    }
}
