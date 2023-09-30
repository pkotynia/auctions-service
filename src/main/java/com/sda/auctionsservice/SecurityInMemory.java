package com.sda.auctionsservice;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@Profile("test")
public class SecurityInMemory {

    @Bean
    public UserDetailsManager userDetailsManager() {
        return new InMemoryUserDetailsManager();
    }

    @Bean
    public InitializingBean initializingBean(UserDetailsManager userDetailsManager) {
        return () -> {
            System.out.println("Hello form initializer - lambda");

            UserDetails user = User
                    .builder()
                    .passwordEncoder(password ->
                            PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(password))
                    .password("password")
                    .username("user")
                    .roles("USER")
                    .build();

            userDetailsManager.createUser(user);
        };


//        Step 2 - we can create anonymous implementation of the interface
//        return new InitializingBean() {
//            @Override
//            public void afterPropertiesSet() throws Exception {
//                System.out.println("Hello form initializer - anonymous Class");
//            }
//        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .httpBasic(Customizer.withDefaults());

        httpSecurity
                .csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable());

        httpSecurity
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> {
                    authorizationManagerRequestMatcherRegistry
                            .requestMatchers("/auctions/**")
                            .hasRole("USER");
                });

        return httpSecurity.build();
    }
}

// Step 1 - create class that implements interface - old way
//class MyInitBean implements InitializingBean {
//
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        System.out.println("Hello form initializer");
//    }
//}