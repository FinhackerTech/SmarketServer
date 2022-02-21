package dev.finhacker.smarket.service.impl;

import dev.finhacker.smarket.domain.user.*;
import dev.finhacker.smarket.service.UserManagerService;
import dev.finhacker.smarket.service.UserService;
import dev.finhacker.smarket.util.msg.MsgCode;
import dev.finhacker.smarket.util.msg.MsgCodeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

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
        /*UserManager testuser=new UserManager("abcdef",getPasswordEncoder().encode("password123"),"123" );
        Integer[] arrInt_0 = new Integer[2];
        arrInt_0[0]=10100068;
        arrInt_0[1]=10100141;
        List<Integer> enterprises= Arrays.asList(arrInt_0);
        try {
            userManagerService.addFavourite(testuser,enterprises);
        } catch (MsgCodeException e) {
            e.printStackTrace();
        }*/
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
        //System.out.println(user.getPassword());
        //System.out.println(password);
        //assert (getPasswordEncoder().matches(password, user.getPassword()));
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
