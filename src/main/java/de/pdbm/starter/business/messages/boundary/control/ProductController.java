package de.pdbm.starter.business.messages.boundary.control;

import de.pdbm.starter.business.messages.service.BrandService;
import de.pdbm.starter.business.messages.service.CategoryService;
import de.pdbm.starter.business.messages.service.ProductService;
import de.pdbm.starter.business.messages.entity.Brand;
import de.pdbm.starter.business.messages.entity.Category;
import de.pdbm.starter.business.messages.entity.Product;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class ProductController implements Serializable {
    @Inject
    ProductService productService;

    @Inject
    BrandService brandService;

    @Inject
    CategoryService categoryService;

    private Integer productId;

    @Positive(message = "Preis muss positiv sein")
    private BigDecimal price;

    @Max(value = 2024, message = "Jahr kann nicht über 2024 sein")
    @Min(value = 1900, message = "Jahr kann nicht früher als 1900")
    @PositiveOrZero(message = "Jahr kann nicht negativ sein")
    private Integer year;

    @Pattern(regexp = "^[a-zA-Z0-9 ']+ - \\d{4}(\\/\\d{4})?$", message = "Bitte geben Sie nach dieser Format 'Surly Krampus Frameset - 2018' oder 'Electra Girl's Hawaii 1 (20-inch) - 2015/2016' ein")
    private String name;

    @ForeignKeyExists(entity = Brand.class, customerMessage = "das BrandId ,das Sie eingegeben haben existiert nicht")
    private Integer brandId;

    @ForeignKeyExists(entity = Category.class, customerMessage = "das CategorieId,das Sie eingegeben haben existiert nicht")
    private Integer categoryId;

    private List<Product> productList;

    private int currentPage = 1;

    private int pageSize = 10;

    private long totalRecords;

    private int clicks;


    // Konstruktor
    public ProductController() {
    }

    // Objekt erstellen und speichern
    public void save() {
        Brand brand = brandService.findBrandById(brandId);
        Category category = categoryService.findCategoryById(categoryId);
        productService.save(new Product(price, year, name, brand, category));
    }
    public boolean isButtonDisplayed(){
        return clicks % 2 == 1;
    }

    public void incrementClicks(){
        clicks++;
    }

    // Paginierung-Methoden
    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public List<Product> getProducts() {
        if (productList == null || productList.isEmpty()) {
            loadProduktList();
        }
        return productList;
    }

    public void loadProduktList() {
        this.productList = productService.findPaginated(currentPage, pageSize);
    }

    public void nextPage() {
        currentPage++;
        loadProduktList();
    }

    public void prevPage() {
        if (currentPage > 0) {
            currentPage--;
            loadProduktList();
        }
    }

    public void firstPage() {
        currentPage = 1;
        loadProduktList();
    }

    public void lastPage() {
        currentPage = getTotalPages();
        loadProduktList();
    }

    public int getTotalPages() {
        return (int) Math.ceil((double) totalRecords / pageSize);
    }

    public void setPage(int page) {
        if (page >= 1 && page <= getTotalPages()) {
            currentPage = page;
            loadProduktList();
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

    public long getTotalRecords() {
        this.totalRecords = productService.getProductCount();
        return totalRecords;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
        currentPage = 1;
        loadProduktList();
    }

    public void goToPage() {
        if (currentPage < 1) {
            currentPage = 1;
            loadProduktList();
        } else if (currentPage > getTotalPages()) {
            currentPage = getTotalPages();
            loadProduktList();
        } else {
            loadProduktList();
        }
    }

    // Getter und Setter
    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    // Navigation fuer Zurueck Button
    public String navigateToHomePage() {
        return "homePage.xhtml?faces-redirect=true";
    }
}