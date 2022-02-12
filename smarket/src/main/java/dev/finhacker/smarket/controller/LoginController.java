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
    @PostMapping("/api/register")
    @ResponseBody
    public Boolean registerManager(@RequestParam String username, @RequestParam String password, @RequestParam String managerName) {
        return userService.registerManager(username, password, managerName) != null;
    }

    /**
     *
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/api/login")
    @ResponseBody
    public Boolean loginManager( @RequestParam String username, @RequestParam String password )
    {
        //TODO

        return userService.loginManager( username, password );
    }

    /**
     * 需要考虑旧密码不正确的情况，这时的报错应该与更改密码时数据层错误不同，返回值不能是简单的boolean
     * @param username
     * @param oldPassword
     * @param newPassword
     * @return
     */
    @PostMapping("/api/changepassword")
    public Boolean changePassword( @RequestParam String username, @RequestParam String oldPassword,@RequestParam String newPassword)
    {
        //TODO
        if(userService.authPassword(username, oldPassword) ) //旧密码正确，允许改变密码
            return userService.changePassword(username,newPassword );
        return false;
    }



}
