package com.revpasswordmanager.dao;


import com.revpasswordmanager.config.DBConnection;
import com.revpasswordmanager.model.PasswordEntry;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PasswordEntryDao {
    public void add(PasswordEntry p) throws SQLException {
        String sql = "INSERT INTO password_entries VALUES(password_seq.NEXTVAL,?,?,?,?,?,NULL,SYSDATE,SYSDATE)";
        PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
        ps.setInt(1, p.userId);
        ps.setInt(2, 0);
        ps.setString(3, p.accountName);
        ps.setString(4, p.username);
        ps.setString(5, p.encryptedPassword);
        ps.setString(6, "No Notes");
        ps.executeUpdate();
    }
}

