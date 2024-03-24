package de.pdbm.starter.business.messages.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "order_items")
public class OrderItem implements Serializable {
    @Id
    @Column(name = "order_item_id")
    private Integer id;

    @Id
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private Integer quantity;

    public OrderItem(Integer id, Order order, Customer customer, Integer quantity) {
        this.id = id;
        this.order = order;
        this.customer = customer;
        this.quantity = quantity;
    }

    public OrderItem() {
    }

    @Override
    public String toString() {
        return "OderItem{" +
                "id=" + id +
                ", order=" + order +
                ", customer=" + customer +
                ", quantity=" + quantity +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
