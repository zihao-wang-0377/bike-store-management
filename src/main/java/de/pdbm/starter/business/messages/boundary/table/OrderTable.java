package de.pdbm.starter.business.messages.boundary.table;

import de.pdbm.starter.business.messages.control.OrderService;
import de.pdbm.starter.business.messages.entity.Order;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class OrderTable implements Serializable {
    @Inject
    OrderService orderService;

    public List<Order> getOrders() {
        return orderService.findAll();
    }

    public String navigateToHomePage() {
        return "homePage.xhtml?faces-redirect=true";
    }
}
