package io.github.jeyaram.surveyshrike.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class SurveyUser implements Serializable {

    @NotNull
    @NonNull
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$")
    @Id
    private String emailId;

    @NotNull
    @NonNull
    private String firstName;

    @NotNull
    @NonNull
    private String lastName;

    @NotNull
    @NonNull
    private String password;

}
