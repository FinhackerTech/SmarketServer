package dev.finhacker.smarket.service.impl;

import dev.finhacker.smarket.domain.user.UserManager;
import dev.finhacker.smarket.domain.user.UserManagerRepository;
import dev.finhacker.smarket.service.UserManagerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserManagerServiceImpl implements UserManagerService {

    @Autowired
    private UserManagerRepository userManagerRepository;


    @Override
    public boolean addFavourite(UserManager manager, Integer enterprise) {

        return  manager.addFavourite(enterprise);
    }

    @Override
    public boolean addFavourite(UserManager manager, List<Integer> enterprises) {
        boolean flag = false;
        for( Integer id : enterprises )
        {
            flag = manager.addFavourite(id);
            if( flag == false )
                return false;
        }
        return true;
    }

    @Override
    public boolean removeFavourite(UserManager manager, Integer enterprise) {
        return manager.removeFavourite(enterprise);
    }

    @Override
    public boolean removeFavourite(UserManager manager, List<Integer> enterprises) {
        boolean flag = false;
        for( Integer id : enterprises )
        {
            flag = manager.removeFavourite(id);
            if( flag == false )
                return false;
        }
        return true;
    }
}
