package de.pdbm.starter.business.messages.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "stocks")
public class Stock {
    @Id
    private Integer product_id;
    @Id
    private Integer store_id;
    private Integer quantity;

    public Stock() {
    }

    public Stock(Integer product_id, Integer store_id, Integer quantity) {
        this.product_id = product_id;
        this.store_id = store_id;
        this.quantity = quantity;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public Integer getStore_id() {
        return store_id;
    }

    public void setStore_id(Integer store_id) {
        this.store_id = store_id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
