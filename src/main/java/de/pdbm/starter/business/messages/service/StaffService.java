package de.pdbm.starter.business.messages.service;

import de.pdbm.starter.business.messages.entity.Customer;
import de.pdbm.starter.business.messages.entity.Product;
import de.pdbm.starter.business.messages.entity.Staff;
import jakarta.ejb.Stateless;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.io.Serializable;
import java.util.List;

import static jakarta.persistence.PersistenceContextType.TRANSACTION;
@Stateless
public class StaffService implements Serializable {
    @PersistenceContext(type = TRANSACTION)
    EntityManager em;

    public Staff findStaffById(Integer id){
        return  em.find(Staff.class,id);
    }

    public List<Staff> getAllStaff(){
        TypedQuery<Staff> query = em.createQuery(
                "select s from Staff s", Staff.class
        );
        return query.getResultList();
    }
    public List<Staff> findPaginated(int page, int size){
        TypedQuery<Staff> query = em.createQuery("select s from Staff s", Staff.class);
        query.setFirstResult((page-1)* size);//start page
        query.setMaxResults(size);//pagesize
        return query.getResultList();
    }

    public long getStaffCount(){
        return em.createQuery("select count(s) from Staff s", Long.class).getSingleResult();
    }
    public void save(Staff staff){
        em.persist(staff);
    }

    public void delete(Staff staff) {
        if (!em.contains(staff)) {
            staff = em.merge(staff);
        }
       // List<Long> referencedProductIds = this.getReferencedProductId(product);


//        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
//                "Fehler", ": das Produkt ist referenced by diese Order" + referencedProductIds + "auf null bevor Sie es löschen: " ));

        em.remove(staff);
//        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
//                "Erfolg", "Produkt erfolgreich gelöscht."));

    }

    public void update(Staff staff) {
        em.merge(staff);
    }

}