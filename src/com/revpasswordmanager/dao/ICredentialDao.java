package com.revpasswordmanager.dao;

import com.revpasswordmanager.model.Credential;

import java.sql.SQLException;
import java.util.List;

public interface ICredentialDao {
    void addCredential(Credential credential) throws SQLException;

    List<Credential> getCredentialsByUserId(int userId) throws SQLException;

    Credential getCredentialById(int id, int userId) throws SQLException;

    void updateCredential(Credential credential) throws SQLException;

    void deleteCredential(int id, int userId) throws SQLException;

    List<Credential> searchCredentialsByAccountName(int userId, String accountName) throws SQLException;
}
