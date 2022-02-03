package dev.finhacker.smarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for "/setting".
 */
@Controller
@RequestMapping(value = "/setting")
public class SettingController {

    /**
     * Change the password of the user.
     * Content type: x-www-form-urlencoded
     * @param oldPassword
     * @param newPassword
     * @return
     */
    @PostMapping("/api/changepassword")
    @ResponseBody
    public Boolean changePassword(@RequestParam String oldPassword, @RequestParam String newPassword) {
        //TODO
        return false;
    }

}
