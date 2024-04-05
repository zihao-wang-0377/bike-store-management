package de.pdbm.starter.business.messages.entity;

import jakarta.persistence.*;

@Entity
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store")
    private Integer storeId;
}
