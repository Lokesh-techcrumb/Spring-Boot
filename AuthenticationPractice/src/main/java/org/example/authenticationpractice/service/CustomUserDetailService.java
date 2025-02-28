package org.example.authenticationpractice.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Here, you should fetch user details from the database.
        // For demonstration, we're using a hardcoded user.

        if ("user".equals(username)) {
            return new User("user", "{noop}password", new ArrayList<>()); // {noop} means no password encoding
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}
