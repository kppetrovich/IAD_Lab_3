package bean;

import domain.User;

import javax.annotation.ManagedBean;
import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@ManagedBean("userBean")
@ApplicationScoped
public class UserBean implements Serializable {
    private Map<String, User> usersMap = new HashMap<>();
    private String username;
    private String password;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public Map<String, User> getUsersMap() {
        return usersMap;
    }

}
