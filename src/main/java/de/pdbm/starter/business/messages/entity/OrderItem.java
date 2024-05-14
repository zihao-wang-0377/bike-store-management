package de.pdbm.starter.business.messages.entity;

import de.pdbm.starter.business.messages.boundary.control.ForeignKeyExists;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

@Entity
@Table(name = "order_items")
public class OrderItem {
    @EmbeddedId
    private OrderItemPk orderItemPk;

    @ManyToOne
    @JoinColumn(name = "order_id", insertable = false, updatable = false)
    @MapsId("order_id")
    private Order order;

    @Column(name = "discount")
    @Min(value = 0, message = "Der Rabatt muss mindestens 0 sein")
    @Max(value = 1, message = "Der Rabatt muss innerhalb 1 sein")
    private BigDecimal discount;

    @Column(name = "list_price")
    @Positive(message = "Preis muss positiv sein")
    private BigDecimal price;
    @PositiveOrZero(message = "Anzahl muss größer als 0")
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @ForeignKeyExists(entity = Product.class,customerMessage = "ProduktId,das Sie gegeben haben existiert nicht")

    private Product product;

    // Konstruktor
    public OrderItem() {
    }

    public OrderItem(OrderItemPk orderItemPk, Order order, BigDecimal discount, BigDecimal price, Integer quantity, Product product) {
        this.orderItemPk = new OrderItemPk();
        this.orderItemPk.setOrder_id(order.getId());
        this.orderItemPk.setItem_id(orderItemPk.getItem_id());
        this.order = order;
        this.discount = discount;
        this.price = price;
        this.quantity = quantity;
        this.product = product;
    }

    // Getter und Setter
    public OrderItemPk getOrderItemPk() {
        return orderItemPk;
    }

    public void setOrderItemPk(OrderItemPk orderItemPk) {
        this.orderItemPk = orderItemPk;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

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

    public Integer getItemId() {
        return orderItemPk != null ? orderItemPk.getItem_id() : null;
    }

    public Integer getOrderId() {
        return orderItemPk != null ? orderItemPk.getOrder_id() : null;
    }

    public void setItemId(Integer itemId) {
        this.orderItemPk.setItem_id(itemId);
    }

    public void setOrderId(Integer OrderId) {
        this.orderItemPk.setOrder_id(OrderId);
    }
}