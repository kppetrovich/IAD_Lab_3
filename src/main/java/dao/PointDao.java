package dao;

import domain.Point;
import domain.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PointDao extends Dao {

    public PointDao(String url, String username, String password) throws SQLException {
        super(url, username, password);
    }

    public List<Point> getPointsByUser(User user) {
        try {
            PreparedStatement query = connection.prepareStatement("select x, y, r, result from POINTSS where user_id = ?");
            query.setLong(1, user.getId());
            ResultSet resultSet = query.executeQuery();
            List<Point> points = new ArrayList<>();
            while (resultSet.next()) {
                double x = resultSet.getDouble("x");
                double y = resultSet.getDouble("y");
                double r = resultSet.getDouble("r");
                boolean result = resultSet.getInt("result") == 1;
                points.add(new Point(x, y, r, result, user));
            }
            return points;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void removeAllByUser(User user) {
        try {
            PreparedStatement query = connection.prepareStatement("delete from POINTSS where USER_ID = ?");
            query.setLong(1, user.getId());
            query.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void savePoint(Point point) {
        try {
            PreparedStatement insertQuery = connection
                .prepareStatement("insert into POINTSS (x, y, r, result, user_id) values (?, ?, ?, ?, ?)");
            insertQuery.setDouble(1, point.getX());
            insertQuery.setDouble(2, point.getY());
            insertQuery.setDouble(3, point.getR());
            insertQuery.setInt(4, point.isResult() ? 1 : 0);
            insertQuery.setLong(5, point.getUser().getId());

            insertQuery.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
