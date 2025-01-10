package com.project.web.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("answers")
public class Answer {
    @Id
    private Integer id;
    private Integer questionId; // References Question ID
    private Integer userId; // References User ID
    private AnswerType selectedOption;
    private Boolean isCorrect;
    private LocalDateTime answeredAt;

    public enum AnswerType {
        A,
        B,
        C,
        D
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public AnswerType getSelectedOption() {
        return selectedOption;
    }

    public void setSelectedOption(AnswerType selectedOption) {
        this.selectedOption = selectedOption;
    }

    public Boolean getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(Boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

    public LocalDateTime getAnsweredAt() {
        return answeredAt;
    }

    public void setAnsweredAt(LocalDateTime answeredAt) {
        this.answeredAt = answeredAt;
    }
}