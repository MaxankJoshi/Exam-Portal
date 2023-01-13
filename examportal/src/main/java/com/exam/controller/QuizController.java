package com.exam.controller;

import com.exam.entities.exam.Category;
import com.exam.entities.exam.Quiz;
import com.exam.service.CategoryService;
import com.exam.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/quiz/api")
@CrossOrigin("*")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<Quiz> createQuiz(@RequestBody Quiz quiz) {
        Quiz createdQuiz = this.quizService.addQuiz(quiz);

        return new ResponseEntity<Quiz>(createdQuiz, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Quiz> updateQuiz(@RequestBody Quiz quiz) {
        Quiz updatedQuiz = this.quizService.updateQuiz(quiz);

        return new ResponseEntity<Quiz>(updatedQuiz,HttpStatus.OK);
    }

    @GetMapping("/get/{quizId}")
    public ResponseEntity<Quiz> getQuiz(@PathVariable("quizId") Integer quizId) {
        Quiz quiz = this.quizService.getQuizById(quizId);

        return new ResponseEntity<Quiz>(quiz,HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<Set<Quiz>> getAllQuizzes() {
        Set<Quiz> allQuizzes = this.quizService.getAllQuizzes();

        return new ResponseEntity<Set<Quiz>>(allQuizzes,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{quizId}")
    public ResponseEntity<?> deleteQuiz(@PathVariable("quizId") Integer quizId) {
        this.quizService.deleteQuizById(quizId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("get/category/{categoryId}")
    public ResponseEntity<Set<Quiz>> getQuizByCategory(@PathVariable("categoryId") Integer categoryId) {
        Category category = this.categoryService.getCategoryById(categoryId);

        Set<Quiz> allQuizzes = this.quizService.getQuizByCategory(category);

        return ResponseEntity.ok(allQuizzes);
    }

    @GetMapping("get/active")
    public ResponseEntity<Set<Quiz>> getActiveQuizzes() {
        Set<Quiz> activeQuizzes = this.quizService.getActiveQuizzes();

        return ResponseEntity.ok(activeQuizzes);
    }

    @GetMapping("get/active/category/{categoryId}")
    public ResponseEntity<Set<Quiz>> getActiveQuizzesByCategory(@PathVariable("categoryId") Integer categoryId) {
        Category category = this.categoryService.getCategoryById(categoryId);

        Set<Quiz> activeQuizzes = this.quizService.getActiveQuizzesOfCategory(category);

        return ResponseEntity.ok(activeQuizzes);
    }
}
