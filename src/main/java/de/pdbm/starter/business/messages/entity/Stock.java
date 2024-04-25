package de.pdbm.starter.business.messages.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "stocks")
public class Stock {
    @ManyToOne
    @MapsId("product_id")
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @MapsId("store_id")
    @JoinColumn(name = "store_id")
    private Store store;

    @EmbeddedId
    private StockPk stockPk;

    private Integer quantity;

    // Konstruktor
    public Stock() {
    }

    // Getter und Setter
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public StockPk getStockPk() {
        return stockPk;
    }

    public void setStockPk(StockPk stockPk) {
        this.stockPk = stockPk;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}