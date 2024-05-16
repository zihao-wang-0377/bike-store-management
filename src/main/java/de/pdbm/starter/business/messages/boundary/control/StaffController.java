package de.pdbm.starter.business.messages.boundary.control;

import de.pdbm.starter.business.messages.entity.Staff;
import jakarta.enterprise.context.SessionScoped;
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
    private int clicks;
    public boolean isButtonDisplayed(){
        return clicks % 2 == 1;
    }

    public void incrementClicks(){
        clicks++;
    }
}
