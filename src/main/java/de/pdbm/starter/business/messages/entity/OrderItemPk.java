package de.pdbm.starter.business.messages.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OrderItemPk implements Serializable {
    private Integer item_id;

    private Integer order_id;

    // Konstruktor
    public OrderItemPk() {
    }

    public OrderItemPk(Integer item_id, Integer order_id) {
        this.item_id = item_id;
        this.order_id = order_id;
    }

    // Getter und Setter
    public Integer getItem_id() {
        return item_id;
    }

    public void setItem_id(Integer item_id) {
        this.item_id = item_id;
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }
}