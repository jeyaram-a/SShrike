package io.github.jeyaram.surveyshrike.service;

import io.github.jeyaram.surveyshrike.domain.Answer;
import io.github.jeyaram.surveyshrike.repository.AnswerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

import static org.mockito.Mockito.*;


@RunWith(SpringJUnit4ClassRunner.class)
public class AnswerServiceTest {

    @InjectMocks
    private AnswerService answerService;
    
    @Mock
    private AnswerRepository answerRepository;

    @Mock
    private AttachmentService attachmentService;

    @Test
    public void test_save_Success() {
        answerService.save("answer");
        verify(answerRepository, times(1)).save(any(Answer.class));
    }

    @Test
    public void test_saveFileAnswer_Success() throws IOException {
        MockMultipartFile file = new MockMultipartFile(
                "file",
                "file",
                "application/file",
                new byte[10]);

        answerService.saveFileAnswer(file, 1L, 2L, "");

        verify(attachmentService, times(1)).saveFile(any(), any());
        verify(answerRepository, times(1)).save(any(Answer.class));
    }

}