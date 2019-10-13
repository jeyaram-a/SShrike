package io.github.jeyaram.surveyshrike.controller;

import io.github.jeyaram.surveyshrike.domain.Answer;
import io.github.jeyaram.surveyshrike.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class AnswerController  {

    @Autowired
    private AnswerService answerService;

    @PostMapping("/offeredans")
    @ResponseStatus(HttpStatus.CREATED)
    public Answer save(@RequestParam String answer) {
        return answerService.save(answer);
    }

    @PostMapping("/offeredans/file")
    @ResponseStatus(HttpStatus.CREATED)
    public Answer saveFile(@RequestParam("file")MultipartFile file, @RequestParam("survey_id") Long surveyId,
                           @RequestParam("question_id") Long questionId,
                           @RequestParam("user_id") String userId) throws IOException {
        return answerService.saveFileAnswer(file, surveyId, questionId, userId);
    }
}
