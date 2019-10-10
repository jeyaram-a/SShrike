package io.github.jeyaram.surveyshrike.repository;

import io.github.jeyaram.surveyshrike.domain.SurveyUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveyUserRepository extends JpaRepository<SurveyUser, Long> {
}
