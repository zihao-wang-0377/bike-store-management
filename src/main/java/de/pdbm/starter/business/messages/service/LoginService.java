package de.pdbm.starter.business.messages.service;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.io.Serializable;
import java.util.List;

import static jakarta.persistence.PersistenceContextType.TRANSACTION;

@Stateless
public class LoginService implements Serializable {
    @PersistenceContext(type = TRANSACTION)
    EntityManager em;

    // Die Rolle des Benutzers zurückgeben
    public String getUserRole(String email) {
        String rolequery = "SELECT CASE " +
                "           WHEN s.manager IS NULL THEN 'ADMIN' " +
                "           WHEN s.manager.staffId = 1 THEN 'USER1' " +
                "           ELSE 'USER2' " +
                "         END " +
                "FROM Staff s " +
                "WHERE s.email = :email";

        TypedQuery<String> query = em.createQuery(rolequery, String.class);
        query.setParameter("email", email);

        List<String> results = query.getResultList();

        if (results.size() > 0) {
            return results.get(0);
        } else {
            return null;
        }
    }

    // Die Telefonnummer des Benutzers bzw. das Passwort zurückgeben
    public String getPassword(String email) {
        TypedQuery<String> query = em.createQuery("select substring(s.phone, 7, length(s.phone)) from Staff s where s.email = :email", String.class);
        query.setParameter("email", email);

        List<String> results = query.getResultList();

        if (results.size() > 0) {
            return results.get(0);
        } else {
            return null;
        }
    }
}
