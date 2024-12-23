package jm.task.core.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jm.task.core.jdbc.util.*;
import jm.task.core.jdbc.model.User;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() {

    }

    @Override
    public void createUsersTable() {
        try {
            String sql = "CREATE TABLE IF NOT EXISTS USER_TABLE" +
                    " (id INT UNSIGNED NOT NULL AUTO_INCREMENT," +
                    " name VARCHAR(40)," +
                    " lastName VARCHAR(40)," +
                    " age TINYINT UNSIGNED," +
                    " PRIMARY KEY (id))";
            connection.createStatement().executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try {
            String sql = "DROP TABLE IF EXISTS USER_TABLE";
            connection.createStatement().executeUpdate(sql);
            // System.out.println("Table drop if it exists");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            createUsersTable();
            PreparedStatement prepareStatement = connection
                    .prepareStatement("INSERT INTO USER_TABLE (name, lastName, age) VALUES (?, ?, ?)");
            prepareStatement.setString(1, name);
            prepareStatement.setString(2, lastName);
            prepareStatement.setByte(3, age);
            prepareStatement.executeUpdate();
            System.out.printf("User с именем — %s добавлен в базу данных\n", name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Connection connected = Util.getConnection();) {
            createUsersTable();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE from USER_TABLE WHERE id=?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connected = Util.getConnection();) {
            createUsersTable();
            String sql = "SELECT * FROM USER_TABLE";
            ResultSet res = connected.createStatement().executeQuery(sql);
            while (res.next()) {
                User user = new User(res.getString("name"),
                        res.getString("lastName"),
                        res.getByte("age"));
                user.setId(res.getLong("id"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        try (Connection connected = Util.getConnection();) {
            createUsersTable();
            String sql = "TRUNCATE TABLE USER_TABLE";
            connected.createStatement().executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
