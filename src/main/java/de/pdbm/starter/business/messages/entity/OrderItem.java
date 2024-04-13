package de.pdbm.starter.business.messages.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Entity
@Table(name = "order_items")
@IdClass(OrderItemPk.class)
public class OrderItem {

    @Id
    @Column(name = "item_id")
    private Integer item_id;


    @Id
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
@Column(name = "discount")
    private BigDecimal discount;
@Column(name = "list_price")
private BigDecimal price;
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public OrderItem(Integer id, Order order, BigDecimal discount, BigDecimal price, Integer quantity, Product product) {
        this.item_id = id;
        this.order = order;
        this.discount = discount;
        this.price = price;
        this.quantity = quantity;
        this.product = product;
    }

    public OrderItem(Order order, BigDecimal discount, BigDecimal price, Integer quantity, Product product) {
        this.order = order;
        this.discount = discount;
        this.price = price;
        this.quantity = quantity;
        this.product = product;
    }
//    public OrderItem(Integer id, Order order, Customer customer, Product product, Integer quantity) {
//        this.id = id;
//        this.order = order;
//        this.customer = customer;
//        this.product = product;
//        this.quantity = quantity;
//    }

    public OrderItem() {
    }

//    @Override
//    public String toString() {
//        return "Bestellposition{" +
//                "id=" + id +
//                ", bestellung=" + order +
//                ", kunde=" + customer +
//                ", produkt=" + product +
//                ", anzahl=" + quantity +
//                '}';
//    }

    public Integer getId() {
        return item_id;
    }

    public void setId(Integer id) {
        this.item_id = id;
    }

    public Integer getOrderId() {
        return order.getId();
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

//    public Integer getCustomerId() {
//        return customer.getId();
//    }
//
//    public Customer getCustomer() {
//        return customer;
//    }
//
//    public void setCustomer(Customer customer) {
//        this.customer = customer;
//    }

    public Integer getProductId() {
        return product.getId();
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
