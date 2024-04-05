package de.pdbm.starter.business.messages.boundary.table;

import de.pdbm.starter.business.messages.control.OrderItemService;
import de.pdbm.starter.business.messages.entity.OrderItem;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.math.BigDecimal;
import java.util.List;

@Named
@RequestScoped
public class OrderItemController {
    private Integer itemId;
    private Integer orderId;
    private BigDecimal discount;
    private BigDecimal price;
    private Integer quantity;
    private Integer productId;
    private List<OrderItem> orderItemList;
    @Inject
    OrderItemService orderItemService;

    public OrderItemController() {
    }

    public OrderItemController(Integer itemId, Integer orderId, BigDecimal discount, BigDecimal price, Integer quantity, Integer productId) {
        this.itemId = itemId;
        this.orderId = orderId;
        this.discount = discount;
        this.price = price;
        this.quantity = quantity;
        this.productId = productId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }
    @PostConstruct
    public void OrderItemListInit(){
        orderItemList=orderItemService.findAll();
    }
    public List<OrderItem> getOrderItemList(){
        return orderItemList;
    }
    public String navigateToHomePage() {
        return "homePage.xhtml?faces-redirect=true";
    }
}

