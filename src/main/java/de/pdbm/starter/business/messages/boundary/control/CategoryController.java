package de.pdbm.starter.business.messages.boundary.control;

import de.pdbm.starter.business.messages.entity.Category;
import de.pdbm.starter.business.messages.service.CategoryService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Named
@SessionScoped
public class CategoryController implements Serializable {
    @Inject
    CategoryService categoryService;

    @Inject
    Validator validator;

    private Integer categoryId;

    private String categoryName;

    private List<Category> categoryList;

    private int currentPage = 1;

    private int pageSize = 10;

    private long totalRecords;

    private Category selectedCategory;

    // Konstruktor
    public CategoryController() {
    }

    public CategoryController(Integer categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    // Objekt erstellen und speichern
    public void save() {
        Category category = new Category(categoryName);
        Set<ConstraintViolation<Category>> violations = validator.validate(category);

        if (!violations.isEmpty()) {
            for (ConstraintViolation<Category> violation : violations) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, violation.getMessage(), null));
            }
            return;
        }
        categoryService.save(category);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Kategorie erfolgreich gespeichert", null));
    }

    // Paginierung-Methoden
    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public List<Category> getCategoryList() {
        if (categoryList == null || categoryList.isEmpty()) {
            loadCategoryList();
        }
        return categoryList;
    }

    public void loadCategoryList() {
        this.categoryList = categoryService.findPaginated(currentPage, pageSize);
    }

    public void nextPage() {
        currentPage++;
        loadCategoryList();
    }

    public void prevPage() {
        if (currentPage > 0) {
            currentPage--;
            loadCategoryList();
        }
    }

    public void firstPage() {
        currentPage = 1;
        loadCategoryList();
    }

    public void lastPage() {
        currentPage = getTotalPages();
        loadCategoryList();
    }

    public long getTotalRecords() {
        totalRecords = categoryService.getCategoryCount();
        return totalRecords;
    }

    public int getTotalPages() {
        return (int) Math.ceil((double) totalRecords / pageSize);
    }

    public void setPage(int page) {
        if (page >= 1 && page <= getTotalPages()) {
            currentPage = page;
            loadCategoryList();
        }
    }

    public Integer[] getPageNumbers() {
        int startPage = Math.max(1, currentPage - 2);
        int endPage = Math.min(getTotalPages(), currentPage + 4);
        List<Integer> pageNumbers = new ArrayList<>();
        for (int i = startPage; i <= endPage; i++) {
            pageNumbers.add(i);
        }
        return pageNumbers.toArray(new Integer[0]);
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void goToPage() {
        if (currentPage < 1) {
            currentPage = 1;
            loadCategoryList();
        } else if (currentPage > getTotalPages()) {
            currentPage = getTotalPages();
            loadCategoryList();
        } else {
            loadCategoryList();
        }
    }

    // Getter und Setter
    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Category getSelectedCategory() {
        return selectedCategory;
    }

    // Suche nach Kategoriennamen
    public void searchByCategoryName() {
        categoryList = categoryService.findCategoryByName(categoryName);
    }

    // Eintrag loeschen
    public void deleteCategory(Category category) {
        categoryService.delete(category);
        loadCategoryList();
        getTotalRecords();
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Kategorie erfolgreich gelöscht", null));

    }
}
