package models;

import entities.DataBase;
import entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserModel {
    private final List<User> usuarios = new ArrayList<>();
    Connection conn = DataBase.getConnection();

    public void registerUser(User user) {
        String sql = "INSERT INTO users (username, password, email) VALUES (?,?,?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, user.getUserName());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getEmail());
            pstmt.executeUpdate();

            usuarios.add(user);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public boolean loginUser(String username, String password) {
        String sql = "SELECT id, username, password, email FROM users WHERE email = ? AND password = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUserName(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                usuarios.add(user);
                return true;
            }

            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<User> getUsuarios() {
        return usuarios;
    }
}
