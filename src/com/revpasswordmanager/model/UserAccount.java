package com.revpasswordmanager.model;

public class UserAccount {
    private int id;
    private int userId;
    private String siteName;
    private String username;
    private String password;
    private String notes;

    public UserAccount(int userId, String siteName, String username, String password, String notes) {
        this.userId = userId;
        this.siteName = siteName;
        this.username = username;
        this.password = password;
        this.notes = notes;
    }

    public UserAccount(int id, int userId, String siteName, String username, String password, String notes) {
        this.id = id;
        this.userId = userId;
        this.siteName = siteName;
        this.username = username;
        this.password = password;
        this.notes = notes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}