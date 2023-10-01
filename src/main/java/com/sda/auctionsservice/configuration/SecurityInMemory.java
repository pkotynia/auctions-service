package com.sda.auctionsservice.configuration;

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

@Configuration // General Spring annotation allowing to use @Bean
@EnableWebSecurity // this is for spring security. We need to provide her 2 configurations - UserDetailsManager and Filter chain
@Profile("test")
public class SecurityInMemory {

    @Bean
    //In context of user we are working with username, password, role and activation
    public UserDetailsManager userDetailsManager() {
        return new InMemoryUserDetailsManager();
    }

    @Bean
    // this is general mechanism allowing us to do the post initialization work
    // in our case are adding user to userDetailsManager
    // but we could also add something to DB and so on
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
    //Here we are configuring Spring Security behavior
    //two things are most important - setting the authorisation type (Basic Auth) and configuring authorisation
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        // we are using Basic Auth (user name nad password are passed in headers), we could also use different types like toke based on oAuth
        httpSecurity
                .httpBasic(Customizer.withDefaults());

        // disabling this security check to be able to use POSTs and PUTs
        httpSecurity
                .csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable());

        //Setting up authorization.
        //We are defining users with which roles have access to which endpoints
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