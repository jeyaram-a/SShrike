package io.github.jeyaram.surveyshrike.controller;

import io.github.jeyaram.surveyshrike.domain.SurveyUser;
import io.github.jeyaram.surveyshrike.service.SurveyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class SurveyUserController {

    @Autowired
    private SurveyUserService surveyUserService;

    @GetMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    public SurveyUser getUser(@RequestParam("email_id") String emailID) {
        return surveyUserService.getSurveyUser(emailID);
    }

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public  SurveyUser save(@RequestBody SurveyUser user) {
        return surveyUserService.save(user);
    }

    @GetMapping("/user/validate")
    @ResponseStatus(HttpStatus.OK)
    public Boolean validate(@RequestParam(name = "email") String email, @RequestParam(name="password") String password) {
        return surveyUserService.validate(email, password);
    }
}
