package dev.finhacker.smarket.service;

public interface UserService {

    /**
     * Change the password of the user.
     * @param username The name of the user.
     * @param newPassword The new password.
     * @return The success of changing.
     */
    boolean changePassword(String username, String newPassword);

}
