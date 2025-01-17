package com.project.web.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.web.Entity.Answer;
import com.project.web.Entity.Question;
import com.project.web.Entity.User;
import com.project.web.Repo.AnswerRepository;
import com.project.web.Repo.QuestionRepository;
import com.project.web.Repo.UserRepository;

@Service
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;

    public AnswerService(AnswerRepository answerRepository, QuestionRepository questionRepository, UserRepository userRepository) {
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
    }

    public Answer submitAnswer(Answer answer) {
        Answer submittedAnswer = answerRepository.save(answer);

        Optional<Question> questionOptional = questionRepository.findById(answer.getQuestionId());
        if (questionOptional.isPresent() && Boolean.TRUE.equals(answer.getIsCorrect())) {
            Question question = questionOptional.get();

            Optional<User> userOptional = userRepository.findById(answer.getUserId());
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                int difficulty = question.getDifficulty();

                int scoreIncrement = difficulty;
                user.setScore(user.getScore() + scoreIncrement);
                
                userRepository.save(user);
            }
        }

        return submittedAnswer;
    }

    public List<Answer> getAnswersByUserId(Integer userId) {
        return answerRepository.findAllByUserId(userId);
    }
}
