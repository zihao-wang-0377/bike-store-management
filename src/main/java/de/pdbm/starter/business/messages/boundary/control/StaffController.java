package de.pdbm.starter.business.messages.boundary.control;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;

@Named
@SessionScoped
public class StaffController implements Serializable {
    private int clicks;
    public boolean isButtonDisplayed(){
        return clicks % 2 == 1;
    }

    public void incrementClicks(){
        clicks++;
    }
}
