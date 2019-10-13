package io.github.jeyaram.surveyshrike.repository;

import io.github.jeyaram.surveyshrike.domain.SurveyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyUserRepository extends JpaRepository<SurveyUser, String> {
     SurveyUser findByEmailIdAndPassword(String emailId, String salt);
}
