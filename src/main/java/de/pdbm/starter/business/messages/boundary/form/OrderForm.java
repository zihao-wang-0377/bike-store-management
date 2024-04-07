package de.pdbm.starter.business.messages.boundary.form;

import de.pdbm.starter.business.messages.control.CustomerService;
import de.pdbm.starter.business.messages.control.OrderService;
import de.pdbm.starter.business.messages.entity.Order;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Named
@ViewScoped
public class OrderForm implements Serializable {
    @Inject
    OrderService orderService;

    @Inject
    CustomerService customerService;

    @NotNull(message = "KundeID darf nicht leer sein")
    private Integer customerId;

    @NotNull(message = "Gesamtbetrag darf nicht leer sein")
    @DecimalMin(value = "0.0", inclusive = false, message = "Gesamtbetrag muss größer als 0 sein")
    @Digits(integer = 10, fraction = 2, message = "Gesamtbetrag muss eine Dezimalzahl mit maximal 10 Ziffern insgesamt und 2 Dezimalstellen sein")
    private BigDecimal total;

    @NotNull(message = "Bestelldatum darf nicht leer sein")
    private LocalDate orderDate;

    private String errorMessage;

    public OrderForm() {
    }

    public void save() {
        if (customerService.findById(customerId) == null) {
            setErrorMessage("• Kunde mit ID " + customerId + " wurde nicht gefunden!");
            return;
        } else {
            setErrorMessage(null);
        }

        Order order = new Order();
        order.setCustomer(customerService.findById(customerId));
//        order.setTotal(total);
        order.setOrderDate(orderDate);

        orderService.save(order);
        setCustomerId(null);
        setTotal(null);
        setOrderDate(null);
    }

    public String getErrorMessage() {
        // 返回 errorMessage 属性的值
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        // 设置 errorMessage 属性的值
        this.errorMessage = errorMessage;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
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

    public void setOrderDateToToday() {
        this.orderDate = LocalDate.now();
    }

    public String navigateToHomePage() {
        return "homePage.xhtml?faces-redirect=true";
    }
}