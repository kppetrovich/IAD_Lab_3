package dao;

import domain.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao extends Dao {

    public UserDao(String url, String username, String password) throws SQLException {
        super(url, username, password);
    }

    public User findUserByUsername(String username) {
        try {
            PreparedStatement query = connection.prepareStatement("select password, id from users where username = ?");
            query.setString(1, username);
            ResultSet resultSet = query.executeQuery();
            if (resultSet.next()) {
                String password = resultSet.getString("password");
                Long id = resultSet.getLong("id");
                return new User(username, password, id);
            } else return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void saveUser(User user) {
        try {
            PreparedStatement query = connection.prepareStatement("insert into users (username, password) values (?, ?)");
            query.setString(1, user.getUsername());
            query.setString(2, user.getPassword());
            query.execute();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
