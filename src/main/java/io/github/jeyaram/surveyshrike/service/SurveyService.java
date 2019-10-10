package io.github.jeyaram.surveyshrike.service;

import io.github.jeyaram.surveyshrike.domain.Survey;
import io.github.jeyaram.surveyshrike.exception.ResourceNotFoundException;
import io.github.jeyaram.surveyshrike.repository.SurveyRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
public class SurveyService {

    @Autowired
    private SurveyRepository surveyRepository;

    @Cacheable(value = "surveys", key="#surveyId")
    public Survey getSurveyQuestionForSurveyId(Long surveyId) {
        log.info(surveyId);
        Optional<Survey> surveyOptional = surveyRepository.findById(surveyId);
        return  surveyOptional.orElseThrow(() -> new ResourceNotFoundException(surveyId.toString()));
    }

    @CachePut(value = "surveys", key = "#survey.id")
    public Survey save(Survey survey) {
        log.info("here");
        return surveyRepository.save(survey);
    }

    @CacheEvict(value = "surveys", key = "#surveyId")
    public void delete(Long surveyId) {
         surveyRepository.deleteById(surveyId);
    }
}
