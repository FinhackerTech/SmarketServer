package dev.finhacker.smarket.service;

import dev.finhacker.smarket.domain.enterprise.Enterprise;
import dev.finhacker.smarket.domain.enterprise.EnterpriseRepository;
import dev.finhacker.smarket.domain.enterprise.news.News;
import dev.finhacker.smarket.domain.enterprise.news.NewsRepository;
import dev.finhacker.smarket.service.impl.EnterpriseServiceImpl;
import dev.finhacker.smarket.util.search.FilterType;
import lombok.AllArgsConstructor;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EnterpriseServiceTest {
    @Autowired
    private EnterpriseService EnterpriseService;

    @Autowired
    private EnterpriseRepository enterpriseRepository;

    @Test
    public void getEnterpriseTest() throws Exception{
        Enterprise enterprise_101= EnterpriseService.getEnterprise(101);
        Enterprise enterprise_10100140= EnterpriseService.getEnterprise(10100140);
        Assert.assertEquals(enterprise_101.getFullName(),"王府井集团股份有限公司");
        Assert.assertEquals(enterprise_10100140.getLogoUrl(),"https://image.qcc.com/logo/fee2867eb9dcb3312d5c3a857083cd8c.jpg");
        Assert.assertNull(EnterpriseService.getEnterprise(79));
    }

    @Test
    public void getAllNewsTest() throws Exception{
        Enterprise enterprise_101453= enterpriseRepository.findById(101453).get();
        Enterprise enterprise_102560= enterpriseRepository.findById(102560).get();
        Assert.assertEquals(EnterpriseService.getAllNews(enterprise_101453).get(0).getSymbol(),"000786");
        Assert.assertTrue(EnterpriseService.getAllNews(enterprise_102560).isEmpty());
    }

    @Test
    public void getEnterpriseBySearchTest() throws Exception{
        Integer[] arrInt_0 = new Integer[2];
        Integer[] arrInt_1 = new Integer[2];
        arrInt_0[0]=700000000;
        arrInt_0[1]=1000000000;
        arrInt_1[0]=800000000;
        arrInt_1[1]=900000000;
        FilterType registercapital_0=new FilterType("RegisterCapital",Arrays.asList(arrInt_0));
        FilterType registercapital_1=new FilterType("RegisterCapital",Arrays.asList(arrInt_1));
        List<FilterType> filterTypes_0= Arrays.asList(registercapital_0);
        List<FilterType> filterTypes_1= Arrays.asList(registercapital_1);
        Assert.assertEquals(EnterpriseService.getEnterpriseBySearch("王府井集团股份有限公司",filterTypes_0).get(0).getEmail(),"wfjcc@wfj.com.cn");
        Assert.assertEquals(EnterpriseService.getEnterpriseBySearch("软件",filterTypes_1).get(0).getFullName(),"北京高能时代环境技术股份有限公司");
    }

    @Test
    public void getEnterpriseBriefPageTest() throws Exception{
        Enterprise enterprise_101453= enterpriseRepository.findById(101453).get();
        Enterprise enterprise_102560= enterpriseRepository.findById(102560).get();
        List<Enterprise> enterprises=Arrays.asList(enterprise_101453,enterprise_102560);
        Sort sort = Sort.by(Sort.Order.desc("create_date"));
        Pageable pageable =PageRequest.of(0, 2, sort);
        System.out.println(EnterpriseService.getEnterpriseBriefPage(enterprises,pageable));
    }
}
