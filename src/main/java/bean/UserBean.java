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
    private boolean isAuthFlag = true;

    public boolean isAuthFlag() {
        return this.isAuthFlag;
    }

    public void revertAuthFlag() {
        this.isAuthFlag = !this.isAuthFlag;
    }

    public Map<String, User> getUsersMap() {
        return usersMap;
    }
}
