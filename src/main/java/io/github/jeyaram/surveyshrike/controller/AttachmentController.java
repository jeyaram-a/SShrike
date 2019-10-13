package io.github.jeyaram.surveyshrike.controller;

import io.github.jeyaram.surveyshrike.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class AttachmentController {

    @Autowired
    private AttachmentService attachmentService;

    @PostMapping("file/upload")
    @ResponseStatus(HttpStatus.CREATED)
    public void upload(@RequestParam("file")MultipartFile file) throws IOException {
        attachmentService.saveFile(file);
    }


}
