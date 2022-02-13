package dev.finhacker.smarket.controller;

import dev.finhacker.smarket.domain.enterprise.Enterprise;
import dev.finhacker.smarket.domain.enterprise.news.News;
import dev.finhacker.smarket.service.EnterpriseService;
import dev.finhacker.smarket.util.search.FilterType;
import dev.finhacker.smarket.util.msg.JsonMsg;
import dev.finhacker.smarket.util.msg.MsgCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
        return new JsonMsg<>(enterprise.getAnalyse());
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
        PageRequest pageRequest = PageRequest.of(pageNumber, 5);
//      Page<Enterprise> enterprisePage = enterpriseService.findAllEnterprise(pageRequest);
//      List<Enterprise> enterpriseList =  enterprisePage.getContent();
        List<Enterprise> enterpriseList =  enterpriseService.getAllEnterprises();
        List<Enterprise.Brief> enterpriseBriefList = new ArrayList<>();
        /*
        for( Enterprise enterprise: enterpriseList )//符合条件，添加如list
        {
            if( matchEnterprise(searchText, enterprise ) )
            {
                enterpriseBriefList.add(enterprise.getBrief());
            }
        }

        enterpriseBriefListToPage( enterpriseBriefList, pageRequest );//list转page
        */
        return null;
    }

    /*
    Implement these in service!!!
    /**
     * 筛选符合条件的enterprise， 尚未实现
     * @param text
     * @param enterprise
     * @return

    private boolean matchEnterprise( String text, Enterprise enterprise )
    {
        //TODO
        return false;
    }

    /**
     * 用于List转Page
     * @param enterpriseBriefList
     * @param pageRequest
     * @return

    private Page<Enterprise.Brief> enterpriseBriefListToPage(List<Enterprise.Brief> enterpriseBriefList, PageRequest pageRequest)
    {
        // 当前页第一条数据在List中的位置
        int start = (int)pageRequest.getOffset();
        // 当前页最后一条数据在List中的位置
        int end = (start + pageRequest.getPageSize()) > enterpriseBriefList.size() ? enterpriseBriefList.size() : ( start + pageRequest.getPageSize());
        // 配置分页数据
        Page<Enterprise.Brief> enterpriseBriefPage = new PageImpl<Enterprise.Brief>(enterpriseBriefList.subList(start, end), pageRequest, enterpriseBriefList.size());
        return enterpriseBriefPage;
    }
    */

}
