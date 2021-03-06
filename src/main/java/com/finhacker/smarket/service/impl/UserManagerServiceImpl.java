package com.finhacker.smarket.service.impl;

import com.finhacker.smarket.domain.user.UserManager;
import com.finhacker.smarket.domain.user.UserManagerRepository;
import com.finhacker.smarket.service.EnterpriseService;
import com.finhacker.smarket.service.UserManagerService;
import com.finhacker.smarket.util.msg.MsgCode;
import com.finhacker.smarket.util.msg.MsgCodeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UserManagerServiceImpl implements UserManagerService {

    @Autowired
    private UserManagerRepository userManagerRepository;

    @Autowired
    private EnterpriseService enterpriseService;

    @Override
    public synchronized boolean addFavourite(UserManager manager, List<Integer> enterprises) throws MsgCodeException {
        for (Integer id : enterprises) {
            if (!manager.addFavourite(id)) {
                throw new MsgCodeException(MsgCode.USER_FAVOURITE_EXISTED);
            }
        }
        return userManagerRepository.save(manager) != null;
    }

    @Override
    public synchronized boolean removeFavourite(UserManager manager, List<Integer> enterprises) throws MsgCodeException {
        for (Integer id : enterprises) {
            if (!manager.removeFavourite(id)) {
                throw new MsgCodeException(MsgCode.USER_FAVOURITE_NOT_EXISTED);
            }
        }
        return userManagerRepository.save(manager) != null;
    }

    @Override
    public List<Integer> getAllFavourite(UserManager manager) {
        return new ArrayList<>(manager.getFavouriteSet());
    }

}
