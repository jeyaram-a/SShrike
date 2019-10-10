package io.github.jeyaram.surveyshrike.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Question implements Serializable {

    @Id
    @GeneratedValue(generator="question_id_seq")
    @SequenceGenerator(name="question_id_seq",sequenceName="question_id_seq", allocationSize=1)
    private Long id;

    String question;

    private QuestionType type;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Answer> offeredAnswers;
}
