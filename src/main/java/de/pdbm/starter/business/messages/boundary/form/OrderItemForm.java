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

    @NotNull(message = "Bestellnummer darf nicht leer sein")
    private Integer orderId;

    @NotNull(message = "KundeID darf nicht leer sein")
    private Integer customerId;

    @NotNull(message = "ProduktID darf nicht leer sein")
    private Integer productId;

    @NotNull(message = "Anzahl darf nicht leer sein")
    @DecimalMin(value = "0.0", inclusive = false, message = "Anzahl muss größer als 0 sein")
    private Integer quantity;

    private String errorMessage1;

    private String errorMessage2;

    private String errorMessage3;

    public OrderItemForm() {
    }

    public void save() {
        // Überprüfe, ob die Bestellung, der Kunde und das Produkt existieren
        if (orderService.findById(orderId) == null) { // Überprüfe, ob die Bestellung existiert
            setErrorMessage1("• Bestellung mit Bestellnummer " + orderId + " wurde nicht gefunden"); // Fehlermeldung, falls nicht
        } else {
            setErrorMessage1(null); // Setze Fehlermeldung zurück, falls Bestellung existiert
        }
        if (customerService.findById(customerId) == null) { // Überprüfe, ob der Kunde existiert
            setErrorMessage2("• Kunde mit ID " + customerId + " wurde nicht gefunden"); // Fehlermeldung, falls nicht
        } else {
            setErrorMessage2(null); // Setze Fehlermeldung zurück, falls Kunde existiert
        }
        if (productService.findById(productId) == null) { // Überprüfe, ob das Produkt existiert
            setErrorMessage3("• Produkt mit ID " + productId + " wurde nicht gefunden"); // Setze eine Fehlermeldung
        } else {
            setErrorMessage3(null); // Setze Fehlermeldung zurück, falls Produkt existiert
        }
        // Überprüfe, ob Fehlermeldungen vorhanden sind
        if (getErrorMessage1() != null || getErrorMessage2() != null || getErrorMessage3() != null) {
            return; // Falls Fehler vorhanden sind, wird nicht gespeichert
        }

        // Neues Objekt wird erstellt und die Bestellung, den Kunden, das Produkt und die Menge gesetzt
        OrderItem orderItem = new OrderItem();
//        orderItem.setOrder(orderService.findById(orderId));
//        orderItem.setCustomer(customerService.findById(customerId));
        orderItem.setProduct(productService.findById(productId));
        orderItem.setQuantity(quantity);

        // Speichere das OrderItem
        orderItemService.save(orderItem);

        // Setze die IDs und die Menge zurück
        setOrderId(null);
        setCustomerId(null);
        setProductId(null);
        setQuantity(null);
    }


    public String getErrorMessage1() {

        return errorMessage1;
    }

    public void setErrorMessage1(String errorMessage1) {

        this.errorMessage1 = errorMessage1;
    }

    public String getErrorMessage2() {

        return errorMessage2;
    }

    public void setErrorMessage2(String errorMessage2) {

        this.errorMessage2 = errorMessage2;
    }

    public String getErrorMessage3() {

        return errorMessage3;
    }

    public void setErrorMessage3(String errorMessage3) {

        this.errorMessage3 = errorMessage3;
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
