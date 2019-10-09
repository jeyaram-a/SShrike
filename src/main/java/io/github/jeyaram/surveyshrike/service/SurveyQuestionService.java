package io.github.jeyaram.surveyshrike.service;

import io.github.jeyaram.surveyshrike.domain.Survey;
import io.github.jeyaram.surveyshrike.domain.SurveyQuestion;
import io.github.jeyaram.surveyshrike.repository.SurveyQuestionRepository;
import io.github.jeyaram.surveyshrike.repository.SurveyRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class SurveyQuestionService {

    @Autowired
    private SurveyQuestionRepository surveyQuestionRepository;

    @Autowired
    private SurveyRepository surveyRepository;

    public SurveyQuestion getSurveyQuestionForSurveyId(Long surveyId) {
        // TODO Remove this
        Survey survey = surveyRepository.getOne(surveyId);
        SurveyQuestion surveyQuestion = surveyQuestionRepository.findBySurvey(survey);
        return surveyQuestion;
    }

    public SurveyQuestion save(SurveyQuestion surveyQuestion) {
        log.info("here");
        return surveyQuestionRepository.save(surveyQuestion);
    }
}
