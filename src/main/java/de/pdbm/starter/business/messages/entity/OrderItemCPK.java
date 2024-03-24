package de.pdbm.starter.business.messages.entity;

import java.io.Serializable;

public class OrderItemCPK implements Serializable {
    private Integer id;
    private Order order;

    public OrderItemCPK(Integer id, Order order) {
        this.id = id;
        this.order = order;
    }

    public OrderItemCPK() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OrderItemCPK that = (OrderItemCPK) o;
        return id.equals(that.id) && order.equals(that.order);
    }

    @Override
    public int hashCode() {
        return id.hashCode() + order.hashCode();
    }
}
