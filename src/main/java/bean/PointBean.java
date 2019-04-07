package bean;

import domain.Point;

import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.LinkedList;

@ManagedBean("pointBean")
@SessionScoped
public class PointBean implements Serializable {
    private double x = 0;
    private double y = 0;
    private double r = 4;
    private boolean isInArea;
    private LinkedList<Point> points = new LinkedList<>();

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

    public LinkedList<Point> getPoints() {
        return points;
    }

    public void addPoint(Point point) {
        points.add(point);
    }

    private double sqr(double value) {
        return value * value;
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
        //work with database
    }

}
