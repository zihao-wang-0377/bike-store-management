package de.pdbm.starter.business.messages.boundary;

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
        List<OrderItem> orderItems = em.createQuery("select oi from OrderItem oi", OrderItem.class)
                .getResultList();
        return orderItems;
    }

    public OrderItem findById(Integer id){
        OrderItem orderItem = em.find(OrderItem.class, id);
        return orderItem;
    }

    public List<OrderItem> findByOrderId(Integer orderId){
        List<OrderItem> orderItems = em.createQuery("select oi from OrderItem oi where oi.order.id = :orderId", OrderItem.class)
                .setParameter("orderId", orderId)
                .getResultList();
        return orderItems;
    }

    public void delete(OrderItem orderItem) {
        em.remove(orderItem);
    }

    public void update(OrderItem orderItem) {
        em.merge(orderItem);
    }
}
