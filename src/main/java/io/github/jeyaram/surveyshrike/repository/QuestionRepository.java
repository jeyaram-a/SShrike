package io.github.jeyaram.surveyshrike.repository;

import io.github.jeyaram.surveyshrike.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
}
