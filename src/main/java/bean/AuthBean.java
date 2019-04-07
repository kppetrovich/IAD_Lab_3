package bean;

import config.DatabaseConfig;
import dao.PointDao;
import dao.UserDao;
import domain.ErrorMessage;
import domain.User;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.sql.SQLException;

@ManagedBean(name = "authBean", eager = true)
@SessionScoped
public class AuthBean implements Serializable {
    private String username;
    private String password;
    private UserDao userDao = null;
    private PointDao pointDao = null;

    @PostConstruct
    public void postConstruct() {
        try {
            userDao = new UserDao(DatabaseConfig.URL, DatabaseConfig.USERNAME, DatabaseConfig.PASSWORD);
            pointDao = new PointDao(DatabaseConfig.URL, DatabaseConfig.USERNAME, DatabaseConfig.PASSWORD);
        } catch (SQLException e) {
            messageBean.setErrorMessage(ErrorMessage.SERVER_UNAVAILABLE);
        }
        System.out.println("MessageBean is null: " + (messageBean == null));
    }

    @ManagedProperty("#{userBean}")
    private UserBean userBean;

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    public MessageBean getMessageBean() {
        return messageBean;
    }

    public void setMessageBean(MessageBean messageBean) {
        this.messageBean = messageBean;
    }

    public PointBean getPointBean() {
        return pointBean;
    }

    public void setPointBean(PointBean pointBean) {
        this.pointBean = pointBean;
    }

    @ManagedProperty("#{messageBean}")
    private MessageBean messageBean;

    @ManagedProperty("#{pointBean}")
    private PointBean pointBean;

    private void authorizeUser(User user) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);

        userBean.getUsersMap().entrySet().removeIf(element -> element.getValue().equals(user));

        userBean.getUsersMap().put(session.getId(), user);

        try {
            pointBean.replacePoints(pointDao.getPointsByUser(user));
        } catch (NullPointerException e) {
            messageBean.setErrorMessage(ErrorMessage.SERVER_UNAVAILABLE);
        }
    }

    public boolean signIn() {
        try {
            User receivedUser = userDao.findUserByUsername(username);
            System.out.println("ReceivedUsername is null: " + (receivedUser == null));
            if (receivedUser.getPassword().equals(password)) {
                System.out.println("Passwords is the same");
                authorizeUser(receivedUser);
                return true;
            } else {
                messageBean.setErrorMessage(ErrorMessage.WRONG_CREDENTIALS);
            }
        } catch (NullPointerException e) {
            messageBean.setErrorMessage(ErrorMessage.SERVER_UNAVAILABLE);
        }
        return false;
    }

    public boolean signUp() {
        try {
            User receivedUser = userDao.findUserByUsername(username);
            if (receivedUser != null) {
                messageBean.setErrorMessage(ErrorMessage.LOGIN_EXISTS);
                return false;
            }
            User newUser = new User(username, password);
            userDao.saveUser(newUser);
        } catch (NullPointerException e) {
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
