package com.revpasswordmanager.dao;

import java.util.List;
import com.revpasswordmanager.model.PasswordEntry;

public interface IPasswordEntryDao {

    void addPasswordEntry(PasswordEntry entry);

    List<PasswordEntry> getPasswordsByUser(int userId);

    void updatePasswordEntry(PasswordEntry entry);

    void deletePasswordEntry(int entryId);
}
