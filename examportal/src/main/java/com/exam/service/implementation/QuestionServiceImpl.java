package com.exam.service.implementation;

import com.exam.entities.exam.Question;
import com.exam.entities.exam.Quiz;
import com.exam.repository.QuestionRepository;
import com.exam.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public Question addQuestion(Question question) {
        return this.questionRepository.save(question);
    }

    @Override
    public Question updateQuestion(Question question) {
        return this.questionRepository.save(question);
    }

    @Override
    public Set<Question> getAllQuestions() {
        return new HashSet<>(this.questionRepository.findAll());
    }

    @Override
    public Question getQuestionById(Integer questionId) {
        return this.questionRepository.findById(questionId).get();
    }

    @Override
    public void deleteQuestionById(Integer questionId) {
        Question question = this.questionRepository.findById(questionId).get();

        this.questionRepository.delete(question);
    }

    @Override
    public Set<Question> getQuestionOfQuiz(Quiz quiz) {
        return this.questionRepository.findByQuiz(quiz);
    }
}
