package de.pdbm.starter.business.messages.control;

import de.pdbm.starter.business.messages.entity.Staff;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.io.Serializable;

import static jakarta.persistence.PersistenceContextType.TRANSACTION;

public class StaffService implements Serializable {
    @PersistenceContext(type = TRANSACTION)
    EntityManager em;

    public Staff findStaffById(Integer id){
        return  em.find(Staff.class,id);
    }
}