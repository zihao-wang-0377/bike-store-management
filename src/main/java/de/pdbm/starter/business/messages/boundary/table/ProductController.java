package de.pdbm.starter.business.messages.boundary.table;

import de.pdbm.starter.business.messages.control.ProductService;
import de.pdbm.starter.business.messages.entity.Product;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.math.BigDecimal;
import java.util.List;

@Named
@RequestScoped
public class ProductController {
private Integer productId;
private BigDecimal price;

private Integer year;

private String name;

private Integer brandId;
private Integer categoryId;
private List<Product> productList;
@Inject
    ProductService productService;
@PostConstruct
    public void productListInit(){
    productList=productService.findAll();
}
public List<Product> getProductList(){
    return productList;
}
    public String navigateToHomePage() {
        return "homePage.xhtml?faces-redirect=true";
    }
}


