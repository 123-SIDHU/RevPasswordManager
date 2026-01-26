package com.revpasswordmanager.dao;

import com.revpasswordmanager.model.VerificationCode;

import java.sql.SQLException;

public interface IVerificationCodeDao {
    void create(VerificationCode verificationCode) throws SQLException;

    VerificationCode getByCodeAndUser(String code, int userId) throws SQLException;

    void markAsUsed(int id) throws SQLException;

    void cleanupExpired() throws SQLException;
}
