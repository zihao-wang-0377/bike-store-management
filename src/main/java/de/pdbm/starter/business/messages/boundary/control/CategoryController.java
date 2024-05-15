package de.pdbm.starter.business.messages.boundary.control;

import de.pdbm.starter.business.messages.entity.Category;
import de.pdbm.starter.business.messages.service.CategoryService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class CategoryController implements Serializable {
    private Integer categoryId;
    private String categoryName;

    private List<Category> categoryList;

    @Inject
    CategoryService categoryService;

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

    public List<Category> getCategoryList() {
        return categoryList;
    }
@PostConstruct
    public void setCategoryList() {
        this.categoryList = categoryService.getAllCategoryList();
    }

    private int clicks;
    public boolean isButtonDisplayed(){
        return clicks % 2 == 1;
    }

    public void incrementClicks(){
        clicks++;
    }
}
