package de.pdbm.starter.business.messages;

import de.pdbm.starter.business.messages.entity.Customer;
import de.pdbm.starter.business.messages.entity.Staff;
import de.pdbm.starter.business.messages.entity.Store;
import de.pdbm.starter.business.messages.service.CustomerService;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;


@Startup
@Singleton
public class TestDatenInit {
    @PersistenceContext
    EntityManager em;
    @Inject
    CustomerService customerService;
    @Inject

//    @PostConstruct
//    public void init() {
//        em.persist(new Customer( "Wolfenbüttel", "max.schuhmacher@ostfalia.de", "Max", "Schuhmacher", "135-06796623", "TH", "Straße 5", "38300"));
//    em.persist(new Staff( 1, "admin.staff@bikes.shop", "Admin", "Staff", "(187) 444-3049", null, 1));
//    }

    @PostConstruct
    @Transactional
    public void init() {
        insertStaffs();
    }

    private void insertStaffs() {
        // Laden Sie das Store-Objekt
        Store store = em.find(Store.class, 1);

        // Erstellen Sie das Staff-Objekt ohne Manager
        Staff adminStaff = new Staff(1, "admin.staff@bikes.shop", "Admin", "Staff", "(187) 444-3049", null, store);
        em.persist(adminStaff);

        // Fügen Sie weitere Staff-Objekte hinzu, ggf. mit Manager
        Staff user1Staff = new Staff(1, "user1.staff@bikes.shop", "User1", "Staff", "(187) 322-3224", adminStaff, store);
        em.persist(user1Staff);

        Staff user2Staff = new Staff(1, "user2.staff@bikes.shop", "User2", "Staff", "(187) 333-7543", user1Staff, store);
        em.persist(user2Staff);

        Staff test1Staff = new Staff(1, "test1.staff@ostfalia.de", "Test1", "Staff", "(187) 114-0000", user1Staff, store);
        em.persist(test1Staff);

        Staff test2Staff = new Staff(1, "test2.staff2@ostfalia.de", "Test2", "Staff", "(187) 114-0404", user2Staff, store);
        em.persist(test2Staff);
    }
}
