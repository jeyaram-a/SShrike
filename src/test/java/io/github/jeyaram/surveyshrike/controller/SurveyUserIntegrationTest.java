package io.github.jeyaram.surveyshrike.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.jeyaram.surveyshrike.domain.SurveyUser;
import io.github.jeyaram.surveyshrike.repository.SurveyUserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class SurveyUserIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private SurveyUserRepository surveyUserRepository;

    @Test
    public void test_save() throws Exception {
        SurveyUser user = new SurveyUser("jj@gg.com", "Jey", "A", "pass");
        mockMvc.perform(post("/user")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(user)))
                    .andExpect(status().isCreated());

        List<SurveyUser> surveys = surveyUserRepository.findAll();
        Assert.assertEquals(1, surveys.size());
    }

    @Test
    public void test_validate() throws Exception {

        SurveyUser user = new SurveyUser("jj@gg.com", "Jey", "A", "pass");
        mockMvc.perform(post("/user")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/user/validate")
                .contentType("application/json")
                .param("email", "jj@gg.com")
                .param("password", "pass"))
                .andExpect(status().isOk());
    }


    @Test
    public void test_get() throws Exception {

        SurveyUser user = new SurveyUser("jj@gg.com", "Jey", "A", "pass");
        mockMvc.perform(post("/user")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/user")
                .param("email_id", "jj@gg.com"))
                .andExpect(status().isOk());
    }
}
