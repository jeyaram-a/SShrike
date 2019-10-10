package io.github.jeyaram.surveyshrike.controller;

import io.github.jeyaram.surveyshrike.domain.ProvidedAnswer;
import io.github.jeyaram.surveyshrike.domain.ProvidedAnswerRequestParam;
import io.github.jeyaram.surveyshrike.service.ProvidedAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProvidedAnswerController {

    @Autowired
    private ProvidedAnswerService providedAnswerService;

    @PostMapping("/save/answer")
    public ProvidedAnswer saveAnswer(@RequestBody ProvidedAnswerRequestParam param) {
        return providedAnswerService.save(param);
    }

}
