package io.github.jeyaram.surveyshrike.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.jeyaram.surveyshrike.domain.Answer;
import io.github.jeyaram.surveyshrike.repository.AnswerRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(secure = false)
@EnableAutoConfiguration(exclude = SecurityAutoConfiguration.class)
@ActiveProfiles("test")
public class AnswerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private AnswerRepository answerRepository;

    @Test
    public void test_save() throws Exception {
        mockMvc.perform(post("/offeredans")
                .contentType("application/json")
                .param("answer", "answer"))
                .andExpect(status().isCreated());

        List<Answer> surveys = answerRepository.findAll();
        Assert.assertEquals(2, surveys.size());
    }

}
