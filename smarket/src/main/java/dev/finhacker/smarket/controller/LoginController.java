package dev.finhacker.smarket.controller;

import dev.finhacker.smarket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for "/login".
 */
@Controller
@RequestMapping(value = "/login")
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * Register a manager user with the username, password, and role.
     * Content type: x-www-form-urlencoded
     * @param username The name of the user.
     * @param password The password of the user.
     * @param managerName The name of the manager.
     * @return True if registering successfully, false if failed.
     */
    @PostMapping("/api/registermanager")
    @ResponseBody
    public Boolean registerManager(@RequestParam String username, @RequestParam String password, @RequestParam String managerName) {
        return userService.registerManager(username, password, managerName) != null;
    }

}
