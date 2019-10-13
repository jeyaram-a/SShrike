package io.github.jeyaram.surveyshrike.repository;

import io.github.jeyaram.surveyshrike.domain.ProvidedAnswer;
import io.github.jeyaram.surveyshrike.domain.Question;
import io.github.jeyaram.surveyshrike.domain.Survey;
import io.github.jeyaram.surveyshrike.domain.SurveyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvidedAnswerRepository extends JpaRepository<ProvidedAnswer, Long> {
    ProvidedAnswer findBySurveyAndQuestionAndUser(Survey surveyId, Question question, SurveyUser user);
}
