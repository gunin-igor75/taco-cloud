package ru.gil.tacocloud.controller;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

class RegistrationControllerTest {

    @Test
    public void test() {
        PasswordEncoder encoder = new BCryptPasswordEncoder(10);
        String encode = encoder.encode("12345");
        System.out.println(encode);
        boolean mes = encoder.matches( "12345", encode);
        System.out.println(mes);
    }
}