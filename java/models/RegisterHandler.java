package models;


import connect.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.UUID;

public class RegisterHandler {

    public boolean registerUser(String email, String password, String username) {
        Connection connection = null;
        PreparedStatement statement = null;
        String userId = UUID.randomUUID().toString();

        try {
            // 获取数据库连接
            connection = DatabaseConnectionManager.getConnection();

            // SQL 插入语句，插入新用户
            String sql = "INSERT INTO users (id, email, password, username) VALUES (?, ?, ?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, userId);
            statement.setString(2, email);
            statement.setString(3, password);
            statement.setString(4, username);

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0; // 如果插入成功返回 true

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            // 关闭资源
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public boolean checkUsernameExists(String username) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // 获取数据库连接
            connection = DatabaseConnectionManager.getConnection();

            // SQL 查询语句，检查用户名是否存在
            String sql = "SELECT COUNT(*) FROM users WHERE email = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);

            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0; // 如果 count 大于 0，表示用户名已存在
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false; // 默认返回 false，如果出现异常或没有找到用户名
    }

}

