package io.github.jeyaram.surveyshrike.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProvidedAnswerRequestParam {

    private Long surveyId;

    private Long questionId;

    private Long answerId;

    private String userId;

}
