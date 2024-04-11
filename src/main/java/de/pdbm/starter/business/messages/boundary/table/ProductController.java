package de.pdbm.starter.business.messages.boundary.table;

import de.pdbm.starter.business.messages.control.ProductService;
import de.pdbm.starter.business.messages.entity.Order;
import de.pdbm.starter.business.messages.entity.Product;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class ProductController implements Serializable {
private Integer productId;
private BigDecimal price;

private Integer year;

private String name;

private Integer brandId;
private Integer categoryId;
private List<Product> productList;
private int currentPage = 1;
private int pageSize = 10;
private long totalRecords;
@Inject
    ProductService productService;

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
    public void loadProduktList(){
        this.productList = productService.findPaginated(currentPage, pageSize);
    }
    public void nextPage(){
        currentPage++;
        loadProduktList();
    }
    public void prevPage(){
        if(currentPage > 0){
            currentPage--;
            loadProduktList();
        }
    }
    public void firstPage(){
        currentPage = 1;
        loadProduktList();
    }
    public void lastPage(){
        currentPage = getTotalPages();
        loadProduktList();
    }
    public int getTotalPages() {
        return (int) Math.ceil((double) totalRecords / pageSize);
    }
    public void setPage(int page){
        if(page >= 1 && page <= getTotalPages()){
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
        loadProduktList();
    }

    public String navigateToHomePage() {
        return "homePage.xhtml?faces-redirect=true";
    }
}


