package com.project.web.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.project.web.Entity.Answer;
import com.project.web.Entity.Category;
import com.project.web.Entity.Question;
import com.project.web.Entity.QuestionResponseDTO;
import com.project.web.Entity.User;
import com.project.web.Repo.AnswerRepository;
import com.project.web.Repo.CategoryRepository;
import com.project.web.Repo.QuestionRepository;
import com.project.web.Repo.UserRepository;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public QuestionService(QuestionRepository questionRepository, 
                           AnswerRepository answerRepository,
                           UserRepository userRepository,
                           CategoryRepository categoryRepository) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    public Question createQuestion(Question question) {
        return questionRepository.save(question);
    }

    public List<Question> getAllQuestions() {
        return (List<Question>) questionRepository.findAll();
    }

    public Optional<Question> getQuestionById(Integer id) {
        return questionRepository.findById(id);
    }

    public List<Question> getQuestionsByCreatedBy(Integer createdBy) {
        return questionRepository.findByCreatedBy(createdBy);
    }

    public Optional<Question> updateQuestion(Integer id, Question updatedQuestion) {
        return questionRepository.findById(id).map(existingQuestion -> {

            if (updatedQuestion.getDifficulty() != null) {
                existingQuestion.setDifficulty(updatedQuestion.getDifficulty());
            }

            if (updatedQuestion.getCategoryId() != null) {
                existingQuestion.setCategoryId(updatedQuestion.getCategoryId());
            }         

            return questionRepository.save(existingQuestion);
        });
    }

    public List<QuestionResponseDTO> getAllQuestionsWithUserAnswers(Integer userId) {

        Iterable<Question> questionIterable = questionRepository.findAll();
        List<Question> questions = StreamSupport.stream(questionIterable.spliterator(), false)
                                                .collect(Collectors.toList());

        return questions.stream().map(question -> {
            Optional<Answer> userAnswer = answerRepository.findByQuestionIdAndUserId(question.getId(), userId);

            String creatorName = userRepository.findById(question.getCreatedBy())
                                               .map(User::getUsername)
                                               .orElse("Unknown Creator");

            String categoryName = categoryRepository.findById(question.getCategoryId())
                                                    .map(Category::getName)
                                                    .orElse("Unknown Category");


            QuestionResponseDTO dto = new QuestionResponseDTO();
            dto.setId(question.getId());
            dto.setName(question.getName());
            dto.setDifficulty(question.getDifficulty());
            dto.setText(question.getText());
            dto.setOptionA(question.getOptionA());
            dto.setOptionB(question.getOptionB());
            dto.setOptionC(question.getOptionC());
            dto.setOptionD(question.getOptionD());
            dto.setUserAnswer(userAnswer.map(Answer::getSelectedOption).orElse(null));
            dto.setCorrectOption(question.getCorrectOption());
            dto.setCategoryName(categoryName);
            dto.setCreatorName(creatorName);

            return dto;
        }).collect(Collectors.toList());
    }
}
