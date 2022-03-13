package com.finhacker.smarket.controller;

import com.finhacker.smarket.domain.enterprise.Enterprise;
import com.finhacker.smarket.domain.enterprise.news.News;
import com.finhacker.smarket.service.EnterpriseService;
import com.finhacker.smarket.util.msg.JsonMsg;
import com.finhacker.smarket.util.msg.MsgCodeException;
import com.finhacker.smarket.util.msg.MsgCode;
import com.finhacker.smarket.util.search.SearchInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Controller for "/enterprise".
 */
@Slf4j
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
    public JsonMsg<Enterprise> getEnterprise(@PathVariable Integer id) {
        Enterprise enterprise = enterpriseService.getEnterprise(id);
        if (enterprise != null) {
            return new JsonMsg<>(enterprise);
        } else {
            return new JsonMsg<>(MsgCode.ENTERPRISE_NOT_FOUND);
        }
    }

    @GetMapping("/api/basic/{id}")
    @ResponseBody
    public JsonMsg<Enterprise.Basic> getBasic(@PathVariable Integer id) {
        Enterprise enterprise = enterpriseService.getEnterprise(id);
        if (enterprise == null) {
            return new JsonMsg<>(MsgCode.ENTERPRISE_NOT_FOUND);
        }
        return new JsonMsg<>(enterprise.getBasic());
    }

    @GetMapping("/api/about/{id}")
    @ResponseBody
    public JsonMsg<Enterprise.About> getAbout(@PathVariable Integer id) {
        Enterprise enterprise = enterpriseService.getEnterprise(id);
        if (enterprise == null) {
            return new JsonMsg<>(MsgCode.ENTERPRISE_NOT_FOUND);
        }
        return new JsonMsg<>(enterprise.getAbout());
    }

    @GetMapping("/api/analyse/{id}")
    @ResponseBody
    public JsonMsg<Enterprise.Analyse> getAnalyse(@PathVariable Integer id) {
        Enterprise enterprise = enterpriseService.getEnterprise(id);
        if (enterprise == null) {
            return new JsonMsg<>(MsgCode.ENTERPRISE_NOT_FOUND);
        }
        return new JsonMsg<>(enterpriseService.getAnalyse(enterprise));
    }

    @GetMapping("/api/news/{id}")
    @ResponseBody
    public JsonMsg<List<News>> getAllNews(@PathVariable Integer id) {
        Enterprise enterprise = enterpriseService.getEnterprise(id);
        if (enterprise == null) {
            return new JsonMsg<>(MsgCode.ENTERPRISE_NOT_FOUND);
        }
        return new JsonMsg<>(enterpriseService.getAllNews(enterprise));
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
            List<Enterprise> enterpriseList = enterpriseService.getEnterpriseBySearch(searchInfo.getSearchText(), searchInfo.getFilterTypes());
            return enterpriseService.getEnterpriseBriefPage(enterpriseList, PageRequest.of(searchInfo.getPageNumber(), 10));
        } catch (MsgCodeException e) {
            log.error(e.getMessage());
            log.error(searchInfo.toString());
        }
        return null;
    }

}
