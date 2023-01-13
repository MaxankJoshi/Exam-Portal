package com.exam.repository;

import com.exam.entities.exam.Question;
import com.exam.entities.exam.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface QuestionRepository extends JpaRepository<Question,Integer> {
    public Set<Question> findByQuiz(Quiz quiz);
}
