package dev.finhacker.smarket.controller;

import dev.finhacker.smarket.domain.enterprise.Enterprise;
import dev.finhacker.smarket.service.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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
    public Enterprise getEnterprise(@PathVariable Integer id) {
        return enterpriseService.getEnterprise(id);
    }

    /**
     * Search the enterprises which match the text.
     * Content type: x-www-form-urlencoded
     * @param text The searching text.
     * @param pageNum The page number.
     * @return The pages of enterprises in brief.
     */
    @GetMapping("/api/search")
    @ResponseBody
    public Page<Enterprise.Brief> search(@RequestParam(required = false, defaultValue = "")String text, @RequestParam(required = false, defaultValue = "0")Integer pageNum) {
        PageRequest pageRequest = PageRequest.of(pageNum, 5);
//        Page<Enterprise> enterprisePage = enterpriseService.findAllEnterprise(pageRequest);
//        List<Enterprise> enterpriseList =  enterprisePage.getContent();
        List<Enterprise> enterpriseList =  enterpriseService.getAllEnterprises();
        List<Enterprise.Brief> enterpriseBriefList = new ArrayList<>();

        for( Enterprise enterprise: enterpriseList )//符合条件，添加如list
        {
            if( matchEnterprise(text, enterprise ) )
            {
                enterpriseBriefList.add(enterprise.getBrief());
            }
        }

        enterpriseBriefListToPage( enterpriseBriefList, pageRequest );//list转page



        return null;
    }

    /**
     * 筛选符合条件的enterprise， 尚未实现
     * @param text
     * @param enterprise
     * @return
     */
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
     */
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

}
