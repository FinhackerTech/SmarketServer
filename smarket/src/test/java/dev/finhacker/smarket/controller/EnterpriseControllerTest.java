package dev.finhacker.smarket.controller;

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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EnterpriseControllerTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void search() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/enterprise/api/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"searchText\":\"\",\"pageNumber\":0,\"filterTypes\":[{\"name\":\"RegisterCapital\",\"parameters\":[700000000, 1000000000]}]}")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
        mvc.perform(MockMvcRequestBuilders.get("/enterprise/api/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"searchText\":\"湖北广济药业股份有限公司\",\"pageNumber\":0,\"filterTypes\":[{\"name\":\"RegisterCapital\",\"parameters\":[300000000, 400000000]}]}")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
        mvc.perform(MockMvcRequestBuilders.get("/enterprise/api/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"searchText\":\"湖北广济药业股份有限公司\",\"pageNumber\":0,\"filterTypes\":[{\"name\":\"RegisterCapital\",\"parameters\":[400000000, 500000000]}]}")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
        mvc.perform(MockMvcRequestBuilders.get("/enterprise/api/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"searchText\":\"软件\",\"pageNumber\":1,\"filterTypes\":[{\"name\":\"RegisterCapital\",\"parameters\":[800000000, 900000000]}]}")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
        mvc.perform(MockMvcRequestBuilders.get("/enterprise/api/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"searchText\":\"全速迈进\",\"pageNumber\":0,\"filterTypes\":[{\"name\":\"RegisterCapital\",\"parameters\":[1875375741, 1875375743]}]}")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    public void getBasic() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/enterprise/api/basic/10100160")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
        mvc.perform(MockMvcRequestBuilders.get("/enterprise/api/basic/202")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"code\" : 201, \"message\" : \"没有该企业\", \"data\" : null}"));
    }

    @Test
    public void getAbout() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/enterprise/api/about/101271")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
        mvc.perform(MockMvcRequestBuilders.get("/enterprise/api/about/3")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"code\" : 201, \"message\" : \"没有该企业\", \"data\" : null}"));
    }

    @Test
    public void getAnalyse() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/enterprise/api/analyse/101311")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
        mvc.perform(MockMvcRequestBuilders.get("/enterprise/api/analyse/971")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"code\" : 201, \"message\" : \"没有该企业\", \"data\" : null}"));
    }

    @Test
    public void getAllNews() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/enterprise/api/news/101334")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
        mvc.perform(MockMvcRequestBuilders.get("/enterprise/api/news/123")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"code\" : 201, \"message\" : \"没有该企业\", \"data\" : null}"));
    }

    @Test
    public void getEnterprise() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/enterprise/api/101")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
        mvc.perform(MockMvcRequestBuilders.get("/enterprise/api/23")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"code\" : 201, \"message\" : \"没有该企业\", \"data\" : null}"));
    }

}