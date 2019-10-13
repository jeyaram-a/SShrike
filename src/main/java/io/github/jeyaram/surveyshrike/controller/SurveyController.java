package io.github.jeyaram.surveyshrike.controller;

import io.github.jeyaram.surveyshrike.domain.Survey;
import io.github.jeyaram.surveyshrike.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class SurveyController {

    @Autowired
    private SurveyService surveyService;

    @GetMapping("/survey/{survey_id}")
    @ResponseStatus(HttpStatus.OK)
    public Survey getSurvey(@PathVariable("survey_id") Long surveyId) {
        return surveyService.getSurvey(surveyId);
    }

    @PostMapping("/survey")
    @ResponseStatus(HttpStatus.CREATED)
    private Survey saveSurvey(@RequestBody Survey survey) {
        return surveyService.save(survey);
    }

    @DeleteMapping("/survey/{survey_id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteSurvey(@PathVariable("survey_id") Long surveyId) {
        surveyService.delete(surveyId);
    }

}
