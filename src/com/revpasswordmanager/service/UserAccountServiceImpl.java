package com.revpasswordmanager.service;

import com.revpasswordmanager.model.UserAccount;

import java.util.List;

public class UserAccountServiceImpl implements IUserAccountService {

    private IUserAccountDao userAccountDao = new UserAccountDaoImpl();

    @Override
    public boolean addUserAccount(UserAccount userAccount) {
        return userAccountDao.addUserAccount(userAccount);
    }

    @Override
    public boolean updateUserAccount(UserAccount userAccount) {
        return userAccountDao.updateUserAccount(userAccount);
    }

    @Override
    public boolean deleteUserAccount(int userId) {
        return userAccountDao.deleteUserAccount(userId);
    }

    @Override
    public UserAccount getUserAccount(int userId) {
        return userAccountDao.getUserAccount(userId);
    }

    @Override
    public List<UserAccount> getAllUserAccounts() {
        return userAccountDao.getAllUserAccounts();
    }
}
