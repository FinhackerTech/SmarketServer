package dev.finhacker.smarket.service.impl;

import dev.finhacker.smarket.domain.user.*;
import dev.finhacker.smarket.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

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
    public User registerManager(String username, String password, String managerName) {
        ensureRoleManager();
        User user = new UserManager(username, password, managerName);
        return userRepository.save(user);
    }

    private void ensureRoleManager() {
        Optional<Role> r = roleRepository.findById(Role.MANAGER.getId());
        if (r.isPresent()) {
            Role.MANAGER = r.get();
        } else {
            Role.MANAGER = roleRepository.save(Role.MANAGER);
        }
    }

}
