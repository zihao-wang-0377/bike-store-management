package de.pdbm.starter.business.messages.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "stores")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store")
    private Integer storeId;
}
