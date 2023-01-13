package com.exam.repository;

import com.exam.entities.exam.Category;
import com.exam.entities.exam.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface QuizRepository extends JpaRepository<Quiz,Integer> {
    public Set<Quiz> findByCategory(Category category);
    public Set<Quiz> findByQuizEnabled(Boolean quizEnabled);
    public Set<Quiz> findByCategoryAndQuizEnabled(Category category, Boolean quizEnabled);
}
