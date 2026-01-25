package com.revpasswordmanager.dao;

import com.revpasswordmanager.model.VerificationCode;
import com.revpasswordmanager.util.DBUtil;

import java.sql.*;
        import java.time.LocalDateTime;

public class VerificationCodeDAO {

    public void saveOTP(VerificationCode vc) {
        String sql = "INSERT INTO verification_codes (user_id, code, purpose, expiry_time) VALUES (?, ?, ?, ?)";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, vc.getUserId());
            ps.setString(2, vc.getCode());
            ps.setString(3, vc.getPurpose());
            ps.setTimestamp(4, Timestamp.valueOf(vc.getExpiryTime()));
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean validateOTP(int userId, String otp, String purpose) {
        String sql = """
                SELECT id, expiry_time FROM verification_codes
                WHERE user_id=? AND code=? AND purpose=? AND is_used=0
                """;

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setString(2, otp);
            ps.setString(3, purpose);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                LocalDateTime expiry = rs.getTimestamp("expiry_time").toLocalDateTime();
                if (expiry.isBefore(LocalDateTime.now())) {
                    return false;
                }
                markAsUsed(rs.getInt("id"));
                return true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    private void markAsUsed(int id) throws SQLException {
        String sql = "UPDATE verification_codes SET is_used=1 WHERE id=?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}

