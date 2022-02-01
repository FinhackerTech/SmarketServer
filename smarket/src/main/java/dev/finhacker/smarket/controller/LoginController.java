package dev.finhacker.smarket.controller;

import dev.finhacker.smarket.domain.user.User;
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

    @PostMapping("/api/register")
    @ResponseBody
    public User register(@RequestParam String username, @RequestParam String password, @RequestParam String role) {
        return userService.register(username, password, role);
    }

}
