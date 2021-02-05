package facades;

import dtos.CustomerDTO;
import entities.BankCustomer;
import java.util.List;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class CustomerFacadeTest {

    private static EntityManagerFactory emf;
    private static CustomerFacade facade;

    public CustomerFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
       emf = EMF_Creator.createEntityManagerFactoryForTest();
       facade = CustomerFacade.getCustomerFacade(emf);
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the code below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        BankCustomer bc = new BankCustomer("bob", "thebuilder", "bobthebuilder", 9000, 1, "info");
        BankCustomer bc2 = new BankCustomer("bob", "thebuilder", "bobthebuilder", 9000, 1, "info");
        try {
            em.getTransaction().begin();
            em.createNamedQuery("BankCustomer.deleteAllRows").executeUpdate();
            em.persist(bc);
            em.persist(bc2);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }


    @Test
    void getCustomerById() {
        CustomerDTO customerDTO = facade.getCustomerById(2);
        assertEquals(2, customerDTO.getId());
    }

    @Test
    void addCustomer() {
        BankCustomer bc = new BankCustomer("bob", "thebuilder", "bobthebuilder", 9000, 1, "info");
        BankCustomer bankCustomer = facade.addCustomer(bc);
        CustomerDTO customerDTO = facade.getCustomerById(bc.getId());
        assertEquals(customerDTO.getId(), bankCustomer.getId());
    }

    @Test
    void getCustomersByName() {
        List<CustomerDTO> customerDTOS = facade.getCustomersByName("bob");
        customerDTOS.forEach(c -> {
            assertEquals("bob thebuilder", c.getFullName());
        });
    }

    @Test
    void getAllBankCustomers() {
        List<BankCustomer> customerDTOS = facade.getAllBankCustomers();
        customerDTOS.forEach(c -> {
            assertEquals("bob", c.getFirstName());
            assertEquals("thebuilder", c.getLastName());
            assertEquals("bobthebuilder", c.getAccountNumber());
        });
        int length = customerDTOS.toArray().length;
        assertEquals(length, 2);
    }
}
