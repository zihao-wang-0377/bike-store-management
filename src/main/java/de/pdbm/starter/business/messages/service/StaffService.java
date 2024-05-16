package de.pdbm.starter.business.messages.service;

import de.pdbm.starter.business.messages.entity.Staff;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.io.Serializable;
import java.util.List;

import static jakarta.persistence.PersistenceContextType.TRANSACTION;

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

    public void save(Staff staff){
        em.persist(staff);
    }

    public void delete(Staff staff) {
        Staff mergedStaff = em.merge(staff);
        em.remove(mergedStaff);
    }

    public void update(Staff staff) {
        em.merge(staff);
    }

}