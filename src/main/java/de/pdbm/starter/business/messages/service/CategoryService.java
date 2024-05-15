package de.pdbm.starter.business.messages.service;

import de.pdbm.starter.business.messages.entity.Category;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.io.Serializable;
import java.util.List;

public class CategoryService implements Serializable {
    @PersistenceContext
    EntityManager em ;

    public Category findCategoryById(Integer id){
        return em.find(Category.class,id);
    }
public List<Category> getAllCategoryList(){
    TypedQuery<Category> query = em.createQuery(
            "select s from Category s", Category.class
    );
    return query.getResultList();
}
}