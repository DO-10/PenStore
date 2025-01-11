package models;

import connect.DatabaseConnectionManager;
import objects.Order;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class OrderService {

    // 创建订单并返回订单ID（UUID）
    public String createOrder(String userId, String address) {
        String orderId = UUID.randomUUID().toString(); // 生成 UUID
        String sql = "INSERT INTO orders (order_id, user_id, shipping_address, created_at) VALUES (?, ?, ?,NOW())";//插入语句

        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, orderId);
            pstmt.setString(2, userId);
            pstmt.setString(3, address);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderId; // 返回生成的订单ID
    }

    // 添加订单项
    public void addOrderItem(String orderId, String productId) {
        String sqlSelect1 = "SELECT quantity FROM cart WHERE product_id = ?";
        String sqlSelect2 = "SELECT price From products Where id = ?";
        String sqlInsert = "INSERT INTO order_items (order_id, product_id, quantity, price) VALUES (?, ?, ?, ?)";


        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement pstmtSelect1 = conn.prepareStatement(sqlSelect1);
             PreparedStatement pstmtSelect2 = conn.prepareStatement(sqlSelect2);
             PreparedStatement pstmtInsert = conn.prepareStatement(sqlInsert)) {

            // 查询购物车中的数量和价格
            pstmtSelect1.setString(1, productId);
            pstmtSelect2.setString(1,productId);
            ResultSet rs1 = pstmtSelect1.executeQuery();
            ResultSet rs2 = pstmtSelect2.executeQuery();

            if (rs1.next()&&rs2.next()) {
                // 从结果集中获取数量和价格
                String quantity = rs1.getString("quantity");
                BigDecimal price = rs2.getBigDecimal("price");

                // 插入订单项
                pstmtInsert.setString(1, orderId);
                pstmtInsert.setString(2, productId);
                pstmtInsert.setString(3, quantity);
                pstmtInsert.setBigDecimal(4, price);
                pstmtInsert.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Order> getOrdersByUserId(String userId) {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT order_id, created_at FROM orders WHERE user_id = ?";

        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Order order = new Order(rs.getString("order_id"));
                order.setCreatedAt(rs.getString("created_at")); // 设置 createdAt
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
    public List<String> getUserAddresses(String userId) {
        List<String> addresses = new ArrayList<>();
        String sql = "SELECT address FROM users WHERE id = ?";

        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String address = rs.getString("address");
                addresses.add(address);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return addresses;
    }
    public void updateOrderDetails(String orderId, String notes, String phone) {
        String sql = "UPDATE orders SET note = ?, phone = ? WHERE order_id = ?";

        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, notes);
            pstmt.setString(2, phone);
            pstmt.setString(3, orderId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
