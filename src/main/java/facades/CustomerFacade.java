package facades;

import dtos.CustomerDTO;
import entities.BankCustomer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import utils.EMF_Creator;

/**
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class CustomerFacade {

    private static CustomerFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private CustomerFacade() {
    }


    /**
     * @param _emf
     * @return an instance of this facade class.
     */
    public static CustomerFacade getCustomerFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CustomerFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public CustomerDTO getCustomerById(long id) {
        EntityManager em = emf.createEntityManager();
        return new CustomerDTO(em.find(BankCustomer.class, id));
    }

    public BankCustomer addCustomer(BankCustomer bc) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(bc);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return bc;
    }

    public List<CustomerDTO> getCustomersByName(String name) {
        EntityManager em = getEntityManager();
        TypedQuery<BankCustomer> query = em
            .createQuery("SELECT b FROM BankCustomer b WHERE b.firstName = :name", BankCustomer.class)
            .setParameter("name", name);
        List<BankCustomer> bankCustomers = query.getResultList();
        return CustomerDTO.getByList(bankCustomers);
    }

    public List<BankCustomer> getAllBankCustomers() {
        EntityManager em = getEntityManager();
        TypedQuery<BankCustomer> query = em.createQuery("SELECT r FROM BankCustomer r", BankCustomer.class);
        return query.getResultList();
    }

    public static void main(String[] args) {
        emf = EMF_Creator.createEntityManagerFactory();
        CustomerFacade fe = getCustomerFacade(emf);
        fe.getAllBankCustomers().forEach(System.out::println);
    }

}
