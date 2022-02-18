package dev.finhacker.smarket.controller;

import dev.finhacker.smarket.domain.user.Role;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import dev.finhacker.smarket.domain.user.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SettingControllerTest {
    private MockMvc mvc;
    private User user;
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockHttpSession session;
    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        /*session = new MockHttpSession();
        user = new User("abc","123", Role.MANAGER);
        session.setAttribute("user",user);*/
    }

    @After
    public void tearDown() throws Exception {
    }

    //let userservice.getcurrentuser() return a test user instead of null so if you want to perform test task modify the getcurrentuser() api
    @Test
    @WithMockUser(username = "abc",password = "123")
    public void changePassword() throws Exception{

        mvc.perform(MockMvcRequestBuilders.post("/setting/api/changepassword")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .content("oldPassword=password&newPassword=newpassword")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }
}
