package com.project.web.Service;

import com.project.web.Entity.Answer;
import com.project.web.Repo.AnswerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService {

    private final AnswerRepository answerRepository;

    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public Answer submitAnswer(Answer answer) {
        answer.setIsCorrect(answer.getSelectedOption().equals(answer.getQuestionId()));
        return answerRepository.save(answer);
    }

    public List<Answer> getAnswersByUserId(Integer userId) {
        return answerRepository.findAllByUserId(userId);
    }
}
