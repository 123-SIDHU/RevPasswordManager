package com.revpasswordmanager.dao;

import com.revpasswordmanager.model.VerificationCode;

import java.sql.*;

public class VerificationCodeDAO {
    private Connection connection;

    public VerificationCodeDAO(Connection connection) {
        this.connection = connection;
    }

    public void create(VerificationCode verificationCode) throws SQLException {
        String sql = "INSERT INTO verification_codes (user_id, code, purpose, expiry_time, is_used) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, verificationCode.getUserId());
            pstmt.setString(2, verificationCode.getCode());
            pstmt.setString(3, verificationCode.getPurpose());
            pstmt.setTimestamp(4, verificationCode.getExpiryTime());
            pstmt.setInt(5, verificationCode.isUsed() ? 1 : 0);
            pstmt.executeUpdate();
        }
    }

    public VerificationCode getByCodeAndUser(String code, int userId) throws SQLException {
        String sql = "SELECT * FROM verification_codes WHERE code = ? AND user_id = ? AND is_used = 0 AND expiry_time > CURRENT_TIMESTAMP";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, code);
            pstmt.setInt(2, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new VerificationCode(
                            rs.getInt("id"),
                            rs.getInt("user_id"),
                            rs.getString("code"),
                            rs.getString("purpose"),
                            rs.getTimestamp("expiry_time"),
                            rs.getBoolean("is_used"),
                            rs.getTimestamp("created_at"));
                }
            }
        }
        return null;
    }

    public void markAsUsed(int id) throws SQLException {
        String sql = "UPDATE verification_codes SET is_used = 1 WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    public void cleanupExpired() throws SQLException {
        String sql = "DELETE FROM verification_codes WHERE expiry_time < CURRENT_TIMESTAMP";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.executeUpdate();
        }
    }
}