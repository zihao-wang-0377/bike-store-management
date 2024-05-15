package de.pdbm.starter.business.messages.entity;


import de.pdbm.starter.business.messages.boundary.control.ForeignKeyExists;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.HashSet;
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
    @PastOrPresent(message = "das Bestelldatum muss in der Vergangheit oder Gegenwart sein")

    private LocalDate orderDate;

    @Column(name = "order_status")
    @Pattern(regexp = "^[1]$", message = "Order status muss unter 1(bestellt), 2(versendet), 3(zustellt),  4(zugestellt). sein,aber am Anfang soll es 1 heißt es erst bestellt")

    private Integer orderStatus;

    @Column(name = "required_date")
    @Future(message = "Erwartetes Lieferdatum muss in der Zukunft sein")

    private LocalDate requiredDate;

    @Column(name = "shipped_date")
    private LocalDate shippedDate;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    //@ForeignKeyExists(entity = Customer.class,customerMessage = "KundeId,die Sie eingegeben haben existiert nicht")

    private Customer customer;

    @ManyToOne
    @JoinColumn (name = "staff_id")
    //@ForeignKeyExists(entity = Staff.class,customerMessage = "staffId,die Sie eingegeben haben existiert nicht")

    private Staff staff;

    @ManyToOne
    @JoinColumn(name = "store_id")
    //@ForeignKeyExists(entity = Store.class,customerMessage = "storeId,die Sie eingegeben haben existiert nicht")

    private Store store;

    @OneToMany(
            cascade = {CascadeType.REMOVE,CascadeType.PERSIST},
            mappedBy = "order"
    )
    private Set<OrderItem> orderItems = new HashSet<>();


    public Order(Integer id, LocalDate orderDate, Integer oderStatus, LocalDate requiredDate, LocalDate shippedDate, Customer customer, Staff staff, Store store) {
        this.id = id;
        this.orderDate = orderDate;
        this.orderStatus = oderStatus;
        this.requiredDate = requiredDate;
        this.shippedDate = shippedDate;
        this.customer = customer;
        this.staff = staff;
        this.store = store;
    }

    public Order(LocalDate orderDate, Integer orderStatus, LocalDate requiredDate, LocalDate shippedDate, Customer customer, Staff staff, Store store) {
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
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

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
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

    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}