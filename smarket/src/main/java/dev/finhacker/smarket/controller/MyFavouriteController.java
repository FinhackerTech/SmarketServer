package dev.finhacker.smarket.controller;

import dev.finhacker.smarket.domain.enterprise.Enterprise;
import dev.finhacker.smarket.domain.user.UserManager;
import dev.finhacker.smarket.service.UserManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for "/myfavourite"
 */
@Controller
@RequestMapping(value = "/myfavourite")
public class MyFavouriteController {

    @Autowired
    private UserManagerService userManagerService;

    /**
     * Mock method, 需要由session维护用户的登录状态，才能知道当前的manager,请在实现session后将本方法替代
     * @return
     */
    private UserManager getMockManager(){
        return new UserManager();
    }

    /**
     * Add the enterprise list to the current user manager's favourites.
     * Content type: json
     * @param enterpriseList The list of ids of enterprises.
     * @return True if adding successfully, false if failed.
     */
    @PostMapping("/api/add")
    @ResponseBody
    public Boolean addFavourite(@RequestBody List<Integer> enterpriseList) {

        return userManagerService.addFavourite(getMockManager(),enterpriseList);
    }

    /**
     * Remove the enterprise list from the current user manager's favourites.
     * Content type: json
     * @param enterpriseList The list of ids of enterprises.
     * @return True if removing successfully, false if failed.
     */
    @PostMapping("/api/remove")
    @ResponseBody
    public Boolean removeFavourite(@RequestBody List<Integer> enterpriseList) {
        //TODO
        return false;
    }

    /**
     * Search the enterprises which match the text.
     * Content type: x-www-form-urlencoded
     * @param text The searching text.
     * @param page The page number.
     * @return The pages of enterprises in brief.
     */
    @GetMapping("/api/search")
    @ResponseBody
    public Page<Enterprise.Brief> search(@RequestParam(required = false, defaultValue = "")String text, @RequestParam(required = false, defaultValue = "0")Integer page) {
        //TODO
        return null;
    }

}
