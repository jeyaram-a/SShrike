package io.github.jeyaram.surveyshrike.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectResult;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Service
@Log4j2
public class AttachmentService {

    @Autowired
    private AmazonS3 s3client;

    public void saveFile(MultipartFile file) throws IOException {

        ObjectMetadata data = new ObjectMetadata();
        data.setContentType(file.getContentType());
        data.setContentLength(file.getSize());

        PutObjectResult result = s3client.putObject("surveyfilessshrike", file.getOriginalFilename(), file.getInputStream(), data);
        log.info(file.getOriginalFilename() +" has been stored successfully md5 = "+result.getContentMd5());
    }

    public void saveFile(MultipartFile file, String fileName) throws IOException {

        ObjectMetadata data = new ObjectMetadata();
        data.setContentType(file.getContentType());
        data.setContentLength(file.getSize());

        PutObjectResult result = s3client.putObject("surveyfilessshrike", fileName, file.getInputStream(), data);
        log.info(fileName +" has been stored successfully md5 = "+result.getContentMd5());
    }
}
