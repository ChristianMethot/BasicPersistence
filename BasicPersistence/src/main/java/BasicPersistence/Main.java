package BasicPersistence;

import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author chris
 */
public class Main {

    public static void main(String[] args) {
        ArrayList<User> users = new ArrayList<User>();
        
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PersistenceSQLite3PU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        users.add(new User("Nathan"));
        users.get(0).getMessages().add(new Message("Hey!"));
        users.get(0).getMessages().add(new Message("How are you?"));
        users.get(0).getMessages().add(new Message("I like pizza"));
        
        users.add(new User("Daniela"));
        users.get(1).getMessages().add(new Message("Traffic jam, will be late"));
        users.get(1).getMessages().add(new Message("Gotcha!"));
        users.get(1).getMessages().add(new Message("How about some Neflix?"));
        
        entityManager.getTransaction().begin();
        
        for (User user: users) {
            entityManager.persist(user);
        }
        
        entityManager.getTransaction().commit();
        
        System.out.println("\n====== Daniela's messages ======\n");
        User daniela = (User) entityManager.createQuery("SELECT u FROM User u WHERE u.userName='Daniela'").getResultList().get(0);
        
        for (Message message: daniela.getMessages()) {
            System.out.println(message.toString());
        }
        
        entityManager.close();
        entityManagerFactory.close();
    } 
}
