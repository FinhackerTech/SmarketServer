package com.finhacker.smarket.controller;

import com.finhacker.smarket.domain.user.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
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
public class LoginControllerTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private UserRepository userRepository;

    private MockHttpSession session;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
        session = new MockHttpSession();
        userRepository.deleteAll();
    }

    @After
    public void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    public void register() throws Exception {
        //TODO
        mvc.perform(MockMvcRequestBuilders.post("/login/api/registermanager")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .content("username=abcdef&password=123456&managerName=abcdef")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"code\":1,\"message\":\"成功\",\"data\":true}"));
        mvc.perform(MockMvcRequestBuilders.post("/login/api/registermanager")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .content("username=abcdef&password=456&managerName=cdf")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"code\":103,\"message\":\"用户名已存在\",\"data\":null}"));
    }

    @Test
    public void login() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/login/api/registermanager")
                        .session(session)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .content("username=abc&password=123&managerName=abc")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
        mvc.perform(MockMvcRequestBuilders.post("/login/api/login")
                        .session(session)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .content("username=abc&password=123"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
        mvc.perform(MockMvcRequestBuilders.get("/login/api/current")
                        .session(session)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    public void loginFailed() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/login/api/login")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .content("username=abc&password=111"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().string("{\"code\":106,\"message\":\"用户名或密码错误\",\"data\":null}"));
        mvc.perform(MockMvcRequestBuilders.post("/login/api/login")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .content("username=abc&password=123&managerName=bc"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().string("{\"code\":106,\"message\":\"用户名或密码错误\",\"data\":null}"));
    }

}