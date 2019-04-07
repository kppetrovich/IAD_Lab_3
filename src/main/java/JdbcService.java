import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcService {
    private Connection connection;

    public JdbcService(String url, String username, String password) throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        connection = DriverManager.getConnection(url, username, password);
    }

    public List<Point> getPointsBySessionId(String sessionId) {
        try {
            PreparedStatement query = connection.prepareStatement("select x, y, r, result from points where session_id = ?");
            query.setString(1, sessionId);
            ResultSet resultSet = query.executeQuery();
            List<Point> points = new ArrayList<>();
            while (resultSet.next()) {
                double x = resultSet.getDouble("x");
                double y = resultSet.getDouble("y");
                double z = resultSet.getDouble("z");
                boolean result = resultSet.getBoolean("result");
                points.add(new Point(x, y, z, result));
            }
            return points;
        } catch (SQLException exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public void addPoint(Point point, String sessionId) {
        try {
            PreparedStatement insertQuery = connection.prepareStatement("insert into points (x, y, r, result, session_id ) values (?, ?, ?, ?, ?)");
            insertQuery.setDouble(1, point.getX());
            insertQuery.setDouble(2, point.getY());
            insertQuery.setDouble(3, point.getR());
            insertQuery.setBoolean(4, point.isResult());
            insertQuery.setString(5, sessionId);

            insertQuery.execute();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
