package io.github.jeyaram.surveyshrike.service;

import io.github.jeyaram.surveyshrike.domain.*;
import io.github.jeyaram.surveyshrike.exception.ResourceNotFoundException;
import io.github.jeyaram.surveyshrike.exception.UnsatisfiedConditionException;
import io.github.jeyaram.surveyshrike.repository.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class ProvidedAnswerServiceTest {

    @InjectMocks
    private ProvidedAnswerService providedAnswerService;

    @Mock
    private AnswerRepository answerRepository;

    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private SurveyRepository surveyRepository;

    @Mock
    private SurveyUserRepository surveyUserRepository;

    @Mock
    private ProvidedAnswerRepository providedAnswerRepository;

    private ProvidedAnswerRequestParam param ;

    @Before
    public void setUp() throws Exception {
        param = new ProvidedAnswerRequestParam(1L, 2L, 3L, "j@gg.com");
    }

    @Test(expected = UnsatisfiedConditionException.class)
    public void save_SurveyIdInvalid_Fail() {
        when(surveyRepository.findById(param.getSurveyId())).thenReturn(Optional.empty());
        providedAnswerService.save(param);
    }

    @Test(expected = UnsatisfiedConditionException.class)
    public void save_QuestionIdInvalid_Fail() {
        when(surveyRepository.findById(param.getSurveyId())).thenReturn(Optional.of(new Survey()));
        when(questionRepository.findById(param.getQuestionId())).thenReturn(Optional.empty());
        providedAnswerService.save(param);
    }

    @Test(expected = UnsatisfiedConditionException.class)
    public void save_AnswerIdInvalid_Fail() {
        when(surveyRepository.findById(param.getSurveyId())).thenReturn(Optional.of(new Survey()));
        when(questionRepository.findById(param.getQuestionId())).thenReturn(Optional.of(new Question()));
        when(answerRepository.findById(param.getAnswerId())).thenReturn(Optional.empty());
        providedAnswerService.save(param);
    }

    @Test(expected = UnsatisfiedConditionException.class)
    public void save_UserIdInvalid_Fail() {
        when(surveyRepository.findById(param.getSurveyId())).thenReturn(Optional.of(new Survey()));
        when(questionRepository.findById(param.getQuestionId())).thenReturn(Optional.of(new Question()));
        when(answerRepository.findById(param.getAnswerId())).thenReturn(Optional.of(new Answer()));
        when(surveyUserRepository.findById(param.getUserId())).thenReturn(Optional.empty());
        providedAnswerService.save(param);
    }

    @Test
    public void save_AllIdValid_Success() {
        when(surveyRepository.findById(param.getSurveyId())).thenReturn(Optional.of(new Survey()));
        when(questionRepository.findById(param.getQuestionId())).thenReturn(Optional.of(new Question()));
        when(answerRepository.findById(param.getAnswerId())).thenReturn(Optional.of(new Answer()));
        when(surveyUserRepository.findById(param.getUserId())).thenReturn(Optional.of(new SurveyUser()));
        providedAnswerService.save(param);
        verify(providedAnswerRepository, times(1)).save(any(ProvidedAnswer.class));
    }

    @Test(expected = UnsatisfiedConditionException.class)
    public void get_SurveyIdInvalid_Fail() {
        when(surveyRepository.findById(param.getSurveyId())).thenReturn(Optional.empty());
        providedAnswerService.get(param.getSurveyId(), param.getQuestionId(), param.getUserId());
    }

    @Test(expected = UnsatisfiedConditionException.class)
    public void get_QuestionIdInvalid_Fail() {
        when(surveyRepository.findById(param.getSurveyId())).thenReturn(Optional.of(new Survey()));
        when(questionRepository.findById(param.getQuestionId())).thenReturn(Optional.empty());
        providedAnswerService.get(param.getSurveyId(), param.getQuestionId(), param.getUserId());
    }

    @Test(expected = UnsatisfiedConditionException.class)
    public void get_AnswerIdInvalid_Fail() {
        when(surveyRepository.findById(param.getSurveyId())).thenReturn(Optional.of(new Survey()));
        when(questionRepository.findById(param.getQuestionId())).thenReturn(Optional.of(new Question()));
        when(answerRepository.findById(param.getAnswerId())).thenReturn(Optional.empty());
        providedAnswerService.get(param.getSurveyId(), param.getQuestionId(), param.getUserId());
    }

    @Test(expected = UnsatisfiedConditionException.class)
    public void get_UserIdInvalid_Fail() {
        when(surveyRepository.findById(param.getSurveyId())).thenReturn(Optional.of(new Survey()));
        when(questionRepository.findById(param.getQuestionId())).thenReturn(Optional.of(new Question()));
        when(answerRepository.findById(param.getAnswerId())).thenReturn(Optional.of(new Answer()));
        when(surveyUserRepository.findById(param.getUserId())).thenReturn(Optional.empty());
        providedAnswerService.get(param.getSurveyId(), param.getQuestionId(), param.getUserId());
    }

    @Test
    public void get_AllIdValid_Success() {
        when(surveyRepository.findById(param.getSurveyId())).thenReturn(Optional.of(new Survey()));
        when(questionRepository.findById(param.getQuestionId())).thenReturn(Optional.of(new Question()));
        when(answerRepository.findById(param.getAnswerId())).thenReturn(Optional.of(new Answer()));
        when(surveyUserRepository.findById(param.getUserId())).thenReturn(Optional.of(new SurveyUser()));
        when(providedAnswerRepository.findBySurveyAndQuestionAndUser(any(), any(), any())).thenReturn(new ProvidedAnswer());
        providedAnswerService.get(param.getSurveyId(), param.getQuestionId(), param.getUserId());
        verify(providedAnswerRepository, times(1)).findBySurveyAndQuestionAndUser(any(), any(), any());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void get_AllIdValidButNoResult_Failure() {
        when(surveyRepository.findById(param.getSurveyId())).thenReturn(Optional.of(new Survey()));
        when(questionRepository.findById(param.getQuestionId())).thenReturn(Optional.of(new Question()));
        when(answerRepository.findById(param.getAnswerId())).thenReturn(Optional.of(new Answer()));
        when(surveyUserRepository.findById(param.getUserId())).thenReturn(Optional.of(new SurveyUser()));
        providedAnswerService.get(param.getSurveyId(), param.getQuestionId(), param.getUserId());
        verify(providedAnswerRepository, times(1)).findBySurveyAndQuestionAndUser(any(), any(), any());
    }
}