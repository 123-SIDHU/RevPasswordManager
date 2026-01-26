package com.revpasswordmanager.dao;

import com.revpasswordmanager.model.Credential;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CredentailDaoImpl implements ICredentialDao {
    private Connection connection;

    public CredentailDaoImpl(Connection connection) {
        this.connection = connection;
    }

    public void addCredential(Credential credential) throws SQLException {
        String sql = "INSERT INTO credentials (user_id, account_name, username, encrypted_password, url, notes) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, credential.getUserId());
            pstmt.setString(2, credential.getAccountName());
            pstmt.setString(3, credential.getUsername());
            pstmt.setString(4, credential.getEncryptedPassword());
            pstmt.setString(5, credential.getUrl());
            pstmt.setString(6, credential.getNotes());
            pstmt.executeUpdate();
        }
    }

    public List<Credential> getCredentialsByUserId(int userId) throws SQLException {
        List<Credential> credentials = new ArrayList<>();
        String sql = "SELECT * FROM credentials WHERE user_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                credentials.add(new Credential(rs.getInt("id"), userId, rs.getString("account_name"),
                        rs.getString("username"), rs.getString("encrypted_password"),
                        rs.getString("url"), rs.getString("notes")));
            }
        }
        return credentials;
    }

    public Credential getCredentialById(int id, int userId) throws SQLException {
        String sql = "SELECT * FROM credentials WHERE id = ? AND user_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setInt(2, userId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Credential(rs.getInt("id"), userId, rs.getString("account_name"),
                        rs.getString("username"), rs.getString("encrypted_password"),
                        rs.getString("url"), rs.getString("notes"));
            }
            return null;
        }
    }

    public void updateCredential(Credential credential) throws SQLException {
        String sql = "UPDATE credentials SET account_name = ?, username = ?, encrypted_password = ?, url = ?, notes = ? WHERE id = ? AND user_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, credential.getAccountName());
            pstmt.setString(2, credential.getUsername());
            pstmt.setString(3, credential.getEncryptedPassword());
            pstmt.setString(4, credential.getUrl());
            pstmt.setString(5, credential.getNotes());
            pstmt.setInt(6, credential.getId());
            pstmt.setInt(7, credential.getUserId());
            pstmt.executeUpdate();
        }
    }

    public void deleteCredential(int id, int userId) throws SQLException {
        String sql = "DELETE FROM credentials WHERE id = ? AND user_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setInt(2, userId);
            pstmt.executeUpdate();
        }
    }

    public List<Credential> searchCredentialsByAccountName(int userId, String accountName) throws SQLException {
        List<Credential> credentials = new ArrayList<>();
        String sql = "SELECT * FROM credentials WHERE user_id = ? AND account_name LIKE ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.setString(2, "%" + accountName + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                credentials.add(new Credential(rs.getInt("id"), userId, rs.getString("account_name"),
                        rs.getString("username"), rs.getString("encrypted_password"),
                        rs.getString("url"), rs.getString("notes")));
            }
        }
        return credentials;
    }
}
