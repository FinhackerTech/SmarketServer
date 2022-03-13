package com.finhacker.smarket.controller;

import com.finhacker.smarket.domain.user.UserManager;
import com.finhacker.smarket.domain.user.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.TestExecutionEvent;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SettingControllerTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private MockHttpSession session;

    @Autowired
    private UserRepository userRepository;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
        session = new MockHttpSession();
        userRepository.deleteAll();
        userRepository.save(new UserManager("abc", getPasswordEncoder().encode("password123"), "abc"));
    }

    @After
    public void tearDown() throws Exception {
        userRepository.deleteAll();
    }

    @Test
    @WithUserDetails(value = "abc", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    public void changePassword() throws Exception{
        String oldpassword=getPasswordEncoder().encode("password123");
        mvc.perform(MockMvcRequestBuilders.post("/setting/api/changepassword")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .content("oldPassword=password123&newPassword=newpassword")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"code\" : 1, \"message\" : \"成功\", \"data\" : true}"));
        mvc.perform(MockMvcRequestBuilders.post("/setting/api/changepassword")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .content("oldPassword=wrongpassword&newPassword=newpassword")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"code\" : 102, \"message\" : \"密码错误\", \"data\" : null}"));
    }
}
