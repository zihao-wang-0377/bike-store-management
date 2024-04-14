package de.pdbm.starter.business.messages.control;

import de.pdbm.starter.business.messages.entity.Customer;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.io.Serializable;
import java.util.List;

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


    public void delete(Customer customer) {
        em.remove(customer);
    }

    public void update(Customer customer) {
        em.merge(customer);
    }
}
