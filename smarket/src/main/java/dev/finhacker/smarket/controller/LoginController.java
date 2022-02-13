package dev.finhacker.smarket.controller;

import dev.finhacker.smarket.service.UserService;
import dev.finhacker.smarket.util.msg.JsonMsg;
import dev.finhacker.smarket.util.msg.MsgCodeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
     * username The name of the user.
     * password The password of the user.
     * managerName The name of the manager.
     * @return True if registering successfully, false if failed.
     */
    @PostMapping("/api/registermanager")
    @ResponseBody
    public JsonMsg<Boolean> registerManager(@RequestBody Map<String, String> map) {
        try {
            String username = map.get("username");
            String password = map.get("password");
            String managerName = map.get("managerName");
            return new JsonMsg<>(userService.registerManager(username, password, managerName) != null);
        } catch (MsgCodeException e) {
            return new JsonMsg<>(e.getMsgCode());
        }
    }

}
