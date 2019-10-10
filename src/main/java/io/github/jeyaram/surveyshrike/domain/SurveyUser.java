package io.github.jeyaram.surveyshrike.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class SurveyUser implements Serializable {

    @Id
    @GeneratedValue(generator="survey_user_id_seq")
    @SequenceGenerator(name="survey_user_id_seq",sequenceName="survey_user_id_seq", allocationSize=1)
    private Long id;

    @NotNull
    @NonNull
    private String firstName;

    @NotNull
    @NonNull
    private String lastName;

    @NotNull
    @NonNull
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$")
    private String emailId;

    @NotNull
    @NonNull
    private String passwordSalt;

}
