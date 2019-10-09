package io.github.jeyaram.surveyshrike.repository;

import io.github.jeyaram.surveyshrike.domain.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
