package de.pdbm.starter.business.messages.security;

import de.pdbm.starter.business.messages.service.LoginService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.security.enterprise.credential.Credential;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import jakarta.security.enterprise.identitystore.IdentityStore;

import java.util.Arrays;
import java.util.HashSet;

@ApplicationScoped
public class UserIdentityStore implements IdentityStore {
    @Inject
    LoginService loginService;

    @Override
    public CredentialValidationResult validate(Credential credential) {
        UsernamePasswordCredential login = (UsernamePasswordCredential) credential;

        String password = loginService.getPassword(login.getCaller());
        String userRole = loginService.getUserRole(login.getCaller());

        if (password != null && userRole != null) {
            if (login.getPasswordAsString().equals(password)) {
                return new CredentialValidationResult(
                        login.getCaller().concat(" (").concat(userRole).concat(") "),
                        new HashSet<>(Arrays.asList(userRole)));
            }
        }
        return CredentialValidationResult.NOT_VALIDATED_RESULT;
    }
}
