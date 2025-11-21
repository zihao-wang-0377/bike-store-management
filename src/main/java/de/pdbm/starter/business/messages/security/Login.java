package de.pdbm.starter.business.messages.security;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.security.enterprise.AuthenticationStatus;
import jakarta.security.enterprise.SecurityContext;
import jakarta.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotBlank;

@Named
@RequestScoped
public class Login {
    @NotBlank(message = "Benutzername darf nicht leer sein")
    private String email;

    @NotBlank(message = "Passwort darf nicht leer sein")
    private String password;

    @Inject
    private SecurityContext securityContext;

    @Inject
    private ExternalContext externalContext;

    @Inject
    private FacesContext facesContext;

    public String login() {
        switch (continueAuthentication()) {
            case SEND_CONTINUE:
                facesContext.responseComplete();
                break;
            case SEND_FAILURE:
                facesContext.addMessage(null, new FacesMessage(
                        FacesMessage.SEVERITY_ERROR, "Anmeldung fehlgeschlagen", null));
                break;
            case SUCCESS:
                return "homePage.xhtml?faces-redirect=true";
            case NOT_DONE:
        }
        return null;
    }

    private AuthenticationStatus continueAuthentication() {
        return securityContext.authenticate(
                (HttpServletRequest) externalContext.getRequest(),
                (HttpServletResponse) externalContext.getResponse(),
                AuthenticationParameters.withParams().credential(new UsernamePasswordCredential(email, password)));
    }

    // Getter und Setter
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
