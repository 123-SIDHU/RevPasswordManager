package com.revpasswordmanager.util;

import java.util.Base64;

public class PasswordUtil {

    public static String hash(String password) {
        return Base64.getEncoder().encodeToString(password.getBytes());
    }

    public static boolean verify(String raw, String hashed) {
        return hash(raw).equals(hashed);
    }
}
