package dev.finhacker.smarket.service;

import dev.finhacker.smarket.domain.user.User;

// 使用session之后，后端不需要username就可以知道当前user,因此changePassword 和 authPassword方法不需要username参数， 这部分后续要重构
public interface UserService {

    /**
     * Get the user by username.
     * @param name The username of the user.
     * @return The user if finding successful, null if failed.
     */
    public User getUserByName(String name);

    /**
     * Change the password of the user.
     * @param newPassword The new password.
     * @param username
     * @return The success of changing.
     */
    public boolean changePassword(String username, String newPassword);

    /**
     * Authenticate user's password
     * @param password
     * @param username
     * @return thr trueness of the password
     */
    public boolean authPassword(String username, String password);

    /**
     * Register a new manager user.
     * @param username The username of the user.
     * @param password The password of the user.
     * @param managerName The name of the manager.
     * @return The new user if register successfully, null if failed.
     */
    public User registerManager(String username, String password, String managerName);

    public Boolean loginManager( String username, String password );

}
