package io.github.jeyaram.surveyshrike.controller;

import io.github.jeyaram.surveyshrike.domain.Question;
import io.github.jeyaram.surveyshrike.domain.SurveyQuestion;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@RestController
public class PutController {

    @GetMapping("/put")
    public void put() {

        PodamFactory factory = new PodamFactoryImpl();
        SurveyQuestion myPojo = factory.manufacturePojo(SurveyQuestion.class);
        RestTemplate template = new RestTemplate();


        myPojo.setId(null);
        myPojo.getSurvey().setId(null);

        myPojo.getQuestions().forEach(x -> {
            x.getOfferedAnswers().forEach( y -> y.setId(null));
            x.setId(null);
        });
        myPojo.getSurvey().getCreatedBy().setId(null);
        myPojo.getSurvey().getCreatedBy().setEmailId("jj@gg.com");

        ResponseEntity<SurveyQuestion> response = template.postForEntity("http://localhost:8080/surveyquestions", myPojo, SurveyQuestion.class);



    }
}
