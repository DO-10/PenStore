package models;
import connect.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class LogService {
    public void logAction(String userId, String actionType) {
        String uuid = UUID.randomUUID().toString();
        String sql = "INSERT INTO logs (log_id, user_id, action, time) VALUES (?, ?, ?, NOW())";

        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, uuid);
            stmt.setString(2, userId);
            stmt.setString(3, actionType);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
