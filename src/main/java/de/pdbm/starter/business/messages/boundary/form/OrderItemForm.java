package de.pdbm.starter.business.messages.boundary.form;

import de.pdbm.starter.business.messages.control.CustomerService;
import de.pdbm.starter.business.messages.control.OrderItemService;
import de.pdbm.starter.business.messages.control.OrderService;
import de.pdbm.starter.business.messages.control.ProductService;
import de.pdbm.starter.business.messages.entity.OrderItem;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.validation.constraints.DecimalMin;
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
    @DecimalMin(value = "0.0", inclusive = false, message = "Anzahl muss größer als 0 sein")
    private Integer quantity;

    private String errorMessage;

    public OrderItemForm() {
    }

    public void save() {
        if (orderService.findById(orderId) == null) {
            setErrorMessage("• Bestellnummer " + orderId + " nicht gefunden");
            return;
        }
        if (customerService.findById(customerId) == null) {
            setErrorMessage("• Kunde mit ID " + customerId + " nicht gefunden");
            return;
        }
        if (productService.findById(productId) == null) {
            setErrorMessage("• Produkt mit ID " + productId + " nicht gefunden");
            return;
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

    public String getErrorMessage() {
        // 返回 errorMessage 属性的值
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        // 设置 errorMessage 属性的值
        this.errorMessage = errorMessage;
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
        return "homePage.xhtml?faces-redirect=true";
    }
}
