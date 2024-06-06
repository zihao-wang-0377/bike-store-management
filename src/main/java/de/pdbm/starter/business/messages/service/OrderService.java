package de.pdbm.starter.business.messages.service;

import de.pdbm.starter.business.messages.entity.Customer;
import de.pdbm.starter.business.messages.entity.Order;
import jakarta.ejb.Stateless;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static jakarta.persistence.PersistenceContextType.TRANSACTION;

@Stateless
public class OrderService implements Serializable {
    @PersistenceContext
    EntityManager em;

    public void save(Order order) {
        // Persistiere (speichere) die übergebene Bestellung in der Datenbank
        if (order.getId() == null) {
            em.persist(order);
        } else {
            em.merge(order);
        }
    }

    public List<Order> findPaginated(int page, int size) {
        TypedQuery<Order> query = em.createQuery("select c from Order c", Order.class);
        query.setFirstResult((page - 1) * size);
        query.setMaxResults(size);
        return query.getResultList();
    }

    public Order findById(Integer id) {
        // Suche eine Bestellung in der Datenbank anhand ihrer ID
        return em.find(Order.class, id);
    }

    public long getOrderCount() {
        return em.createQuery("select count(c) from Order c", Long.class).getSingleResult();
    }

    public void delete(Order order) {
        if (!em.contains(order)) {
            order = em.merge(order);
        }
        em.remove(order);
    }

    public List<Long> getReferencedOrderItemId(Order order) {
        TypedQuery<Long> query = em.createQuery(
                "select o.orderItemPk FROM OrderItem o where o.orderItemPk.order_id = :order", Long.class
        );
        query.setParameter("order", order.getId());
        return query.getResultList();
    }

    public List<Order> findByOrderDate(LocalDate orderDate) {
        TypedQuery<Order> query = em.createQuery("SELECT o FROM Order o WHERE o.orderDate = :orderDate", Order.class);
        query.setParameter("orderDate", orderDate);
        return query.getResultList();
    }

    public List<Order> getOrderByCustomerId(Integer id) {
        TypedQuery<Order> query = em.createQuery("select o from Order o where o.customer = :customer", Order.class);
        query.setParameter("customer", id);
        List<Order> result = query.getResultList();
        return result;
    }

    @Transactional
    public void update(Order order) {
        Order existingOrder = em.find(Order.class, order.getId());
        if (existingOrder != null) {
            existingOrder.setOrderStatus(order.getOrderStatus());
            existingOrder.setOrderDate(order.getOrderDate());
            existingOrder.setShippedDate(order.getShippedDate());
            existingOrder.setRequiredDate(order.getRequiredDate());
            existingOrder.setCustomer(order.getCustomer());
            existingOrder.setStaff(order.getStaff());
            existingOrder.setStore(order.getStore());
            em.merge(existingOrder);

            em.flush();
        } else {

            throw new IllegalArgumentException("Order not found");
        }

    }
}
