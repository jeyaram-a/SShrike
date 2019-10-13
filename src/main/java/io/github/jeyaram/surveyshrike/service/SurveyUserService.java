package io.github.jeyaram.surveyshrike.service;

import io.github.jeyaram.surveyshrike.domain.SurveyUser;
import io.github.jeyaram.surveyshrike.exception.ResourceNotFoundException;
import io.github.jeyaram.surveyshrike.repository.SurveyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class SurveyUserService {

    @Autowired
    private SurveyUserRepository surveyUserRepository;

    @Cacheable(value = "surveys", key="#email")
    public SurveyUser getSurveyUser(String email) {
            return surveyUserRepository.findById(email).orElseThrow(() -> new ResourceNotFoundException(email));
    }

    @CachePut(value = "surveys", key = "#user.emailId")
    public SurveyUser save(SurveyUser user) {
        return surveyUserRepository.save(user);
    }

    public Boolean validate(String email, String password) {
        SurveyUser user = surveyUserRepository.findByEmailIdAndPassword(email, password);
        if(Objects.isNull(user))
            return false;
        return true;
    }
}
