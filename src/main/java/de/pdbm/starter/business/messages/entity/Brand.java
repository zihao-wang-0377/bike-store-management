package de.pdbm.starter.business.messages.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "brands")
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brand_id")
    private Integer brandId;
    @Column(name = "brand_name")
    private String brandName;

}
