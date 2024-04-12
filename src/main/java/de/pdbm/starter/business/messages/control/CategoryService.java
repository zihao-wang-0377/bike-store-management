package de.pdbm.starter.business.messages.control;

import de.pdbm.starter.business.messages.entity.Category;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class CategoryService {
    @PersistenceContext
    EntityManager em ;
    public Category findCategoryById(Integer id){
        return em.find(Category.class,id);
    }
}
