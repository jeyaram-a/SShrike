package io.github.jeyaram.surveyshrike.service;

import io.github.jeyaram.surveyshrike.domain.SurveyUser;
import io.github.jeyaram.surveyshrike.exception.ResourceNotFoundException;
import io.github.jeyaram.surveyshrike.repository.SurveyUserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class SurveyUserServiceTest {

    @InjectMocks
    private SurveyUserService surveyUserService;

    @Mock
    private SurveyUserRepository surveyUserRepository;

    private SurveyUser user;

    @Before
    public void setup() {
        user = new SurveyUser();
        user.setEmailId("jj@gg.com");
    }

    @Test
    public void test_validate_Success() {
        when(surveyUserRepository.findByEmailIdAndPassword(anyString(), anyString())).thenReturn(new SurveyUser());
        Assert.assertTrue(surveyUserService.validate(anyString(), anyString()));
    }

    @Test
    public void test_validate_Failure() {
        Assert.assertFalse(surveyUserService.validate(anyString(), anyString()));
    }

    @Test
    public void test_getSurveyUser_Success() {
        when(surveyUserRepository.findById(anyString())).thenReturn(java.util.Optional.of(user));
        Assert.assertEquals(surveyUserService.getSurveyUser(anyString()).getEmailId(), user.getEmailId());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void test_getSurveyUser_Failure() {
        when(surveyUserRepository.findById(anyString())).thenReturn(Optional.empty());
        surveyUserService.getSurveyUser(anyString());
    }

    @Test
    public void test_save_Success() {
        when(surveyUserRepository.save(any())).thenReturn(user);
        Assert.assertEquals(surveyUserService.save(any()).getEmailId(), user.getEmailId());
    }
}