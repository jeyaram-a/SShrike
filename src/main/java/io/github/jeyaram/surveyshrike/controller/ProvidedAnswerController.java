package io.github.jeyaram.surveyshrike.controller;

import io.github.jeyaram.surveyshrike.domain.Answer;
import io.github.jeyaram.surveyshrike.domain.ProvidedAnswer;
import io.github.jeyaram.surveyshrike.domain.ProvidedAnswerRequestParam;
import io.github.jeyaram.surveyshrike.service.ProvidedAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProvidedAnswerController {

    @Autowired
    private ProvidedAnswerService providedAnswerService;

    @PostMapping("/answer/save")
    @ResponseStatus(HttpStatus.CREATED)
    public ProvidedAnswer saveAnswer(@RequestBody ProvidedAnswerRequestParam param) {
        return providedAnswerService.save(param);
    }

    @GetMapping("/answer")
    @ResponseStatus(HttpStatus.OK)
    public Answer getAnswer(@RequestParam("survey_id") Long surveyId,
                            @RequestParam("question_id") Long questionId,
                            @RequestParam("user_id") String userId) {
        return providedAnswerService.get(surveyId, questionId, userId);
    }

}
