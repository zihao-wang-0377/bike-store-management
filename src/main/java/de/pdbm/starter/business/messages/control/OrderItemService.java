package de.pdbm.starter.business.messages.control;

import de.pdbm.starter.business.messages.entity.Customer;
import de.pdbm.starter.business.messages.entity.Order;
import de.pdbm.starter.business.messages.entity.OrderItem;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;

import static jakarta.persistence.PersistenceContextType.TRANSACTION;

@Stateless
public class OrderItemService {
    @PersistenceContext(type = TRANSACTION )
    EntityManager em;

    public void save(OrderItem orderItem){
        em.persist(orderItem);
    }


    public List<OrderItem> findPaginated(int page, int size){
        TypedQuery<OrderItem> query = em.createQuery("select c from OrderItem c", OrderItem.class);
        query.setFirstResult(page * size);
        query.setMaxResults(size);
        return query.getResultList();
    }

    public OrderItem findById(Integer id){
        // Suche eine Bestellposition in der Datenbank anhand ihrer ID
        return em.find(OrderItem.class, id);
    }

    public List<OrderItem> findByOrderId(Integer orderId){
        // Suche alle Bestellpositionen in der Datenbank für eine bestimmte Bestellnummer
        return em.createQuery("select oi from OrderItem oi where oi.order.id = :orderId", OrderItem.class)
                .setParameter("orderId", orderId)
                .getResultList();
    }


    public void delete(OrderItem orderItem) {
        em.remove(orderItem);
    }

    public void update(OrderItem orderItem) {
        em.merge(orderItem);
    }
}
