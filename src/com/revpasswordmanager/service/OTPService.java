package com.revpasswordmanager.service;

import com.revpasswordmanager.dao.VerificationCodeDAO;
import com.revpasswordmanager.model.VerificationCode;

import java.time.LocalDateTime;
import java.util.Random;

public class OTPService {

    private final VerificationCodeDAO dao = new VerificationCodeDAO();

    public String generateOTP(int userId, String purpose) {
        String otp = String.valueOf(100000 + new Random().nextInt(900000));
        LocalDateTime expiry = LocalDateTime.now().plusMinutes(5);

        VerificationCode vc = new VerificationCode(userId, otp, purpose, expiry);
        dao.saveOTP(vc);

        return otp;
    }

    public boolean verifyOTP(int userId, String otp, String purpose) {
        return dao.validateOTP(userId, otp, purpose);
    }
}
