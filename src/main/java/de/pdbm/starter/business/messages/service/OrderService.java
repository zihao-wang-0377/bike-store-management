package de.pdbm.starter.business.messages.service;

import de.pdbm.starter.business.messages.entity.Customer;
import de.pdbm.starter.business.messages.entity.Order;
import jakarta.ejb.Stateless;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import static jakarta.persistence.PersistenceContextType.TRANSACTION;

@Stateless
public class OrderService implements Serializable {
    @PersistenceContext(type = TRANSACTION )
    EntityManager em;

    public void save(Order order){
        // Persistiere (speichere) die übergebene Bestellung in der Datenbank
        if (order.getId() == null) {
            em.persist(order);
        } else {
            em.merge(order);
        }
    }

    public List<Order> findPaginated(int page, int size){
        TypedQuery<Order> query = em.createQuery("select c from Order c", Order.class);
        query.setFirstResult((page - 1) * size);
        query.setMaxResults(size);
        return query.getResultList();
    }

    public Order findById(Integer id){
        // Suche eine Bestellung in der Datenbank anhand ihrer ID
        return em.find(Order.class, id);
    }

    public long getOrderCount(){
        return em.createQuery("select count(c) from Order c", Long.class).getSingleResult();
    }

    public void delete(Order order) {
        if (!em.contains(order)) {
            order = em.merge(order);
        }
        List<Long> referencedOrderItemIds = this.getReferencedOrderItemId(order);
        if (referencedOrderItemIds != null && !referencedOrderItemIds.isEmpty()){


            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Fehler", ": das Customer ist referenced by diese Orders, Sie können das nicht einfach wegmachen.Bitte setzen Sie die Customer_id von diesen Orders" + referencedOrderItemIds + "auf null bevor Sie es löschen: " ));
        } else {
            em.remove(order);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Erfolg", "Kunde erfolgreich gelöscht."));
        }
    }
    public List<Long> getReferencedOrderItemId(Order order){
        TypedQuery<Long> query = em.createQuery(
                "select o.orderItemPk FROM OrderItem o where o.orderItemPk.order_id = :order",Long.class
        );
        query.setParameter("order", order.getId());
        return query.getResultList();
    }

    public void update(Order order) {
        em.merge(order);
    }
}