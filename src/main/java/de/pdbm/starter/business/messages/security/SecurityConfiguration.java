package de.pdbm.starter.business.messages.security;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.annotation.FacesConfig;
import jakarta.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import jakarta.security.enterprise.authentication.mechanism.http.LoginToContinue;

@CustomFormAuthenticationMechanismDefinition(
        loginToContinue = @LoginToContinue(
                loginPage = "/loginPage.xhtml",
                errorPage = "/loginPage.xhtml",
                useForwardToLogin = false
        )
)
@FacesConfig
@ApplicationScoped
public class SecurityConfiguration {
}
