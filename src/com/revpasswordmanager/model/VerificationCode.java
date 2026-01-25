package com.revpasswordmanager.model;

import java.time.LocalDateTime;

public class VerificationCode {

    private int id;
    private int userId;
    private String code;
    private String purpose;
    private LocalDateTime expiryTime;
    private boolean used;

    public VerificationCode(int userId, String code, String purpose, LocalDateTime expiryTime) {
        this.userId = userId;
        this.code = code;
        this.purpose = purpose;
        this.expiryTime = expiryTime;
        this.used = false;
    }

    public int getUserId() { return userId; }
    public String getCode() { return code; }
    public String getPurpose() { return purpose; }
    public LocalDateTime getExpiryTime() { return expiryTime; }
    public boolean isUsed() { return used; }
}
