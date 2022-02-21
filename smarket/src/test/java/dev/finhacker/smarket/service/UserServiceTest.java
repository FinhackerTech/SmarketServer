package dev.finhacker.smarket.service;

import dev.finhacker.smarket.domain.enterprise.Enterprise;
import dev.finhacker.smarket.domain.enterprise.EnterpriseRepository;
import dev.finhacker.smarket.domain.enterprise.news.News;
import dev.finhacker.smarket.domain.enterprise.news.NewsRepository;
import dev.finhacker.smarket.domain.user.User;
import dev.finhacker.smarket.domain.user.UserManager;
import dev.finhacker.smarket.domain.user.UserManagerRepository;
import dev.finhacker.smarket.domain.user.UserRepository;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
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
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    private PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Test
    public void loadUserByUsernameTest() throws Exception{
        User user_1=userRepository.getById(1);
        Assert.assertEquals(userService.getUserByName("Song Anqi"),user_1);
        Assert.assertNull(userService.getUserByName("username that does not exist"));
    }

    @Test
    public void changePasswordTest() throws Exception{
        User user_2=userRepository.getById(2);
        Assert.assertTrue(userService.changePassword(user_2,"123456"));
    }

    @Test
    public void authPasswordTest() throws Exception{
        User user_2=userRepository.getById(2);
        Assert.assertTrue(userService.authPassword(user_2,"123456"));
    }

    @Test
    @WithMockUser(username = "abcdef",password = "password123")
    public void getCurrentUserTest() throws Exception{
        Assert.assertNull(userService.getCurrentUser());
    }

    @Test
    public void getUserByNameTest() throws Exception{
        User user_2=userRepository.getById(2);
        Assert.assertEquals(user_2,userService.getUserByName("Ku Wai Han"));
    }

    @Test
    public void registerManagerTest() throws Exception{
        System.out.println(userService.registerManager("newtest","testpassword","testmanager"));
    }

}
