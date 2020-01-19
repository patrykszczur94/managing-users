package com.papryk.code.usermanagement;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class UserConfiguration {

    @Bean
    public UserService userService() {
        UserRepository userRepository = new InMemoryUserRepository();
        return new UserService(userRepository);
    }
}
