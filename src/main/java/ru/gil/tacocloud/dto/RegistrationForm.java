package ru.gil.tacocloud.dto;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.gil.tacocloud.model.Users;

@Data
public class RegistrationForm {

    private String username;
    private String password;
    private String fullName;
    private String street;
    private String city;
    private String region;
    private String zip;
    private String phone;

    public Users toUsers(PasswordEncoder encoder) {
        return Users.builder()
                .username(username)
                .password(encoder.encode(password))
                .fullName(fullName)
                .street(street)
                .city(city)
                .region(region)
                .zip(zip)
                .phoneNumber(phone)
                .build();
    }
}
