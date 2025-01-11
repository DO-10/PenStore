    package models;

import connect.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginHandler {

    public String login(String email, String password) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // 获取数据库连接
            connection = DatabaseConnectionManager.getConnection();

            // SQL 查询语句，用于验证用户名和密码
            String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, password);

            resultSet = statement.executeQuery();

            // 检查查询结果，如果存在匹配记录，表示用户名和密码正确


            if (resultSet.next()) {
                return resultSet.getString("id");  // 获取用户 ID
            } else {
                return null;  // 用户名或密码不正确时返回 null
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
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
    }
    public String getUsername(String userId) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            // 获取数据库连接
            connection = DatabaseConnectionManager.getConnection();

            // SQL 查询语句，用于验证用户名和密码
            String sql = "SELECT username FROM users WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, userId);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("username");
            }
            return null;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
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
    }

}



