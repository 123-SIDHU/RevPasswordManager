package com.revpasswordmanager.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.revpasswordmanager.model.PasswordEntry;
import com.revpasswordmanager.util.JdbcUtil;

public class PasswordEntryDaoImpl implements IPasswordEntryDao {

    @Override
    public void addPasswordEntry(PasswordEntry entry) {
        String sql = "INSERT INTO password_entries VALUES (password_seq.NEXTVAL, ?, ?, ?, ?, ?)";

        try (Connection con = JdbcUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, entry.getUserId());
            ps.setString(2, entry.getAccountName());
            ps.setString(3, entry.getUsername());
            ps.setString(4, entry.getPassword());
            ps.setString(5, entry.getNotes());

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<PasswordEntry> getPasswordsByUser(int userId) {
        List<PasswordEntry> list = new ArrayList<>();
        String sql = "SELECT * FROM password_entries WHERE user_id=?";

        try (Connection con = JdbcUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                PasswordEntry entry = new PasswordEntry();
                entry.setEntryId(rs.getInt(1));
                entry.setUserId(rs.getInt(2));
                entry.setAccountName(rs.getString(3));
                entry.setUsername(rs.getString(4));
                entry.setPassword(rs.getString(5));
                entry.setNotes(rs.getString(6));
                list.add(entry);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void updatePasswordEntry(PasswordEntry entry) {
        String sql = "UPDATE password_entries SET password=?, notes=? WHERE entry_id=?";

        try (Connection con = JdbcUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, entry.getPassword());
            ps.setString(2, entry.getNotes());
            ps.setInt(3, entry.getEntryId());

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletePasswordEntry(int entryId) {
        String sql = "DELETE FROM password_entries WHERE entry_id=?";

        try (Connection con = JdbcUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, entryId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
