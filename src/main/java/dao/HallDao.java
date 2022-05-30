package dao;

import dbHelper.DbHelper;
import models.ExhibitionData;
import models.HallData;
import models.UserData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class HallDao {
    private static final String SELECT_HALL_BY_ID = "SELECT * FROM halls WHERE id = ?";
    private static final String SELECT_HALL_BY_NAME = "SELECT * FROM halls WHERE name = ?";
    private static final String SELECT_ALL_HALLS = "SELECT id, name FROM halls";
    private static final String SET_HALL = "INSERT INTO halls (name) VALUES (?)";
    private static final String DELETE_HALL = "DELETE FROM halls WHERE id = ?";
    private static final String UPDATE_HALL = "UPDATE halls SET name = ? WHERE id = ?";

    private static HallDao instance;
    private DbHelper dbHelper;

    private HallDao() {
        dbHelper = DbHelper.getInstance();
    }

    public synchronized static HallDao getInstance() {
        if (instance == null) {
            instance = new HallDao();
        }
        return instance;
    }

    public String getHallNameById(int id) {
        try {
            String hallName = null;
            try (Connection connection = dbHelper.getConnection();
                 PreparedStatement statement = connection.prepareStatement(SELECT_HALL_BY_ID)) {
                statement.setInt(1, id);
                try (ResultSet rs = statement.executeQuery()) {
                    if (rs.next()) {
                        hallName = rs.getString("name");
                    }
                }
            }
            return hallName;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public HallData getHallById(int id) {
        try {
            HallData hallData = null;
            try (Connection connection = dbHelper.getConnection();
                 PreparedStatement statement = connection.prepareStatement(SELECT_HALL_BY_ID)) {
                statement.setInt(1, id);
                try (ResultSet rs = statement.executeQuery()) {
                    if (rs.next()) {
                        hallData = new HallData();
                        hallData.setId(rs.getInt("id"));
                        hallData.setName(rs.getString("name"));
                    }
                }
            }
            return hallData;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public HallData getHallByName(String name) {
        try {
            HallData hallData = null;
            try (Connection connection = dbHelper.getConnection();
                 PreparedStatement statement = connection.prepareStatement(SELECT_HALL_BY_NAME)) {
                statement.setString(1, name);
                try (ResultSet rs = statement.executeQuery()) {
                    if (rs.next()) {
                        hallData = new HallData();
                        hallData.setId(rs.getInt("id"));
                        hallData.setName(rs.getString("name"));
                    }
                }
            }
            return hallData;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public List<HallData> getAllHalls() {
        List<HallData> halls = new ArrayList<>();
        try (Connection connection = dbHelper.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_HALLS)) {

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                HallData hallData = new HallData();
                hallData.setId(rs.getInt("id"));
                hallData.setName(rs.getString("name"));
                halls.add(hallData);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return halls;
    }

    public boolean setHall(String name) {
        try (Connection connection = dbHelper.getConnection();
             PreparedStatement statement = connection.prepareStatement(SET_HALL)) {
            statement.setString(1, name);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteHall(String id) {
        try (Connection connection = dbHelper.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_HALL)) {
            preparedStatement.setString(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateHall(String id, String name) {
        try (Connection connection = dbHelper.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_HALL)) {
            statement.setString(2, id);
            statement.setString(1, name);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
