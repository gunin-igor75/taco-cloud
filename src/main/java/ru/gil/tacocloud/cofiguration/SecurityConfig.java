package ru.gil.tacocloud.cofiguration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.gil.tacocloud.model.Users;
import ru.gil.tacocloud.repository.UsersRepository;

import java.util.Optional;

@Slf4j
@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
//        List<UserDetails> userList = new ArrayList<>();
//        userList.add(
//                new User("buzz", encoder.encode("password"),
//                        List.of(new SimpleGrantedAuthority("ROLE_USER"))));
//        userList.add(
//                new User("woody", encoder.encode("password"),
//                        List.of(new SimpleGrantedAuthority("ROLE_USER"))));
//        return new InMemoryUserDetailsManager(userList);
//    }

    @Bean
    public UserDetailsService userDetailsService(UsersRepository usersRepository) {
        return username -> {
            Optional<Users> nameOrEmpty = usersRepository.findByUsername(username);
            if (nameOrEmpty.isPresent()) {
                return nameOrEmpty.get();
            }
            String message = "User "  + username + " not found";
            log.debug(message);
            throw new UsernameNotFoundException(message);
        };
    }
}
