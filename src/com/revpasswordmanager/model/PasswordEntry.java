package com.revpasswordmanager.model;

import java.util.Date;

public class PasswordEntry {

    private int passwordId;
    private int userId;
    private String accountName;
    private String username;
    private String encryptedPassword;
    private String notes;
    private Date createdAt;
    private Date updatedAt;

    public PasswordEntry() {}

    public PasswordEntry(int userId, String accountName, String username,
                         String encryptedPassword, String notes) {
        this.userId = userId;
        this.accountName = accountName;
        this.username = username;
        this.encryptedPassword = encryptedPassword;
        this.notes = notes;
    }

    public int getPasswordId() { return passwordId; }
    public void setPasswordId(int passwordId) { this.passwordId = passwordId; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getAccountName() { return accountName; }
    public void setAccountName(String accountName) { this.accountName = accountName; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEncryptedPassword() { return encryptedPassword; }
    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }

    public Date getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Date updatedAt) { this.updatedAt = updatedAt; }
}
