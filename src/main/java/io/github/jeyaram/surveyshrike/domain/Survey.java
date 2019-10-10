package io.github.jeyaram.surveyshrike.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Survey implements Serializable {

    private static final long SerialVersionUID = 10l;

    @Id
    @GeneratedValue(generator="survey_id_seq")
    @SequenceGenerator(name="survey_id_seq",sequenceName="survey_id_seq", allocationSize=1)
    private Long id;

    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    private SurveyUser createdBy;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(FetchMode.SUBSELECT)
    private List<Question> questions;

}
