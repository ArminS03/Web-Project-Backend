package com.project.web.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.web.Entity.Question;
import com.project.web.Entity.QuestionResponseDTO;
import com.project.web.Service.QuestionService;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping
    public ResponseEntity<Question> createQuestion(@RequestBody Question question) {
        Question createdQuestion = questionService.createQuestion(question);
        return ResponseEntity.status(201).body(createdQuestion);
    }

    @GetMapping
    public ResponseEntity<List<Question>> getAllQuestions() {
        List<Question> questions = questionService.getAllQuestions();
        return ResponseEntity.ok(questions);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<QuestionResponseDTO>> getQuestionsWithUserAnswers(@PathVariable Integer userId) {
        List<QuestionResponseDTO> questions = questionService.getAllQuestionsWithUserAnswers(userId);
        return ResponseEntity.ok(questions);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable Integer id, @RequestBody Question updatedQuestion) {
        Optional<Question> question = questionService.updateQuestion(id, updatedQuestion);
        return question.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/created-by/{userId}")
    public ResponseEntity<List<Question>> getQuestionsByUserId(@PathVariable Integer userId) {
        List<Question> questions = questionService.getQuestionsByCreatedBy(userId);
        return ResponseEntity.ok(questions);
    }
}
