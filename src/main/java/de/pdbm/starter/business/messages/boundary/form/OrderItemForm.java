package de.pdbm.starter.business.messages.boundary.form;

import de.pdbm.starter.business.messages.control.CustomerService;
import de.pdbm.starter.business.messages.control.OrderItemService;
import de.pdbm.starter.business.messages.control.OrderService;
import de.pdbm.starter.business.messages.control.ProductService;
import de.pdbm.starter.business.messages.entity.OrderItem;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

@Named
@ViewScoped
public class OrderItemForm implements Serializable {
    @Inject
    OrderItemService orderItemService;

    @Inject
    OrderService orderService;

    @Inject
    CustomerService customerService;

    @Inject
    ProductService productService;

    @NotNull(message = "Bestellnummer kann nicht leer sein")
    private Integer orderId;

    @NotNull(message = "KundeID kann nicht leer sein")
    private Integer customerId;

    @NotNull(message = "ProduktID kann nicht leer sein")
    private Integer productId;

    @NotNull(message = "Anzahl kann nicht leer sein")
    private Integer quantity;

    public OrderItemForm() {
    }

    public void save() {
        if (orderService.findById(orderId) == null) {
            throw new IllegalArgumentException("Bestellnummer " + orderId + " nicht gefunden");
        }
        if (customerService.findById(customerId) == null) {
            throw new IllegalArgumentException("Kunde mit ID " + customerId + " nicht gefunden");
        }
        if (productService.findById(productId) == null) {
            throw new IllegalArgumentException("Produkt mit ID " + productId + " nicht gefunden");
        }

        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(orderService.findById(orderId));
        orderItem.setCustomer(customerService.findById(customerId));
        orderItem.setProduct(productService.findById(productId));
        orderItem.setQuantity(quantity);
        orderItemService.save(orderItem);
        setOrderId(null);
        setCustomerId(null);
        setProductId(null);
        setQuantity(null);
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String navigateToHomePage() {
        return "homePage.xhtml";
    }
}
