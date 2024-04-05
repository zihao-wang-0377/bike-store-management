package de.pdbm.starter.business.messages.entity;

import jakarta.persistence.*;

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
    private BigDecimal price;
@Column(name = "model_year")
private Integer modelYear;
    @Column(name = "product_name")
    private String name;

//    private String description;

//    public Product(Integer id, String name, BigDecimal price, String description) {
//        this.id = id;
//        this.name = name;
//        this.price = price;
//        this.description = description;
//    }

    public Product() {
    }

    public Product(Integer id, BigDecimal price, Integer modelYear, String name) {
        this.id = id;
        this.price = price;
        this.modelYear = modelYear;
        this.name = name;
    }
//    @Override
//    public String toString() {
//        return "Produkt{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", preis=" + price +
//                ", beschreibung='" + description + '\'' +
//                '}';
//    }

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

//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }


    public Integer getModelYear() {
        return modelYear;
    }

    public void setModelYear(Integer modelYear) {
        this.modelYear = modelYear;
    }
}
