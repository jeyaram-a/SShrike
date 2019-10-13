package io.github.jeyaram.surveyshrike.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectResult;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.io.InputStream;

import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class AttachmentServiceTest {

    @InjectMocks
    private AttachmentService attachmentService;

    @Mock
    private AmazonS3 amazonS3;

    private PutObjectResult result;

    @Before
    public void setUp() throws Exception {
        result = new PutObjectResult();
        result.setContentMd5("123Gh");
    }

    @Test
    public void saveFile_FileGiven_SaveSuccess() throws IOException {
        when(amazonS3.putObject(anyString(), anyString(), any(InputStream.class), any(ObjectMetadata.class))).thenReturn(result);
        MockMultipartFile file = new MockMultipartFile(
                "file",
                "file",
                "application/sql",
                new byte[10]);
        attachmentService.saveFile(file);
        verify(amazonS3, times(1)).putObject(anyString(), anyString(), any(InputStream.class), any(ObjectMetadata.class));

    }

    @Test
    public void saveFileWithName_FileGiven_SaveSuccess() throws IOException {
        when(amazonS3.putObject(anyString(), anyString(), any(InputStream.class), any(ObjectMetadata.class))).thenReturn(result);
        MockMultipartFile file = new MockMultipartFile(
                "file",
                "file",
                "application/file",
                new byte[10]);
        attachmentService.saveFile(file, "fileName");
        verify(amazonS3, times(1)).putObject(anyString(), anyString(), any(InputStream.class), any(ObjectMetadata.class));

    }

}