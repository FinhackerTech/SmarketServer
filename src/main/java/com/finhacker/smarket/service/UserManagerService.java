package com.finhacker.smarket.service;

import com.finhacker.smarket.domain.user.UserManager;
import com.finhacker.smarket.util.msg.MsgCodeException;

import java.util.List;

public interface UserManagerService {

    /**
     * Add a enterprise to the manager's favourite.
     * @param manager The manager.
     * @param enterprises The enterprise.
     * @return The success of adding.
     */
    boolean addFavourite(UserManager manager, List<Integer> enterprises) throws MsgCodeException;


    /**
     * Remove the enterprise from the manager's favourite.
     * @param manager The manager.
     * @param enterprises The enterprise.
     * @return The success of removing.
     */
    boolean removeFavourite(UserManager manager, List<Integer> enterprises) throws MsgCodeException;


    /**
     * Get the list of the favourite enterprises of the user manager.
     * @param manager The manager.
     * @return The list of the favourite enterprises.
     */
    List<Integer> getAllFavourite(UserManager manager);

}
