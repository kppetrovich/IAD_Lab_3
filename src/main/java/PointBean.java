
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.LinkedList;

@ManagedBean(name = "point", eager = true)
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

    public boolean isInArea() {
        if ((x >= 0) && (y >= 0) && (y <= (r / 2 - x) / 2)) {

            return true;
        } else {
            if ((x <= 0) && (y >= 0) && ((x * x + y * y) <= r / 2 * r / 2 / 4)) {
                return true;
            } else {
                return (x <= 0) && (y <= 0) && (x >= -r / 2) && (y >= -r / 2);
            }
        }
    }

    public void addPoint() {
        //work with database
    }

}
