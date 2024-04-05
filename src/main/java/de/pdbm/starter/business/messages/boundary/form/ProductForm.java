package de.pdbm.starter.business.messages.boundary.form;

import de.pdbm.starter.business.messages.control.ProductService;
import de.pdbm.starter.business.messages.entity.Product;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.math.BigDecimal;

@Named
@ViewScoped
public class ProductForm implements Serializable {
    @Inject
    ProductService productService;

    @NotBlank(message = "Name kann nicht leer sein")
    private String name;

    @NotNull(message = "Preis kann nicht leer sein")
    @DecimalMin(value = "0.0", inclusive = false, message = "Preis muss größer als 0 sein")
    private BigDecimal price;

    private String description;

    public ProductForm() {
    }

    public void save() {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
//        product.setDescription(description);
        productService.save(product);
        setName(null);
        setPrice(null);
        setDescription(null);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String navigateToHomePage() {
        return "homePage.xhtml?faces-redirect=true";
    }
}
