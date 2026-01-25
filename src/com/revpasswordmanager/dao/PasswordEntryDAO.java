package com.revpasswordmanager.dao;


import com.passwordmanager.model.PasswordEntry;
import java.sql.SQLException;
import java.util.List;

public interface PasswordEntryDAO {

    void addPassword(PasswordEntry entry) throws SQLException;

    List<PasswordEntry> getPasswordsByUser(int userId) throws SQLException;

    String getEncryptedPasswordById(int passwordId) throws SQLException;

    void updatePassword(int passwordId, String encryptedPassword, String notes)
            throws SQLException;

    void deletePassword(int passwordId) throws SQLException;

    List<PasswordEntry> searchPasswords(int userId, String keyword)
            throws SQLException;
}
