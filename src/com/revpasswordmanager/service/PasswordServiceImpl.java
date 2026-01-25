package com.revpasswordmanager.service;



import com.revpasswordmanager.dao.PasswordEntryDAO;
import com.revpasswordmanager.dao.PasswordHistoryDAO;
import com.revpasswordmanager.dao.PasswordEntryDAOImpl;
import com.revpasswordmanager.dao.PasswordHistoryDAOImpl;
import com.revpasswordmanager.model.PasswordEntry;
import com.revpasswordmanager.service.PasswordService;
import com.revpasswordmanager.util.PasswordUtil;

import java.sql.SQLException;
import java.util.List;

public class PasswordServiceImpl implements PasswordService {

    private final PasswordEntryDAO entryDAO = new PasswordEntryDAOImpl();
    private final PasswordHistoryDAO historyDAO = new PasswordHistoryDAOImpl();

    @Override
    public void addPassword(int userId, String acc, String user,
                            String rawPassword, String notes)
            throws SQLException {

        PasswordEntry p = new PasswordEntry(
                userId,
                acc,
                user,
                PasswordUtil.hash(rawPassword),
                notes
        );
        entryDAO.addPassword(p);
    }

    @Override
    public List<PasswordEntry> viewPasswords(int userId) throws SQLException {
        return entryDAO.getPasswordsByUser(userId);
    }

    @Override
    public void updatePassword(int passwordId, int userId,
                               String newRawPassword, String notes)
            throws SQLException {

        String oldEncrypted = entryDAO.getEncryptedPasswordById(passwordId);
        historyDAO.saveOldPassword(userId, oldEncrypted);

        String newEncrypted = PasswordUtil.hash(newRawPassword);
        entryDAO.updatePassword(passwordId, newEncrypted, notes);
    }

    @Override
    public void deletePassword(int passwordId) throws SQLException {
        entryDAO.deletePassword(passwordId);
    }

    @Override
    public List<PasswordEntry> searchPasswords(int userId, String keyword)
            throws SQLException {
        return entryDAO.searchPasswords(userId, keyword);
    }
}
