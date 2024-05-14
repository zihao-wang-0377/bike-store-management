package de.pdbm.starter.business.messages.entity;

import de.pdbm.starter.business.messages.boundary.control.ForeignKeyExists;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer id;

    @Column(name = "list_price")
    @Positive(message = "Preis muss positiv sein")
    private BigDecimal price;

    @Column(name = "model_year")
    @Max(value = 2024, message = "Jahr kann nicht über 2024 sein")
    @Min(value = 1900, message = "Jahr kann nicht früher als 1900")
    @PositiveOrZero(message = "Jahr kann nicht negativ sein")
    private Integer modelYear;

    @Column(name = "product_name")
    @Pattern(regexp = "^[a-zA-Z0-9 ']+ - \\d{4}(\\/\\d{4})?$", message = "Bitte geben Sie nach dieser Format 'Surly Krampus Frameset - 2018' oder 'Electra Girl's Hawaii 1 (20-inch) - 2015/2016' ein")

    private String name;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    @ForeignKeyExists(entity = Brand.class, customerMessage = "das BrandId ,das Sie eingegeben haben existiert nicht")

    private Brand brand;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @ForeignKeyExists(entity = Category.class, customerMessage = "das CategorieId,das Sie eingegeben haben existiert nicht")

    private Category category;

    // Konstruktor
    public Product() {
    }

    public Product(BigDecimal price, Integer modelYear, String name, Brand brand, Category category) {
        this.price = price;
        this.modelYear = modelYear;
        this.name = name;
        this.brand = brand;
        this.category = category;
    }

    // Getter und Setter
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getModelYear() {
        return modelYear;
    }

    public void setModelYear(Integer modelYear) {
        this.modelYear = modelYear;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}