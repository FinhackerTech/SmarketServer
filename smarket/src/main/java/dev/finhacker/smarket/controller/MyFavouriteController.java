package dev.finhacker.smarket.controller;

import dev.finhacker.smarket.domain.enterprise.Enterprise;
import dev.finhacker.smarket.domain.user.User;
import dev.finhacker.smarket.domain.user.UserManager;
import dev.finhacker.smarket.service.UserManagerService;
import dev.finhacker.smarket.service.UserService;
import dev.finhacker.smarket.util.search.FilterType;
import dev.finhacker.smarket.util.msg.JsonMsg;
import dev.finhacker.smarket.util.msg.MsgCode;
import dev.finhacker.smarket.util.msg.MsgCodeException;
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

    @Autowired
    private UserService userService;

    /**
     * Add the enterprise list to the current user manager's favourites.
     * Content type: json
     * @param enterpriseList The list of ids of enterprises.
     * @return True if adding successfully, false if failed.
     */
    @PostMapping("/api/add")
    @ResponseBody
    public JsonMsg<Boolean> addFavourite(@RequestBody List<Integer> enterpriseList) {
        try {
            User user = userService.getCurrentUser();
            if (user instanceof UserManager) {
                return new JsonMsg<>(userManagerService.addFavourite((UserManager) user, enterpriseList));
            } else throw new MsgCodeException(MsgCode.UNKNOWN);
        } catch (MsgCodeException e) {
            return new JsonMsg<>(e.getMsgCode());
        }
    }

    /**
     * Remove the enterprise list from the current user manager's favourites.
     * Content type: json
     * @param enterpriseList The list of ids of enterprises.
     * @return True if removing successfully, false if failed.
     */
    @PostMapping("/api/remove")
    @ResponseBody
    public JsonMsg<Boolean> removeFavourite(@RequestBody List<Integer> enterpriseList) {
        try {
            User user = userService.getCurrentUser();
            if (user instanceof UserManager) {
                return new JsonMsg<>(userManagerService.removeFavourite((UserManager) user, enterpriseList));
            } else throw new MsgCodeException(MsgCode.UNKNOWN);
        } catch (MsgCodeException e) {
            return new JsonMsg<>(e.getMsgCode());
        }
    }

    /**
     * Search the enterprises which match the text.
     * Content type: x-www-form-urlencoded
     * @param searchText The searching text.
     * @param pageNumber The page number.
     * @param filterTypes The list of filter types.
     * @return The pages of enterprises in brief.
     */
    @GetMapping("/api/search")
    @ResponseBody
    public Page<Enterprise.Brief> search(@RequestParam(required = false, defaultValue = "")String searchText,
                                         @RequestParam(required = false, defaultValue = "0")Integer pageNumber,
                                         @RequestParam(required = false, defaultValue = "null")List<FilterType> filterTypes) {
        //TODO
        return null;
    }

}
