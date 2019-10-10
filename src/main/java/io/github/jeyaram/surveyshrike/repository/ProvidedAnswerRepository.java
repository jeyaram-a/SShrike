package io.github.jeyaram.surveyshrike.repository;

import io.github.jeyaram.surveyshrike.domain.Answer;
import io.github.jeyaram.surveyshrike.domain.ProvidedAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvidedAnswerRepository extends JpaRepository<ProvidedAnswer, Long> {
    Answer findBySurveyAndQuestionAndAnswerAndUser(Long surveyId, Long questionId, Long answerId, Long userId);
}
