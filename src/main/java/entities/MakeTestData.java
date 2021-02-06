package entities;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import utils.EMF_Creator;

public class MakeTestData {

    public static void main(String[] args) {
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        BankCustomer bc = new BankCustomer("bob", "thebuilder", "bobthebuilder", 404, 1, "Info");
        BankCustomer bc2 = new BankCustomer("bob", "thebuilder", "bobthebuilder", 404, 1, "Info");
        BankCustomer bc3 = new BankCustomer("bob", "thebuilder", "bobthebuilder", 404, 1, "Info");
        try {
            em.getTransaction().begin();
            em.persist(bc);
            em.persist(bc2);
            em.persist(bc3);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

}
