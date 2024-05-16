package de.pdbm.starter.business.messages.service;

import de.pdbm.starter.business.messages.entity.Customer;
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
public class CustomerService implements Serializable {
    @PersistenceContext(type = TRANSACTION )
    EntityManager em;

    public void save(Customer customer){
        em.persist(customer);
    }

    public List<Customer> findPaginated(int page, int size){
        TypedQuery<Customer> query = em.createQuery("select c from Customer c", Customer.class);
        query.setFirstResult((page-1)* size);//start page
        query.setMaxResults(size);//pagesize
        return query.getResultList();
    }

    public long getCustomerCount(){
        return em.createQuery("select count(c) from Customer c", Long.class).getSingleResult();
    }

    public Customer findById(Integer id){
        // Suche einen Kunden in der Datenbank anhand seiner ID
        return em.find(Customer.class, id);
    }

    public List<Customer> findByLastName(String lastname) {
        TypedQuery<Customer> query = em.createQuery("SELECT c FROM Customer c WHERE LOWER(c.lastname) LIKE LOWER(:lastname)", Customer.class);
        query.setParameter("lastname", "%" + lastname + "%");
        return query.getResultList();
    }

    public void delete(Customer customer) {
        if (!em.contains(customer)) {
            customer = em.merge(customer);
        }
        List<Long> referencedOrderIds = this.getReferencedOrderId(customer);


            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Hinweis", ": das Customer ist referenced by diese Orders, " + referencedOrderIds   ));

            em.remove(customer);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Erfolg", "Kunde erfolgreich gelöscht."));

    }

    public List<Long> getReferencedOrderId(Customer customer){
       TypedQuery<Long> query = em.createQuery(
               "select o.id FROM Order o where o.customer = :customer",Long.class
       );
        query.setParameter("customer", customer);
        return query.getResultList();
    }
    public void update(Customer customer) {
        em.merge(customer);
    }
}