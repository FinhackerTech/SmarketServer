package com.finhacker.smarket.controller;

import com.finhacker.smarket.domain.enterprise.Enterprise;
import com.finhacker.smarket.domain.user.User;
import com.finhacker.smarket.domain.user.UserManager;
import com.finhacker.smarket.service.EnterpriseService;
import com.finhacker.smarket.service.UserManagerService;
import com.finhacker.smarket.service.UserService;
import com.finhacker.smarket.util.search.FilterType;
import com.finhacker.smarket.util.msg.JsonMsg;
import com.finhacker.smarket.util.msg.MsgCode;
import com.finhacker.smarket.util.msg.MsgCodeException;
import com.finhacker.smarket.util.search.SearchInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller for "/myfavourite"
 */
@Slf4j
@Controller
@RequestMapping(value = "/myfavourite")
public class MyFavouriteController {

    @Autowired
    private UserManagerService userManagerService;

    @Autowired
    private UserService userService;

    @Autowired
    private EnterpriseService enterpriseService;

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
     * Content type: json
     * @param searchInfo The info of searching.
     * @return The pages of enterprises in brief.
     */
    @GetMapping("/api/search")
    @ResponseBody
    public Page<Enterprise.Brief> search(@Valid @RequestBody SearchInfo searchInfo) {
        try {
            User user = userService.getCurrentUser();
            if (user instanceof UserManager) {
                List<Integer> favouriteList = userManagerService.getAllFavourite((UserManager) user);
                searchInfo.getFilterTypes().add(new FilterType("ListedCoId", new ArrayList<>(favouriteList)));
                List<Enterprise> enterpriseList = enterpriseService.getEnterpriseBySearch(searchInfo.getSearchText(), searchInfo.getFilterTypes());
                Pageable pageable = PageRequest.of(searchInfo.getPageNumber(), 10);
                return enterpriseService.getEnterpriseBriefPage(enterpriseList, pageable);
            } else throw new MsgCodeException(MsgCode.UNKNOWN);
        } catch (MsgCodeException e) {
            log.error(e.getMessage());
            log.error(searchInfo.toString());
        }
        return null;
    }

}
