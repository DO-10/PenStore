package models;


import connect.DatabaseConnectionManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import objects.User;

import java.io.File;
import java.nio.file.Paths;
import java.sql.*;


public class UserService {
    public String getUsernameById(String userId) {
        String username = null;
        String sql = "SELECT username FROM users WHERE id = ?";

        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                username = rs.getString("username");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return username;
    }

    public void changename(String newname, String username) {
        String sql = "UPDATE users SET username = ? WHERE username = ?";
        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newname);
            pstmt.setString(2, username);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("用户名更新成功");
            } else {
                System.out.println("用户名更新失败，可能是因为没有找到匹配的用户名");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUserById(String userId) {
        String sql = "SELECT * FROM users WHERE id = ?";
        User user = new User();
        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {

                user.setId(rs.getString("id"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPhoto(rs.getString("photo"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
       return user;
    }

    public String changePhoto(String userId, Part filePart, HttpServletRequest request) {
        String avatarUrl = null;
        String UPLOAD_DIRECTORY = request.getServletContext().getRealPath("/") + "avatars";



        // 验证文件是否有效
        if (filePart != null && filePart.getSize() > 0) {
            try {
                // 获取用户名
                String username = getUsernameById(userId);

                if (username == null) {
                    System.out.println("用户不存在");
                    return null;
                }

                // 获取文件名并构造头像文件路径
                String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                File uploadDir = new File(UPLOAD_DIRECTORY);
                if (!uploadDir.exists()) uploadDir.mkdirs(); // 创建文件夹

                // 保存头像文件
                String avatarPath = UPLOAD_DIRECTORY + File.separator + username + "_" + fileName;
                filePart.write(avatarPath);

                // 生成URL
                avatarUrl = "avatars/" + username + "_" + fileName;

                // 更新数据库中的头像 URL
                updateAvatarUrlInDatabase(userId, avatarUrl);
                System.out.println("头像上传成功：" + avatarUrl);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("文件上传失败，请重试。");
        }

        return avatarUrl;
    }



    private void updateAvatarUrlInDatabase(String userId, String avatarUrl) {
        String sql = "UPDATE users SET photo = ? WHERE id = ?";

        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, avatarUrl);
            pstmt.setString(2, userId);
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("头像 URL 更新成功");
            } else {
                System.out.println("头像 URL 更新失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

