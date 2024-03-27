package de.pdbm.starter.business.messages.boundary.table;

import de.pdbm.starter.business.messages.control.OrderItemService;
import de.pdbm.starter.business.messages.entity.OrderItem;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class OrderItemTable implements Serializable {
    @Inject
    OrderItemService orderItemService;

    public List<OrderItem> getOrderItems() {
        return orderItemService.findAll();
    }

    public String navigateToHomePage() {
        return "homePage.xhtml";
    }
}
