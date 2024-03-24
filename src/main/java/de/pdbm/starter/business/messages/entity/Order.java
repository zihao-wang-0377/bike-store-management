package de.pdbm.starter.business.messages.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
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
    private Set<OrderItem> orderItems = new HashSet<>();
}
