package dev.finhacker.smarket.service;

import dev.finhacker.smarket.domain.user.UserManager;

import java.util.List;

public interface UserManagerService {

    /**
     * Add a enterprise to the manager's favourite.
     * @param manager The manager.
     * @param enterprise The enterprise.
     * @return The success of adding.
     */
    boolean addFavourite(UserManager manager, Integer enterprise);
    boolean addFavourite(UserManager manager, List<Integer> enterprises);


    /**
     * Remove the enterprise from the manager's favourite.
     * @param manager The manager.
     * @param enterprise The enterprise.
     * @return The success of removing.
     */
    boolean removeFavourite(UserManager manager, Integer enterprise);
    boolean removeFavourite(UserManager manager, List<Integer> enterprises);


}
