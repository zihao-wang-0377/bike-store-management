package de.pdbm.starter.business.messages.service;

import de.pdbm.starter.business.messages.entity.Category;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.io.Serializable;
import java.util.List;

@Stateless
public class CategoryService implements Serializable {
    @PersistenceContext
    EntityManager em;

    public void save(Category category) {
        em.persist(category);
    }

    public List<Category> findPaginated(int page, int size) {
        TypedQuery<Category> query = em.createQuery("select c from Category c", Category.class);
        query.setFirstResult((page - 1) * size);
        query.setMaxResults(size);
        return query.getResultList();
    }
    public Category findByCategoryName(String categoryName) {
        TypedQuery<Category> query = em.createQuery("select c from Category c where c.categoryName = :categoryName", Category.class);
        query.setParameter("categoryName", categoryName);
        return query.getSingleResult();
    }
    public long getCategoryCount() {
        return em.createQuery("select count(c) from Category c", Long.class).getSingleResult();
    }

    public Category findCategoryById(Integer id) {
        return em.find(Category.class, id);
    }

    public void delete(Category category) {
        if (!em.contains(category)) {
            category = em.merge(category);
        }
        em.remove(category);
    }
}