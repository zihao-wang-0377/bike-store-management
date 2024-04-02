package de.pdbm.starter.business.messages.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private BigDecimal total;

    @Column(name = "order_date")
    private LocalDate orderDate;

    @OneToMany(
            cascade = {CascadeType.REMOVE,CascadeType.PERSIST},
            mappedBy = "order"
    )
    private Set<OrderItem> orderItems;

    public Order(Integer id, Customer customer, BigDecimal total, LocalDate orderDate) {
//    public Order(Integer id, Customer customer, BigDecimal total) {
        this.id = id;
        this.customer = customer;
        this.total = total;
        this.orderDate = orderDate;
    }

    public Order() {
    }

    @Override
    public String toString() {
        return "Bestellung{" +
                "id=" + id +
                ", kunde=" + customer +
                ", gesamtbetrag=" + total +
                ", bestelldatum=" + orderDate +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customer.getId();
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }
}
