package dao;

import dbHelper.DbHelper;
import models.OrderData;
import models.UserData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {
    private static final String GET_ORDER_BY_USER_ID = "SELECT * FROM orders WHERE user_id = ?";
    private static final String GET_QUANTITY_BY_EXHIBITION_ID = "SELECT quantity FROM orders WHERE exhibition_id = ?";
    private static final String GET_ORDER_BY_EXHIBITION_ID = "SELECT * FROM orders WHERE exhibition_id = ?";
    private static final String SET_ORDER = "INSERT INTO orders (user_id, exhibition_id, quantity)" +
            " VALUES (?, ?, ?)";
    private static final String DELETE_ORDER_BY_EXHIBITION_ID = "DELETE FROM orders WHERE exhibition_id = ?";
    private static final String DELETE_ORDER_BY_USER_ID = "DELETE FROM orders WHERE user_id = ?";

    private static OrderDao instance;
    private DbHelper dbHelper;

    private OrderDao() {
        dbHelper = DbHelper.getInstance();
    }

    public synchronized static OrderDao getInstance() {
        if (instance == null) {
            instance = new OrderDao();
        }
        return instance;
    }

//    public int getSumForOrder(int exhibitionId, String quantity) {
//
//        int quantity = getQuantityByExhibitionId(exhibitionId);
//
//        return quantity;
//    }



    public List<OrderData> getOrderByUserId(int userId) {
        List<OrderData> orders = new ArrayList<>();

        try (Connection connection = dbHelper.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_ORDER_BY_USER_ID)) {
            statement.setInt(1, userId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                OrderData orderData = new OrderData();
                orderData.setId(rs.getInt("id"));
                orderData.setUserId(rs.getInt("user_id"));
                orderData.setUser(UserDao.getInstance().getUserById(rs.getInt("user_id")));
                orderData.setExhibitionId(rs.getInt("exhibition_id"));
                orderData.setExhibitionName(ExhibitionDao.getInstance().getExhibitionNameById(rs.getInt("exhibition_id")));
                orderData.setQuantity(rs.getInt("quantity"));
                orderData.setSumForExhibition((ExhibitionDao.getInstance().getExhibitionPriceById(rs.getInt("exhibition_id"))) * rs.getInt("quantity"));
                orders.add(orderData);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return orders;
    }



    public boolean setOrder(int userId, String exhibitionId, String quantity) {
        try (Connection connection = dbHelper.getConnection();
             PreparedStatement statement = connection.prepareStatement(SET_ORDER)) {
            statement.setInt(1, userId);
            statement.setString(2, exhibitionId);
            statement.setString(3, quantity);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public List<OrderData> getOrderByExhibitionId(int userId) {
        List<OrderData> orders = new ArrayList<>();

        try (Connection connection = dbHelper.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_ORDER_BY_EXHIBITION_ID)) {
            statement.setInt(1, userId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                OrderData orderData = new OrderData();
                orderData.setId(rs.getInt("id"));
                orderData.setUserId(rs.getInt("user_id"));
                orderData.setUser(UserDao.getInstance().getUserById(rs.getInt("user_id")));
                orderData.setExhibitionId(rs.getInt("exhibition_id"));
                orderData.setExhibitionName(ExhibitionDao.getInstance().getExhibitionNameById(rs.getInt("exhibition_id")));
                orderData.setQuantity(rs.getInt("quantity"));
                orders.add(orderData);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return orders;
    }

    public int getQuantityByExhibitionId(int id) {
        int quantity = 0;
        try (Connection connection = dbHelper.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_QUANTITY_BY_EXHIBITION_ID)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                quantity += rs.getInt("quantity");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return quantity;
    }

    public boolean deleteOrderByExhibitionId(String id) {
        try (Connection connection = dbHelper.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ORDER_BY_EXHIBITION_ID)) {
            preparedStatement.setString(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();

        }
        return false;
    }
    public boolean deleteOrderByUserId(String id) {
        try (Connection connection = dbHelper.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ORDER_BY_USER_ID)) {
            preparedStatement.setString(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();

        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(OrderDao.getInstance().getOrderByUserId(2));

    }
}
