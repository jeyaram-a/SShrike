package io.github.jeyaram.surveyshrike.service;

import io.github.jeyaram.surveyshrike.domain.Answer;
import io.github.jeyaram.surveyshrike.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private AttachmentService attachmentService;

    public Answer save(String answer) {
        Answer answerOb = new Answer(answer);
        return answerRepository.save(answerOb);
    }

    public Answer saveFileAnswer(MultipartFile file, Long surveyId, Long questionId, String userId) throws IOException {
        String fileName = "".join(surveyId.toString(), questionId.toString(), userId);
        attachmentService.saveFile(file, fileName);
        Answer answerOb = new Answer(fileName);
        return answerRepository.save(answerOb);
    }
}
