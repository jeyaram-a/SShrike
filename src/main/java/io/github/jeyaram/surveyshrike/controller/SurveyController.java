package io.github.jeyaram.surveyshrike.controller;

import io.github.jeyaram.surveyshrike.domain.Survey;
import io.github.jeyaram.surveyshrike.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SurveyController {

    @Autowired
    private SurveyService surveyQuestionService;

    @GetMapping("/survey/{survey_id}")
    public Survey getSurvey(@PathVariable("survey_id") Long surveyId) {
        return surveyQuestionService.getSurveyQuestionForSurveyId(surveyId);
    }

    @PostMapping("/survey")
    private Survey saveSurvey(@RequestBody Survey survey) {
        return surveyQuestionService.save(survey);
    }

    @DeleteMapping("/survey/{survey_id}")
    public void deleteSurvey(@PathVariable("survey_id") Long surveyId) {
        surveyQuestionService.delete(surveyId);
    }

}
