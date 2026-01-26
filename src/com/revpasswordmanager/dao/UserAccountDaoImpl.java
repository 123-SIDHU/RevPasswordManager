package com.revpasswordmanager.dao;

import com.revpasswordmanager.model.UserAccount;
import com.revpasswordmanager.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserAccountDaoImpl implements IUserAccountDao {

    @Override
    public boolean addUserAccount(UserAccount userAccount) {
        String sql = "INSERT INTO user_accounts (user_id, site_name, username, password, notes) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = DatabaseConnection.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, userAccount.getUserId());
            pstmt.setString(2, userAccount.getSiteName());
            pstmt.setString(3, userAccount.getUsername());
            pstmt.setString(4, userAccount.getPassword());
            pstmt.setString(5, userAccount.getNotes());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateUserAccount(UserAccount userAccount) {
        String sql = "UPDATE user_accounts SET site_name = ?, username = ?, password = ?, notes = ? WHERE id = ?";
        try (Connection con = DatabaseConnection.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, userAccount.getSiteName());
            pstmt.setString(2, userAccount.getUsername());
            pstmt.setString(3, userAccount.getPassword());
            pstmt.setString(4, userAccount.getNotes());
            pstmt.setInt(5, userAccount.getId());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteUserAccount(int id) {
        String sql = "DELETE FROM user_accounts WHERE id = ?";
        try (Connection con = DatabaseConnection.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public UserAccount getUserAccount(int id) {
        String sql = "SELECT * FROM user_accounts WHERE id = ?";
        try (Connection con = DatabaseConnection.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new UserAccount(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getString("site_name"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("notes"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<UserAccount> getAllUserAccounts() {
        List<UserAccount> accounts = new ArrayList<>();
        String sql = "SELECT * FROM user_accounts";
        try (Connection con = DatabaseConnection.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                accounts.add(new UserAccount(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getString("site_name"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("notes")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }
}
