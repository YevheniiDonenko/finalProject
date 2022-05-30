package dbHelper;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DbHelper {

    private static final Logger log = Logger.getLogger(DbHelper.class);
    public static final String URL = "jdbc:mysql://localhost:3306/final";
    public static final String USER = "root";
    public static final String PASSWORD = "20031996";
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";

    private static DbHelper instance;

    private DbHelper() {

    }

    public synchronized static DbHelper getInstance() {
        if (instance == null) {
            instance = new DbHelper();
        }
        return instance;
    }

    public Connection getConnection() {
        log.debug("Connection starts");
        try {
            Class.forName(DB_DRIVER);
            log.debug("Successful connection");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            log.error("Error > " + e);
            throw new RuntimeException(e);
        }
    }
}
