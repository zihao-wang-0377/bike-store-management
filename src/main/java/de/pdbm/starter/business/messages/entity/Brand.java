package de.pdbm.starter.business.messages.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "brands")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brand_id")
    private Integer brandId;

    @Column(name = "brand_name")
    private String brandName;

    @OneToMany(mappedBy = "brand", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Set<Product> products = new HashSet<>();

    // Konstruktor
    public Brand() {
    }

    public Brand(String brandName) {
        this.brandName = brandName;
    }

    // Getter und Setter
    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    @Override
    public String toString() {
        return brandName;
    }
}
