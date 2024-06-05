package de.pdbm.starter.business.messages.boundary.control;

import de.pdbm.starter.business.messages.entity.*;
import de.pdbm.starter.business.messages.service.OrderItemService;
import de.pdbm.starter.business.messages.service.OrderService;
import de.pdbm.starter.business.messages.service.ProductService;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Named
@SessionScoped
public class OrderItemController implements Serializable {
    @Inject
    OrderItemService orderItemService;

    @Inject
    OrderService orderService;
@Inject
    private Validator validator;
    @Inject
    ProductService productService;

    @Pattern(regexp = "^[12345]$", message = "BestellPosition soll zwischen 1 bis 5 sein")
    private String itemId;

    @ForeignKeyExists(entity = Order.class, customerMessage = "OrderId,das Sie gegeben haben existiert nicht")
    private Integer orderId;
    @Min(value = 0, message = "Der Rabatt muss mindestens 0 sein")
    @Max(value = 1, message = "Der Rabatt muss innerhalb 1 sein")

    private BigDecimal discount;
    @Positive(message = "Preis muss positiv sein")
    private BigDecimal price;
    @PositiveOrZero(message = "Anzahl muss größer als 0")
    private Integer quantity;
@ForeignKeyExists(entity = Product.class,customerMessage = "ProduktId,das Sie gegeben haben existiert nicht")
    private Integer productId;

    private OrderItemPk orderItemPk = new OrderItemPk();

    private List<OrderItem> orderItemList;

    private int currentPage = 1;

    private int pageSize = 10;

    private long totalRecords;

    private OrderItem selectedOrderItem;

    // Konstruktor
    public OrderItemController() {
    }

    public OrderItemController(String itemId, Integer orderId, BigDecimal discount, BigDecimal price, Integer quantity, Integer productId) {
        this.itemId = itemId;
        this.orderId = orderId;
        this.discount = discount;
        this.price = price;
        this.quantity = quantity;
        this.productId = productId;
    }

    // Objekt erstellen und speichern
    public void save() {
      OrderItem orderItem = new OrderItem();
        Set<ConstraintViolation<OrderItem>> violations = validator.validate(orderItem);
        if (!violations.isEmpty()) {
            for (ConstraintViolation<OrderItem> violation : violations) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, violation.getMessage(), null));
            }
            return;
        }

        Integer itemIdInt = Integer.parseInt(itemId);
        Order order = orderService.findById(orderId);
        Product product = productService.findById(productId);
        orderItemPk.setItem_id(itemIdInt);
        orderItemPk.setOrder_id(orderId);
        orderItem.setOrderItemPk(orderItemPk);
        orderItem.setOrder(order);
        orderItem.setDiscount(discount);
        orderItem.setProduct(product);
        orderItem.setQuantity(quantity);
        orderItem.setPrice(price);
        orderItemService.save(orderItem);
    }

    // Paginierung-Methoden
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

    public void loadOrderItemList() {
        this.orderItemList = orderItemService.findPaginated(currentPage, pageSize);
    }

    public void nextPage() {
        currentPage++;
        loadOrderItemList();
    }

    public void prevPage() {
        if (currentPage > 0) {
            currentPage--;
            loadOrderItemList();
        }
    }

    public void firstPage() {
        currentPage = 1;
        loadOrderItemList();
    }

    public void lastPage() {
        currentPage = getTotalPages();
        loadOrderItemList();
    }

    public int getTotalPages() {
        return (int) Math.ceil((double) totalRecords / pageSize);
    }

    public long getTotalRecords() {
        if (totalRecords == 0) {
            totalRecords = orderItemService.getOrderItemCount();
        }
        return totalRecords;
    }

    public Integer[] getPageNumbers() {
        int startPage = Math.max(1, currentPage - 2);
        int endPage = Math.min(getTotalPages(), currentPage + 4);
        List<Integer> pageNumbers = new ArrayList<>();
        for (int i = startPage; i <= endPage; i++) {
            pageNumbers.add(i);
        }
        return pageNumbers.toArray(new Integer[0]);
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
        currentPage = 1;
        loadOrderItemList();
    }

    public void setPage(int page) {
        if (page >= 1 && page <= getTotalPages()) {
            currentPage = page;
            loadOrderItemList();
        }
    }

    public void goToPage() {
        if (currentPage < 1) {
            currentPage = 1;
            loadOrderItemList();
        } else if (currentPage > getTotalPages()) {
            currentPage = getTotalPages();
            loadOrderItemList();
        } else {
            loadOrderItemList();
        }
    }

    // Getter und Setter
    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
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

    public OrderItem getSelectedOrderItem() {
        return selectedOrderItem;
    }

    public String showDetails(OrderItem selectedOrderItem) {
        this.selectedOrderItem = selectedOrderItem;
        return "orderItemDetail.xhtml?faces-redirect=true";
    }

    // Suche nach Bestellnummer
    public void searchByOrderId() {
        orderItemList = orderItemService.findByOrderId(orderId);
    }

    // Eintrag loeschen
    public void deleteOrderItemRecord(OrderItem orderItem) {
        orderItemService.delete(orderItem);
        loadOrderItemList();
        getTotalRecords();
    }
    public String updateOrderItemRecord(){
        orderItemService.update(selectedOrderItem);
        return "orderItemTable.xhtml?faces-redirect=true";
    }
}
