package io.github.jeyaram.surveyshrike.controller;

import io.github.jeyaram.surveyshrike.domain.SurveyQuestion;
import io.github.jeyaram.surveyshrike.service.SurveyQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SurveyQuestionController {

    @Autowired
    private SurveyQuestionService surveyQuestionService;

    @GetMapping("/surveyquestions/{survey_id}")
    public SurveyQuestion getSurveyQuestion(@PathVariable("survey_id") Long surveyId) {
        return surveyQuestionService.getSurveyQuestionForSurveyId(surveyId);
    }

    @PostMapping("/surveyquestions")
    private SurveyQuestion saveSurveyQuestion(@RequestBody SurveyQuestion surveyQuestion) {
        return surveyQuestionService.save(surveyQuestion);
    }
}
