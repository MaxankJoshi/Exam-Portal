package com.exam.service;

import com.exam.entities.exam.Question;
import com.exam.entities.exam.Quiz;

import java.util.Set;

public interface QuestionService {
    public Question addQuestion(Question question);
    public Question updateQuestion(Question question);
    public Set<Question> getAllQuestions();
    public Question getQuestionById(Integer questionId);
    public void deleteQuestionById(Integer questionId);
    public Set<Question> getQuestionOfQuiz(Quiz quiz);
}
