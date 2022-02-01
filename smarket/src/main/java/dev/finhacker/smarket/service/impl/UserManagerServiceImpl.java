package dev.finhacker.smarket.service.impl;

import dev.finhacker.smarket.domain.enterprise.Enterprise;
import dev.finhacker.smarket.domain.user.UserManager;
import dev.finhacker.smarket.domain.user.UserManagerRepository;
import dev.finhacker.smarket.service.UserManagerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserManagerServiceImpl implements UserManagerService {

    @Autowired
    private UserManagerRepository userManagerRepository;

    @Override
    public boolean addFavourite(UserManager manager, Enterprise enterprise) {
        //TODO
        return false;
    }

    @Override
    public boolean removeFavourite(UserManager manager, Enterprise enterprise) {
        //TODO
        return false;
    }
}
