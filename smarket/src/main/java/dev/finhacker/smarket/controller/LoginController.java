package dev.finhacker.smarket.controller;

import dev.finhacker.smarket.domain.user.User;
import dev.finhacker.smarket.service.UserService;
import dev.finhacker.smarket.util.msg.JsonMsg;
import dev.finhacker.smarket.util.msg.MsgCode;
import dev.finhacker.smarket.util.msg.MsgCodeException;
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
    public JsonMsg<Boolean> registerManager(@RequestParam String username, @RequestParam String password, @RequestParam String managerName) {
        try {
            return new JsonMsg<>(userService.registerManager(username, password, managerName) != null);
        } catch (MsgCodeException e) {
            return new JsonMsg<>(e.getMsgCode());
        }
    }

    @GetMapping("/api/current")
    @ResponseBody
    public JsonMsg<User> getCurrentUser() {
        User user = userService.getCurrentUser();
        if (user != null) return new JsonMsg<>(user);
        return new JsonMsg<>(MsgCode.USER_CURRENT_NULL);
    }

}
