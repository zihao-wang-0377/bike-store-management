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

    @NotBlank(message = "Name darf nicht leer sein")
    private String name;

    @NotNull(message = "Preis darf nicht leer sein")
    @DecimalMin(value = "0.0", inclusive = false, message = "Preis muss größer als 0 sein")
    private BigDecimal price;

    private String description;

    public ProductForm() {
    }

    public void save() {
        // Neues Objekt wird erstellt und Name, Preis und Beschreibung gesetzt
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);

        // Speichere das Produkt
        productService.save(product);

        // Setze die Eingaben für Name, Preis und Beschreibung zurück
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