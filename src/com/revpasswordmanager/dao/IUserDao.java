package com.revpasswordmanager.dao;

import com.revpasswordmanager.model.SecurityQuestion;

import java.sql.SQLException;
import java.util.List;

public interface IUserDao {
    void createUser(User user) throws SQLException;

    User getUserByUsername(String username) throws SQLException;

    void updateUser(User user) throws SQLException;

    void addSecurityQuestion(SecurityQuestion sq) throws SQLException;

    List<SecurityQuestion> getSecurityQuestionsByUserId(int userId) throws SQLException;

    void updateSecurityQuestion(SecurityQuestion sq) throws SQLException;

    void deleteSecurityQuestion(int id) throws SQLException;

    SecurityQuestion getSecurityQuestionById(int id) throws SQLException;
}
