package io.github.jeyaram.surveyshrike.service;

import io.github.jeyaram.surveyshrike.domain.Survey;
import io.github.jeyaram.surveyshrike.exception.ResourceNotFoundException;
import io.github.jeyaram.surveyshrike.repository.SurveyRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class SurveyServiceTest {

    @InjectMocks
    private SurveyService surveyService;

    @Mock
    private SurveyRepository surveyRepository;

    private Survey survey;

    @Before
    public void setup() {
        survey = new Survey();
    }

    @Test
    public void test_getSurveyQuestionForSurveyId_Success() {
        when(surveyRepository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(survey));
        surveyService.getSurvey(1L);
        verify(surveyRepository, times(1)).findById(anyLong());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void test_getSurveyQuestionForSurveyId_Failure() {
        when(surveyRepository.findById(anyLong())).thenReturn(Optional.empty());
        surveyService.getSurvey(1L);
    }

    @Test()
    public void test_save_Success() {
        when(surveyRepository.save(any())).thenReturn(new Survey());
        surveyService.save(new Survey());
        verify(surveyRepository, times(1)).save(any());
    }

    @Test()
    public void test_delete_Failure() {
        surveyService.delete(1L);
        verify(surveyRepository, times(1)).deleteById(any());
    }


}