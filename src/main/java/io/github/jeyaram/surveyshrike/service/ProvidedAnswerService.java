package io.github.jeyaram.surveyshrike.service;

import io.github.jeyaram.surveyshrike.domain.*;
import io.github.jeyaram.surveyshrike.exception.ResourceNotFoundException;
import io.github.jeyaram.surveyshrike.exception.UnsatisfiedConditionException;
import io.github.jeyaram.surveyshrike.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class ProvidedAnswerService {

    @Autowired
    private ProvidedAnswerRepository providedAnswerRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private SurveyUserRepository surveyUserRepository;

    public ProvidedAnswer save(ProvidedAnswerRequestParam param) {

        Optional<Survey> surveyOptional = surveyRepository.findById(param.getSurveyId());
        surveyOptional.orElseThrow(() -> new UnsatisfiedConditionException("survey "+ param.getSurveyId()+" not present"));

        Optional<Question> questionOptional = questionRepository.findById(param.getQuestionId());
        questionOptional.orElseThrow(() -> new UnsatisfiedConditionException("question "+ param.getQuestionId()+" not present"));

        Optional<Answer> answerOptional = answerRepository.findById(param.getAnswerId());
        answerOptional.orElseThrow(() -> new UnsatisfiedConditionException("answer  "+ param.getAnswerId()+" not present"));

        Optional<SurveyUser> surveyUserOptional = surveyUserRepository.findById(param.getUserId());
        surveyUserOptional.orElseThrow(() -> new UnsatisfiedConditionException("user  "+ param.getUserId()+" not present"));

        ProvidedAnswer providedAnswer = new ProvidedAnswer(surveyOptional.get(), questionOptional.get(), answerOptional.get(), surveyUserOptional.get());

        return providedAnswerRepository.save(providedAnswer);
    }

    public Answer get(Long surveyId, Long questionId, String userId) {
        Optional<Survey> surveyOptional = surveyRepository.findById(surveyId);
        surveyOptional.orElseThrow(() -> new UnsatisfiedConditionException("survey "+ surveyId.toString()+" not present"));

        Optional<Question> questionOptional = questionRepository.findById(questionId);
        questionOptional.orElseThrow(() -> new UnsatisfiedConditionException("question "+ questionId.toString()+" not present"));

        Optional<SurveyUser> surveyUserOptional = surveyUserRepository.findById(userId);
        surveyUserOptional.orElseThrow(() -> new UnsatisfiedConditionException("user  "+ userId+" not present"));

        ProvidedAnswer providedAnswer =  providedAnswerRepository.findBySurveyAndQuestionAndUser(surveyOptional.get(), questionOptional.get(), surveyUserOptional.get());

        if(Objects.isNull(providedAnswer)) {
            throw new ResourceNotFoundException(String.join("-", surveyId.toString(), questionId.toString(), userId));
        }

        return providedAnswer.getAnswer();
    }
}
