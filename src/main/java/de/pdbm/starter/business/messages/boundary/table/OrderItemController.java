package de.pdbm.starter.business.messages.boundary.table;

import de.pdbm.starter.business.messages.control.OrderItemService;
import de.pdbm.starter.business.messages.entity.Order;
import de.pdbm.starter.business.messages.entity.OrderItem;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Named
@ViewScoped
public class OrderItemController implements Serializable {
    private Integer itemId;
    private Integer orderId;
    private BigDecimal discount;
    private BigDecimal price;
    private Integer quantity;
    private Integer productId;
    private List<OrderItem> orderItemList;
    private int currentPage = 0;
    private int pageSize = 10;
    private long dataNumber;
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

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public List<OrderItem> getOrderItems() {
        if (orderItemList == null || orderItemList.isEmpty()) {
            loadOrderItemList();
        }
        return orderItemList;
    }
    public void loadOrderItemList(){
        this.orderItemList = orderItemService.findPaginated(currentPage, pageSize);
    }
    public void nextPage(){
        currentPage++;
        loadOrderItemList();
    }
    public void prevPage(){
        if(currentPage > 0){
            currentPage--;
            loadOrderItemList();
        }

    }

    public long getDataNumber() {
        this.dataNumber = orderItemService.getOrderItemCount();
        return dataNumber;
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
    public String navigateToHomePage() {
        return "homePage.xhtml?faces-redirect=true";
    }
}

