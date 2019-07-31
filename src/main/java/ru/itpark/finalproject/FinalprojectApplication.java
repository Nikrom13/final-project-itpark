package ru.itpark.finalproject;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itpark.finalproject.domain.User;
import ru.itpark.finalproject.repository.UserRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
public class FinalprojectApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinalprojectApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner(UserRepository repository, PasswordEncoder passwordEncoder) {
        return (args) -> {
            repository.save(
                    new User(
                            0,
                            "admin",
                            passwordEncoder.encode("password"),
                            "Николай",
                            "Алексеевич",
                            "Россия",
                            "Казань",
                            "Восстания, 51",
                            "+7 843 555 55 55",
                            "+7 843 246 66 55",
                            List.of(new SimpleGrantedAuthority("ROLE_ADMIN")),
                            true,
                            true,
                            true,
                            true,
                            Collections.emptyList()
                    )
            );

            repository.save(
                    new User(
                            0,
                            "vasya",
                            passwordEncoder.encode("password"),
                            "Василий",
                            "Иванович",
                            "Россия",
                            "Волгоград",
                            "Пушкина, 20",
                            "+7 246 66 44 1",
                            "+7 246 33 41 5",
                            List.of(new SimpleGrantedAuthority("ROLE_USER")),
                            true,
                            true,
                            true,
                            true,
                            Collections.emptyList()
                    )
            );
        };
    }

}
