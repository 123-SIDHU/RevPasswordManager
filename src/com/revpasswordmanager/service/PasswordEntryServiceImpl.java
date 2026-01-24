package com.revpasswordmanager.service;

import java.util.List;

import com.revpasswordmanager.dao.IPasswordEntryDao;
import com.revpasswordmanager.dao.PasswordEntryDaoImpl;
import com.revpasswordmanager.model.PasswordEntry;

public class PasswordEntryServiceImpl implements IPasswordEntryService {

    private IPasswordEntryDao dao = new PasswordEntryDaoImpl();

    @Override
    public void addPassword(PasswordEntry entry) {
        dao.addPasswordEntry(entry);
    }

    @Override
    public List<PasswordEntry> viewPasswords(int userId) {
        return dao.getPasswordsByUser(userId);
    }

    @Override
    public void updatePassword(PasswordEntry entry) {
        dao.updatePasswordEntry(entry);
    }

    @Override
    public void deletePassword(int entryId) {
        dao.deletePasswordEntry(entryId);
    }
}
