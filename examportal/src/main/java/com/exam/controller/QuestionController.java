package com.exam.controller;

import com.exam.entities.exam.Question;
import com.exam.entities.exam.Quiz;
import com.exam.service.QuestionService;
import com.exam.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/question/api")
@CrossOrigin("*")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity<Question> createQuestion(@RequestBody Question question) {
        question.setQuestionImage("default.png");
        Question createdQuestion = this.questionService.addQuestion(question);

        return new ResponseEntity<Question>(createdQuestion, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Question> updateQuestion(@RequestBody Question question) {
        Question updatedQuestion = this.questionService.updateQuestion(question);

        return ResponseEntity.ok(updatedQuestion);
    }

    @GetMapping("/get/{questionId}")
    public ResponseEntity<Question> getQuestion(@PathVariable("questionId") Integer questionId) {
        Question question = this.questionService.getQuestionById(questionId);

        return ResponseEntity.ok(question);
    }

    @GetMapping("/getAll")
    public ResponseEntity<Set<Question>> getAllQuestions() {
        Set<Question> allQuestions = this.questionService.getAllQuestions();

        return ResponseEntity.ok(allQuestions);
    }

    @DeleteMapping("/delete/{questionId}")
    public ResponseEntity<?> deleteQuestion(@PathVariable("questionId") Integer questionId) {
        this.questionService.deleteQuestionById(questionId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/get/by/{quizId}")
    public ResponseEntity<List<Question>> getQuestionsByQuiz(@PathVariable("quizId") Integer quizId) {
        Quiz quiz = this.quizService.getQuizById(quizId);

        Set<Question> allQuestions = this.questionService.getQuestionOfQuiz(quiz);

        List<Question> list = new ArrayList<>(allQuestions);

        if(list.size() > Integer.parseInt(quiz.getQuizNumberOfQuestions())) {
            list.subList(0,Integer.parseInt(quiz.getQuizNumberOfQuestions() + 1));
        }

        for(Question question:list) {
            question.setQuestionAnswer("");
        }

        Collections.shuffle(list);

        return ResponseEntity.ok(list);
    }

    @GetMapping("/getAll/{quizId}")
    public ResponseEntity<Set<Question>> getAllQuestionsByQuiz(@PathVariable("quizId") Integer quizId) {
        Quiz quiz = this.quizService.getQuizById(quizId);

        Set<Question> allQuestions = this.questionService.getQuestionOfQuiz(quiz);

        return ResponseEntity.ok(allQuestions);
    }

    @PostMapping("/eval-quiz")
    public ResponseEntity<Map<String,Integer>> evalQuiz(@RequestBody List<Question> questions) {
        int marksGot = 0;
        int correctAnswers = 0;
        int attempted = 0;
        int singleMarks = Integer.parseInt(questions.get(0).getQuiz().getQuizMaxMarks()) / Integer.parseInt(questions.get(0).getQuiz().getQuizNumberOfQuestions());

        for(Question question:questions) {
            Question questionBack = this.questionService.getQuestionById(question.getQuestionId());

            if(questionBack.getQuestionAnswer().equals(question.getQuestionGivenAnswer())) {
                correctAnswers++;
                marksGot += singleMarks;
                attempted++;
            }

            else if (question.getQuestionGivenAnswer() != null) {
                attempted++;
            }
        }

        Map<String,Integer> result = new HashMap<>();
        result.put("marksGot",marksGot);
        result.put("correctAnswers",correctAnswers);
        result.put("attempted",attempted);

        return ResponseEntity.ok(result);
    }
}
