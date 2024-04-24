package de.pdbm.starter.business.messages.service;

import de.pdbm.starter.business.messages.entity.OrderItem;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.io.Serializable;
import java.util.List;

import static jakarta.persistence.PersistenceContextType.TRANSACTION;

@Stateless
public class OrderItemService implements Serializable {
    @PersistenceContext(type = TRANSACTION )
    EntityManager em;

    public void save(OrderItem orderItem){
        if (orderItem.getOrderItemPk() == null) {
            em.persist(orderItem);
        } else {
            em.merge(orderItem);
        }
    }

    public List<OrderItem> findPaginated(int page, int size){
        TypedQuery<OrderItem> query = em.createQuery("select c from OrderItem c", OrderItem.class);
        query.setFirstResult((page - 1) * size);
        query.setMaxResults(size);
        return query.getResultList();
    }

    public long getOrderItemCount(){
        return em.createQuery("select count(c) from OrderItem c", Long.class).getSingleResult();
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