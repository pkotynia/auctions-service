package com.sda.auctionsservice.registering;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class RegisterController {

    private final UserDetailsManager userDetailsManager;

    public RegisterController(UserDetailsManager userDetailsManager) {
        this.userDetailsManager = userDetailsManager;
    }

    @PostMapping
    public UserRecord createUser(@RequestBody UserRecord user) {

        UserDetails userDetails = User
                .builder()
                .passwordEncoder(password -> password)
                .password(user.password())
                .username(user.username())
                .roles("USER")
                .build();

        userDetailsManager.createUser(userDetails);
        return user;
    }
}

record UserRecord(String username, String password) {}
