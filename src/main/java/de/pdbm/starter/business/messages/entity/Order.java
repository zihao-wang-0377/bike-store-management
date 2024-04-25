package de.pdbm.starter.business.messages.entity;


import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer id;

    @Column(name = "order_date")
    private LocalDate orderDate;

    @Column(name = "order_status")
    private Integer oderStatus;

    @Column(name = "required_date")
    private LocalDate requiredDate;

    @Column(name = "shipped_date")
    private LocalDate shippedDate;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn (name = "staff_id")
    private Staff staff;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    public Order(Integer id, LocalDate orderDate, Integer oderStatus, LocalDate requiredDate, LocalDate shippedDate, Customer customer, Staff staff, Store store) {
        this.id = id;
        this.orderDate = orderDate;
        this.oderStatus = oderStatus;
        this.requiredDate = requiredDate;
        this.shippedDate = shippedDate;
        this.customer = customer;
        this.staff = staff;
        this.store = store;
    }

    public Order(LocalDate orderDate, Integer oderStatus, LocalDate requiredDate, LocalDate shippedDate, Customer customer, Staff staff, Store store) {
        this.orderDate = orderDate;
        this.oderStatus = oderStatus;
        this.requiredDate = requiredDate;
        this.shippedDate = shippedDate;
        this.customer = customer;
        this.staff = staff;
        this.store = store;
    }

    // Konstruktor
    public Order() {
    }

    public String getLocalizedOrderDate() {
        if (orderDate == null) {
            return "";
        }
        Locale locale = Locale.GERMANY; // Deutsch (Deutschland)
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).withLocale(locale);
        return orderDate.format(formatter);
    }

    // Getter und Setter
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

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public Integer getOderStatus() {
        return oderStatus;
    }

    public void setOderStatus(Integer oderStatus) {
        this.oderStatus = oderStatus;
    }

    public LocalDate getRequiredDate() {
        return requiredDate;
    }

    public void setRequiredDate(LocalDate requiredDate) {
        this.requiredDate = requiredDate;
    }

    public LocalDate getShippedDate() {
        return shippedDate;
    }

    public void setShippedDate(LocalDate shippedDate) {
        this.shippedDate = shippedDate;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}