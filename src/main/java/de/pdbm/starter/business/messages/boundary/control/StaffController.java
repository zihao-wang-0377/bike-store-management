package de.pdbm.starter.business.messages.boundary.control;

import de.pdbm.starter.business.messages.entity.Product;
import de.pdbm.starter.business.messages.entity.Staff;
import de.pdbm.starter.business.messages.service.StaffService;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class StaffController implements Serializable {
    private Integer staffId;
    private Integer active;
    private String email;
    private String firstName;
    private String nachName;
    private String phone;

    private String managerId;
    private Integer storeId;

    private List<Staff> staffList;

    private int currentPage = 1;

    private int pageSize = 10;

    private long totalRecords;
    private int clicks;

    private Staff selectedStaff;
    @Inject
    StaffService staffService;

    public List<Staff> getStaffs() {
        if (staffList == null || staffList.isEmpty()) {
            loadStaffList();
        }
        return staffList;
    }

    public void loadStaffList() {
        this.staffList = staffService.findPaginated(currentPage, pageSize);
    }
    public long getTotalRecords() {
        this.totalRecords = staffService.getStaffCount();
        return totalRecords;
    }

    public void deleteStaffRecord(Staff staff){
        staffService.delete(staff);
        loadStaffList();
        getTotalRecords();

    }

    public String showDetails(Staff selectedStaff){
        this.selectedStaff = selectedStaff;
        return "staffDetail.xhtml?faces-redirect=true";
    }


    public String updateStaffRecord(){
        staffService.update(selectedStaff);
        return "staffTable.xhtml?faces-redirect=true";
    }
    public boolean isButtonDisplayed(){
        return clicks % 2 == 1;
    }

    public void incrementClicks(){
        clicks++;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getNachName() {
        return nachName;
    }

    public void setNachName(String nachName) {
        this.nachName = nachName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setTotalRecords(long totalRecords) {
        this.totalRecords = totalRecords;
    }

    public Staff getSelectedStaff() {
        return selectedStaff;
    }

    public void setSelectedStaff(Staff selectedStaff) {
        this.selectedStaff = selectedStaff;
    }
}
