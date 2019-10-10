package io.github.jeyaram.surveyshrike.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class ProvidedAnswer {

    @Id
    @GeneratedValue(generator="provided_answer_id_seq")
    @SequenceGenerator(name="provided_answer_id_seq",sequenceName="provided_answer_id_seq", allocationSize=1)
    private Long id;

    @OneToOne
    @NonNull
    private Survey survey;

    @OneToOne
    @NonNull
    private Question question;

    @OneToOne
    @NonNull
    private Answer answer;

    @OneToOne
    @NonNull
    private SurveyUser user;

}
