package dev.finhacker.smarket.service;

import dev.finhacker.smarket.domain.user.User;
import dev.finhacker.smarket.util.msg.MsgCodeException;

public interface UserService {


    /**
     * Get current user.
     * @return The current user;
     */
    User getCurrentUser();

    /**
     * Get the user by username.
     * @param name The username of the user.
     * @return The user if finding successful, null if failed.
     */
    User getUserByName(String name);

    /**
     * Change the password of the user.
     * @param user
     * @param newPassword The new password. Ensure the password is raw.
     * @return The success of changing.
     */
    Boolean changePassword(User user, String newPassword);

    /**
     * Authenticate user's password
     * @param user
     * @param password
     * @return thr trueness of the password
     */
    Boolean authPassword(User user, String password);

    /**
     * Register a new manager user.
     * @param username The username of the user.
     * @param password The password of the user. Ensure the password is raw.
     * @param managerName The name of the manager.
     * @return The new user if register successfully, null if failed.
     */
    User registerManager(String username, String password, String managerName) throws MsgCodeException;

}
