package com.finhacker.smarket.controller;

import com.finhacker.smarket.domain.user.User;
import com.finhacker.smarket.service.UserService;
import com.finhacker.smarket.util.msg.JsonMsg;
import com.finhacker.smarket.util.msg.MsgCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for "/setting".
 */
@Controller
@RequestMapping(value = "/setting")
public class SettingController {

    @Autowired
    private UserService userService;


    /**
     * Change the password of the user.
     * Use the session in controller!!!
     * Content type: x-www-form-urlencoded
     * @param oldPassword
     * @param newPassword
     * @return
     */
    @PostMapping("/api/changepassword")
    @ResponseBody
    public JsonMsg<Boolean> changePassword(@RequestParam String oldPassword, @RequestParam String newPassword) {
        User user = userService.getCurrentUser();
        if (user == null) {
            return new JsonMsg<>(MsgCode.USER_CURRENT_NULL);
        }
        if (userService.authPassword(user, oldPassword)) {
            return new JsonMsg<>(userService.changePassword(user, newPassword));
        } else {
            return new JsonMsg<>(MsgCode.USER_PASSWORD_NOT_CORRECT);
        }
    }

}
