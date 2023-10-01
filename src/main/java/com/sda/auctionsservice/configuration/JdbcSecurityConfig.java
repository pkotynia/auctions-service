package com.sda.auctionsservice.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@EnableWebSecurity
@Configuration
@Profile("prod") // for prod we are using data base for storing user details
public class JdbcSecurityConfig {

    @Bean
    //JdbcUserDetailsManager is responsible for handling users (creating, updating, password changing)
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    // We do not encode password
    //Normally plain text password should not be stored in DB.
    //Encoding is an operation that produce unique hash from our password.
    //Encoded password is also traveling in HTTP header
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        // authentication - user is who he claims to be
        // authorization - we are giving users permissions to perform operations base on his role or authority
        httpSecurity
                .httpBasic(Customizer.withDefaults());

        httpSecurity
                .csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable());

        httpSecurity
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> {
                    authorizationManagerRequestMatcherRegistry
                            .requestMatchers("/auctions/**")
                            .hasAnyAuthority("USER")
                            .anyRequest()
                            .permitAll();

                });

        return httpSecurity.build();
    }
}
