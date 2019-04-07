package bean;

import config.DatabaseConfig;
import dao.PointDao;
import domain.ErrorMessage;
import domain.Point;
import domain.User;

import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ManagedBean("pointBean")
@SessionScoped
public class PointBean implements Serializable {
    private double x = 0;
    private double y = 0;
    private double r = 4;
    private List<Point> points = new ArrayList<>();

    @ManagedProperty("userBean")
    private UserBean userBean = null;

    @ManagedProperty("messageBean")
    private MessageBean messageBean = null;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void replacePoints(List<Point> points) {
        this.points.clear();
        this.points.addAll(points);
    }

    public boolean isInArea() {
        if ((x >= 0) && (y >= 0) && (sqr(r / 2) >= sqr(x) + sqr(y))) {
            return true;
        }
        if (x >= 0 && y <= 0 && x <= r && Math.abs(y) <= r) {
            return true;
        }
        return (x <= 0 && y <= 0 && y >= -2 * x - r / 2);
    }

    public void addPoint() {
        User owner = getUserFromContext();
        Point point = new Point(x, y, r, isInArea(), owner);
        points.add(point);

        try {
            PointDao pointDao = new PointDao(DatabaseConfig.URL, DatabaseConfig.USERNAME, DatabaseConfig.PASSWORD);
            pointDao.savePoint(point);
        } catch (SQLException e) {
            messageBean.setErrorMessage(ErrorMessage.SERVER_UNAVAILABLE);
        }
    }

    private double sqr(double value) {
        return value * value;
    }

    private User getUserFromContext() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        return userBean.getUsersMap().get(session.getId());
    }

}
