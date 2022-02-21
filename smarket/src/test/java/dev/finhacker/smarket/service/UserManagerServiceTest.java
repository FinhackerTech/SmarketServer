package dev.finhacker.smarket.service;

import dev.finhacker.smarket.domain.enterprise.Enterprise;
import dev.finhacker.smarket.domain.enterprise.EnterpriseRepository;
import dev.finhacker.smarket.domain.enterprise.news.News;
import dev.finhacker.smarket.domain.enterprise.news.NewsRepository;
import dev.finhacker.smarket.domain.user.UserManager;
import dev.finhacker.smarket.domain.user.UserManagerRepository;
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

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserManagerServiceTest {

    @Autowired
    private UserManagerService userManagerService;

    @Autowired
    private UserManagerRepository userManagerRepository;

    //When test, add spring.jpa.properties.hibernate.enable_lazy_load_no_trans=true to properties.
    @Test
    public void addFavouriteTest() throws Exception{
        UserManager userManager_6= userManagerRepository.getById(6);
        Integer[] arrInt_0 = new Integer[2];
        arrInt_0[0]=10100068;
        arrInt_0[1]=10100141;
        List<Integer> enterprises=Arrays.asList(arrInt_0);
        Assert.assertTrue(userManagerService.addFavourite(userManager_6,enterprises));
    }

    @Test
    public void removeFavouriteTest() throws Exception{
        UserManager userManager_6= userManagerRepository.getById(6);
        Integer[] arrInt_0 = new Integer[1];
        arrInt_0[0]=10100068;
        List<Integer> enterprises=Arrays.asList(arrInt_0);
        Assert.assertTrue(userManagerService.removeFavourite(userManager_6,enterprises));
        Assert.assertTrue(userManagerService.addFavourite(userManager_6,enterprises));
    }

    @Test
    public void getAllFavouriteTest() throws Exception{
        UserManager userManager_6= userManagerRepository.getById(6);
        Assert.assertEquals(10100048, (int) userManagerService.getAllFavourite(userManager_6).get(0));
        Assert.assertEquals(10100068, (int) userManagerService.getAllFavourite(userManager_6).get(2));
    }
}
