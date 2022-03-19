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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
public class MyFavouriteControllerTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private UserRepository userRepository;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
        userRepository.deleteAll();
        userRepository.save(new UserManager("abc", new BCryptPasswordEncoder().encode("123"), "abc"));
    }

    @After
    public void tearDown() throws Exception {
        userRepository.deleteAll();
    }

    @Test
    @WithUserDetails(value = "abc", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    public void addFavourite() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/myfavourite/api/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content("[101,101005]")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"code\" : 1, \"message\" : \"成功\", \"data\" : true}"));
    }

    @Test
    @WithUserDetails(value = "abc", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    public void removeFavourite() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/myfavourite/api/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[10100068]")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"code\" : 1, \"message\" : \"成功\", \"data\" : true}"));
        mvc.perform(MockMvcRequestBuilders.post("/myfavourite/api/remove")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[10100068]")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"code\" : 1, \"message\" : \"成功\", \"data\" : true}"));
        mvc.perform(MockMvcRequestBuilders.post("/myfavourite/api/remove")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[103]")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"code\" : 105, \"message\" : \"收藏不存在\", \"data\" : null}"));
    }

    @Test
    @WithUserDetails(value = "abc", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    public void search() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/myfavourite/api/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"searchText\":\"金属制品业\",\"pageNumber\":0,\"filterTypes\":[{\"name\":\"RegisterCapital\",\"parameters\":[400000000, 500000000]}]}")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
        mvc.perform(MockMvcRequestBuilders.post("/myfavourite/api/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"searchText\":\"不存在关键词\",\"pageNumber\":0,\"filterTypes\":[{\"name\":\"RegisterCapital\",\"parameters\":[400000000, 500000000]}]}")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }
}