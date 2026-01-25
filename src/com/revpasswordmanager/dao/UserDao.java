package com.revpasswordmanager.dao;

import com.revpasswordmanager.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revpasswordmanager.model.SecurityQuestion;

public class UserDao {
    private Connection connection;

    public UserDao(Connection connection) {
        this.connection = connection;
    }

    public void createUser(User user) throws SQLException {
        String sql = "INSERT INTO users (username, master_password_hash, name, email) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getMasterPasswordHash());
            pstmt.setString(3, user.getName());
            pstmt.setString(4, user.getEmail());
            pstmt.executeUpdate();
        }
    }

    public User getUserByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt("id"), username, rs.getString("master_password_hash"),
                        rs.getString("name"), rs.getString("email"));
            }
            return null;
        }
    }

    public void updateUser(User user) throws SQLException {
        String sql = "UPDATE users SET master_password_hash = ?, name = ?, email = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, user.getMasterPasswordHash());
            pstmt.setString(2, user.getName());
            pstmt.setString(3, user.getEmail());
            pstmt.setInt(4, user.getId());
            pstmt.executeUpdate();
        }
    }

    public void addSecurityQuestion(SecurityQuestion sq) throws SQLException {
        String sql = "INSERT INTO security_questions (user_id, question, answer_hash) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, sq.getUserId());
            pstmt.setString(2, sq.getQuestion());
            pstmt.setString(3, sq.getAnswerHash());
            pstmt.executeUpdate();
        }
    }

    public List<SecurityQuestion> getSecurityQuestionsByUserId(int userId) throws SQLException {
        List<SecurityQuestion> questions = new ArrayList<>();
        String sql = "SELECT * FROM security_questions WHERE user_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                SecurityQuestion q = new SecurityQuestion(userId, rs.getString("question"),
                        rs.getString("answer_hash"));
                q.setId(rs.getInt("id"));
                questions.add(q);
            }
        }
        return questions;
    }
}