package io.github.jeyaram.surveyshrike.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
public class Answer implements Serializable {

    @Id
    @GeneratedValue(generator="answer_id_seq")
    @SequenceGenerator(name="answer_id_seq",sequenceName="answer_id_seq", allocationSize=1)
    private Long id;

    @Length(min = 1)
    String answer;
}
