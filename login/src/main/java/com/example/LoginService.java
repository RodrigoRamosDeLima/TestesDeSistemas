package com.example;

public class LoginService {
    private String validUsername = "user";
    private String validPassword = "password123";

    public boolean authenticate(String username, String password) {
        return validUsername.equals(username) && validPassword.equals(password);
    }
}
