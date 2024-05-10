package de.pdbm.starter.business.messages.security;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;

@Named
@RequestScoped
public class Logout {
    @Inject
    private HttpServletRequest request;

    public String logout() throws ServletException {
        request.logout();
        request.getSession().invalidate();

        return "/loginPage.xhtml?faces-redirect=true";
    }
}
