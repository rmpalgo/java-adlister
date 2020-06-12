package com.codeup.adlister.dao;

import com.codeup.adlister.models.User;
import dao.Config;
import com.mysql.cj.jdbc.Driver;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.List;

public class MySQLUserDao implements Users {
    private Connection connection = null;

    public MySQLUserDao (Config config) {
        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(
                    config.getUrl(),
                    config.getUsername(),
                    config.getPassword()
            );
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database!", e);
        }
    }

    @Override
    public User findByUsername(String username) {
       String query = "SELECT * FROM users WHERE username = ? LIMIT 1";
       try {
           PreparedStatement stmt = connection.prepareStatement(query);
           stmt.setString(1, username);
           return extractUser(stmt.executeQuery());
       } catch (SQLException e) {
           throw new RuntimeException("Error retrieving user", e);
       }
    }

    @Override
    public Long insert(User user) {
        String query = "INSERT INTO users(username, email, password) VALUES (?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            return rs.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving user", e);
        }
    }

    private User extractUser(ResultSet rs) throws SQLException{
        if( ! rs.next() ) {
            return null;
        }
        return new User(
                rs.getLong("id"),
                rs.getString("username"),
                rs.getString("email"),
                rs.getString("password")
        );
    }

}
