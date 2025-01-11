package models;


import connect.DatabaseConnectionManager;
import objects.Product;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductService {

    public List<Product> searchProducts(String query) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE name LIKE ?";

        try (Connection connection = DatabaseConnectionManager.getConnection(); // 使用 DatabaseConnectionManager
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, "%" + query + "%"); // 使用 LIKE 查询
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Product product = new Product();
                product.setImageUrl(resultSet.getString("image_url"));
                product.setId(resultSet.getString("id"));
                product.setName(resultSet.getString("name"));
                product.setDescription(resultSet.getString("description"));
                product.setPrice(resultSet.getBigDecimal("price"));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
    public Product getProductById(String id) {
        Product product = null;
        String sql = "SELECT * FROM products WHERE id = ?";

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                product = new Product();
                product.setId(resultSet.getString("id"));
                product.setName(resultSet.getString("name"));
                product.setDescription(resultSet.getString("description"));
                product.setPrice(resultSet.getBigDecimal("price"));
                product.setStock(resultSet.getInt("stock"));
                product.setImageUrl(resultSet.getString("image_url")); // 假设有一个 image_url 字段
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }
    public List<Product> getProductsWithCartQuantities(String[] productIds) {
        List<Product> products = new ArrayList<>();
        if (productIds == null || productIds.length == 0) {
            return products; // 返回空列表
        }

        StringBuilder sql = new StringBuilder(
                "SELECT p.id, p.name, p.description, p.price, p.stock, p.image_url, c.quantity " +
                        "FROM products p LEFT JOIN cart c ON p.id = c.product_id " +
                        "WHERE p.id IN ("
        );
        for (int i = 0; i < productIds.length; i++) {
            sql.append("?");
            if (i < productIds.length - 1) {
                sql.append(", ");
            }
        }
        sql.append(")");

        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {

            for (int i = 0; i < productIds.length; i++) {
                pstmt.setString(i + 1, productIds[i]);
            }

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getString("id"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getBigDecimal("price"));
                product.setStock(rs.getInt("stock"));
                product.setImageUrl(rs.getString("image_url"));
                product.setQuantity(rs.getString("quantity") != null ? rs.getString("quantity") : "0"); // 设置数量，默认0
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public List<Product> getProductsByOrderId(String orderId) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT product_id, quantity, price FROM order_items WHERE order_id = ?";

        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, orderId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String productId = rs.getString("product_id");
                String quantity = rs.getString("quantity");
                BigDecimal price = rs.getBigDecimal("price");

                // 通过 productId 获取产品详细信息
                Product product = getProductById(productId);
                if (product != null) {
                    product.setQuantity(quantity);
                    product.setPrice(price);
                    products.add(product);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
    public List<Product> getProductsByCategoryId(int categoryId) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE category_id = ?";

        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, categoryId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getString("id"));
                product.setName(resultSet.getString("name"));
                product.setDescription(resultSet.getString("description"));
                product.setPrice(resultSet.getBigDecimal("price"));
                product.setStock(resultSet.getInt("stock"));
                product.setImageUrl(resultSet.getString("image_url"));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
}

