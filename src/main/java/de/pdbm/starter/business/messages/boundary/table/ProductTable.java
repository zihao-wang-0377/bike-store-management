package de.pdbm.starter.business.messages.boundary.table;

import de.pdbm.starter.business.messages.control.ProductService;
import de.pdbm.starter.business.messages.entity.Product;
import jakarta.faces.annotation.View;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class ProductTable implements Serializable {
    @Inject
    ProductService productService;

    public List<Product> getProducts() {
        return productService.findAll();
    }
}
