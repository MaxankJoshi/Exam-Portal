package com.exam.service.implementation;

import com.exam.entities.exam.Category;
import com.exam.entities.exam.Quiz;
import com.exam.repository.CategoryRepository;
import com.exam.repository.QuizRepository;
import com.exam.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Override
    public Quiz addQuiz(Quiz quiz) {
        return this.quizRepository.save(quiz);
    }

    @Override
    public Quiz updateQuiz(Quiz quiz) {
        return this.quizRepository.save(quiz);
    }

    @Override
    public Set<Quiz> getAllQuizzes() {
        return new HashSet<>(this.quizRepository.findAll());
    }

    @Override
    public Quiz getQuizById(Integer quizId) {
        return this.quizRepository.findById(quizId).get();
    }

    @Override
    public void deleteQuizById(Integer quizId) {
        this.quizRepository.deleteById(quizId);
    }

    @Override
    public Set<Quiz> getQuizByCategory(Category category) {
        Set<Quiz> quizzes = this.quizRepository.findByCategory(category);

        return quizzes;
    }

    @Override
    public Set<Quiz> getActiveQuizzes() {
        Set<Quiz> quizzes = this.quizRepository.findByQuizEnabled(true);

        return quizzes;
    }

    @Override
    public Set<Quiz> getActiveQuizzesOfCategory(Category category) {
        Set<Quiz> quizzes = this.quizRepository.findByCategoryAndQuizEnabled(category, true);

        return quizzes;
    }
}
