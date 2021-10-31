package PersistenceSQLite3;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author chris
 */
@Entity
@Table ( name = "users" )
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String userName;
    
    @OneToMany ( targetEntity = Message.class )
    private List<Message> messages;

    public User() {
    }

    public User(String userName) {
        this.userName = userName;
        this.messages = new ArrayList<Message>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", userName=" + userName + ", message=" + messages + '}';
    }
}
