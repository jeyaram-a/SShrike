package io.github.jeyaram.surveyshrike.controller;

import io.github.jeyaram.surveyshrike.domain.ProvidedAnswer;
import io.github.jeyaram.surveyshrike.domain.ProvidedAnswerRequestParam;
import io.github.jeyaram.surveyshrike.domain.Survey;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@RestController
@Log4j2
public class PutController {

    @GetMapping("/put")
    public void put() {

        PodamFactory factory = new PodamFactoryImpl();
        Survey myPojo = factory.manufacturePojo(Survey.class);
        RestTemplate template = new RestTemplate();


        myPojo.setId(null);

        myPojo.getQuestions().forEach(x -> {
            x.getOfferedAnswers().forEach( y -> y.setId(null));
            x.setId(null);
        });
        myPojo.getCreatedBy().setId(null);
        myPojo.getCreatedBy().setEmailId("jj@gg.com");

        ResponseEntity<Survey> response = template.postForEntity("http://localhost:8080/survey", myPojo, Survey.class);

        log.info(response);
    }

    @GetMapping("/put/pa")
    public  void putPa() {
        ProvidedAnswerRequestParam param = new ProvidedAnswerRequestParam(1L, 2L, 3L,4L);
        RestTemplate template = new RestTemplate();

        ResponseEntity<ProvidedAnswer> response=template.postForEntity("http://localhost:8080/save/answer", param, ProvidedAnswer.class);

        log.info(response);
    }
}
