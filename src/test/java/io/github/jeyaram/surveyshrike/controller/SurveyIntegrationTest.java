package io.github.jeyaram.surveyshrike.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.jeyaram.surveyshrike.domain.Survey;
import io.github.jeyaram.surveyshrike.domain.SurveyUser;
import io.github.jeyaram.surveyshrike.repository.ProvidedAnswerRepository;
import io.github.jeyaram.surveyshrike.repository.SurveyRepository;
import io.github.jeyaram.surveyshrike.repository.SurveyUserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(secure = false)
@EnableAutoConfiguration(exclude = SecurityAutoConfiguration.class)
@ActiveProfiles("test")
public class SurveyIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private ProvidedAnswerRepository providedAnswerRepository;

    @Autowired
    private SurveyUserRepository surveyUserRepository;


    private SurveyUser user ;

    @Before
    public void setup() {
        user = new SurveyUser("jj@gg.com", "Jey", "A", "pass");
    }

    @Test
    @WithMockUser(value = "user")
    public void test_save() throws Exception {
        providedAnswerRepository.deleteAll();
        surveyRepository.deleteAll();
        surveyUserRepository.deleteAll();

        Survey survey = new Survey();
        survey.setCreatedBy(user);

        mockMvc.perform(post("/survey")
                .contentType("application/json")
                .param("sendWelcomeMail", "true")
                .content(objectMapper.writeValueAsString(survey)))
                .andExpect(status().isCreated());

        List<Survey> surveys = surveyRepository.findAll();
        Assert.assertEquals(1, surveys.size());

    }

    @Test
    @WithMockUser(value = "user")
    public void test_get() throws Exception {

        mockMvc.perform(get("/survey/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("jj@gg.com")));

        List<Survey> surveys = surveyRepository.findAll();
        Assert.assertEquals(1, surveys.size());

    }

}
