package dev.finhacker.smarket.service.impl;

import dev.finhacker.smarket.domain.user.Role;
import dev.finhacker.smarket.domain.user.User;
import dev.finhacker.smarket.domain.user.UserManager;
import dev.finhacker.smarket.domain.user.UserRepository;
import dev.finhacker.smarket.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("Username not found!");
        }
        return user;
    }

    @Override
    public User getUserByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public boolean changePassword(User user, String newPassword) {
        //TODO
        return false;
    }

    @Override
    public User register(String username, String password, String role) {
        User user = null;
        switch (role) {
            case "MANAGER": user = new UserManager(username, password);
        }
        userRepository.save(user);
        return user;
    }

}
