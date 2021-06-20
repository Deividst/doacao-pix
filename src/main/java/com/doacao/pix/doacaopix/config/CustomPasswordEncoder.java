package com.doacao.pix.doacaopix.config;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Base64;

public class CustomPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence plainTextPassword) {
        return Base64.getEncoder().encodeToString(plainTextPassword.toString().getBytes());
    }

    @Override
    public boolean matches(CharSequence plainTextPassword, String passwordInDatabase) {
        return Base64.getEncoder().encodeToString(plainTextPassword.toString().getBytes()).equals(passwordInDatabase);
    }
}