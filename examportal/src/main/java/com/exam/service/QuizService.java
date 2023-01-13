package com.exam.service;

import com.exam.entities.exam.Category;
import com.exam.entities.exam.Quiz;

import java.util.Set;

public interface QuizService {
    public Quiz addQuiz(Quiz quiz);
    public Quiz updateQuiz(Quiz quiz);
    public Set<Quiz> getAllQuizzes();
    public Quiz getQuizById(Integer quizId);
    public void deleteQuizById(Integer quizId);
    public Set<Quiz> getQuizByCategory(Category category);
    public Set<Quiz> getActiveQuizzes();
    public Set<Quiz> getActiveQuizzesOfCategory(Category category);
}
