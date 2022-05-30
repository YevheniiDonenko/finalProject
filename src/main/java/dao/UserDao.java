package dao;

import dbHelper.DbHelper;
import models.UserData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private static final String SELECT_USER_BY_ID = "SELECT * FROM users WHERE id = ?";
    private static final String SELECT_USER_BY_LOGIN = "SELECT * FROM users WHERE login = ?";
    private static final String SELECT_USER_BY_EMAIL = "SELECT * FROM users WHERE email = ?";
    private static final String SELECT_ALL_USERS = "SELECT id, login, password, name,  email, role FROM users";
    private static final String SELECT_CLIENT = "SELECT u.id, u.name,  u.login, u.email " + "FROM users u \r\n"
            + "JOIN role r ON r.role=u.role WHERE r.role = client;";
    private static final String SET_USER = "INSERT INTO users (login, password, name,"
            + " email) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_USER = "UPDATE users SET login = ?, password = ?, "
            + "name = ?, email = ?, role = ? WHERE id = ?";
    private static final String UPDATE_USER_ROLE = "UPDATE users SET role = ? WHERE id = ?";
    private static final String DELETE_USER = "DELETE FROM users WHERE id = ?";
    private static UserDao instance;
    private DbHelper dbHelper;

    private UserDao() {
        dbHelper = DbHelper.getInstance();
    }

    public synchronized static UserDao getInstance() {
        if (instance == null) {
            instance = new UserDao();
        }
        return instance;
    }

    public UserData getUserById(int id) {
        try {
            UserData userData = null;
            try (Connection connection = dbHelper.getConnection();
                 PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_ID)) {
                statement.setInt(1, id);
                try (ResultSet rs = statement.executeQuery()) {
                    if (rs.next()) {
                        userData = new UserData();
                        userData.setId(rs.getInt("id"));
                        userData.setLogin(rs.getString("login"));
                        userData.setPassword(rs.getString("password"));
                        userData.setName(rs.getString("name"));
                        userData.setEmail(rs.getString("email"));
                        userData.setRole(rs.getString("role"));
                    }
                }
            }
            return userData;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public UserData getUserByLogin(String login) {
        try {
            UserData userData = null;
            try (Connection connection = dbHelper.getConnection();
                 PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_LOGIN)) {
                statement.setString(1, login);
                try (ResultSet rs = statement.executeQuery()) {
                    if (rs.next()) {
                        userData = new UserData();
                        userData.setId(rs.getInt("id"));
                        userData.setLogin(rs.getString("login"));
                        userData.setPassword(rs.getString("password"));
                        userData.setName(rs.getString("name"));
                        userData.setEmail(rs.getString("email"));
                        userData.setRole(rs.getString("role"));
                    }
                }
            }
            return userData;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public UserData getUserByEmail(String email) {
        try {
            UserData userData = null;
            try (Connection connection = dbHelper.getConnection();
                 PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_EMAIL)) {
                statement.setString(1, email);
                try (ResultSet rs = statement.executeQuery()) {
                    if (rs.next()) {
                        userData = new UserData();
                        userData.setId(rs.getInt("id"));
                        userData.setLogin(rs.getString("login"));
                        userData.setPassword(rs.getString("password"));
                        userData.setName(rs.getString("name"));
                        userData.setEmail(rs.getString("email"));
                        userData.setRole(rs.getString("role"));
                    }
                }
            }
            return userData;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<UserData> getAllUsers() {
        List<UserData> users = new ArrayList<>();
        try (Connection connection = dbHelper.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_USERS)) {

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                UserData userData = new UserData();
                userData.setId(rs.getInt("id"));
                userData.setLogin(rs.getString("login"));
                userData.setPassword(rs.getString("password"));
                userData.setName(rs.getString("name"));
                userData.setEmail(rs.getString("email"));
                userData.setRole(rs.getString("role"));
                users.add(userData);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public List<UserData> getClient() {
        List<UserData> users = new ArrayList<>();
        try (Connection connection = dbHelper.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_CLIENT)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                UserData userData = new UserData();
                userData.setId(rs.getInt("id"));
                userData.setName(rs.getString("name"));
                userData.setLogin(rs.getString("login"));
                userData.setEmail(rs.getString("email"));
                users.add(userData);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public boolean updateUser(int id, String login, String password, String name, String email, String role) {
        try (Connection connection = dbHelper.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USER)) {
            statement.setInt(1, id);
            statement.setString(2, login);
            statement.setString(3, password);
            statement.setString(4, name);
            statement.setString(5, email);
            statement.setString(6, role);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean updateUser(String id, String role) {
        try (Connection connection = dbHelper.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USER_ROLE)) {
            statement.setString(2, id);
            statement.setString(1, role);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean setUser(String login, String password, String name, String email) {
        try (Connection connection = dbHelper.getConnection();
             PreparedStatement statement = connection.prepareStatement(SET_USER)) {
            statement.setString(1, login);
            statement.setString(2, password);
            statement.setString(3, name);
            statement.setString(4, email);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean deleteUser(String id) {
        try (Connection connection = dbHelper.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER)) {
            preparedStatement.setString(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();

        }
        return false;
    }
}

