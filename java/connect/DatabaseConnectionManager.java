package connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionManager {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/consumers";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "xy20041004";

    static {
        try {
            // 加载数据库驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }
}

