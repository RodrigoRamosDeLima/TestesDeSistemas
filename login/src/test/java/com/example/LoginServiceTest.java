package com.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoginTest {
    private Login login;

    @BeforeEach
    public void setUp() {
        login = new Login();
    }

    @Test
    public void testLoginSuccessful() {
        assertTrue(login.authenticate("user", "password123"), "Login should be successful with valid credentials.");
    }

    @Test
    public void testLoginInvalidUsername() {
        assertFalse(login.authenticate("invalidUser", "password123"), "Login should fail with invalid username.");
    }

    @Test
    public void testLoginInvalidPassword() {
        assertFalse(login.authenticate("user", "wrongPassword"), "Login should fail with invalid password.");
    }
}
