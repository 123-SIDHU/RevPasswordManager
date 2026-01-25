package com.revpasswordmanager;

import com.revpasswordmanager.dao.UserDao;
import com.revpasswordmanager.model.User;
import com.revpasswordmanager.service.OTPService;
import com.revpasswordmanager.util.DatabaseConnection;

import java.sql.Connection;

public class OTPVerification {
    public static void main(String[] args) {
        System.out.println("Starting OTP Verification...");

        try {
            Connection connection = DatabaseConnection.getConnection();
            if (connection == null) {
                System.err.println("Failed to connect to database!");
                return;
            }

            // 1. Setup Data - Create a temp user if needed
            UserDao userDao = new UserDao(connection);
            String testUsername = "test_user_otp_" + System.currentTimeMillis();
            User testUser = new User(testUsername, "dummy_hash", "Test User OTP", "test@example.com");

            System.out.println("Creating test user: " + testUsername);
            userDao.createUser(testUser);
            User savedUser = userDao.getUserByUsername(testUsername);

            if (savedUser == null) {
                System.err.println("Failed to create test user!");
                return;
            }
            int userId = savedUser.getId();
            System.out.println("User created with ID: " + userId);

            // 2. Generate OTP
            OTPService otpService = new OTPService();
            String purpose = "TEST_PURPOSE";
            System.out.println("Generating OTP...");
            String code = otpService.generateOTP(userId, purpose);
            System.out.println("Generated Code: " + code);

            if (code == null) {
                System.err.println("OTP Generation failed!");
                return;
            }

            // 3. Validate OTP (Positive Case)
            System.out.println("Validating OTP (Positive Case)...");
            boolean isValid = otpService.validateOTP(userId, code, purpose);
            System.out.println("Is Valid (Expected True): " + isValid);
            if (!isValid) {
                System.err.println("Test Failed: OTP should be valid.");
            }

            // 4. Validate OTP (Negative Case - Already Used)
            System.out.println("Validating OTP (Negative Case - Replay)...");
            boolean isReplayValid = otpService.validateOTP(userId, code, purpose);
            System.out.println("Is Replay Valid (Expected False): " + isReplayValid);
            if (isReplayValid) {
                System.err.println("Test Failed: OTP should be invalid (already used).");
            }

            // 5. Cleanup (Optional, but good practice)
            // Note: In a real test env we would delete the user.
            // For now, we leave it or manual cleanup might be needed if run repeatedly.

            System.out.println("OTP Verification Complete.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
