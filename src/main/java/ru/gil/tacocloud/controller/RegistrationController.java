package ru.gil.tacocloud.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gil.tacocloud.dto.RegistrationForm;
import ru.gil.tacocloud.repository.UsersRepository;

@RequiredArgsConstructor
@Controller
@RequestMapping("/register")
public class RegistrationController {

    private final UsersRepository usersRepository;

    private final PasswordEncoder encoder;

    @GetMapping
    public String registerForm() {
        return "registration";
    }

    @PostMapping
    public String processRegistration(RegistrationForm form) {
        usersRepository.save(form.toUsers(encoder));
        return "redirect:/login";
    }
}
