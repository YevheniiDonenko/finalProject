package dao;

import dbHelper.DbHelper;
import models.ExhibitionData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ExhibitionDao {

    private static final String SELECT_EXHIBITION_BY_ID = "SELECT * FROM exhibitions WHERE id = ?";
    private static final String SELECT_USER_BY_NAME = "SELECT * FROM users WHERE name = ?";
    private static final String DELETE_EXHIBITION = "DELETE FROM exhibitions WHERE id = ?";
    private static final String SELECT_ALL_EXHIBITIONS = "SELECT id, name, theme, description, start, finish, price, hall_id FROM exhibitions";
    private static final String SELECT_ALL_EXHIBITIONS_ORDER_BY_NAME = "SELECT id, name, theme, description, start, finish, price, hall_id FROM exhibitions ORDER BY name";
    private static final String SELECT_ALL_EXHIBITIONS_ORDER_BY_PRICE = "SELECT id, name, theme, description, start, finish, price, hall_id FROM exhibitions ORDER BY price";
    private static final String SELECT_ALL_EXHIBITIONS_BY_DATE = "SELECT id, name, theme, description, start, finish, price, hall_id FROM exhibitions ORDER BY start";
    private static final String SELECT_ALL_EXHIBITIONS_BY_PRICE = "SELECT id, name, theme, description, start, finish, price, hall_id FROM exhibitions ORDER BY price";
    private static final String SELECT_ALL_EXHIBITIONS_BETWEEN_PRICE = "SELECT id, name, theme, description, start, finish, price, hall_id FROM exhibitions WHERE price BETWEEN ? and ? ORDER BY price";
    private static final String SET_EXHIBITION = "INSERT INTO exhibitions (name, theme, description, start,"
            + " finish, price, hall_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_EXHIBITION = "UPDATE exhibitions SET name = ?, theme = ?, description = ?, "
            + "start = ?, finish = ?, price = ?, hall_id = ? WHERE id = ?";
    private static final String SELECT_EXHIBITION_PRICE_BY_ID = "SELECT price FROM exhibitions WHERE id = ?";
    private static ExhibitionDao instance;
    private final DbHelper dbHelper;

    private ExhibitionDao() {
        dbHelper = DbHelper.getInstance();
    }

    public synchronized static ExhibitionDao getInstance() {
        if (instance == null) {
            instance = new ExhibitionDao();
        }
        return instance;
    }

    public int getExhibitionPriceById(int id) {
        try {
            int exhibitionPrice = 0;
            try (Connection connection = dbHelper.getConnection();
                 PreparedStatement statement = connection.prepareStatement(SELECT_EXHIBITION_PRICE_BY_ID)) {
                statement.setInt(1, id);
                try (ResultSet rs = statement.executeQuery()) {
                    if (rs.next()) {
                        exhibitionPrice = rs.getInt("price");
                    }
                }
            }
            return exhibitionPrice;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ExhibitionData getExhibitionById(int id) {
        try {
            ExhibitionData exhibitionData = null;
            try (Connection connection = dbHelper.getConnection();
                 PreparedStatement statement = connection.prepareStatement(SELECT_EXHIBITION_BY_ID)) {
                statement.setInt(1, id);
                try (ResultSet rs = statement.executeQuery()) {
                    if (rs.next()) {
                        exhibitionData = new ExhibitionData();
                        exhibitionData.setId(rs.getInt("id"));
                        exhibitionData.setName(rs.getString("name"));
                        exhibitionData.setTheme(rs.getString("theme"));
                        exhibitionData.setDescription(rs.getString("description"));
                        exhibitionData.setStart(rs.getString("start"));
                        exhibitionData.setFinish(rs.getString("finish"));
                        exhibitionData.setHall_id(rs.getInt("hall_id"));
                        exhibitionData.setHall_name(HallDao.getInstance().getHallNameById(rs.getInt("hall_id")));
                        exhibitionData.setPrice(rs.getDouble("price"));
                        exhibitionData.setQuantity(OrderDao.getInstance().getQuantityByExhibitionId(rs.getInt("id")));
                    }
                }
            }
            return exhibitionData;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<ExhibitionData> getAllExhibitions() {
        return getExhibitionData(SELECT_ALL_EXHIBITIONS);
    }
    public List<ExhibitionData> getAllExhibitionsOrderByName() {
        return getExhibitionData(SELECT_ALL_EXHIBITIONS_ORDER_BY_NAME);
    }
    public List<ExhibitionData> getAllExhibitionsOrderByPrice() {
        return getExhibitionData(SELECT_ALL_EXHIBITIONS_ORDER_BY_PRICE);
    }

    public List<ExhibitionData> getAllExhibitionsByDate() {
        return getExhibitionData(SELECT_ALL_EXHIBITIONS_BY_DATE);
    }

    public List<ExhibitionData> getAllExhibitionsByPrice() {
        return getExhibitionData(SELECT_ALL_EXHIBITIONS_BY_PRICE);
    }

    public List<ExhibitionData> getAllExhibitionsBetweenPrice(String start, String finish) {
        List<ExhibitionData> exhibitions = new ArrayList<>();
        try (Connection connection = dbHelper.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_EXHIBITIONS_BETWEEN_PRICE)) {
            statement.setString(1, start);
            statement.setString(2, finish);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                ExhibitionData exhibitionData = new ExhibitionData();
                exhibitionData.setId(rs.getInt("id"));
                exhibitionData.setName(rs.getString("name"));
                exhibitionData.setTheme(rs.getString("theme"));
                exhibitionData.setDescription(rs.getString("description"));
                exhibitionData.setStart(rs.getString("start"));
                exhibitionData.setFinish(rs.getString("finish"));
                exhibitionData.setHall_id(rs.getInt("hall_id"));
                exhibitionData.setHall_name(HallDao.getInstance().getHallNameById(rs.getInt("hall_id")));
                exhibitionData.setPrice(rs.getDouble("price"));
                exhibitionData.setQuantity(OrderDao.getInstance().getQuantityByExhibitionId(rs.getInt("id")));

                exhibitions.add(exhibitionData);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return exhibitions;
    }

    private List<ExhibitionData> getExhibitionData(String selectAllExhibitionsByPrice) {
        List<ExhibitionData> exhibitions = new ArrayList<>();
        try (Connection connection = dbHelper.getConnection();
             PreparedStatement statement = connection.prepareStatement(selectAllExhibitionsByPrice)) {

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                ExhibitionData exhibitionData = new ExhibitionData();
                exhibitionData.setId(rs.getInt("id"));
                exhibitionData.setName(rs.getString("name"));
                exhibitionData.setTheme(rs.getString("theme"));
                exhibitionData.setDescription(rs.getString("description"));
                exhibitionData.setStart(rs.getString("start"));
                exhibitionData.setFinish(rs.getString("finish"));
                exhibitionData.setHall_id(rs.getInt("hall_id"));
                exhibitionData.setHall_name(HallDao.getInstance().getHallNameById(rs.getInt("hall_id")));
                exhibitionData.setPrice(rs.getDouble("price"));
                exhibitionData.setQuantity(OrderDao.getInstance().getQuantityByExhibitionId(rs.getInt("id")));

                exhibitions.add(exhibitionData);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return exhibitions;
    }

    public boolean updateExhibition(String id, String name, String theme, String description, String start, String finish,
                                    String price, String hall_id) {
        try (Connection connection = dbHelper.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_EXHIBITION)) {
            statement.setString(8, id);
            statement.setString(1, name);
            statement.setString(2, theme);
            statement.setString(3, description);
            statement.setString(4, start);
            statement.setString(5, finish);
            statement.setString(6, price);
            statement.setString(7, hall_id);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();

            return false;
        }
        return true;
    }

    public boolean deleteExhibition(String id) {
        try (Connection connection = dbHelper.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_EXHIBITION)) {
            preparedStatement.setString(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();

        }
        return false;
    }

    public boolean setExhibition(String name, String theme, String description, String start, String finish,
                                 String price, String hall_id) {
        try (Connection connection = dbHelper.getConnection();
             PreparedStatement statement = connection.prepareStatement(SET_EXHIBITION)) {
            statement.setString(1, name);
            statement.setString(2, theme);
            statement.setString(3, description);
            statement.setString(4, start);
            statement.setString(5, finish);
            statement.setString(6, price);
            statement.setString(7, hall_id);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public String getExhibitionNameById(int id) {
        try {
            String exhibitionName = null;
            try (Connection connection = dbHelper.getConnection();
                 PreparedStatement statement = connection.prepareStatement(SELECT_EXHIBITION_BY_ID)) {
                statement.setInt(1, id);
                try (ResultSet rs = statement.executeQuery()) {
                    if (rs.next()) {
                        exhibitionName = rs.getString("name");
                    }
                }
            }
            return exhibitionName;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    public static void main(String[] args) {
        System.out.println(ExhibitionDao.getInstance().getExhibitionPriceById(2));
    }
}