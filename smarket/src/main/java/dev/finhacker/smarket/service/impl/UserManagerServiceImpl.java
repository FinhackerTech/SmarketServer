package dev.finhacker.smarket.service.impl;

import dev.finhacker.smarket.domain.user.UserManager;
import dev.finhacker.smarket.domain.user.UserManagerRepository;
import dev.finhacker.smarket.service.UserManagerService;
import dev.finhacker.smarket.util.msg.MsgCode;
import dev.finhacker.smarket.util.msg.MsgCodeException;
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
    public boolean addFavourite(UserManager manager, List<Integer> enterprises) throws MsgCodeException {
        for (Integer id : enterprises) {
            if (!manager.addFavourite(id)) {
                throw new MsgCodeException(MsgCode.USER_FAVOURITE_EXISTED);
            }
        }
        return userManagerRepository.save(manager) != null;
    }

    @Override
    public boolean removeFavourite(UserManager manager, List<Integer> enterprises) throws MsgCodeException {
        for (Integer id : enterprises) {
            if (!manager.removeFavourite(id)) {
                throw new MsgCodeException(MsgCode.USER_FAVOURITE_NOT_EXISTED);
            }
        }
        return userManagerRepository.save(manager) != null;
    }

}
