package de.pdbm.starter.business.messages.boundary.table;

import de.pdbm.starter.business.messages.control.OrderService;
import de.pdbm.starter.business.messages.entity.Customer;
import de.pdbm.starter.business.messages.entity.Order;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.time.LocalDate;
import java.util.List;

@Named
@RequestScoped
public class OrderController {
private Integer orderId;
private LocalDate orderDate;
private Integer orderStatus;
private LocalDate requiredDate;
private LocalDate shippedDate;
private Integer customerId;
private Integer staffId;
private Integer storeId;
private List<Order> orderList;
@Inject
    OrderService orderService;

    public OrderController() {
    }

    public OrderController(Integer orderId, LocalDate orderDate, Integer orderStatus, LocalDate requiredDate, LocalDate shippedDate, Integer customerId, Integer staffId, Integer storeId) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.requiredDate = requiredDate;
        this.shippedDate = shippedDate;
        this.customerId = customerId;
        this.staffId = staffId;
        this.storeId = storeId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
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

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }
    @PostConstruct
    public void OrderListInit() {
        orderList=orderService.findAll();
    }

    public List<Order> getOrderList(){
        return orderList;
    }
    public String navigateToHomePage() {
        return "homePage.xhtml?faces-redirect=true";
    }

}
