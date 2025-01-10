package com.project.web.Controller;

import com.project.web.Entity.Answer;
import com.project.web.Service.AnswerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/answers")
public class AnswerController {

    private final AnswerService answerService;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @PostMapping
    public ResponseEntity<Answer> submitAnswer(@RequestBody Answer answer) {
        Answer submittedAnswer = answerService.submitAnswer(answer);
        return ResponseEntity.status(201).body(submittedAnswer);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Answer>> getAnswersByUserId(@PathVariable Integer userId) {
        List<Answer> answers = answerService.getAnswersByUserId(userId);
        return ResponseEntity.ok(answers);
    }
}
