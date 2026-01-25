package com.revpasswordmanager.controller;

import com.revpasswordmanager.service.OTPService;

import java.util.Scanner;

public class OTPController {

    private final OTPService otpService = new OTPService();
    private final Scanner scanner = new Scanner(System.in);

    public void startOTPFlow() {

        int userId = 101; // demo user
        String purpose = "PASSWORD_RESET";

        String otp = otpService.generateOTP(userId, purpose);
        System.out.println("Generated OTP (demo only): " + otp);

        System.out.print("Enter OTP: ");
        String enteredOtp = scanner.nextLine();

        boolean result = otpService.verifyOTP(userId, enteredOtp, purpose);

        if (result) {
            System.out.println("✅ OTP Verified Successfully");
        } else {
            System.out.println("❌ Invalid or Expired OTP");
        }
    }
}
