package dev.finhacker.smarket.service;

import dev.finhacker.smarket.domain.user.User;

public interface UserService {

    /**
     * Get the user by username.
     * @param name The username of the user.
     * @return The user if finding successful, null if failed.
     */
    User getUserByName(String name);

    /**
     * Change the password of the user.
     * @param user The user.
     * @param newPassword The new password.
     * @return The success of changing.
     */
    boolean changePassword(User user, String newPassword);

    /**
     * Register a new manager user.
     * @param username The username of the user.
     * @param password The password of the user.
     * @param managerName The name of the manager.
     * @return The new user if register successfully, null if failed.
     */
    User registerManager(String username, String password, String managerName);

}
