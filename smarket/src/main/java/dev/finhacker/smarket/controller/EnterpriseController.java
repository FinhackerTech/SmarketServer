package dev.finhacker.smarket.controller;

import dev.finhacker.smarket.domain.enterprise.Enterprise;
import dev.finhacker.smarket.service.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for "/enterprise".
 */
@Controller
@RequestMapping(value = "/enterprise")
public class EnterpriseController {

    @Autowired
    private EnterpriseService enterpriseService;

    /**
     * Get enterprise information
     * @param id The id of the enterprise
     * @return The enterprise
     */
    @GetMapping("/api/{id}")
    @ResponseBody
    public Enterprise getEnterprise(@PathVariable Integer id) {
        return enterpriseService.getEnterprise(id);
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
