package de.pdbm.starter.business.messages.boundary.control;

import de.pdbm.starter.business.messages.entity.Staff;
import de.pdbm.starter.business.messages.service.StaffService;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Named
@SessionScoped
public class StaffController implements Serializable {
    @Inject
    StaffService staffService;

    @Inject
    Validator validator;

    private Integer staffId;

    private Integer active;

    private String email;

    private String firstName;

    private String lastName;

    private String phone;

    private String managerId;

    private Integer storeId;

    private List<Staff> staffList;

    private int currentPage = 1;

    private int pageSize = 10;

    private long totalRecords;

    private Staff selectedStaff;

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
        totalRecords = staffService.getStaffCount();
        return totalRecords;
    }

    public void deleteStaffRecord(Staff staff) {
        staffService.delete(staff);

        loadStaffList();
        getTotalRecords();
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Staff deleted successfully", null));
    }

    public String showDetails(Staff selectedStaff) {
        this.selectedStaff = selectedStaff;
        return "staffDetail.xhtml?faces-redirect=true";
    }

    public void save() {
        Staff staff = new Staff();
        staff.setActive(active);
        staff.setEmail(email);
        staff.setFirstName(firstName);
        staff.setLastName(lastName);
        Set<ConstraintViolation<Staff>> violations = validator.validate(staff);
        if (!violations.isEmpty()) {
            for (ConstraintViolation<Staff> violation : violations) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, violation.getMessage(), null));
            }
            return;
        }
        staffService.save(staff);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Staff created successfully", null));
        loadStaffList();
    }

    public void updateStaffRecord() {
        Set<ConstraintViolation<Staff>> violations = validator.validate(selectedStaff);
        if (!violations.isEmpty()) {
            for (ConstraintViolation<Staff> violation : violations) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, violation.getMessage(), null));
            }
            return;
        }
        staffService.update(selectedStaff);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Staff updated successfully", null));

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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public Integer[] getPageNumbers() {
        int startPage = Math.max(1, currentPage - 2);
        int endPage = Math.min(getTotalPages(), currentPage + 4);
        List<Integer> pageNumbers = new ArrayList<>();
        for (int i = startPage; i <= endPage; i++) {
            pageNumbers.add(i);
        }
        return pageNumbers.toArray(new Integer[0]);
    }

    public int getTotalPages() {
        return (int) Math.ceil((double) totalRecords / pageSize);
    }

    public void setPage(int page) {
        if (page >= 1 && page <= getTotalPages()) {
            currentPage = page;
            loadStaffList();
        }
    }

    public void nextPage() {
        currentPage++;
        loadStaffList();
    }

    public void prevPage() {
        if (currentPage > 0) {
            currentPage--;
            loadStaffList();
        }
    }

    public void firstPage() {
        currentPage = 1;
        loadStaffList();
    }

    public void lastPage() {
        currentPage = getTotalPages();
        loadStaffList();
    }

    // Suche nach Nachname
    public void searchByLastName() {
        this.staffList = staffService.findByLastName(lastName);
    }
}
