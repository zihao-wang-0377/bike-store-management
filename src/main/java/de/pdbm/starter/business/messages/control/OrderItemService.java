package de.pdbm.starter.business.messages.control;

import de.pdbm.starter.business.messages.entity.OrderItem;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

import static jakarta.persistence.PersistenceContextType.TRANSACTION;

@Stateless
public class OrderItemService {
    @PersistenceContext(type = TRANSACTION )
    EntityManager em;

    public void save(OrderItem orderItem){
        em.persist(orderItem);
    }

    public List<OrderItem> findAll(){
        return em.createQuery("select oi from OrderItem oi", OrderItem.class)
                .getResultList();
    }

    public OrderItem findById(Integer id){
        return em.find(OrderItem.class, id);
    }

    public List<OrderItem> findByOrderId(Integer orderId){
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
