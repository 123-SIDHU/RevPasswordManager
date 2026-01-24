package com.revpasswordmanager.controller;

import java.util.List;
import java.util.Scanner;

import com.revpasswordmanager.model.PasswordEntry;
import com.revpasswordmanager.service.IPasswordEntryService;
import com.revpasswordmanager.service.PasswordEntryServiceImpl;

public class PasswordEntryController {

    private IPasswordEntryService service = new PasswordEntryServiceImpl();
    private Scanner sc = new Scanner(System.in);

    public void addPassword(int userId) {
        PasswordEntry entry = new PasswordEntry();

        entry.setUserId(userId);
        System.out.print("Account Name: ");
        entry.setAccountName(sc.nextLine());
        System.out.print("Username: ");
        entry.setUsername(sc.nextLine());
        System.out.print("Password: ");
        entry.setPassword(sc.nextLine());
        System.out.print("Notes: ");
        entry.setNotes(sc.nextLine());

        service.addPassword(entry);
        System.out.println("Password added successfully.");
    }

    public void viewPasswords(int userId) {
        List<PasswordEntry> list = service.viewPasswords(userId);
        for (PasswordEntry e : list) {
            System.out.println(
                    e.getEntryId() + " | " +
                            e.getAccountName() + " | " +
                            e.getUsername() + " | " +
                            e.getNotes()
            );
        }
    }

    public void deletePassword() {
        System.out.print("Enter Entry ID to delete: ");
        int id = sc.nextInt();
        sc.nextLine();
        service.deletePassword(id);
        System.out.println("Password deleted.");
    }
}
