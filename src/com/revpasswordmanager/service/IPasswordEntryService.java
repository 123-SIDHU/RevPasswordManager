package com.revpasswordmanager.service;

import java.util.List;
import com.revpasswordmanager.model.PasswordEntry;

public interface IPasswordEntryService {

    void addPassword(PasswordEntry entry);

    List<PasswordEntry> viewPasswords(int userId);

    void updatePassword(PasswordEntry entry);

    void deletePassword(int entryId);
}
