package dev.finhacker.smarket.service;

public interface UserManagerService {

    /**
     * Add a enterprise to the manager's favourite.
     * @param managerName The name of the manager.
     * @param enterpriseId The ID of the enterprise.
     * @return The success of adding.
     */
    boolean addFavourite(String managerName, Integer enterpriseId);

    /**
     * Remove the enterprise from the manager's favourite.
     * @param managerName The name of the manager.
     * @param enterpriseId The ID of the enterprise.
     * @return The success of removing.
     */
    boolean removeFavourite(String managerName, Integer enterpriseId);

}
