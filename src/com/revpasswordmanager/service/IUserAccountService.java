package com.revpasswordmanager.service;

import com.revpasswordmanager.model.UserAccount;

import java.util.List;

public interface IUserAccountService {
    public boolean  addUserAccount(UserAccount userAccount);
    public boolean  updateUserAccount(UserAccount userAccount);
    public boolean  deleteUserAccount(int userId);
    public UserAccount getUserAccount(int userId);
    public List<UserAccount> getAllUserAccounts();
}

