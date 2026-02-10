package com.yodishtr.LibraryManagementSystem.Services;

import com.yodishtr.LibraryManagementSystem.Entities.User;
import com.yodishtr.LibraryManagementSystem.Repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()){
            throw new UsernameNotFoundException("User not found");
        } else {
            User userDetails = user.get();
            UserDetailsAdapter userDetailsAdapter = new UserDetailsAdapter(userDetails);
            return userDetailsAdapter;
        }
    }
}
