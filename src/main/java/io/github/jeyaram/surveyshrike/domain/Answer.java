package io.github.jeyaram.surveyshrike.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class Answer {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Length(min = 1)
    String answer;
}
