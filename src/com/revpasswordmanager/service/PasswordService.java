package com.revpasswordmanager.service;



import com.revpasswordmanager.dao.PasswordEntryDAO;
import com.revpasswordmanager.model.PasswordEntry;
import com.revpasswordmanager.util.PasswordUtil;

public class PasswordService {
    PasswordEntryDAO dao = new PasswordEntryDAO();

    public void addPassword(int userId, String acc, String user, String pwd) throws Exception {
        PasswordEntry p = new PasswordEntry();
        p.userId = userId;
        p.accountName = acc;
        p.username = user;
        p.encryptedPassword = PasswordUtil.hash(pwd);
        dao.add(p);
    }
}

