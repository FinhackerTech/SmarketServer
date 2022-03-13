package com.finhacker.smarket.service.impl;

import com.finhacker.smarket.domain.user.User;
import com.finhacker.smarket.domain.user.UserManager;
import com.finhacker.smarket.domain.user.UserRepository;
import com.finhacker.smarket.service.UserManagerService;
import com.finhacker.smarket.service.UserService;
import com.finhacker.smarket.domain.user.*;
import com.finhacker.smarket.util.msg.MsgCode;
import com.finhacker.smarket.util.msg.MsgCodeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserManagerService userManagerService;

    private PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("Username not found!");
        }
        return user;
    }

    @Override
    public User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            return (User) principal;
        }
        return null;
    }

    @Override
    public User getUserByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public Boolean changePassword(User user, String newPassword) {
        user.setPassword(getPasswordEncoder().encode(newPassword));
        return userRepository.save(user) != null;
    }

    @Override
    public Boolean authPassword(User user, String password) {
        return getPasswordEncoder().matches(password, user.getPassword());
    }

    @Override
    public synchronized User registerManager(String username, String password, String managerName) throws MsgCodeException {
        if (userRepository.findByName(username) != null) {
            throw new MsgCodeException(MsgCode.USER_HAS_EXISTED);
        }
        User user = new UserManager(username, getPasswordEncoder().encode(password), managerName);
        return userRepository.save(user);
    }

}
