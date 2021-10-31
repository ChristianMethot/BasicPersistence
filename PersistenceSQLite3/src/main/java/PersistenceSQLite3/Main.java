package PersistenceSQLite3;

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
        
        users.add(new User("Samantha Fox"));
        users.get(0).getMessages().add(new Message("Salut"));
        users.get(0).getMessages().add(new Message("Ça va bien?"));
        users.get(0).getMessages().add(new Message("J'aime la crème glacée"));
        
        users.add(new User("Batman"));
        users.get(1).getMessages().add(new Message("Un problème?"));
        users.get(1).getMessages().add(new Message("J'arrive"));
        users.get(1).getMessages().add(new Message("J'ai une batmobile dans mon garage"));
        
        entityManager.getTransaction().begin();
        
        for (User user: users) {
            entityManager.persist(user);
            for (Message message: user.getMessages()) {
                entityManager.persist(message);
            }
        }
        
        entityManager.getTransaction().commit();
        
        System.out.println(" ====== Historique des messages de Batman ======");
        User batman = entityManager.find( User.class, new Long(5) );
        for (Message message: batman.getMessages()) {
            System.out.println(message.toString());
        }
        
        entityManager.close();
        entityManagerFactory.close();
    } 
}
