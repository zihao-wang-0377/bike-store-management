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
private int currentPage = 0;
private int pageSize = 10;
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

    public String navigateToHomePage() {
        return "homePage.xhtml?faces-redirect=true";
    }
}


