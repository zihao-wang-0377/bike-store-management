package de.pdbm.starter.business.messages.entity;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class StockPk implements Serializable {
    private Integer product_id;

    private Integer store_id;

    // Konstruktor
    public StockPk() {
    }

    public StockPk(Integer product_id, Integer store_id) {
        this.product_id = product_id;
        this.store_id = store_id;
    }

    // Getter und Setter
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StockPk stockPk = (StockPk) o;
        return Objects.equals(product_id, stockPk.product_id) && Objects.equals(store_id, stockPk.store_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product_id, store_id);
    }
    @Override
    public String toString(){
        return "StockPk{" +
                "ProduktId=" + product_id +
                ", StoreId=" + store_id +
                '}';
    }
}
