package dev.finhacker.smarket.controller;

import dev.finhacker.smarket.domain.user.User;
import dev.finhacker.smarket.service.UserService;
import dev.finhacker.smarket.util.msg.JsonMsg;
import dev.finhacker.smarket.util.msg.MsgCode;
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
        if (userService.authPassword(user, oldPassword)) {
            return new JsonMsg<>(userService.changePassword(user, newPassword));
        } else {
            return new JsonMsg<>(MsgCode.USER_PASSWORD_NOT_CORRECT);
        }
    }

}
