package models;



import connect.DatabaseConnectionManager;
import objects.Product;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class CartService {

    // 获取用户购物车的商品列表
    public List<Product> getCartItemsByUserId(String userId) {
        List<Product> cartItems = new ArrayList<>();

        String sql = "SELECT p.id, p.name, p.description, p.price, c.quantity " +
                "FROM cart c " +
                "JOIN products p ON c.product_id = p.id " +
                "WHERE c.user_id = ?";

        try (Connection connection = DatabaseConnectionManager.getConnection(); // 使用 DatabaseConnectionManager 获取连接
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, userId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getString("id"));
                product.setName(resultSet.getString("name"));
                product.setDescription(resultSet.getString("description"));
                product.setPrice(resultSet.getBigDecimal("price"));
                product.setQuantity(resultSet.getString("quantity")); // 设置商品数量

                cartItems.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cartItems;
    }
    public boolean addToCart(String userId, int productId) {
        // 先检查购物车中是否已存在该商品
        String checkSql = "SELECT quantity FROM cart WHERE user_id = ? AND product_id = ?";
        String insertSql = "INSERT INTO cart (id, user_id, product_id, quantity) VALUES (?, ?, ?, 1)"; // 假设初始数量为 1
        String updateSql = "UPDATE cart SET quantity = quantity + 1 WHERE user_id = ? AND product_id = ?";

        try (Connection connection = DatabaseConnectionManager.getConnection()) {
            // 检查商品是否已在购物车中
            try (PreparedStatement checkStatement = connection.prepareStatement(checkSql)) {
                checkStatement.setString(1, userId);
                checkStatement.setInt(2, productId);
                ResultSet resultSet = checkStatement.executeQuery();

                if (resultSet.next()) {
                    // 如果商品已存在，更新数量
                    int currentQuantity = resultSet.getInt("quantity");
                    try (PreparedStatement updateStatement = connection.prepareStatement(updateSql)) {
                                updateStatement.setString(1, userId);
                        updateStatement.setInt(2, productId);
                        updateStatement.executeUpdate();
                    }
                } else {
                    // 如果商品不存在，插入新记录
                    String uuid = UUID.randomUUID().toString();
                    try (PreparedStatement insertStatement = connection.prepareStatement(insertSql)) {
                        insertStatement.setString(1, uuid);
                        insertStatement.setString(2, userId);
                        insertStatement.setInt(3, productId);
                        insertStatement.executeUpdate();
                    }
                }
                return true;

            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public void deleteProduct(String userId, String productId) {
        String deleteSql = "DELETE FROM cart WHERE user_id = ? AND product_id = ?";
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(deleteSql)) {
            statement.setString(1, userId);
            statement.setString(2, productId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public BigDecimal calculateTotalPriceByUserId(String userId) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        String sql = "SELECT p.price, c.quantity FROM cart c " +
                "JOIN products p ON c.product_id = p.id " +
                "WHERE c.user_id = ? AND c.ischosen = 1";

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, userId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                BigDecimal price = resultSet.getBigDecimal("price");
                int quantity = resultSet.getInt("quantity");
                totalPrice = totalPrice.add(price.multiply(BigDecimal.valueOf(quantity))); // 计算总价格
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return totalPrice;
    }
    //1
    public void updateQuantity(String userId, String productId, String operation) {
        int changeAmount = 0;

        // 根据operation的值设置changeAmount
        if (Objects.equals(operation, "increase")) {
            changeAmount = 1; // 增加数量
        } else if (Objects.equals(operation, "decrease")) {
            changeAmount = -1; // 减少数量
        } else {
            throw new IllegalArgumentException("Invalid operation: " + operation);
        }

        String updateSql = "UPDATE cart SET quantity = quantity + ? WHERE user_id = ? AND product_id = ?";
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(updateSql)) {
            statement.setInt(1, changeAmount);
            statement.setString(2, userId);
            statement.setString(3, productId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //1

    public void updateIsChosen(String userId, String productId, boolean isChosen) {
        String sql = "UPDATE cart SET ischosen = ? WHERE user_id = ? AND product_id = ?";
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, isChosen ? 1 : 0); // 如果 isChosen 为 true，设置为 1，否则为 0
            statement.setString(2, userId);
            statement.setString(3, productId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


        public int getProductQuantity(String userId, String productId) {
        String sql = "SELECT quantity FROM cart WHERE user_id = ? AND product_id = ?";
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, userId);
            statement.setString(2, productId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("quantity");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}

