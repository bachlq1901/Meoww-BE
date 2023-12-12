package com.meow.configs;

import com.meow.entities.User;
import com.meow.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class PersistenceConfig {
    private final UserService userService;

    @Autowired
    PersistenceConfig(
            UserService userService
    ) {
        this.userService = userService;
    }

    @Bean
    public AuditorAware<User> auditorProvider () {
        return () -> Optional.ofNullable(userService.getCurrentUser());
    }
}
