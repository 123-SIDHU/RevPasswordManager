package com.revpasswordmanager.service;

import com.revpasswordmanager.dao.VerificationCodeDAO;
import com.revpasswordmanager.model.VerificationCode;
import com.revpasswordmanager.util.DatabaseConnection;

import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;

public class OTPService {
    private static final int OTP_LENGTH = 6;
    private static final long OTP_VALIDITY_MS = 5 * 60 * 1000; // 5 minutes
    private SecureRandom random = new SecureRandom();

    public String generateOTP(int userId, String purpose) {
        StringBuilder otp = new StringBuilder();
        for (int i = 0; i < OTP_LENGTH; i++) {
            otp.append(random.nextInt(10));
        }

        Timestamp expiryTime = new Timestamp(System.currentTimeMillis() + OTP_VALIDITY_MS);
        VerificationCode code = new VerificationCode(userId, otp.toString(), purpose, expiryTime);

        Connection connection = DatabaseConnection.getConnection();
        try {
            VerificationCodeDAO dao = new VerificationCodeDAO(connection);
            dao.create(code);
            return otp.toString();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean validateOTP(int userId, String code, String purpose) {
        Connection connection = DatabaseConnection.getConnection();
        try {
            VerificationCodeDAO dao = new VerificationCodeDAO(connection);
            VerificationCode validCode = dao.getByCodeAndUser(code, userId);

            if (validCode != null && validCode.getPurpose().equals(purpose)) {
                dao.markAsUsed(validCode.getId());
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
