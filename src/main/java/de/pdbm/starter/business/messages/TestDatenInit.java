package de.pdbm.starter.business.messages;

import de.pdbm.starter.business.messages.entity.*;
import de.pdbm.starter.business.messages.service.CustomerService;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Properties;

@Startup
@Singleton
public class TestDatenInit {

    @PersistenceContext(unitName = "test")
    private EntityManager emDev;

//    @PersistenceContext(unitName = "development")
//    private EntityManager emProd;

    @Inject
    private CustomerService customerService;

    @PostConstruct
    @Transactional
    public void init() {
        if (shouldLoadTestData()) {
            loadTestData();
        } else {
            System.out.println("Skipping test data initialization");
        }
    }

    private boolean shouldLoadTestData() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("application.properties")) {
            Properties prop = new Properties();
            if (input == null) {
                System.out.println("Sorry, unable to find application.properties");
                return false;
            }
            prop.load(input);
            return Boolean.parseBoolean(prop.getProperty("load.test.data"));
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    private void loadTestData() {
        insertStaffs();
        insertCustomer();
        insertBrand();
        insertProduct();
        insertStore();
        insertOrder();
        insertKatogory();
    }

    private void insertStaffs() {
        Store store = emDev.find(Store.class, 1);

        Staff adminStaff = new Staff(1, "admin.staff@bikes.shop", "Admin", "Staff", "(187) 444-3049", null, store);
        emDev.persist(adminStaff);

        Staff user1Staff = new Staff(1, "user1.staff@bikes.shop", "User1", "Staff", "(187) 322-3224", adminStaff, store);
        emDev.persist(user1Staff);

        Staff user2Staff = new Staff(1, "user2.staff@bikes.shop", "User2", "Staff", "(187) 333-7543", user1Staff, store);
        emDev.persist(user2Staff);

        Staff test1Staff = new Staff(1, "test1.staff@ostfalia.de", "Test1", "Staff", "(187) 114-0000", user1Staff, store);
        emDev.persist(test1Staff);

        Staff test2Staff = new Staff(1, "test2.staff2@ostfalia.de", "Test2", "Staff", "(187) 114-0404", user2Staff, store);
        emDev.persist(test2Staff);
    }

    private void insertCustomer() {
        Customer test1Customer = new Customer("Wolfenbuttel", "1234@gmail.com", "ma", "Elon", "(559) 628-2239", "HH", "12 exer", "38302");
        emDev.persist(test1Customer);
        Customer test2Customer = new Customer("Wolfenbuttel", "12344@gmail.com", "mask", "michel", "(559) 628-2239", "HH", "12 exer", "38304");
        emDev.persist(test2Customer);
    }

    private void insertBrand() {
        Brand test1Brand = new Brand("Big bike");
        emDev.persist(test1Brand);
        Brand test2Brand = new Brand("1");
        emDev.persist(test2Brand);
    }

    private void insertStore() {
        Store test1Store = new Store();
        emDev.persist(test1Store);
    }

    private void insertProduct() {
        Product product = new Product(new BigDecimal(100), 2024, "giant", null, null);
        emDev.persist(product);
    }

    private void insertOrder() {
        Order order = new Order();
        emDev.persist(order);
    }
    private void insertKatogory(){
        Category category = new Category("1");
        emDev.persist(category);
    }
}
