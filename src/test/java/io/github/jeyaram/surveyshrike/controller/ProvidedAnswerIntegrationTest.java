package io.github.jeyaram.surveyshrike.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.jeyaram.surveyshrike.domain.ProvidedAnswer;
import io.github.jeyaram.surveyshrike.domain.ProvidedAnswerRequestParam;
import io.github.jeyaram.surveyshrike.domain.Survey;
import io.github.jeyaram.surveyshrike.repository.*;
import org.junit.Assert;
import org.junit.Before;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(secure = false)
@EnableAutoConfiguration(exclude = SecurityAutoConfiguration.class)
@ActiveProfiles("test")
public class ProvidedAnswerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private SurveyUserRepository surveyUserRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    private ObjectMapper objectMapper = new ObjectMapper();
    
    private Survey survey;

    private ProvidedAnswerRequestParam param;

    private Survey resultSurvey;

    @Before
    public void setup() {
        PodamFactory podamFactory = new PodamFactoryImpl();
        survey = podamFactory.manufacturePojo(Survey.class);
        survey.setId(null);
        survey.getQuestions().forEach(x -> {
            x.getOfferedAnswers().forEach( y -> y.setId(null));
            x.setId(null);
        });
        survey.getCreatedBy().setEmailId("jj1@gg.com");
    }

    @Autowired
    private ProvidedAnswerRepository repository;

    @Test
    public void test_saveAndget() throws Exception {

        MvcResult result =  mockMvc.perform(post("/survey")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(survey)))
                .andExpect(status().isCreated())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        resultSurvey = objectMapper.readValue(content, Survey.class);

        param = new ProvidedAnswerRequestParam(resultSurvey.getId(), resultSurvey.getQuestions().get(0).getId(), resultSurvey.getQuestions().get(0).getOfferedAnswers().get(0).getId(), resultSurvey.getCreatedBy().getEmailId());

        mockMvc.perform(post("/answer/save")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(param)))
                .andExpect(status().isCreated());

        List<ProvidedAnswer> surveys = repository.findAll();
        Assert.assertEquals(1, surveys.size());

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("survey_id", param.getSurveyId().toString());
        params.add("question_id", param.getQuestionId().toString());
        params.add("answer_id", param.getAnswerId().toString());
        params.add("user_id", param.getUserId());

        MvcResult result1 = mockMvc.perform(get("/answer")
                .contentType("application/json")
                .params(params))
                .andExpect(status().isOk())
                .andReturn();

        String content1 = result1.getResponse().getContentAsString();
        System.out.println(content1);

        Assert.assertTrue(content1.contains("answer"));

    }



}
