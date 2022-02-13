package dev.finhacker.smarket.controller;

import dev.finhacker.smarket.domain.user.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginControllerTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private UserRepository userRepository;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
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
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"abc\",\"password\":\"123\",\"managerName\":\"abc\"}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
        mvc.perform(MockMvcRequestBuilders.post("/login/api/registermanager")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"abc\",\"password\":\"123\",\"managerName\":\"cdf\"}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

}