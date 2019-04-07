package bean;

import config.DatabaseConfig;
import dao.UserDao;
import domain.ErrorMessage;
import domain.User;

import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.sql.SQLException;

@ManagedBean("authBean")
@SessionScoped
public class AuthBean implements Serializable {
    private String username;
    private String password;

    @ManagedProperty("userBean")
    private UserBean userBean = null;

    @ManagedProperty("messageBean")
    private MessageBean messageBean = null;

    private void authorizeUser(User user) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        userBean.getUsersMap().put(session.getId(), user);
    }

    public boolean signIn() {
        try {
            UserDao userDao = new UserDao(DatabaseConfig.URL, DatabaseConfig.USERNAME, DatabaseConfig.PASSWORD);
            User receivedUser = userDao.findUserByUsername(username);
            if (receivedUser.getPassword().equals(password)) {
                authorizeUser(receivedUser);
                return true;
            } else {
                messageBean.setErrorMessage(ErrorMessage.WRONG_CREDENTIALS);
            }
        } catch (SQLException | NullPointerException e) {
            messageBean.setErrorMessage(ErrorMessage.SERVER_UNAVAILABLE);
        }
        return false;
    }

    public boolean signUp() {
        try {
            UserDao userDao = new UserDao(DatabaseConfig.URL, DatabaseConfig.USERNAME, DatabaseConfig.PASSWORD);
            User receivedUser = userDao.findUserByUsername(username);
            if (receivedUser != null) {
                messageBean.setErrorMessage(ErrorMessage.LOGIN_EXISTS);
                return false;
            }
            User newUser = new User(username, password);
            userDao.saveUser(newUser);
        } catch (SQLException | NullPointerException e) {
            messageBean.setErrorMessage(ErrorMessage.SERVER_UNAVAILABLE);
        }
        return false;
    }

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
}
